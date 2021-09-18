package grandPrix.grandPrixCorrida;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import grandPrix.grandPrixPenalidadeModel;

public class grandPrixCorridaDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static ArrayList<grandPrixCorridaMainTableModel> readListaResultados(String grandPrixNome){
        
        ArrayList<grandPrixCorridaMainTableModel> resultados = new ArrayList<>();
        String sql = "SELECT colocacao as \"Colocação\", pilotonumero as \"Número\", pilotonome \"Nome\", pilotosobrenome \"Sobrenome\", equiperegistronome \"Equipe\", posicaolargada \"Grid\", pontos \"Pontos\", COALESCE(causadnf, '') as \"DNF\", case when corridaSubstitutoEquipeID IS NOT NULL then 'Sim' else 'Não' end FROM resultadoindividualcorrida LEFT JOIN dnf ON causadnfcorrida = dnfid INNER JOIN grandprixregistro ON corridaedicaograndprix = edicaograndprix INNER JOIN resultadoindividualquali ON qualipilotoid = corridapilotoid AND qualiedicaograndprix = corridaedicaograndprix INNER JOIN pilotoregistro ON corridapilotoid = pilotoid INNER JOIN piloto ON pilotoregistronome = pilotonome AND pilotoregistrosobrenome = pilotosobrenome LEFT JOIN contrato ON contratopilotoid = pilotoid AND (anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro ON equipeid = (SELECT COALESCE(corridasubstitutoequipeid, contratoequipeid)) WHERE nomegrandprix = '"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty colocacao = new SimpleStringProperty(resultado.getString("Colocação"));
                SimpleStringProperty grid = new SimpleStringProperty(resultado.getString("Número"));
                SimpleStringProperty equipe = new SimpleStringProperty(resultado.getString("Nome"));
                SimpleStringProperty num = new SimpleStringProperty(resultado.getString("Sobrenome"));
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("Equipe"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("Grid"));
                SimpleStringProperty pontos = new SimpleStringProperty(resultado.getString("Pontos"));
                SimpleStringProperty dnf = new SimpleStringProperty(resultado.getString("DNF"));
                SimpleStringProperty substituto = new SimpleStringProperty(resultado.getString("case"));

                grandPrixCorridaMainTableModel resultadoIndividual = new grandPrixCorridaMainTableModel(colocacao, num, nome, sobrenome, equipe, grid, pontos, dnf, substituto);
                resultados.add(resultadoIndividual);

            }

        }catch(SQLException e){
            System.out.println("Error resultado corrida");
        }
        return resultados;
    }

    public static ArrayList<grandPrixPenalidadeModel> readListaPenalidades(String grandPrixNome){
        
        ArrayList<grandPrixPenalidadeModel> penalidades = new ArrayList<>();
        String sql = "SELECT pilotoRegistroNome, pilotoRegistroSobrenome, descrpenalidade, penalidadeCorridaCausa FROM penalidade, ocorrerpenalidadecorrida, pilotoregistro, grandprixregistro WHERE penalidadeCorridaID=penalidadeid AND penalidadeCorridaPilotoID=pilotoid AND penalidadeCorridaEdicaoGrandPrix=edicaoGrandPrix AND nomeGrandPrix='"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("pilotoRegistroNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("pilotoRegistroSobrenome"));
                SimpleStringProperty penalidade = new SimpleStringProperty(resultado.getString("descrpenalidade"));
                SimpleStringProperty causa = new SimpleStringProperty(resultado.getString("penalidadeCorridaCausa"));

                grandPrixPenalidadeModel penalidadeElement = new grandPrixPenalidadeModel(nome, sobrenome, penalidade, causa);

                penalidades.add(penalidadeElement);

            }

        }catch(SQLException e){
            System.out.println("Error penalidade corrida");
        }
        return penalidades;
    }

}
