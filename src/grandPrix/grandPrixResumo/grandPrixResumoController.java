package grandPrix.grandPrixResumo;

import java.net.URL;
import java.util.ResourceBundle;

import grandPrix.grandPrixCorrida.grandPrixCorridaDAO;
import grandPrix.grandPrixCorrida.grandPrixCorridaMainTableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.screensController;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import pista.pistaDAO;
import pista.pistaMainTableController;
import pista.pistaMainTableModel;
import pista.pistaTracadoModel;

public class grandPrixResumoController extends pistaMainTableController{
    screensController myController;
    
    @FXML private TableView<grandPrixResumoMainTableModel> tableView;
    public static TableView<grandPrixResumoMainTableModel> gpstaticTableView;
    @FXML private TableColumn<grandPrixResumoMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<grandPrixResumoMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<grandPrixResumoMainTableModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<grandPrixResumoMainTableModel, SimpleStringProperty> tableColumn4;
    
    @FXML private TableView<grandPrixPodioModel> tableView2;
    public static TableView<grandPrixPodioModel> gpstaticTableView2;
    @FXML private TableColumn<grandPrixPodioModel, SimpleStringProperty> t2tableColumn1;
    @FXML private TableColumn<grandPrixPodioModel, SimpleStringProperty> t2tableColumn2;
    @FXML private TableColumn<grandPrixPodioModel, SimpleStringProperty> t2tableColumn3;
    
    @FXML private TableView<grandPrixTempoModel> tableView3;
    public static TableView<grandPrixTempoModel> gpstaticTableView3;
    @FXML private TableColumn<grandPrixTempoModel, SimpleStringProperty> t3tableColumn1;
    @FXML private TableColumn<grandPrixTempoModel, SimpleStringProperty> t3tableColumn2;
    @FXML private TableColumn<grandPrixTempoModel, SimpleStringProperty> t3tableColumn3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        initTable3();
        gpstaticTableView = tableView;
        gpstaticTableView2 = tableView2;
        gpstaticTableView3 = tableView3;

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                grandPrixResumoMainTableModel listaPista = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToPista();
                    ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(pistaDAO.readListaPistas(listaPista.getPista()));
                    staticTableView.setItems(pistas);
                    ObservableList<pistaTracadoModel> tracado = FXCollections.observableArrayList(pistaDAO.readListaTracados(listaPista.getPista()));
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




    @FXML
    private void goToCorrida(ActionEvent event){

        myController.setScreen(Main.screen12ID);

        //ObservableList<grandPrixCorridaMainTableModel> gp = FXCollections.observableArrayList(grandPrixCorridaDAO.readListaResultados(getNome()));
        //gpstaticTableView.setItems(gp);
    }

    @FXML
    private void goToQuali(ActionEvent event){
        myController.setScreen(Main.screen13ID);
    }


    private void goToPista(){
        myController.setScreen(Main.screen6ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("pista"));
    
    }

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("primeiro"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("segundo"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("terceiro"));

    }

    public void initTable3(){

        t3tableColumn1.setCellValueFactory(new PropertyValueFactory<>("dotd"));
        t3tableColumn2.setCellValueFactory(new PropertyValueFactory<>("melhorVoltaTempo"));
        t3tableColumn3.setCellValueFactory(new PropertyValueFactory<>("melhorVoltaPiloto"));

    }
}
