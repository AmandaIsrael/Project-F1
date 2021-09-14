package pista;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.utils.ConnectPostgre;

public class pistaMainTableController implements Initializable /*, controlledScreen */{
    @FXML private TableView<pistaMainTableModel> tableView;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<pistaMainTableModel, SimpleStringProperty> tableColumn3;
    private static Connection con = ConnectPostgre.ConnectDatabase();
    private String nomePista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    /*
    @Override
    public void setScreenParent(screensController screenPage) {}
    */

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pais"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        ObservableList<pistaMainTableModel> pistas = FXCollections.observableArrayList(readListaPistas(nomePista));

        tableView.setItems(pistas);
    }

    public String getNomePista(){
        return this.nomePista;
    }

    public void setNomePista(String nomePista) {
        this.nomePista = nomePista;
    }

    public static ArrayList<pistaMainTableModel> readListaPistas(String nomePista){
        
        ArrayList<pistaMainTableModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista WHERE nomPista = "+ nomePista;

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
            System.out.println("Error");
        }
        return pistas;
    }
}
