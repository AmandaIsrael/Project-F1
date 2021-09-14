package ano;
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

public class anoController implements Initializable /*, controlledScreen */{
    @FXML private TableView<anoModel> tableView;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<anoModel, SimpleStringProperty> tableColumn3;
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
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("pista"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("data"));

        ObservableList<anoModel> anos = FXCollections.observableArrayList(readListaPistas());

        tableView.setItems(anos);
    }

    public static ArrayList<anoModel> readListaPistas(){
        
        ArrayList<anoModel> anos = new ArrayList<>();
        String sql = "SELECT nomeGrandPrix, registroNomePista, dataFim FROM grandPrix, pistaRegistro WHERE pistaID = grandPrixPistaID";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomeGrandPrix"));
                SimpleStringProperty pista = new SimpleStringProperty(resultado.getString("registroNomePista"));
                SimpleStringProperty data = new SimpleStringProperty(resultado.getString("dataFim"));

                anoModel ano = new anoModel(nome, pista, data);

                anos.add(ano);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return anos;
    }
}
