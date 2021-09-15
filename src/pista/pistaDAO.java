package pista;

import javafx.beans.property.SimpleStringProperty;
import sample.utils.ConnectPostgre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class pistaDAO {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static void updatePista(pistaMainTableModel pistaAnterior, pistaMainTableModel pistaAtual){

        String sql = "UPDATE pista SET nomepista = '" + pistaAtual.getNome() + "', paispista = '" + pistaAtual.getPais() + "', cidadepista = '" + pistaAtual.getCidade() + "' WHERE nomepista = '" + pistaAnterior.getNome() +"' AND" +
                "paispista = '" + pistaAnterior.getPais() + "' AND cidadepista = '" + pistaAnterior.getCidade() + "';";

        try{
            Statement declaracao = con.createStatement();
            declaracao.executeQuery(sql);

        }catch(SQLException e){
            System.out.println("Error updatePista");
        }
    }

    public static void updateTracado(pistaTracadoModel tracado){

        String sql = "UPDATE tracado SET anoAlteracaoTracado = '" + tracado.getAnoAlteracao() + "', distanciaTracado = '" + tracado.getDistancia() + "', numeroVoltasTracado = '" + tracado.getNumeroVoltas() + "'";

        try{
            Statement declaracao = con.createStatement();
            declaracao.executeQuery(sql);

        }catch(SQLException e){
            System.out.println("Error updateTracado");
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
