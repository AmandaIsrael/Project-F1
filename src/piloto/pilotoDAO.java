package piloto;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import sample.IDGetter;
import javax.swing.*;

public final class pilotoDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static int getIDPilotoRegistro(){
        String sql = "SELECT MAX(PilotoID) as id FROM Pilotoregistro;";
        int id = -1;
        try {
            Statement declaracao = con.createStatement();
            ResultSet ps = declaracao.executeQuery(sql);

            while (ps.next()) {
                id = ps.getInt("id");
                return id;
            }

        }
        catch (SQLException throwables) {
            System.out.println(throwables);
            System.out.println("Erro ao pegar ID em Pilotoregistro!");
        }
        return id;
    }

    public static void inserirPilotoRegistro(String nome, String sobrenome){
        String sql = "INSERT INTO PilotoRegistro (pilotoID,	pilotoRegistroNome,	pilotoRegistroSobrenome) VALUES (?,?,?);";
        int id = getIDPilotoRegistro() + 1;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setString(3, sobrenome);

            ps.executeUpdate();

        }catch (SQLException throwables) {
            System.out.println(throwables);
            System.out.println("Erro ao inserir PilotoRegistro!");
        }
    }

    public static void inserirPiloto(String nome, String sobrenome, String numero, String abrev, String nascimento, String cidade, String nacionalidade){
        String sql = "INSERT INTO piloto (pilotoNome,pilotoSobrenome,pilotoNumero,pilotoAbrev,pilotoNascimento,pilotoCidade,pilotoNacionalidade) VALUES (?,?,?,?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);
            ps.setInt(3, Integer.parseInt(numero));
            ps.setString(4, abrev);
            ps.setDate(5, Date.valueOf(nascimento));
            ps.setString(6, cidade);
            ps.setString(7, nacionalidade);

            ps.executeUpdate();

            inserirPilotoRegistro(nome, sobrenome);

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Piloto!");
        }
    }
    
    public static void updatePiloto(pilotoMainTableModel piloto){
        String sql = "UPDATE piloto SET pilotoNumero = ?, pilotoAbrev = ?, pilotoNascimento = ?, pilotoCidade = ?, pilotoNacionalidade = ? WHERE pilotoNome = ? AND pilotoSobrenome = ?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(piloto.getNumero().equals("")){
                ps.setInt(1, 0);
            }else{
                ps.setInt(1, Integer.parseInt(piloto.getNumero()));
            }
            ps.setString(2, piloto.getAbreviacao());
            if(piloto.getNascimento().equals("")){
                ps.setDate(3, Date.valueOf("1900-01-01"));
            }else{
                ps.setDate(3, Date.valueOf(piloto.getNascimento()));
            }
            ps.setString(4, piloto.getCidade());
            ps.setString(5, piloto.getNacionalidade());
            ps.setString(6, piloto.getNome());
            ps.setString(7, piloto.getSobrenome());
            
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updatePiloto");
        }
    }

    public static void inserirContrato(String nome, String sobrenome, String equipe, String anoInicio, String anoFim, String salario){
        String sql = "INSERT INTO Contrato (contratoPilotoID,contratoEquipeID,contratoAnoInicio,contratoAnoFim,contratoSalario) VALUES (?,?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getPilotoID(nome, sobrenome));
            ps.setInt(2, IDGetter.getEquipeID(equipe));
            ps.setInt(3, Integer.parseInt(anoInicio));
            ps.setInt(4, Integer.parseInt(anoFim));
            ps.setInt(5, Integer.parseInt(salario));


            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Contrato!");
        }
    }

    public static void updateContrato(pilotoContratoModel contrato, String pilotoNome, String pilotoSobrenome){
        String sql = "UPDATE contrato SET contratoAnoFim = ?, contratoSalario = ? WHERE contratoPilotoID = ? AND contratoEquipeID = ? AND contratoAnoInicio = ?;";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(contrato.getAnoFim().equals("")){
                ps.setInt(1, -1);
            }else{
                ps.setInt(1, Integer.parseInt(contrato.getAnoFim()));
            }
            if(contrato.getSalario().equals("")){
                ps.setInt(2, -1);
            }else{
                ps.setInt(2, Integer.parseInt(contrato.getSalario().replace("US$ ", "").replace("K", "")));
            }
            ps.setInt(3, IDGetter.getPilotoID(pilotoNome, pilotoSobrenome));
            ps.setInt(4, IDGetter.getEquipeID(contrato.getEquipe()));
            ps.setInt(5, Integer.parseInt(contrato.getAnoInicio()));
            
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            System.out.println("Error updatePiloto");
        }
    }

    public static void deletePilotoRegistro(String nome, String sobrenome) {
        String sql = "DELETE FROM pilotoRegistro WHERE pilotoRegistroNome = ? AND pilotoRegistroSobrenome = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deletePilotoRegistro");
        }
    }

    public static void deletePiloto(pilotoMainTableModel piloto){

        deletePilotoRegistro(piloto.getNome(), piloto.getSobrenome());

        String sql = "DELETE FROM piloto WHERE pilotoNome = ? AND pilotoSobrenome = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, piloto.getNome());
            ps.setString(2, piloto.getSobrenome());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println(throwables);
            System.out.println("Error deletePiloto");
        }

    }

    public static void deleteContrato(pilotoContratoModel contrato, String pilotoNome, String pilotoSobrenome){

        String sql = "DELETE FROM contrato WHERE contratoPilotoID  = ? AND contratoEquipeID = ? AND contratoAnoInicio = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getPilotoID(pilotoNome, pilotoSobrenome));
            ps.setInt(2, IDGetter.getEquipeID(contrato.getEquipe()));
            ps.setInt(3, Integer.parseInt(contrato.getAnoInicio()));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println("Error deleteContrato");
        }
    }

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
