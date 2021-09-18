package grandPrix.grandPrixQuali;
import java.net.URL;
import java.util.ResourceBundle;

import grandPrix.grandPrixPenalidadeModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

public class grandPrixQualiMainTableController implements Initializable, controlledScreen{
    screensController myController;
    
    @FXML private TableView<grandPrixQualiMainTableModel> tableView;
    public static TableView<grandPrixQualiMainTableModel> staticTableView;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<grandPrixQualiMainTableModel, SimpleStringProperty> tableColumn7;
    
    @FXML private TableView<grandPrixPenalidadeModel> tableView2;
    public static TableView<grandPrixPenalidadeModel> staticTableView2;
    @FXML private TableColumn<grandPrixPenalidadeModel, SimpleStringProperty> t2tableColumn1;
    @FXML private TableColumn<grandPrixPenalidadeModel, SimpleStringProperty> t2tableColumn2;
    @FXML private TableColumn<grandPrixPenalidadeModel, SimpleStringProperty> t2tableColumn3;
    @FXML private TableColumn<grandPrixPenalidadeModel, SimpleStringProperty> t2tableColumn4;

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

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("posicao"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("num"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        tableColumn7.setCellValueFactory(new PropertyValueFactory<>("substituto"));

    }

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("penalidade"));
        t2tableColumn4.setCellValueFactory(new PropertyValueFactory<>("causa"));

    }
}
