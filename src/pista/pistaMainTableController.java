package pista;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import listaPista.listaPistasController;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



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

    private pistaMainTableModel pista;
    private pistaTracadoModel tracado;

    private HashMap<Integer, pistaMainTableModel> tabelaPistaAtual = new HashMap<>();
    public static HashMap<Integer, pistaMainTableModel> statictabelaPistaAtual = new HashMap<>();

    private HashMap<Integer, pistaTracadoModel> tabelaTracadoAtual = new HashMap<>();
    public static HashMap<Integer, pistaTracadoModel> statictabelaTracadoAtual = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staticTableView = tableView;
        staticTableView2 = tableView2;
        statictabelaPistaAtual = tabelaPistaAtual;
        statictabelaTracadoAtual = tabelaTracadoAtual;
        initTable();
        initTable2();
        setTable();
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
    private void goToAddTracado(ActionEvent event){
        myController.setScreen(Main.screen14ID);
    }

    private void goToPistasDelete(){
        myController.setScreen(Main.screen5ID);
    }

    private void updatePista(pistaMainTableModel pista){
        if(pista.getPais().equals("") && pista.getCidade().equals("")){
            deletePista(pista);
        }else{
            pistaDAO.updatePista(pista);
            listaPistasController.refreshTable();
        }
    }

    private void deletePista(pistaMainTableModel pista){
        pistaDAO.deletePista(pista);
        goToPistasDelete();
        listaPistasController.refreshTable();
    }

    private void updateTracado(pistaTracadoModel tracado){
        if(tracado.getDistancia().equals("") && tracado.getNumeroVoltas().equals("")){
            deleteTracado(tracado);
        }else{
            pistaDAO.updateTracado(tracado, tabelaPistaAtual.get(0).getNome());
            listaPistasController.refreshTable();
        }
    }

    private void deleteTracado(pistaTracadoModel tracado){
        pistaDAO.deleteTracado(tracado, tabelaPistaAtual.get(0).getNome());
        refreshTabelaTracado();
    }

    public static void refreshTabelaTracado(){
        ObservableList<pistaTracadoModel> tracado = FXCollections.observableArrayList(pistaDAO.readListaTracados(statictabelaPistaAtual.get(0).getNome()));
        staticTableView2.getItems().clear();
        staticTableView2.setItems(tracado);
    }


    public void initTable(){

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));

        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        this.tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        this.tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable2(){

        this.t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("anoAlteracao"));

        this.t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        this.t2tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());

        this.t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numeroVoltas"));
        this.t2tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
    }



    public void setTable(){

        this.tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {
                pista = pistaMainTableModelStringCellEditEvent.getRowValue();
                pista.setPais(pistaMainTableModelStringCellEditEvent.getNewValue());
                updatePista(pista);
            }
        });

        this.tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {
                pista = pistaMainTableModelStringCellEditEvent.getRowValue();
                pista.setCidade(pistaMainTableModelStringCellEditEvent.getNewValue());
                updatePista(pista);
            }
        });

        this.t2tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                tracado = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracado.setDistancia(pistaTracadoModelStringCellEditEvent.getNewValue());
                updateTracado(tracado);
            }
        });

        this.t2tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                tracado = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracado.setNumeroVoltas(pistaTracadoModelStringCellEditEvent.getNewValue());
                updateTracado(tracado);
            }
        });


    }



}
