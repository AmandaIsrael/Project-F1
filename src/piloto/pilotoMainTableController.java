package piloto;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;

import equipe.equipeMainTableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.utils.screensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import listaPilotos.listaPilotosController;
import piloto.pilotoMainTableController;
import equipe.equipeDAO;
import equipe.equipeLiderModel;
import equipe.equipeMainTableModel;
import equipe.equipeMotoresModel;
import equipe.equipeNomeAntigoModel;

public class pilotoMainTableController extends equipeMainTableController{
    screensController myController;
    
    @FXML private TableView<pilotoMainTableModel> tableView;
    public static TableView<pilotoMainTableModel> pilostaticTableView;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn1;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn2;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn3;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn4;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn5;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn6;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn7;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn8;
    @FXML private TableColumn<pilotoMainTableModel, String> tableColumn9;

    @FXML private TableView<pilotoContratoModel> tableView2;
    public static TableView<pilotoContratoModel> pilostaticTableView2;
    @FXML private TableColumn<pilotoContratoModel, String> t2tableColumn1;
    @FXML private TableColumn<pilotoContratoModel, String> t2tableColumn2;
    @FXML private TableColumn<pilotoContratoModel, String> t2tableColumn3;
    @FXML private TableColumn<pilotoContratoModel, String> t2tableColumn4;

    private pilotoMainTableModel piloto;
    private pilotoContratoModel contrato;

    private HashMap<Integer, pilotoMainTableModel> tabelaPilotoAtual = new HashMap<>();
    public static HashMap<Integer, pilotoMainTableModel> statictabelaPilotoAtual = new HashMap<>();

    private HashMap<Integer, pilotoContratoModel> tabelaContratoAtual = new HashMap<>();
    public static HashMap<Integer, pilotoContratoModel> statictabelaContratoAtual = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        pilostaticTableView = tableView;
        pilostaticTableView2 = tableView2;
        statictabelaPilotoAtual = tabelaPilotoAtual;
        statictabelaContratoAtual = tabelaContratoAtual;

        tableView2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pilotoContratoModel contrato = tableView2.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToEquipe();

                    ObservableList<equipeLiderModel> lider = FXCollections.observableArrayList(equipeDAO.readListaLideres(contrato.getEquipe()));
                    staticTableView.setItems(lider);
                    ObservableList<equipeNomeAntigoModel> nomes = FXCollections.observableArrayList(equipeDAO.readListaNomesAntigos(contrato.getEquipe()));
                    staticTableView2.setItems(nomes);
                    ObservableList<equipeMotoresModel> motores = FXCollections.observableArrayList(equipeDAO.readListaMotores(contrato.getEquipe()));
                    staticTableView3.setItems(motores);
                    ObservableList<equipeMainTableModel> equipe = FXCollections.observableArrayList(equipeDAO.readListaEquipes(contrato.getEquipe()));
                    staticTableView4.setItems(equipe);
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

    private void goToEquipe(){
        myController.setScreen(Main.screen7ID);
    }

    @FXML
    private void goToAddContrato(ActionEvent event){
        myController.setScreen(Main.screen16ID);
    }

    private void goToPilotosDelete(){
        myController.setScreen(Main.screen3ID);
    }

    private void updatePiloto(pilotoMainTableModel piloto){
        if(piloto.getNumero().equals("") && piloto.getAbreviacao().equals("") && piloto.getNascimento().equals("") && piloto.getCidade().equals("") && piloto.getNacionalidade().equals("")){
            deletePiloto(piloto);
        }else{
            pilotoDAO.updatePiloto(piloto);
            listaPilotosController.refreshTable();
        }
    }

    private void deletePiloto(pilotoMainTableModel piloto){
        pilotoDAO.deletePiloto(piloto);
        goToPilotosDelete();
        listaPilotosController.refreshTable();
    }

    private void updateContrato(pilotoContratoModel contrato){
        if(contrato.getAnoFim().equals("") && contrato.getSalario().equals("")){
            deleteContrato(contrato);
        }else{
            pilotoDAO.updateContrato(contrato, tabelaPilotoAtual.get(0).getNome(), tabelaPilotoAtual.get(0).getSobrenome());
            listaPilotosController.refreshTable();
        }
    }

    private void deleteContrato(pilotoContratoModel contrato){
        pilotoDAO.deleteContrato(contrato, tabelaPilotoAtual.get(0).getNome(), tabelaPilotoAtual.get(0).getSobrenome());
        refreshTabelaContrato();
    }

    private void refreshTabelaContrato(){
        ObservableList<pilotoContratoModel> contrato = FXCollections.observableArrayList(pilotoDAO.readListaContratos(tabelaPilotoAtual.get(0).getNome(), tabelaPilotoAtual.get(0).getSobrenome()));
        tableView2.getItems().clear();
        tableView2.setItems(contrato);
    }

    public void initTable(){

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn4.setCellValueFactory(new PropertyValueFactory<>("abreviacao"));
        this.tableColumn4.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn5.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        this.tableColumn5.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn6.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        this.tableColumn6.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn7.setCellValueFactory(new PropertyValueFactory<>("nacionalidade"));
        this.tableColumn7.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tableColumn8.setCellValueFactory(new PropertyValueFactory<>("numPoles"));
        this.tableColumn9.setCellValueFactory(new PropertyValueFactory<>("numVitorias"));

    }

    public void initTable2(){

        this.t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        this.t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("anoInicio"));
        this.t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("anoFim"));
        this.t2tableColumn3.setCellFactory(TextFieldTableCell.forTableColumn());
        this.t2tableColumn4.setCellValueFactory(new PropertyValueFactory<>("salario"));
        this.t2tableColumn4.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void setTable(){

        this.tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoMainTableModel, String> pilotoMainTableModelStringCellEditEvent) {
                piloto = pilotoMainTableModelStringCellEditEvent.getRowValue();
                piloto.setNumero(pilotoMainTableModelStringCellEditEvent.getNewValue());
                updatePiloto(piloto);
            }
        });

        this.tableColumn4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoMainTableModel, String> pilotoMainTableModelStringCellEditEvent) {
                piloto = pilotoMainTableModelStringCellEditEvent.getRowValue();
                piloto.setAbreviacao(pilotoMainTableModelStringCellEditEvent.getNewValue());
                updatePiloto(piloto);
            }
        });

        this.tableColumn5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoMainTableModel, String> pilotoMainTableModelStringCellEditEvent) {
                piloto = pilotoMainTableModelStringCellEditEvent.getRowValue();
                piloto.setNascimento(pilotoMainTableModelStringCellEditEvent.getNewValue());
                updatePiloto(piloto);
            }
        });

        this.tableColumn6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoMainTableModel, String> pilotoMainTableModelStringCellEditEvent) {
                piloto = pilotoMainTableModelStringCellEditEvent.getRowValue();
                piloto.setCidade(pilotoMainTableModelStringCellEditEvent.getNewValue());
                updatePiloto(piloto);
            }
        });

        this.tableColumn7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoMainTableModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoMainTableModel, String> pilotoMainTableModelStringCellEditEvent) {
                piloto = pilotoMainTableModelStringCellEditEvent.getRowValue();
                piloto.setNacionalidade(pilotoMainTableModelStringCellEditEvent.getNewValue());
                updatePiloto(piloto);
            }
        });

        this.t2tableColumn3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoContratoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoContratoModel, String> pilotoContratoModelStringCellEditEvent) {
                contrato = pilotoContratoModelStringCellEditEvent.getRowValue();
                contrato.setAnoFim(pilotoContratoModelStringCellEditEvent.getNewValue());
                updateContrato(contrato);
            }
        });

        this.t2tableColumn4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pilotoContratoModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pilotoContratoModel, String> pilotoContratoModelStringCellEditEvent) {
                contrato = pilotoContratoModelStringCellEditEvent.getRowValue();
                contrato.setSalario(pilotoContratoModelStringCellEditEvent.getNewValue());
                updateContrato(contrato);
            }
        });


    }
}
