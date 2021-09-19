package listaPilotos;
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
import piloto.pilotoMainTableController;
import piloto.pilotoDAO;
import piloto.pilotoContratoModel;
import piloto.pilotoMainTableModel;


public class listaPilotosController extends pilotoMainTableController {
    screensController myController;
    @FXML private TableView<listaPilotosModel> tableView;
    @FXML private TableColumn<listaPilotosModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaPilotosModel, SimpleStringProperty> tableColumn2;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                listaPilotosModel listaPilotos = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToPiloto();

                    ObservableList<pilotoMainTableModel> piloto = FXCollections.observableArrayList(pilotoDAO.readListaPilotos(listaPilotos.getNome(), listaPilotos.getSobrenome()));
                    pilostaticTableView.setItems(piloto);
                    ObservableList<pilotoContratoModel> contrato = FXCollections.observableArrayList(pilotoDAO.readListaContratos(listaPilotos.getNome(), listaPilotos.getSobrenome()));
                    pilostaticTableView2.setItems(contrato);
                    
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
    private void goToHome(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }

    @FXML
    private void goToEquipes(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void goToPistas(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    private void goToPiloto(){
        myController.setScreen(Main.screen8ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        ObservableList<listaPilotosModel> pilotos = FXCollections.observableArrayList(readListaPilotos());

        tableView.setItems(pilotos);
    }

    public static ArrayList<listaPilotosModel> readListaPilotos(){
        
        ArrayList<listaPilotosModel> pilotos = new ArrayList<>();
        String sql = "SELECT pilotoNome, pilotoSobrenome FROM piloto";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("pilotoNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("pilotoSobrenome"));

                listaPilotosModel piloto = new listaPilotosModel(nome, sobrenome);

                pilotos.add(piloto);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pilotos;
    }

}
