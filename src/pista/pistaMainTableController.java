package pista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;

public class pistaMainTableController implements Initializable, controlledScreen {
    screensController myController;
    @FXML private TableView<pistaMainTableModel> tableView;
    public static TableView<pistaMainTableModel> staticTableView;
    @FXML private TableColumn<pistaMainTableModel, String> tableColumn1;
    @FXML private TableColumn<pistaMainTableModel, String> tableColumn2;
    @FXML private TableColumn<pistaMainTableModel, String> tableColumn3;
    @FXML private TableView<pistaTracadoModel> tableView2;
    public static TableView<pistaTracadoModel> staticTableView2;
    @FXML private TableColumn<pistaTracadoModel, String> t2tableColumn1;
    @FXML private TableColumn<pistaTracadoModel, String> t2tableColumn2;
    @FXML private TableColumn<pistaTracadoModel, String> t2tableColumn3;
    private pistaMainTableModel pistaAnterior;
    private pistaMainTableModel pistaAtual;
    private pistaTracadoModel tracadoAnterior;
    private pistaTracadoModel tracadoAtual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        setTable();

        pistaAnterior = null;
        pistaAtual = null;
        tracadoAnterior = null;
        tracadoAtual = null;

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

    @FXML
    private void Update(ActionEvent event){
        pistaDAO.updatePista(pistaAnterior, pistaAtual);
    }

    public void initTable(){

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<pistaMainTableModel, String>("nome"));
        this.tableColumn1.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        this.tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        this.tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable2(){

        this.t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("anoAlteracao"));
        this.t2tableColumn1.setCellFactory(TextFieldTableCell.forTableColumn());

        this.t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        this.t2tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());

        this.t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numeroVoltas"));
        this.t2tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void setTable(){
        this.tableColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                pistaAnterior = pistaMainTableModelStringCellEditEvent.getRowValue();
                pistaAtual = pistaMainTableModelStringCellEditEvent.getRowValue();
                pistaAtual.setNome(pistaMainTableModelStringCellEditEvent.getNewValue());
            }
        });

        this.tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                if(pistaAnterior == pistaMainTableModelStringCellEditEvent.getRowValue()){
                    pistaAtual.setCidade(pistaMainTableModelStringCellEditEvent.getNewValue());
                }else{
                    tableView.getItems().remove(pistaAtual);
                    tableView.getItems().add(pistaAnterior);
                }

            }
        });

        this.tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                if(pistaAnterior == pistaMainTableModelStringCellEditEvent.getRowValue()){
                    pistaAtual.setPais(pistaMainTableModelStringCellEditEvent.getNewValue());
                }else{
                    tableView.getItems().remove(pistaAtual);
                    tableView.getItems().add(pistaAnterior);
                }
            }
        });

        this.t2tableColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                tracadoAnterior = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracadoAtual = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracadoAtual.setAnoAlteracao(pistaTracadoModelStringCellEditEvent.getNewValue());
            }
        });

        this.t2tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                if(tracadoAnterior == pistaTracadoModelStringCellEditEvent.getRowValue()){
                    tracadoAtual.setDistancia(pistaTracadoModelStringCellEditEvent.getNewValue());
                }else{
                    tableView2.getItems().remove(tracadoAtual);
                    tableView2.getItems().add(tracadoAnterior);
                }
            }
        });

        this.t2tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                if(tracadoAnterior == pistaTracadoModelStringCellEditEvent.getRowValue()){
                    tracadoAtual.setAnoAlteracao(pistaTracadoModelStringCellEditEvent.getNewValue());
                }else{
                    tableView2.getItems().remove(tracadoAtual);
                    tableView2.getItems().add(tracadoAnterior);
                }
            }
        });
    }

}
