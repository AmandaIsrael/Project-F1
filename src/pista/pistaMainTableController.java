package pista;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
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

    private pistaMainTableModel pista = null;
    private pistaTracadoModel tracado = null;

    private ArrayList<pistaMainTableModel> pistaValuesAnterior = new ArrayList<>();
    public static ArrayList<pistaMainTableModel> staticPistaValuesAnterior = new ArrayList<>();

    private ArrayList<pistaTracadoModel> tracadoValuesAnterior = new ArrayList<>();
    public static ArrayList<pistaTracadoModel> staticTracadoValuesAnterior = new ArrayList<>();

    private HashMap<Integer, pistaMainTableModel> pistaValuesAtual = new HashMap<>();
    private HashMap<Integer, pistaTracadoModel> tracadoValuesAtual = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        setTable();
        staticTableView = tableView;
        staticTableView2 = tableView2;
        staticPistaValuesAnterior = pistaValuesAnterior;
        staticTracadoValuesAnterior = tracadoValuesAnterior;
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
        for(int i = 0; i < pistaValuesAtual.size(); ++i){
            pistaDAO.updatePista(pistaValuesAnterior.get(i), pistaValuesAtual.get(i));
        }

        for(int i = 0; i < tracadoValuesAtual.size(); ++i){
            pistaDAO.updateTracado(tracadoValuesAnterior.get(i), tracadoValuesAtual.get(i));
        }
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

    public int findRowPista(pistaMainTableModel pista){
        for(int i = 0; i < pistaValuesAnterior.size(); ++i){
            if(pista == pistaValuesAnterior.get(i)){
                return i;
            }
        }
        return -1;
    }

    public int findRowTracado(pistaTracadoModel tracado){
        for(int i = 0; i < tracadoValuesAnterior.size(); ++i){
            if(tracado == tracadoValuesAnterior.get(i)){
                return i;
            }
        }
        return -1;
    }


    public void setTable(){


        this.tableColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                pista = pistaMainTableModelStringCellEditEvent.getRowValue();
                pista.setNome(pistaMainTableModelStringCellEditEvent.getNewValue());
                int i = findRowPista(pista);
                pistaValuesAtual.put(i, pista);
            }
        });

        this.tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                pista = pistaMainTableModelStringCellEditEvent.getRowValue();
                pista.setPais(pistaMainTableModelStringCellEditEvent.getNewValue());
                int i = findRowPista(pista);
                pistaValuesAtual.put(i, pista);

            }
        });

        this.tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaMainTableModel, String> pistaMainTableModelStringCellEditEvent) {

                pista = pistaMainTableModelStringCellEditEvent.getRowValue();
                pista.setCidade(pistaMainTableModelStringCellEditEvent.getNewValue());
                int i = findRowPista(pista);
                pistaValuesAtual.put(i, pista);

            }
        });

        this.t2tableColumn1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {

                tracado = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracado.setAnoAlteracao(pistaTracadoModelStringCellEditEvent.getNewValue());
                int i = findRowTracado(tracado);
                tracadoValuesAtual.put(i, tracado);
            }
        });

        this.t2tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                tracado = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracado.setDistancia(pistaTracadoModelStringCellEditEvent.getNewValue());
                int i = findRowTracado(tracado);
                tracadoValuesAtual.put(i, tracado);
            }
        });

        this.t2tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pistaTracadoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pistaTracadoModel, String> pistaTracadoModelStringCellEditEvent) {
                tracado = pistaTracadoModelStringCellEditEvent.getRowValue();
                tracado.setNumeroVoltas(pistaTracadoModelStringCellEditEvent.getNewValue());
                int i = findRowTracado(tracado);
                tracadoValuesAtual.put(i, tracado);
            }
        });


    }



}
