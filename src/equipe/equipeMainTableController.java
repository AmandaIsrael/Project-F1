package equipe;

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

public class equipeMainTableController implements Initializable, controlledScreen {
    screensController myController;
    
    @FXML private TableView<equipeLiderModel> tableView;
    public static TableView<equipeLiderModel> staticTableView;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn7;
    @FXML private TableColumn<equipeLiderModel, SimpleStringProperty> tableColumn8;
    
    @FXML private TableView<equipeNomeAntigoModel> tableView2;
    public static TableView<equipeNomeAntigoModel> staticTableView2;
    @FXML private TableColumn<equipeNomeAntigoModel, SimpleStringProperty> t2tableColumn1;
    @FXML private TableColumn<equipeNomeAntigoModel, SimpleStringProperty> t2tableColumn2;
    @FXML private TableColumn<equipeNomeAntigoModel, SimpleStringProperty> t2tableColumn3;
    
    @FXML private TableView<equipeMotoresModel> tableView3;
    public static TableView<equipeMotoresModel> staticTableView3;
    @FXML private TableColumn<equipeMotoresModel, SimpleStringProperty> t3tableColumn1;
    @FXML private TableColumn<equipeMotoresModel, SimpleStringProperty> t3tableColumn2;
    @FXML private TableColumn<equipeMotoresModel, SimpleStringProperty> t3tableColumn3;
    @FXML private TableColumn<equipeMotoresModel, SimpleStringProperty> t3tableColumn4;

    @FXML private TableView<equipeMainTableModel> tableView4;
    public static TableView<equipeMainTableModel> staticTableView4;
    @FXML private TableColumn<equipeMainTableModel, SimpleStringProperty> t4tableColumn1;
    @FXML private TableColumn<equipeMainTableModel, SimpleStringProperty> t4tableColumn2;
    @FXML private TableColumn<equipeMainTableModel, SimpleStringProperty> t4tableColumn3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        initTable3();
        initTable4();
        staticTableView = tableView;
        staticTableView2 = tableView2;
        staticTableView3 = tableView3;
        staticTableView4 = tableView4;
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
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        tableColumn7.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        tableColumn8.setCellValueFactory(new PropertyValueFactory<>("anoFim"));

    }

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nomeAntigo"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoFim"));

    }

    public void initTable3(){

        t3tableColumn1.setCellValueFactory(new PropertyValueFactory<>("motor"));
        t3tableColumn2.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        t3tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        t3tableColumn4.setCellValueFactory(new PropertyValueFactory<>("anoFim"));

    }

    public void initTable4(){

        t4tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        t4tableColumn2.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        t4tableColumn3.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));

    }

    
}
