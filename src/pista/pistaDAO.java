package pista;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.*;
import java.util.ArrayList;

public final class pistaDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

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
        /*
        String sql = "DELETE FROM pista WHERE nomepista = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pista.getNome());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            System.out.println("Error deletePista");
        }

         */
    }

    public static void updateTracado(pistaTracadoModel tracado){


    }

    public static void idPistaRegistro(String nomePista){
        /*
        String sql = "SELECT pistaID FROM pista WHERE nomePista = '"+ nomePista  + "'";

        try{
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){

                int nome = new SimpleStringProperty(resultado.getString("nomepista"));

                pistaMainTableModel pista = new pistaMainTableModel(nome, pais, cidade);

                pistas.add(pista);

            }

        }catch(SQLException e){
            System.out.println("Error readListaPistas");
        }
        return pistas;

         */
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
