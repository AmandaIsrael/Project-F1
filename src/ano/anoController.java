package ano;
import java.sql.Statement;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import grandPrix.grandPrixResumo.grandPrixPodioModel;
import grandPrix.grandPrixResumo.grandPrixResumoController;
import grandPrix.grandPrixResumo.grandPrixResumoDAO;
import grandPrix.grandPrixResumo.grandPrixResumoMainTableModel;
import grandPrix.grandPrixResumo.grandPrixTempoModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pista.pistaDAO;
import pista.pistaMainTableModel;
import pista.pistaTracadoModel;
import pista.pistaMainTableController;

public class anoController extends grandPrixResumoController {
    screensController myController;
    @FXML private TableView<anoModel> tableView;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn3;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anoModel ano = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToGP();
                    ObservableList<grandPrixResumoMainTableModel> gp = FXCollections.observableArrayList(grandPrixResumoDAO.readGrandPrix(ano.getNome()));
                    gpstaticTableView.setItems(gp);
                    ObservableList<grandPrixPodioModel> podio = FXCollections.observableArrayList(grandPrixResumoDAO.readListaPodio(ano.getNome()));
                    gpstaticTableView2.setItems(podio);
                    ObservableList<grandPrixTempoModel> tempo = FXCollections.observableArrayList(grandPrixResumoDAO.readListaTempos(ano.getNome()));
                    gpstaticTableView3.setItems(tempo);

                }
            }});
    }

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }

    @FXML
    private void goToPilotos(ActionEvent event){
        myController.setScreen(Main.screen3ID);
    }

    @FXML
    private void goToEquipes(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void goToPistas(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    @FXML
    private void goToWCC(ActionEvent event){
        myController.setScreen(Main.screen9ID);
    }

    @FXML
    private void goToWDC(ActionEvent event){
        myController.setScreen(Main.screen10ID);
    }

    private void goToGP(){
        myController.setScreen(Main.screen11ID);
    }


    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pista"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("data"));

        ObservableList<anoModel> anos = FXCollections.observableArrayList(readListaPistas());
        
        tableView.setItems(anos);
    }

    public static ArrayList<anoModel> readListaPistas(){
        
        ArrayList<anoModel> anos = new ArrayList<>();
        String sql = "SELECT nomeGrandPrix, registroNomePista, dataFim FROM grandPrix, pistaRegistro WHERE pistaID = grandPrixPistaID";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomeGrandPrix"));
                SimpleStringProperty pista = new SimpleStringProperty(resultado.getString("registroNomePista"));
                SimpleStringProperty data = new SimpleStringProperty(resultado.getString("dataFim"));

                anoModel ano = new anoModel(nome, pista, data);

                anos.add(ano);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return anos;
    }
}
