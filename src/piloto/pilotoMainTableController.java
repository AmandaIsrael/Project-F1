package piloto;

import java.io.IOException;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.controlledScreen;
import sample.utils.screensController;

public class pilotoMainTableController implements Initializable, controlledScreen{
    screensController myController;
    
    @FXML private TableView<pilotoMainTableModel> tableView;
    public static TableView<pilotoMainTableModel> staticTableView;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn7;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn8;
    @FXML private TableColumn<pilotoMainTableModel, SimpleStringProperty> tableColumn9;

    @FXML private TableView<pilotoContratoModel> tableView2;
    public static TableView<pilotoContratoModel> staticTableView2;
    @FXML private TableColumn<pilotoContratoModel, SimpleStringProperty> t2tableColumn1;
    @FXML private TableColumn<pilotoContratoModel, SimpleStringProperty> t2tableColumn2;
    @FXML private TableColumn<pilotoContratoModel, SimpleStringProperty> t2tableColumn3;
    @FXML private TableColumn<pilotoContratoModel, SimpleStringProperty> t2tableColumn4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        staticTableView = tableView;
        staticTableView2 = tableView2;
    }

    @Override
    public void setScreenParent(screensController screenPage) {myController = screenPage;}

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

    @FXML
    private void goToPistas(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("abreviacao"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tableColumn7.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        tableColumn8.setCellValueFactory(new PropertyValueFactory<>("numPoles"));
        tableColumn9.setCellValueFactory(new PropertyValueFactory<>("numVitorias"));

    }

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoFim"));
        t2tableColumn4.setCellValueFactory(new PropertyValueFactory<>("salario"));

    }
}
