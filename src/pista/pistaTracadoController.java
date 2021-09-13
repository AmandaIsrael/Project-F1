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
import utils.ConnectPostgre;

public class pistaTracadoController implements Initializable /*, controlledScreen */ {
    @FXML private TableView<pistaTracadoModel> tableView;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<pistaTracadoModel, SimpleStringProperty> tableColumn3;
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

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("anoAlteracao"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("numeroVoltas"));

        ObservableList<pistaTracadoModel> tracados = FXCollections.observableArrayList(readListaPistas());

        tableView.setItems(tracados);
    }

    public static ArrayList<pistaTracadoModel> readListaPistas(){
        
        ArrayList<pistaTracadoModel> tracados = new ArrayList<>();
        String sql = "SELECT anoAlteracaoTracado, distanciaTracado, numeroVoltasTracado FROM tracado, pistaRegistro WHERE pistaID = tracadoPistaID";

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
            System.out.println("Error");
        }
        return tracados;
    }
}
