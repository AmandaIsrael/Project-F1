package equipe;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class equipeDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();
    
    public static ArrayList<equipeLiderModel> readListaLideres(String equipeNome){
        
        ArrayList<equipeLiderModel> lideres = new ArrayList<>();
        String sql = "SELECT liderNome, liderSobrenome, liderNacionalidade, liderCidade, liderNacimento, cargo, lideradaAnoInicio, lideradaAnoFim FROM lider, liderada, liderRegistro, equipeRegistro WHERE lideradaLiderID = liderID AND liderRegistroNome = liderNome AND liderRegistroSobrenome = liderSobrenome AND lideradaEquipeID=equipeID AND equipeRegistroNome="+equipeNome;

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("liderNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("liderSobrenome"));
                SimpleStringProperty cargo = new SimpleStringProperty(resultado.getString("cargo"));
                SimpleStringProperty nascimento = new SimpleStringProperty(resultado.getString("liderNascimento"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("liderCidade "));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("liderNacionalidade"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("lideradaAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("lideradaAnoFim"));

                equipeLiderModel lider = new equipeLiderModel(nome, sobrenome, cargo, nascimento, cidade, nacionalidade, anoInicio, anoFim);

                lideres.add(lider);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return lideres;
    }

    public static ArrayList<equipeNomeAntigoModel> readListaNomesAntigos(String equipeNome){
        
        ArrayList<equipeNomeAntigoModel> nomesAntigos = new ArrayList<>();
        String sql = "SELECT nomeAntigo, nomeAntigoAnoInicio, nomeAntigoAnoFim FROM nomeAntigo, equipeRegistro WHERE nomeAntigoEquipeID=equipeID AND equipeRegistroNome= '"+equipeNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nomeAntigo = new SimpleStringProperty(resultado.getString("nomeAntigo"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("nomeAntigoAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("nomeAntigoAnoFim"));

                equipeNomeAntigoModel nomeAntigoElement = new equipeNomeAntigoModel(nomeAntigo, anoInicio, anoFim);

                nomesAntigos.add(nomeAntigoElement);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return nomesAntigos;
    }

    public static ArrayList<equipeMotoresModel> readListaMotores(String equipeNome){
        
        ArrayList<equipeMotoresModel> motores = new ArrayList<>();
        String sql = "(SELECT motorNome, nacionalidadeFornecedor, motorAnoInicio, motorAnoFim FROM utilizouMotor, fornecedorMotor, equiperegistro WHERE nomeFornecedor=motorNome AND motorEquipe=equipeID AND equipeRegistroNome='"+equipeNome+"') UNION (SELECT motorAtual, nacionalidadeMotorAtual, equipeAnoInicioMotorAtual, NULL FROM MotorEquipe, Equipe WHERE equipeMotorAtual=motorAtual AND equipeNome='"+equipeNome+"') ORDER BY motorAnoInicio";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty motorNome = new SimpleStringProperty(resultado.getString("motorNome"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("nacionalidadeFornecedor"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("motorAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("motorAnoFim"));

                equipeMotoresModel motor = new equipeMotoresModel(motorNome, nacionalidade, anoInicio, anoFim);

                motores.add(motor);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return motores;
    }

    public static ArrayList<equipeMainTableModel> readListaEquipes(String equipeNome){
        
        ArrayList<equipeMainTableModel> equipes = new ArrayList<>();
        String sql = "SELECT equipeNome, equipeCidade, equipeNacionalidade FROM Equipe WHERE equipeNome = '"+equipeNome+"'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("equipeNome"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("equipeCidade"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("equipeNacionalidade"));

                equipeMainTableModel equipe = new equipeMainTableModel(nome, cidade, nacionalidade);

                equipes.add(equipe);

            }

        }catch(SQLException e){
            System.out.println("Error");
        }
        return equipes;
    }
}
