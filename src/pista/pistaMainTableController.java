package pista;

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
    //private static Connection con = ConnectPostgre.ConnectDatabase();
    //private String nomePista = "Red Bull Ring";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initTable2();
        staticTableView = tableView;
        staticTableView2 = tableView2;
    }

    @Override
    public void setScreenParent(screensController screenPage) {}

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

    /*
    public void populateTable(String nomePista){
        this.tableView.getItems().clear();
        setNomePista(nomePista);
        ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(readListaPistas(nomePista));
        System.out.println(pistas.get(0).nome.get());
        System.out.println(pistas.get(0).cidade.get());
        System.out.println(pistas.get(0).pais.get());

        this.tableView.setItems(pistas);
        this.tableView.refresh();
        tableView.getColumns().get(0).setVisible(false);
        tableView.getColumns().get(0).setVisible(true);

       // initTable();
        //this.tableView.refresh();
    }

     */

    public void initTable(){

        this.tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        this.tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        /*
        if(nomePista != "") {

            ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(readListaPistas(nomePista));

            this.tableView.setItems(pistas);
        }

         */
    }

/*
    public String getNomePista(){
        return this.nomePista;
    }

    public void setNomePista(String nomePista) {
        this.nomePista = nomePista;
    }

    public void setScreenName(String nomePista){
        setNomePista(nomePista);
        //initialize();
    }



    public static ArrayList<pistaMainTableModel> readListaPistas(String nomePista){
        
        ArrayList<pistaMainTableModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista WHERE nomePista = '"+ nomePista + "'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){

                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomepista"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("paispista"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("cidadepista"));

                pistaMainTableModel pista = new pistaMainTableModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error readListaPistas");
        }
        return pistas;
    }

 */

    public void initTable2(){

        t2tableColumn1.setCellValueFactory(new PropertyValueFactory<>("anoAlteracao"));
        t2tableColumn2.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        t2tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numeroVoltas"));
        /*
        if(nomePista != "") {
            ObservableList<pistaTracadoModel> tracados = FXCollections.observableArrayList(readListaTracados(nomePista));
            tableView2.setItems(tracados);
        }

         */
    }

    /*
    public static ArrayList<pistaTracadoModel> readListaTracados(String nomePista){

        ArrayList<pistaTracadoModel> tracados = new ArrayList<>();
        String sql = "SELECT anoAlteracaoTracado, distanciaTracado, numeroVoltasTracado FROM tracado, pistaRegistro WHERE pistaID = tracadoPistaID AND pistaregistronome = '" + nomePista + "'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty anoAlteracao = new SimpleStringProperty(resultado.getString("anoAlteracaopista"));
                SimpleStringProperty distancia = new SimpleStringProperty(resultado.getString("distanciapista"));
                SimpleStringProperty numeroVoltas = new SimpleStringProperty(resultado.getString("numeroVoltaspista"));

                pistaTracadoModel tracado = new pistaTracadoModel(anoAlteracao, distancia, numeroVoltas);
                tracados.add(tracado);

            }

        }catch(SQLException e){
            System.out.println("Error readListaTracados");
        }
        return tracados;
    }

     */
}
