package WDC;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.utils.ConnectPostgre;
import sample.utils.screensController;
import equipe.equipeDAO;
import equipe.equipeLiderModel;
import equipe.equipeMainTableModel;
import equipe.equipeMotoresModel;
import equipe.equipeNomeAntigoModel;
import equipe.equipeMainTableController;

public class WDCController extends equipeMainTableController{
    screensController myController;
    @FXML private TableView<WDCModel> tableView;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn1;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn2;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn3;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn4;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn5;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn6;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn7;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn8;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn9;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn10;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn11;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn12;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn13;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn14;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn15;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn16;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn17;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn18;
    @FXML private TableColumn<WDCModel, SimpleStringProperty> tableColumn19;
    private static Connection con = ConnectPostgre.ConnectDatabase();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();

        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WDCModel listaEquipes = tableView.getSelectionModel().getSelectedItem();
                if (mouseEvent.getClickCount() == 2){
                    goToEquipe();

                    ObservableList<equipeLiderModel> lider = FXCollections.observableArrayList(equipeDAO.readListaLideres(listaEquipes.getEquipe()));
                    staticTableView.setItems(lider);
                    ObservableList<equipeNomeAntigoModel> nomes = FXCollections.observableArrayList(equipeDAO.readListaNomesAntigos(listaEquipes.getEquipe()));
                    staticTableView2.setItems(nomes);
                    ObservableList<equipeMotoresModel> motores = FXCollections.observableArrayList(equipeDAO.readListaMotores(listaEquipes.getEquipe()));
                    staticTableView3.setItems(motores);
                    ObservableList<equipeMainTableModel> equipe = FXCollections.observableArrayList(equipeDAO.readListaEquipes(listaEquipes.getEquipe()));
                    staticTableView4.setItems(equipe);
                    
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

    private void goToEquipe(){
        myController.setScreen(Main.screen7ID);
    }

    public void initTable(){

        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("equipe"));
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


        ObservableList<WDCModel> pilotos = FXCollections.observableArrayList(readListaWDC());

        tableView.setItems(pilotos);
    }

    public static ArrayList<WDCModel> readListaWDC(){
        
        ArrayList<WDCModel> campeonatos = new ArrayList<>();
        //String sql = "CREATE OR REPLACE TEMPORARY VIEW aut_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Áustria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqaut AS SELECT equipeid, SUM(pontos) from aut_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW sty_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Estíria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqsty AS SELECT equipeid, SUM(pontos) from sty_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW hun_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Hungria' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqhun AS SELECT equipeid, SUM(pontos) from hun_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW gbr_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Inglaterra' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqgbr AS SELECT equipeid, SUM(pontos) from gbr_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW g70_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = '70º Aniversário' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqg70 AS SELECT equipeid, SUM(pontos) from g70_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW esp_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Espanha' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqesp AS SELECT equipeid, SUM(pontos) from esp_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW bel_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Bélgica' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqbel AS SELECT equipeid, SUM(pontos) from bel_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW ita_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Itália' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqita AS SELECT equipeid, SUM(pontos) from ita_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW tos_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Toscana' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqtos AS SELECT equipeid, SUM(pontos) from tos_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW rus_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Rússia' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqrus AS SELECT equipeid, SUM(pontos) from rus_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW eif_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio Eifel' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqeif AS SELECT equipeid, SUM(pontos) from eif_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW prt_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio de Portugal' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqprt AS SELECT equipeid, SUM(pontos) from prt_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW ero_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Emilia-Romagna' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqero AS SELECT equipeid, SUM(pontos) from ero_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW tur_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio da Turquia' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqtur AS SELECT equipeid, SUM(pontos) from tur_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW bhr_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio do Bahrain' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqbhr AS SELECT equipeid, SUM(pontos) from bhr_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW sak_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio Sakhir' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqsak AS SELECT equipeid, SUM(pontos) from sak_temp group by equipeid;  CREATE OR REPLACE TEMPORARY VIEW abd_temp as SELECT equipeid, pilotoid, melhorVoltaPilotoID, COALESCE(case when pilotoid = melhorVoltaPilotoID then pontos + 1 else pontos end,0) \"pontos\" FROM resultadoindividualcorrida INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN grandprix ON grandprixregistro.nomegrandprix = grandprix.nomegrandprix AND grandprix.nomegrandprix = 'Grande Prêmio de Abu Dhabi' RIGHT JOIN pilotoregistro ON corridapilotoid = pilotoid LEFT JOIN contrato     ON contratopilotoid = pilotoid AND (grandprixregistro.anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro     ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid));  CREATE OR REPLACE TEMPORARY VIEW eqabd AS SELECT equipeid, SUM(pontos) from abd_temp group by equipeid;  SELECT equipeRegistroNome \"Equipe\", eqaut.sum \"AUT\", eqsty.sum \"STY\",     eqhun.sum \"HUN\", eqgbr.sum \"GBR\", eqg70.sum \"70A\", eqesp.sum \"ESP\",     eqbel.sum \"BEL\", eqita.sum \"ITA\", eqtos.sum \"TUS\", eqrus.sum \"RUS\",     eqeif.sum \"EIF\", eqprt.sum \"POR\", eqero.sum \"EMI\", eqtur.sum \"TUR\",     eqbhr.sum \"BHR\", eqsak.sum \"SKH\", eqabd.sum \"ABU\",     (eqaut.sum+eqsty.sum+eqhun.sum+eqgbr.sum+eqg70.sum+eqesp.sum+eqbel.sum     +eqita.sum+eqtos.sum+eqrus.sum+eqeif.sum+eqprt.sum+eqero.sum+eqtur.sum     +eqbhr.sum+eqsak.sum+eqabd.sum + (CASE WHEN equipeRegistroNome='Racing Point' THEN -15 ELSE 0 END))as \"Total\" FROM equiperegistro INNER JOIN eqaut ON eqaut.equipeid=equiperegistro.equipeid INNER JOIN eqsty ON eqsty.equipeid=eqaut.equipeid INNER JOIN eqhun ON eqhun.equipeid=eqaut.equipeid INNER JOIN eqgbr ON eqgbr.equipeid=eqaut.equipeid  INNER JOIN eqg70 ON eqg70.equipeid=eqaut.equipeid INNER JOIN eqesp ON eqesp.equipeid=eqaut.equipeid  INNER JOIN eqbel ON eqbel.equipeid=eqaut.equipeid INNER JOIN eqita ON eqita.equipeid=eqaut.equipeid INNER JOIN eqtos ON eqtos.equipeid=eqaut.equipeid INNER JOIN eqrus ON eqrus.equipeid=eqaut.equipeid  INNER JOIN eqeif ON eqeif.equipeid=eqaut.equipeid INNER JOIN eqprt ON eqprt.equipeid=eqaut.equipeid INNER JOIN eqero ON eqero.equipeid=eqaut.equipeid  INNER JOIN eqtur ON eqtur.equipeid=eqaut.equipeid INNER JOIN eqbhr ON eqbhr.equipeid=eqaut.equipeid INNER JOIN eqsak ON eqsak.equipeid=eqaut.equipeid INNER JOIN eqabd ON eqabd.equipeid=eqaut.equipeid ORDER BY \"Total\" DESC; ";
        String sql = "SELECT * FROM wccquery";
        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty equipe = new SimpleStringProperty(resultado.getString("equipe"));
                SimpleStringProperty aut = new SimpleStringProperty(resultado.getString("aut"));
                SimpleStringProperty sty = new SimpleStringProperty(resultado.getString("sty"));
                SimpleStringProperty hun = new SimpleStringProperty(resultado.getString("hun"));
                SimpleStringProperty gbr = new SimpleStringProperty(resultado.getString("gbr"));
                SimpleStringProperty g70a = new SimpleStringProperty(resultado.getString("70a"));
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


                WDCModel wdc = new WDCModel(equipe, aut, sty, hun, gbr, g70a, esp, bel, ita, tus, rus, eif, por, emi, tur, bhr, skh, abu, total);

                campeonatos.add(wdc);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return campeonatos;
    }
}
