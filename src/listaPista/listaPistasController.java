package listaPista;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pista.pistaDAO;
import pista.pistaMainTableModel;
import pista.pistaTracadoModel;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import pista.pistaMainTableController;

public class listaPistasController extends pistaMainTableController implements Initializable, controlledScreen {
    screensController myController;
    @FXML private TableView<listaPistaModel> tableView;
    private static TableView<listaPistaModel> statictableView;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn3;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statictableView = tableView;
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listaPistaModel listaPista = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToPista();
                    ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(pistaDAO.readListaPistas(listaPista.getNome()));
                    staticTableView.setItems(pistas);
                    ObservableList<pistaTracadoModel> tracado = FXCollections.observableArrayList(pistaDAO.readListaTracados(listaPista.getNome()));
                    staticTableView2.setItems(tracado);

                    for(int i = 0; i < staticTableView.getItems().size(); ++i){
                        statictabelaPistaAtual.put(i, staticTableView.getItems().get(i));
                    }
                    for(int i = 0; i < staticTableView2.getItems().size(); ++i){
                        statictabelaTracadoAtual.put(i, staticTableView2.getItems().get(i));
                    }
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
    private void goToEquipes(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }


    private void goToPista(){
        myController.setScreen(Main.screen6ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        refreshTable();

    }

    public static void refreshTable(){
        ObservableList<listaPistaModel> pistas = FXCollections.observableArrayList(readListaPistas());
        statictableView.getItems().clear();
        statictableView.setItems(pistas);
    }

    public static ArrayList<listaPistaModel> readListaPistas(){
        
        ArrayList<listaPistaModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomepista"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("paispista"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("cidadepista"));

                listaPistaModel pista = new listaPistaModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pistas;
    }
    
}
