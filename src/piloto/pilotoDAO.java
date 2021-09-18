package piloto;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class pilotoDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();
    
    public static ArrayList<pilotoMainTableModel> readListaPilotos(String pilotoNome, String pilotoSobrenome){
        
        ArrayList<pilotoMainTableModel> pilotos = new ArrayList<>();
        String sql = "SELECT * FROM piloto WHERE pilotoNome = '"+pilotoNome+"' AND pilotoSobrenome = '"+pilotoSobrenome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("pilotoNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("pilotoSobrenome"));
                SimpleStringProperty numero = new SimpleStringProperty(resultado.getString("pilotoNumero"));
                SimpleStringProperty abreviacao = new SimpleStringProperty(resultado.getString("pilotoAbrev"));
                SimpleStringProperty nascimento = new SimpleStringProperty(resultado.getString("pilotoNascimento"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("pilotoCidade"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("pilotoNacionalidade"));
                SimpleStringProperty numPoles = new SimpleStringProperty(resultado.getString("pilotoTotalPoles"));
                SimpleStringProperty numVitorias = new SimpleStringProperty(resultado.getString("pilotoTotalVitorias"));

                pilotoMainTableModel piloto = new pilotoMainTableModel(nome, sobrenome, numero, abreviacao, nascimento, cidade, nacionalidade, numPoles, numVitorias);
                pilotos.add(piloto);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return pilotos;
    }

    public static ArrayList<pilotoContratoModel> readListaContratos(String pilotoNome, String pilotoSobrenome){
        
        ArrayList<pilotoContratoModel> contratos = new ArrayList<>();
        String sql = "SELECT equipeRegistroNome, contratoAnoInicio, contratoAnoFim, contratoSalario FROM contrato, equipeRegistro, pilotoRegistro WHERE contratoPilotoID = pilotoID AND equipeID = contratoEquipeID AND pilotoRegistroNome ='"+pilotoNome+"' AND pilotoRegistroSobrenome = '"+pilotoSobrenome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty equipe = new SimpleStringProperty(resultado.getString("equipeRegistroNome"));
                SimpleStringProperty salario = new SimpleStringProperty("US$ "+resultado.getString("contratoSalario")+"K");
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("contratoAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("contratoAnoFim"));

                if(anoFim.get().equals("-1")){
                    anoFim.set("Atual");
                }
                pilotoContratoModel contrato = new pilotoContratoModel(equipe, anoInicio, anoFim, salario);

                contratos.add(contrato);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return contratos;
    }
}
