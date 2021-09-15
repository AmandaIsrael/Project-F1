package pista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

public class pistaMainTableController implements Initializable, controlledScreen {
    screensController myController;
    @FXML private TableView<pistaMainTableModel> tableView;
    public static TableView<pistaMainTableModel> staticTableView;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn3;
    @FXML private TableView<pistaTracadoModel> tableView2;
    public static TableView<pistaTracadoModel> staticTableView2;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> t2tableColumn1;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> t2tableColumn2;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> t2tableColumn3;

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

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
    }

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("anoAlteracao"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numeroVoltas"));
    }

}
