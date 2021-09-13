package listaPista;     
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
import utils.ConnectPostgre;

public class listaPistasController implements Initializable /*, controlledScreen */{

    @FXML private TableView<listaPistaModel> tableView;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<listaPistaModel, SimpleStringProperty> tableColumn3;
    private static Connection con = ConnectPostgre.ConnectDatabase();

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

        ObservableList<listaPistaModel> pistas = FXCollections.observableArrayList(readListaPistas());

        tableView.setItems(pistas);
    }

    public static ArrayList<listaPistaModel> readListaPistas(){
        
        ArrayList<listaPistaModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomepista"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("paispista"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("cidadepista"));

                listaPistaModel pista = new listaPistaModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pistas;
    }
    
}
