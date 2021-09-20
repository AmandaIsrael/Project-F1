package equipe;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import sample.IDGetter;
import java.sql.Date;
import javax.swing.*;

public class equipeDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();
    
    public static int getIDEquipeRegistro(){
        String sql = "SELECT MAX(equipeID) as id FROM equiperegistro;";
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
            System.out.println("Erro ao pegar ID em equiperegistro!");
        }
        return id;
    }

    public static void inserirEquipeRegistro(String nome){
        String sql = "INSERT INTO equiperegistro (equipeID,	equipeRegistroNome) VALUES (?,?);";
        int id = getIDEquipeRegistro() + 1;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nome);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            System.out.println("Erro ao inserir EquipeRegistro!");
        }

    }

    public static void inserirEquipeTable(String nome, String cidade, String nacionalidade,String motorAtual, String anoInicioMotorAtual){
        String sql = "INSERT INTO Equipe (equipeNome,equipeCidade,equipeNacionalidade,equipeMotorAtual,equipeAnoInicioMotorAtual) VALUES (?,?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cidade);
            ps.setString(3, nacionalidade);
            ps.setString(4, motorAtual);
            ps.setInt(5, Integer.parseInt(anoInicioMotorAtual));

            ps.executeUpdate();

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Equipe!");
        }
    }

    public static void inserirFornecedorMotorAtual(String motorAtual, String nacionalidadeMotorAtual){
        String sql = "INSERT INTO MotorEquipe (motorAtual,nacionalidadeMotorAtual) VALUES (?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, motorAtual);
            ps.setString(2, nacionalidadeMotorAtual);

            ps.executeUpdate();

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir fornecedorMotor!");
        }
    }
    
    public static void inserirEquipe(String nome, String cidade, String nacionalidade, String motorAtual, String anoInicioMotorAtual, String nacionalidadeMotorAtual){
        inserirFornecedorMotorAtual(motorAtual, nacionalidadeMotorAtual);
        inserirEquipeTable(nome, cidade, nacionalidade, motorAtual, anoInicioMotorAtual);
        inserirEquipeRegistro(nome);
    }

    public static void inserirNomeAntigo(String nomeAntigo, String equipe, String anoInicio, String anoFim){
        String sql = "INSERT INTO NomeAntigo (nomeAntigo,nomeAntigoEquipeID,nomeAntigoAnoInicio,nomeAntigoAnoFim) VALUES (?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nomeAntigo);
            ps.setInt(2, IDGetter.getEquipeID(equipe));
            ps.setInt(3, Integer.parseInt(anoInicio));
            ps.setInt(4, Integer.parseInt(anoFim));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Nome Antigo!");
        }
    }

    public static void inserirFornecedorMotor(String nomeMotor, String nacionalidadeMotor){
        String sql = "INSERT INTO FornecedorMotor (nomeFornecedor,nacionalidadeFornecedor) VALUES (?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nomeMotor);
            ps.setString(2, nacionalidadeMotor);

            ps.executeUpdate();


        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir fornecedorMotor!");
        }
    }

    public static void inserirUtilizouMotor(String nomeMotor, String nomeEquipe, String anoInicio, String anoFim){
        String sql = "INSERT INTO UtilizouMotor (motorNome,motorEquipe,motorAnoInicio,motorAnoFim) VALUES (?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nomeMotor);
            ps.setInt(2, IDGetter.getEquipeID(nomeEquipe));
            ps.setInt(3, Integer.parseInt(anoInicio));
            ps.setInt(4, Integer.parseInt(anoFim));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir utilizouMotor!");
        }
    }

    public static void inserirMotor(String nomeMotor, String nomeEquipe, String nacionalidadeMotor, String anoInicio, String anoFim){
        inserirFornecedorMotor(nomeMotor, nacionalidadeMotor);
        inserirUtilizouMotor(nomeMotor, nomeEquipe, anoInicio, anoFim);
    }

    public static int getIDLiderRegistro(){
        String sql = "SELECT MAX(LiderID) as id FROM Liderregistro;";
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
            System.out.println("Erro ao pegar ID em Liderregistro!");
        }
        return id;
    }

    public static void inserirLiderTable(String liderNome, String liderSobrenome, String liderNacionalidade, String liderCidade, String liderNascimento){
        String sql = "INSERT INTO Lider (liderNome,liderSobrenome,liderNacionalidade,liderCidade ,liderNascimento) VALUES (?,?,?,?,?);";
        try{

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, liderNome);
            ps.setString(2, liderSobrenome);
            ps.setString(3, liderNacionalidade);
            ps.setString(4, liderCidade);
            ps.setDate(5, Date.valueOf(liderNascimento));

            ps.executeUpdate();

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Lider!");
        }
    }

    public static void inserirLiderRegistro(String liderNome, String liderSobrenome){
        String sql = "INSERT INTO LiderRegistro (liderID,liderRegistroNome,liderRegistroSobrenome) VALUES (?,?,?);";
        int id = getIDLiderRegistro() + 1;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, liderNome);
            ps.setString(3, liderSobrenome);

            ps.executeUpdate();

        }catch (SQLException throwables) {
            System.out.println("Erro ao inserir LiderRegistro!");
        }
    }

    public static void inserirLiderada(String liderNome, String liderSobrenome, String cargo, String lideradaAnoInicio, String lideradaAnoFim, String equipeNome){
        
        String sql = "INSERT INTO Liderada (lideradaLiderID,lideradaEquipeID,cargo,lideradaAnoInicio,lideradaAnoFim) VALUES (?,?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getLiderID(liderNome, liderSobrenome));
            ps.setInt(2, IDGetter.getEquipeID(equipeNome));
            ps.setString(3, cargo);
            ps.setInt(4, Integer.parseInt(lideradaAnoInicio));
            ps.setInt(5, Integer.parseInt(lideradaAnoFim));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Liderada!");
        }

    }

    public static void inserirLider(String liderNome, String liderSobrenome, String liderNacionalidade, String liderCidade, String liderNascimento, String cargo, String lideradaAnoInicio, String lideradaAnoFim, String equipeNome){
        inserirLiderTable(liderNome, liderSobrenome, liderNacionalidade, liderCidade, liderNascimento);
        inserirLiderRegistro(liderNome, liderSobrenome);
        inserirLiderada(liderNome, liderSobrenome, cargo, lideradaAnoInicio, lideradaAnoFim, equipeNome);
    }

    public static void updateEquipe(equipeMainTableModel equipe){
        String sql = "UPDATE equipe SET equipeCidade = ?, equipeNacionalidade = ? WHERE equipeNome = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipe.getCidade());
            ps.setString(2, equipe.getNacionalidade());
            ps.setString(3, equipe.getNome());

            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateEquipe");
        }
    }

    public static void updateNomeAntigo(equipeNomeAntigoModel nomeAntigo, String equipeNome){
        String sql = "UPDATE nomeAntigo SET nomeAntigoAnoInicio = ?, nomeAntigoAnoFim = ? WHERE nomeAntigoEquipeID = ? AND nomeAntigo = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(nomeAntigo.getAnoInicio().equals("")){
                ps.setInt(1, -1);
            }else{
                ps.setInt(1, Integer.parseInt(nomeAntigo.getAnoInicio()));
            }
            if(nomeAntigo.getAnoFim().equals("")){
                ps.setInt(2, -1);
            }else{
                ps.setInt(2, Integer.parseInt(nomeAntigo.getAnoFim()));
            }

            ps.setInt(3, IDGetter.getEquipeID(equipeNome));
            ps.setString(4, nomeAntigo.getNomeAntigo());

            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateEquipe");
        }
    }

    public static void updateEquipeMotor(String nacionalidade, String nomeFornecedor){
        String sql = "UPDATE MotorEquipe SET nacionalidadeMotorAtual = ? WHERE motorAtual = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nacionalidade);
            ps.setString(2, nomeFornecedor);

            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateEquipeMotor");
        }
    }

    public static void updateFornecedorMotor(String nacionalidade, String nomeFornecedor){
        String sql = "UPDATE fornecedorMotor SET nacionalidadeFornecedor = ? WHERE nomeFornecedor = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nacionalidade);
            ps.setString(2, nomeFornecedor);

            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateFornecedorMotor");
        }
    }

    public static void updateMotorAntigo(equipeMotoresModel motor, String equipeNome){
        String sql = "UPDATE utilizouMotor SET motorAnoFim = ? WHERE motorNome = ? AND motorEquipe = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);

            if(motor.getAnoFim().equals("")){
                ps.setInt(1, -1);
            }else{
                ps.setInt(1, Integer.parseInt(motor.getAnoFim()));
            }
            ps.setString(2, motor.getMotor());
            ps.setInt(3, IDGetter.getEquipeID(equipeNome));

            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateAntigo");
        }
    }

    public static void updateMotorAtual(equipeMotoresModel motor, String equipeNome){
        String sql = "UPDATE equipe SET equipeAnoInicioMotorAtual = ? WHERE equipeMotorAtual = ? AND equipeNome = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(motor.getAnoInicio().equals("")){
                ps.setInt(1, -1);
            }else{
                ps.setInt(1, Integer.parseInt(motor.getAnoInicio()));
            }
            ps.setString(2, motor.getMotor());
            ps.setString(3, equipeNome);

            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e.toString());
            System.out.println("Error updateMotorAtual");
        }
    }

    public static void updateMotor(equipeMotoresModel motor, String equipeNome){
        
        if(motor.getAnoFim()=="Atual"){
            updateMotorAtual(motor, equipeNome);
            updateEquipeMotor(motor.getNacionalidade(), motor.getMotor());
        }else{
            updateMotorAntigo(motor, equipeNome);
            updateFornecedorMotor(motor.getNacionalidade(), motor.getMotor());
        }
    }

    public static void updateLiderTable(equipeLiderModel lider){
        String sql = "UPDATE lider SET liderNacionalidade = ?, liderCidade  = ?, liderNascimento = ? WHERE liderNome = ? AND liderSobrenome = ?;";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lider.getNacionalidade());
            ps.setString(2, lider.getCidade());
            if(lider.getNascimento().equals("")){
                ps.setDate(3, Date.valueOf("1900-01-01"));
            }else{
                ps.setDate(3, Date.valueOf(lider.getNascimento()));
            }
            ps.setString(4, lider.getNome());
            ps.setString(5, lider.getSobrenome());
            
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            System.out.println("Error updateLiderTable");
        }
    }

    public static void updateLiderada(equipeLiderModel lider, String equipeNome){
        String sql = "UPDATE liderada SET cargo = ?, lideradaAnoInicio = ?, lideradaAnoFim = ? WHERE lideradaLiderID = ? AND lideradaEquipeID = ?;";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lider.getCargo());
            if(lider.getAnoInicio().equals("")){
                ps.setInt(2, -1);
            }else{
                ps.setInt(2, Integer.parseInt(lider.getAnoInicio()));
            }
            if(lider.getAnoFim().equals("")){
                ps.setNull(3, java.sql.Types.NULL);
            }else{
                ps.setInt(3, Integer.parseInt(lider.getAnoFim()));
            }
            ps.setInt(4, IDGetter.getLiderID(lider.getNome(), lider.getSobrenome()));
            ps.setInt(5, IDGetter.getEquipeID(equipeNome));
            
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            System.out.println("Error updateLiderada");
        }
    }

    public static void updateLider(equipeLiderModel lider, String equipeNome){
        updateLiderada(lider, equipeNome);
        updateLiderTable(lider);
    }   

    public static void deleteFornecedorMotor(equipeMotoresModel motor){
        String sql = "DELETE FROM fornecedorMotor WHERE nomeFornecedor = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, motor.getMotor());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            System.out.println("Error deleteFornecedorMotor");
        }
    }

    public static void deleteMotorTable(equipeMotoresModel motor, String nomeEquipe){
        String sql = "DELETE FROM utilizouMotor WHERE motorNome = ? AND motorAnoInicio = ? AND motorequipe = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, motor.getMotor());
            ps.setInt(2, Integer.parseInt(motor.getAnoInicio()));
            ps.setInt(3, IDGetter.getEquipeID(nomeEquipe));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteMotorTable");
        }
    }

    public static void deleteMotor(equipeMotoresModel motor, String nomeEquipe){
        if(motor.getAnoFim()!= "Atual"){
            deleteMotorTable(motor, nomeEquipe);
            deleteFornecedorMotor(motor);
        }
        else{
            JOptionPane.showMessageDialog(null, "ERRO: Não é possível remover o motor atual da equipe!");
        }
    }

    public static void deleteEquipeRegistro(equipeMainTableModel equipe){
        String sql = "DELETE FROM equipeRegistro WHERE equipeID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getEquipeID(equipe.getNome()));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteEquipeRegistro");
        }
    }

    public static String getMotorAtual(equipeMainTableModel equipe){
        String sql = "SELECT equipeMotorAtual FROM equipe WHERE equipeNome = '"+equipe.getNome()+"';";
        String motorAtual = "";
        try {
            Statement declaracao = con.createStatement();
            ResultSet ps = declaracao.executeQuery(sql);

            while (ps.next()) {
                motorAtual = ps.getString("equipeMotorAtual");
                return motorAtual;
            }

        }
        catch (SQLException throwables) {
            System.out.println(throwables);
            System.out.println("Erro ao pegar motor atual!");
        }
        return motorAtual;
    }

    public static void deleteMotorEquipe(equipeMainTableModel equipe){
        String motorAtual = getMotorAtual(equipe);
        
        String sql = "DELETE FROM motorEquipe WHERE motorAtual = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, motorAtual);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println(throwables.toString());
            System.out.println("Error deleteMotorEquipe");
        }
    }

    public static void deleteEquipeTable(equipeMainTableModel equipe){
        String sql = "DELETE FROM equipe WHERE equipeNome = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, equipe.getNome());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteEquipe");
        }
    }

    public static void deleteEquipe(equipeMainTableModel equipe){
        deleteEquipeRegistro(equipe);
        deleteEquipeTable(equipe);
        deleteMotorEquipe(equipe);
    }

    public static void deleteLiderTable(equipeLiderModel lider){
        String sql = "DELETE FROM lider WHERE liderNome = ? AND liderSobrenome = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lider.getNome());
            ps.setString(2, lider.getSobrenome());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteLiderTable");
        }
    }

    public static void deleteLiderRegistro(equipeLiderModel lider){
        String sql = "DELETE FROM liderRegistro WHERE liderID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getLiderID(lider.getNome(), lider.getSobrenome()));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteLiderRegistro");
        }
    }

    public static void deleteLiderada(equipeLiderModel lider, String equipeNome){
        String sql = "DELETE FROM liderada WHERE lideradaLiderID = ? AND lideradaEquipeID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IDGetter.getLiderID(lider.getNome(), lider.getSobrenome()));
            ps.setInt(2, IDGetter.getEquipeID(equipeNome));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteLiderada");
        }
    }

    public static void deleteLider(equipeLiderModel lider, String equipeNome){
        deleteLiderada(lider, equipeNome);
        deleteLiderRegistro(lider);
        deleteLiderTable(lider);
    }

    public static void deleteNomeAntigo(String nome){
        String sql = "DELETE FROM nomeAntigo WHERE nomeAntigo = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            System.out.println(erro);
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deleteNomeAntigo");
        }
    }
    
    public static ArrayList<equipeLiderModel> readListaLideres(String equipeNome){
        
        ArrayList<equipeLiderModel> lideres = new ArrayList<>();
        String sql = "SELECT liderNome, liderSobrenome, liderNacionalidade, liderCidade, liderNascimento, cargo, lideradaAnoInicio, COALESCE(lideradaAnoFim, -1) as lideradaAnoFim FROM lider, liderada, liderRegistro, equipeRegistro WHERE lideradaLiderID = liderID AND liderRegistroNome = liderNome AND liderRegistroSobrenome = liderSobrenome AND lideradaEquipeID=equipeID AND equipeRegistroNome= '"+equipeNome + "'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("liderNome"));
                SimpleStringProperty sobrenome = new SimpleStringProperty(resultado.getString("liderSobrenome"));
                SimpleStringProperty cargo = new SimpleStringProperty(resultado.getString("cargo"));
                SimpleStringProperty nascimento = new SimpleStringProperty(resultado.getString("liderNascimento"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("liderCidade"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("liderNacionalidade"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("lideradaAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("lideradaAnoFim"));

                if(anoFim.get().equals("-1")){
                    anoFim.set("Atual");
                }
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
        String sql = "(SELECT motorNome, nacionalidadeFornecedor, motorAnoInicio, motorAnoFim FROM utilizouMotor, fornecedorMotor, equiperegistro WHERE nomeFornecedor=motorNome AND motorEquipe=equipeID AND equipeRegistroNome='"+equipeNome+"') UNION (SELECT motorAtual, nacionalidadeMotorAtual, equipeAnoInicioMotorAtual, -1 FROM MotorEquipe, Equipe WHERE equipeMotorAtual=motorAtual AND equipeNome='"+equipeNome+"') ORDER BY motorAnoInicio";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty motorNome = new SimpleStringProperty(resultado.getString("motorNome"));
                SimpleStringProperty nacionalidade = new SimpleStringProperty(resultado.getString("nacionalidadeFornecedor"));
                SimpleStringProperty anoInicio = new SimpleStringProperty(resultado.getString("motorAnoInicio"));
                SimpleStringProperty anoFim = new SimpleStringProperty(resultado.getString("motorAnoFim"));

                if(anoFim.get().equals("-1")){
                    anoFim.set("Atual");
                }
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
