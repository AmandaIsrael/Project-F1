package pista;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import sample.IDGetter;

public final class pistaDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();


    public static int getIDPistaRegistro(){
        String sql = "SELECT MAX(pistaID) as id FROM pistaregistro;";
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
            System.out.println("Erro ao pegar ID em pistaregistro!");
        }
        return id;
    }

    public static void inserirPistaRegistro(String nomePista){
        String sql = "INSERT INTO pistaRegistro (pistaID, registroNomePista) VALUES (?,?);";
        int id = getIDPistaRegistro() + 1;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nomePista);

            ps.executeUpdate();

        }catch (SQLException throwables) {
            System.out.println("Erro ao inserir PistaRegistro!");
        }
    }

    public static void inserirPista(String nome, String pais, String cidade){
        String sql = "INSERT INTO pista (nomePista, paisPista, cidadePista) VALUES (?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, pais);
            ps.setString(3, cidade);

            ps.executeUpdate();

            inserirPistaRegistro(nome);

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Pista!");
        }
    }


    public static void updatePista(pistaMainTableModel pista){

        String sql = "UPDATE pista SET paispista = ?, cidadepista = ? WHERE nomepista = ?;";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pista.getPais());
            ps.setString(2, pista.getCidade());
            ps.setString(3, pista.getNome());

            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error updatePista");
        }
    }

    public static void deletePista(pistaMainTableModel pista){

        deletePistaRegistro(pista.getNome());

        String sql = "DELETE FROM pista WHERE nomepista = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pista.getNome());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println("Error deletePista");
        }

    }

    public static void deletePistaRegistro(String nomePista) {
        String sql = "DELETE FROM pistaRegistro WHERE registroNomePista = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nomePista);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Error deletePistaRegistro");
        }
    }

    public static void inserirTracado(int anoAlteracao, int distancia, int numeroVoltas, String nome){
        String sql = "INSERT INTO tracado (anoAlteracaoTracado, TracadoPistaID, distanciaTracado, numeroVoltasTracado) VALUES (?,?,?,?);";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, anoAlteracao);
            ps.setInt(2, IDGetter.getPistaID(nome));
            ps.setInt(3, distancia);
            ps.setInt(4, numeroVoltas);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        }catch (SQLException throwables) {
            String erro = throwables.toString();
            JOptionPane.showMessageDialog(null, erro.replace("org.postgresql.util.PSQLException: ERROR:", "ERROR: "));
            System.out.println("Erro ao inserir Tracado!");
        }
    }

    public static void updateTracado(pistaTracadoModel tracado, String nomePista){

        String sql = "UPDATE tracado SET distanciaTracado = ?, numeroVoltasTracado = ? WHERE anoAlteracaoTracado = ? AND TracadoPistaID = ?;";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            if(tracado.getDistancia().equals("")){
                ps.setInt(1, 0);
            }else{
                ps.setInt(1, Integer.parseInt(tracado.getDistancia()));
            }
            if(tracado.getNumeroVoltas().equals("")){
                ps.setInt(2, 0);
            }else{
                ps.setInt(2, Integer.parseInt(tracado.getNumeroVoltas()));
            }
            if(tracado.getAnoAlteracao().equals("")){
                ps.setInt(3, 0);
            }else{
                ps.setInt(3, Integer.parseInt(tracado.getAnoAlteracao()));
            }
            ps.setInt(4, IDGetter.getPistaID(nomePista));
            ps.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error updateTracado");
        }
    }

    public static void deleteTracado(pistaTracadoModel tracado, String nomePista){

        String sql = "DELETE FROM tracado WHERE anoAlteracaoTracado  = ? AND TracadoPistaID = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tracado.getAnoAlteracao()));
            ps.setInt(2, IDGetter.getPistaID(nomePista));

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println("Error deleteTracado");
        }
    }


    public static ArrayList<pistaMainTableModel> readListaPistas(String nomePista){

        ArrayList<pistaMainTableModel> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista WHERE nomePista = '"+ nomePista + "'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){

                SimpleStringProperty nome = new SimpleStringProperty(resultado.getString("nomepista"));
                SimpleStringProperty pais = new SimpleStringProperty(resultado.getString("paispista"));
                SimpleStringProperty cidade = new SimpleStringProperty(resultado.getString("cidadepista"));

                pistaMainTableModel pista = new pistaMainTableModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error readListaPistas");
        }
        return pistas;
    }

    public static ArrayList<pistaTracadoModel> readListaTracados(String nomePista){

        ArrayList<pistaTracadoModel> tracados = new ArrayList<>();
        String sql = "SELECT anoAlteracaoTracado, distanciaTracado, numeroVoltasTracado FROM tracado, pistaRegistro WHERE pistaID = tracadoPistaID AND registronomepista = '" + nomePista + "'";

        try{

            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                SimpleStringProperty anoAlteracao = new SimpleStringProperty(resultado.getString("anoAlteracaoTracado"));
                SimpleStringProperty distancia = new SimpleStringProperty(resultado.getString("distanciaTracado"));
                SimpleStringProperty numeroVoltas = new SimpleStringProperty(resultado.getString("numeroVoltasTracado"));

                pistaTracadoModel tracado = new pistaTracadoModel(anoAlteracao, distancia, numeroVoltas);
                tracados.add(tracado);


            }

        }catch(SQLException e){
            System.out.println("Error readListaTracados");
        }
        return tracados;
    }

}
