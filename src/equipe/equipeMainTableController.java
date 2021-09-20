package equipe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import java.util.HashMap;
import listaEquipes.listaEquipesController;
import equipe.equipeMainTableController;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import equipe.equipeMainTableController;

public class equipeMainTableController implements Initializable, controlledScreen {
    screensController myController;
    
    @FXML private TableView<equipeLiderModel> tableView;
    public static TableView<equipeLiderModel> staticTableView;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn1;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn2;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn3;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn4;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn5;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn6;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn7;
    @FXML private TableColumn<equipeLiderModel, String> tableColumn8;
    
    @FXML private TableView<equipeNomeAntigoModel> tableView2;
    public static TableView<equipeNomeAntigoModel> staticTableView2;
    @FXML private TableColumn<equipeNomeAntigoModel, String> t2tableColumn1;
    @FXML private TableColumn<equipeNomeAntigoModel, String> t2tableColumn2;
    @FXML private TableColumn<equipeNomeAntigoModel, String> t2tableColumn3;
    
    @FXML private TableView<equipeMotoresModel> tableView3;
    public static TableView<equipeMotoresModel> staticTableView3;
    @FXML private TableColumn<equipeMotoresModel, String> t3tableColumn1;
    @FXML private TableColumn<equipeMotoresModel, String> t3tableColumn2;
    @FXML private TableColumn<equipeMotoresModel, String> t3tableColumn3;
    @FXML private TableColumn<equipeMotoresModel, String> t3tableColumn4;

    @FXML private TableView<equipeMainTableModel> tableView4;
    public static TableView<equipeMainTableModel> staticTableView4;
    @FXML private TableColumn<equipeMainTableModel, String> t4tableColumn1;
    @FXML private TableColumn<equipeMainTableModel, String> t4tableColumn2;
    @FXML private TableColumn<equipeMainTableModel, String> t4tableColumn3;

    private equipeMainTableModel equipe;
    private equipeNomeAntigoModel nomesAntigos;
    private equipeMotoresModel motores;
    private equipeLiderModel lideres;

    private HashMap<Integer, equipeLiderModel> tabelaLiderAtual = new HashMap<>();
    public static HashMap<Integer, equipeLiderModel> statictabelaLiderAtual = new HashMap<>();

    private HashMap<Integer, equipeMainTableModel> tabelaEquipeAtual = new HashMap<>();
    public static HashMap<Integer, equipeMainTableModel> statictabelaEquipeAtual = new HashMap<>();

    private HashMap<Integer, equipeMotoresModel> tabelaMotoresAtual = new HashMap<>();
    public static HashMap<Integer, equipeMotoresModel> statictabelaMotoresAtual = new HashMap<>();

    private HashMap<Integer, equipeNomeAntigoModel> tabelaNomesAntigosAtual = new HashMap<>();
    public static HashMap<Integer, equipeNomeAntigoModel> statictabelaNomesAntigosAtual = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        staticTableView = tableView;
        staticTableView2 = tableView2;
        staticTableView3 = tableView3;
        staticTableView4 = tableView4;
        statictabelaLiderAtual = tabelaLiderAtual;
        statictabelaEquipeAtual = tabelaEquipeAtual;
        statictabelaMotoresAtual = tabelaMotoresAtual;
        statictabelaNomesAntigosAtual = tabelaNomesAntigosAtual;
        initTable();
        initTable2();
        initTable3();
        initTable4();
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
    private void goToAddLider(ActionEvent event){
        myController.setScreen(Main.screen19ID);
    }

    @FXML
    private void goToAddMotor(ActionEvent event){
        myController.setScreen(Main.screen20ID);
    }

    @FXML
    private void goToAddNomeAntigo(ActionEvent event){
        myController.setScreen(Main.screen21ID);
    }

    private void goToEquipesDelete(){
        myController.setScreen(Main.screen4ID);
    }

    private void updateEquipe(equipeMainTableModel equipe){
        if(equipe.getCidade().equals("") && equipe.getNacionalidade().equals("")){
            deleteEquipe(equipe);
        }else{
            equipeDAO.updateEquipe(equipe);
            listaEquipesController.refreshTable();
        }
    }

    private void deleteEquipe(equipeMainTableModel equipe){
        equipeDAO.deleteEquipe(equipe);
        goToEquipesDelete();
        listaEquipesController.refreshTable();
    }

    private void updateNomeAntigo(equipeNomeAntigoModel nomeAntigo){
        if(nomeAntigo.getAnoFim().equals("") && nomeAntigo.getAnoInicio().equals("")){
            deleteNomeAntigo(nomeAntigo);
        }else{
            equipeDAO.updateNomeAntigo(nomeAntigo, tabelaEquipeAtual.get(0).getNome());
            listaEquipesController.refreshTable();
        }
    }

    private void deleteNomeAntigo(equipeNomeAntigoModel nomeAntigo){
        equipeDAO.deleteNomeAntigo(nomeAntigo.getNomeAntigo());
        refreshTabelaNomeAntigo();
    }

    public static void refreshTabelaNomeAntigo(){
        ObservableList<equipeNomeAntigoModel> nomeAntigo = FXCollections.observableArrayList(equipeDAO.readListaNomesAntigos(statictabelaEquipeAtual.get(0).getNome()));
        staticTableView2.getItems().clear();
        staticTableView2.setItems(nomeAntigo);
    }

    private void updateMotor(equipeMotoresModel motor){
        if(motor.getNacionalidade().equals("") && motor.getAnoFim().equals("")){
            deleteMotor(motor, tabelaEquipeAtual.get(0).getNome());
        }else{
            equipeDAO.updateMotor(motor, tabelaEquipeAtual.get(0).getNome());
            listaEquipesController.refreshTable();
        }
    }

    private void deleteMotor(equipeMotoresModel motor, String nomeEquipe){
        equipeDAO.deleteMotor(motor, nomeEquipe);
        refreshTabelaMotor();
    }

    public static void refreshTabelaMotor(){
        ObservableList<equipeMotoresModel> motor = FXCollections.observableArrayList(equipeDAO.readListaMotores(statictabelaEquipeAtual.get(0).getNome()));
        staticTableView3.getItems().clear();
        staticTableView3.setItems(motor);
    }

    private void updateLider(equipeLiderModel equipe){
        if(equipe.getNacionalidade().equals("") && equipe.getAnoInicio().equals("") && equipe.getAnoFim().equals("") && equipe.getCargo().equals("") && equipe.getNascimento().equals("")){
            deleteLider(equipe, tabelaEquipeAtual.get(0).getNome());
        }else{
            equipeDAO.updateLider(equipe, tabelaEquipeAtual.get(0).getNome());
            listaEquipesController.refreshTable();
        }
    }

    private void deleteLider(equipeLiderModel equipe, String nomeEquipe){
        equipeDAO.deleteLider(equipe, nomeEquipe);
        refreshTabelaLider();
    }

    public static void refreshTabelaLider(){
        ObservableList<equipeLiderModel> equipe = FXCollections.observableArrayList(equipeDAO.readListaLideres(statictabelaEquipeAtual.get(0).getNome()));
        staticTableView.getItems().clear();
        staticTableView.setItems(equipe);
    }

    public void initTable(){

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        this.tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn4.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        this.tableColumn4.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn5.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        this.tableColumn5.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn6.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        this.tableColumn6.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn7.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        this.tableColumn7.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn8.setCellValueFactory(new PropertyValueFactory<>("anoFim"));
        this.tableColumn8.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable2(){

        this.t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nomeAntigo"));
        this.t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        this.t2tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        this.t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoFim"));
        this.t2tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void initTable3(){

        this.t3tableColumn1.setCellValueFactory(new PropertyValueFactory<>("motor"));
        this.t3tableColumn2.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        this.t3tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        this.t3tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        this.t3tableColumn4.setCellValueFactory(new PropertyValueFactory<>("anoFim"));
        this.t3tableColumn4.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void initTable4(){

        this.t4tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.t4tableColumn2.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        this.t4tableColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
        this.t4tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        this.t4tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void setTable(){
        this.tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setCargo(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.tableColumn4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setNascimento(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.tableColumn5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setCidade(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.tableColumn6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setNacionalidade(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.tableColumn7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setAnoInicio(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.tableColumn8.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeLiderModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeLiderModel, String> equipeLiderModelStringCellEditEvent) {
                lideres = equipeLiderModelStringCellEditEvent.getRowValue();
                lideres.setAnoFim(equipeLiderModelStringCellEditEvent.getNewValue());
                updateLider(lideres);
            }
        });

        this.t2tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeNomeAntigoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeNomeAntigoModel, String> equipeNomeAntigoModelStringCellEditEvent) {
                nomesAntigos = equipeNomeAntigoModelStringCellEditEvent.getRowValue();
                nomesAntigos.setAnoInicio(equipeNomeAntigoModelStringCellEditEvent.getNewValue());
                updateNomeAntigo(nomesAntigos);
            }
        });

        this.t2tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeNomeAntigoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeNomeAntigoModel, String> equipeNomeAntigoModelStringCellEditEvent) {
                nomesAntigos = equipeNomeAntigoModelStringCellEditEvent.getRowValue();
                nomesAntigos.setAnoFim(equipeNomeAntigoModelStringCellEditEvent.getNewValue());
                updateNomeAntigo(nomesAntigos);
            }
        });

        this.t3tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeMotoresModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeMotoresModel, String> equipeMotoresModelStringCellEditEvent) {
                motores = equipeMotoresModelStringCellEditEvent.getRowValue();
                motores.setNacionalidade(equipeMotoresModelStringCellEditEvent.getNewValue());
                updateMotor(motores);
            }
        });

        this.t3tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeMotoresModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeMotoresModel, String> equipeMotoresModelStringCellEditEvent) {
                motores = equipeMotoresModelStringCellEditEvent.getRowValue();
                motores.setAnoInicio(equipeMotoresModelStringCellEditEvent.getNewValue());
                updateMotor(motores);
            }
        });

        this.t3tableColumn4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeMotoresModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeMotoresModel, String> equipeMotoresModelStringCellEditEvent) {
                motores = equipeMotoresModelStringCellEditEvent.getRowValue();
                motores.setAnoFim(equipeMotoresModelStringCellEditEvent.getNewValue());
                updateMotor(motores);
            }
        });

        this.t4tableColumn2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeMainTableModel, String> equipeMainTableModelStringCellEditEvent) {
                equipe = equipeMainTableModelStringCellEditEvent.getRowValue();
                equipe.setCidade(equipeMainTableModelStringCellEditEvent.getNewValue());
                updateEquipe(equipe);
            }
        });

        this.t4tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<equipeMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<equipeMainTableModel, String> equipeMainTableModelStringCellEditEvent) {
                equipe = equipeMainTableModelStringCellEditEvent.getRowValue();
                equipe.setNacionalidade(equipeMainTableModelStringCellEditEvent.getNewValue());
                updateEquipe(equipe);
            }
        });
    }

    
}
