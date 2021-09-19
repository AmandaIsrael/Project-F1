package grandPrix.grandPrixResumo;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class grandPrixResumoDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static ArrayList<grandPrixResumoMainTableModel> readGrandPrix(String grandPrixNome){
        
        ArrayList<grandPrixResumoMainTableModel> grandprixes = new ArrayList<>();
        String sql = "SELECT nomeGrandPrix, dataInicio, dataFim, registroNomePista FROM GrandPrix, pistaregistro WHERE pistaid=grandprixpistaid AND nomegrandprix='"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomegrandprix"));
                SimpleStringProperty dataInicio = new SimpleStringProperty(resultado.getString("datainicio"));
                SimpleStringProperty dataFim = new SimpleStringProperty(resultado.getString("datafim"));
                SimpleStringProperty pista = new SimpleStringProperty(resultado.getString("registronomepista"));

                grandPrixResumoMainTableModel grandprix = new grandPrixResumoMainTableModel(nome, dataInicio, dataFim, pista);

                grandprixes.add(grandprix);
            }

        }catch(SQLException e){
            System.out.println("Error Grand Prix Main Table");
        }
        return grandprixes;
    }
    
    public static ArrayList<grandPrixPodioModel> readListaPodio(String grandPrixNome){
        
        ArrayList<grandPrixPodioModel> podios = new ArrayList<>();
        String sql = "SELECT primeiro.concat AS \"primeiro\", segundo.concat AS \"segundo\", terceiro.concat AS \"terceiro\" FROM (SELECT CONCAT(pilotoRegistroNome, ' ', pilotoRegistroSobrenome), colocacao FROM pilotoregistro, resultadoindividualcorrida, grandprixregistro WHERE pilotoid=corridaPilotoID AND corridaEdicaoGrandPrix=edicaoGrandPrix AND colocacao=1 AND nomegrandprix='"+grandPrixNome+"') as primeiro, (SELECT CONCAT(pilotoRegistroNome, ' ', pilotoRegistroSobrenome), colocacao FROM pilotoregistro, resultadoindividualcorrida, grandprixregistro WHERE pilotoid=corridaPilotoID AND corridaEdicaoGrandPrix=edicaoGrandPrix AND colocacao=2 AND nomegrandprix='"+grandPrixNome+"') as segundo, (SELECT CONCAT(pilotoRegistroNome, ' ', pilotoRegistroSobrenome), colocacao FROM pilotoregistro, resultadoindividualcorrida, grandprixregistro WHERE pilotoid=corridaPilotoID AND corridaEdicaoGrandPrix=edicaoGrandPrix AND colocacao=3 AND nomegrandprix='"+grandPrixNome+"') as terceiro";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty primeiro = new SimpleStringProperty(resultado.getString("primeiro"));
                SimpleStringProperty segundo = new SimpleStringProperty(resultado.getString("segundo"));
                SimpleStringProperty terceiro = new SimpleStringProperty(resultado.getString("terceiro"));

            grandPrixPodioModel podio = new grandPrixPodioModel(primeiro, segundo, terceiro);

                podios.add(podio);

            }

        }catch(SQLException e){
            System.out.println("Error podio");
        }
        return podios;
    }

    public static ArrayList<grandPrixTempoModel> readListaTempos(String grandPrixNome){
        
        ArrayList<grandPrixTempoModel> tempos = new ArrayList<>();
        String sql = "SELECT dotd.dotd, melhortempovolta, CONCAT(pilotoRegistroNome, ' ', pilotoRegistrosobrenome) as melhorVoltaPiloto FROM pilotoregistro, grandprix, (SELECT CONCAT(pilotoRegistroNome, ' ', pilotoRegistrosobrenome) as dotd FROM pilotoregistro, grandprix WHERE pilotoid=driveroftheday AND nomegrandprix='"+grandPrixNome+"') as dotd WHERE pilotoid=melhorvoltapilotoid AND nomegrandprix= '"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty dotd = new SimpleStringProperty(resultado.getString("dotd"));
                SimpleStringProperty melhorVoltaTempo = new SimpleStringProperty(resultado.getString("melhorTempoVolta"));
                SimpleStringProperty melhorVoltaPiloto = new SimpleStringProperty(resultado.getString("melhorVoltaPiloto"));

                grandPrixTempoModel tempo = new grandPrixTempoModel(dotd, melhorVoltaTempo, melhorVoltaPiloto);

                tempos.add(tempo);
            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return tempos;
    }
}
