package listaEquipes;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.screensController;
import equipe.equipeMainTableController;
import equipe.equipeDAO;
import equipe.equipeLiderModel;
import equipe.equipeMainTableModel;
import equipe.equipeMotoresModel;
import equipe.equipeNomeAntigoModel;

public class listaEquipesController extends equipeMainTableController{
    screensController myController;
    @FXML private TableView<listaEquipesModel> tableView;
    @FXML private TableColumn<listaEquipesModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaEquipesModel, SimpleStringProperty> tableColumn2;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listaEquipesModel listaEquipes = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToEquipe();

                    ObservableList<equipeLiderModel> lider = FXCollections.observableArrayList(equipeDAO.readListaLideres(listaEquipes.getNome()));
                    staticTableView.setItems(lider);
                    ObservableList<equipeNomeAntigoModel> nomes = FXCollections.observableArrayList(equipeDAO.readListaNomesAntigos(listaEquipes.getNome()));
                    staticTableView2.setItems(nomes);
                    ObservableList<equipeMotoresModel> motores = FXCollections.observableArrayList(equipeDAO.readListaMotores(listaEquipes.getNome()));
                    staticTableView3.setItems(motores);
                    ObservableList<equipeMainTableModel> equipe = FXCollections.observableArrayList(equipeDAO.readListaEquipes(listaEquipes.getNome()));
                    staticTableView4.setItems(equipe);
                }
            }});
    }

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToAno(ActionEvent event){
        myController.setScreen(Main.screen2ID);
    }

    @FXML
    private void goToPilotos(ActionEvent event){
        myController.setScreen(Main.screen3ID);
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }

    @FXML
    private void goToPistas(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    private void goToEquipe(){
        myController.setScreen(Main.screen7ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));

        ObservableList<listaEquipesModel> equipes = FXCollections.observableArrayList(readListaEquipes());

        tableView.setItems(equipes);
    }

    public static ArrayList<listaEquipesModel> readListaEquipes(){
        
        ArrayList<listaEquipesModel> equipes = new ArrayList<>();
        String sql = "SELECT * FROM equipe";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("equipeNome"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("equipeNacionalidade"));

                listaEquipesModel equipe = new listaEquipesModel(nome, nacionalidade);

                equipes.add(equipe);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return equipes;
    }
}
