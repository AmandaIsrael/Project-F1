package WCC;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.controlledScreen;
import sample.utils.screensController;
import piloto.pilotoMainTableController;
import piloto.pilotoMainTableModel;
import piloto.pilotoDAO;
import WCC.WCCModel;

public class WCCController extends pilotoMainTableController implements Initializable, controlledScreen {
    screensController myController;
    @FXML private TableView<WCCModel> tableView;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn7;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn8;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn9;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn10;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn11;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn12;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn13;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn14;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn15;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn16;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn17;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn18;
    @FXML private TableColumn<WCCModel, SimpleStringProperty> tableColumn19;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WCCModel listaPilotos = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToPiloto();
                    String [] stringList = listaPilotos.getPiloto().split(" ");
                    String pilotoNome = stringList[0], pilotoSobrenome  = stringList[1];
                    

                    ObservableList<pilotoMainTableModel> piloto = FXCollections.observableArrayList(pilotoDAO.readListaPilotos(pilotoNome, pilotoSobrenome));
                    staticTableView.setItems(piloto);
                    
                }
            }});
    }

    @Override
    public void setScreenParent(screensController screenPage) { myController = screenPage; }

    @FXML
    private void goToAno(ActionEvent event){
        myController.setScreen(Main.screen2ID);
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }

    @FXML
    private void goToEquipes(ActionEvent event){
        myController.setScreen(Main.screen4ID);
    }

    @FXML
    private void goToPistas(ActionEvent event){
        myController.setScreen(Main.screen5ID);
    }

    private void goToPiloto(){
        myController.setScreen(Main.screen8ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("piloto"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("aut"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("sty"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("hun"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("gbr"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("g70a"));
        tableColumn7.setCellValueFactory(new PropertyValueFactory<>("esp"));
        tableColumn8.setCellValueFactory(new PropertyValueFactory<>("bel"));
        tableColumn9.setCellValueFactory(new PropertyValueFactory<>("ita"));
        tableColumn10.setCellValueFactory(new PropertyValueFactory<>("tus"));
        tableColumn11.setCellValueFactory(new PropertyValueFactory<>("rus"));
        tableColumn12.setCellValueFactory(new PropertyValueFactory<>("eif"));
        tableColumn13.setCellValueFactory(new PropertyValueFactory<>("por"));
        tableColumn14.setCellValueFactory(new PropertyValueFactory<>("emi"));
        tableColumn15.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tableColumn16.setCellValueFactory(new PropertyValueFactory<>("bhr"));
        tableColumn17.setCellValueFactory(new PropertyValueFactory<>("skh"));
        tableColumn18.setCellValueFactory(new PropertyValueFactory<>("abu"));
        tableColumn19.setCellValueFactory(new PropertyValueFactory<>("total"));


        ObservableList<WCCModel> pilotos = FXCollections.observableArrayList(readListaWCC());

        tableView.setItems(pilotos);
    }

    public static ArrayList<WCCModel> readListaWCC(){
        
        ArrayList<WCCModel> campeonatos = new ArrayList<>();
        String sql = "CREATE OR REPLACE TEMPORARY VIEW aut as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Áustria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW sty as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Estíria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW hun as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Hungria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW gbr as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Inglaterra' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW  g70 as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = '70º Aniversário' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW esp as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Espanha' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW bel as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Bélgica' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW ita as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Itália' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW tos as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Toscana' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW rus as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Rússia' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW eif as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio Eifel' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW prt as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio de Portugal' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW ero as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Emilia-Romagna' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW  tur as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Turquia' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW bhr as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio do Bahrain' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW sak as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio Sakhir' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;  CREATE OR REPLACE TEMPORARY VIEW abd as SELECT pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio de Abu Dhabi' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid;   SELECT CONCAT(pilotoregistronome,' ',pilotoregistrosobrenome) \"Piloto\", aut.pontos \"AUT\", sty.pontos \"STY\",     hun.pontos \"HUN\", gbr.pontos \"GBR\", g70.pontos \"70A\", esp.pontos \"ESP\",     bel.pontos \"BEL\", ita.pontos \"ITA\", tos.pontos \"TUS\", rus.pontos \"RUS\",  eif.pontos \"EIF\", prt.pontos \"POR\", ero.pontos \"EMI\", tur.pontos \"TUR\", bhr.pontos \"BHR\", sak.pontos \"SKH\", abd.pontos \"ABU\",     (aut.pontos+sty.pontos+hun.pontos+gbr.pontos+g70.pontos+esp.pontos+bel.pontos +ita.pontos+tos.pontos+rus.pontos+eif.pontos+prt.pontos+ero.pontos+tur.pontos +bhr.pontos+sak.pontos+abd.pontos)as \"Total\" FROM pilotoregistro INNER JOIN aut ON aut.pilotoid=pilotoregistro.pilotoid  INNER JOIN sty ON sty.pilotoid=aut.pilotoid  INNER JOIN hun ON hun.pilotoid=aut.pilotoid INNER JOIN gbr ON gbr.pilotoid=aut.pilotoid  INNER JOIN g70 ON g70.pilotoid=aut.pilotoid INNER JOIN esp ON esp.pilotoid=aut.pilotoid INNER JOIN bel ON bel.pilotoid=aut.pilotoid INNER JOIN ita ON ita.pilotoid=aut.pilotoid  INNER JOIN tos ON tos.pilotoid=aut.pilotoid INNER JOIN rus ON rus.pilotoid=aut.pilotoid INNER JOIN eif ON eif.pilotoid=aut.pilotoid  INNER JOIN prt ON prt.pilotoid=aut.pilotoid INNER JOIN ero ON ero.pilotoid=aut.pilotoid INNER JOIN tur ON tur.pilotoid=aut.pilotoid INNER JOIN bhr ON bhr.pilotoid=aut.pilotoid INNER JOIN sak ON sak.pilotoid=aut.pilotoid INNER JOIN abd ON abd.pilotoid=aut.pilotoid ORDER BY \"Total\" DESC;  ";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty piloto = new SimpleStringProperty(resultado.getString("piloto"));
                SimpleStringProperty aut = new SimpleStringProperty(resultado.getString("aut"));
                SimpleStringProperty sty = new SimpleStringProperty(resultado.getString("sty"));
                SimpleStringProperty hun = new SimpleStringProperty(resultado.getString("hun"));
                SimpleStringProperty gbr = new SimpleStringProperty(resultado.getString("gbr"));
                SimpleStringProperty g70a = new SimpleStringProperty(resultado.getString("g70a"));
                SimpleStringProperty esp = new SimpleStringProperty(resultado.getString("esp"));
                SimpleStringProperty bel = new SimpleStringProperty(resultado.getString("bel"));
                SimpleStringProperty ita = new SimpleStringProperty(resultado.getString("ita"));
                SimpleStringProperty tus = new SimpleStringProperty(resultado.getString("tus"));
                SimpleStringProperty rus = new SimpleStringProperty(resultado.getString("rus"));
                SimpleStringProperty eif = new SimpleStringProperty(resultado.getString("eif"));
                SimpleStringProperty por = new SimpleStringProperty(resultado.getString("por"));
                SimpleStringProperty emi = new SimpleStringProperty(resultado.getString("emi"));
                SimpleStringProperty tur = new SimpleStringProperty(resultado.getString("tur"));
                SimpleStringProperty bhr = new SimpleStringProperty(resultado.getString("bhr"));
                SimpleStringProperty skh = new SimpleStringProperty(resultado.getString("skh"));
                SimpleStringProperty abu = new SimpleStringProperty(resultado.getString("abu"));
                SimpleStringProperty total = new SimpleStringProperty(resultado.getString("total"));

                WCCModel wcc = new WCCModel(piloto, aut, sty, hun, gbr, g70a, esp, bel, ita, tus, rus, eif, por, emi, tur, bhr, skh, abu, total);

                campeonatos.add(wcc);
            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return campeonatos;
    }
}
