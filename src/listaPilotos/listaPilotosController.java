package listaPilotos;
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


public class listaPilotosController implements Initializable /*, controlledScreen */{
    @FXML private TableView<listaPilotosModel> tableView;
    @FXML private TableColumn<listaPilotosModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<listaPilotosModel, SimpleStringProperty> tableColumn2;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        ObservableList<listaPilotosModel> pilotos = FXCollections.observableArrayList(readListaPilotos());

        tableView.setItems(pilotos);
    }

    public static ArrayList<listaPilotosModel> readlistaPilotos(){
        
        ArrayList<listaPilotosModel> pilotos = new ArrayList<>();
        String sql = "SELECT pilotoNome, pilotoSobrenome FROM piloto";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("pilotoNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("pilotoSobrenome"));

                listaPilotosModel pista = new listaPilotosModel(nome, sobrenome);

                pilotos.add(piloto);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pilotos;
    }

}
