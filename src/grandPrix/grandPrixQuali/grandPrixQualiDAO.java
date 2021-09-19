package grandPrix.grandPrixQuali;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import grandPrix.grandPrixPenalidadeModel;

public class grandPrixQualiDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static ArrayList<grandPrixQualiMainTableModel> readListaLideres(String grandPrixNome){
        
        ArrayList<grandPrixQualiMainTableModel> resultadosQuali = new ArrayList<>();
        String sql = "SELECT posicaolargada \"Grid\", pilotonumero as \"Número\", pilotonome \"Nome\", pilotosobrenome \"Sobrenome\", equiperegistronome \"Equipe\", to_char(to_timestamp(tempoquali), 'MI:SS.MS') as \"Tempo\", case when qualiSubstitutoEquipeID IS NOT NULL then 'Sim' else 'Não' end FROM resultadoindividualquali INNER JOIN grandprixregistro ON qualiedicaograndprix = edicaograndprix INNER JOIN pilotoregistro ON qualipilotoid = pilotoid INNER JOIN piloto ON pilotoregistronome = pilotonome AND pilotoregistrosobrenome = pilotosobrenome LEFT JOIN contrato ON contratopilotoid = pilotoid AND (anograndprix between contratoanoinicio AND contratoanofim) INNER JOIN equiperegistro ON equipeid = (SELECT COALESCE(qualisubstitutoequipeid, contratoequipeid)) WHERE nomegrandprix = '"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty posicao = new SimpleStringProperty(resultado.getString("grid"));
                SimpleStringProperty num = new SimpleStringProperty(resultado.getString("número"));
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("sobrenome"));
                SimpleStringProperty equipe = new SimpleStringProperty(resultado.getString("equipe"));
                SimpleStringProperty tempo = new SimpleStringProperty(resultado.getString("tempo"));
                SimpleStringProperty substituto = new SimpleStringProperty(resultado.getString("case"));

                grandPrixQualiMainTableModel resultadoQuali = new grandPrixQualiMainTableModel(posicao, num, nome, sobrenome, equipe, tempo, substituto);
                resultadosQuali.add(resultadoQuali);

            }

        }catch(SQLException e){
            System.out.println("Error resultado quali");
        }
        return resultadosQuali;
    }

    public static ArrayList<grandPrixPenalidadeModel> readListaPenalidades(String grandPrixNome){
        
        ArrayList<grandPrixPenalidadeModel> penalidades = new ArrayList<>();
        String sql = "SELECT pilotoRegistroNome, pilotoRegistroSobrenome, descrpenalidade, penalidadeQualiCausa FROM penalidade, ocorrerpenalidadequali, pilotoregistro, grandprixregistro WHERE penalidadeQualiID=penalidadeid AND penalidadeQualiPilotoID=pilotoid AND penalidadeQualiEdicaoGrandPrix=edicaoGrandPrix AND nomeGrandPrix='"+grandPrixNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("pilotoRegistroNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("pilotoRegistroSobrenome"));
                SimpleStringProperty penalidade = new SimpleStringProperty(resultado.getString("descrpenalidade"));
                SimpleStringProperty causa = new SimpleStringProperty(resultado.getString("penalidadeQualiCausa"));

                grandPrixPenalidadeModel penalidadeElement = new grandPrixPenalidadeModel(nome, sobrenome, penalidade, causa);

                penalidades.add(penalidadeElement);

            }

        }catch(SQLException e){
            System.out.println("Error penalidade quali");
        }
        return penalidades;
    }
}
