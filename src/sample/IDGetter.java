package sample;

import sample.utils.ConnectPostgre;

import java.sql.*;

public class IDGetter {
    private static Connection con = ConnectPostgre.ConnectDatabase();

    public static int getPistaID(String nomePista){
        String sql = "SELECT pistaID FROM PistaRegistro WHERE registroNomePista = '" + nomePista + "'";
        int id = 0;
        try {
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                id = resultado.getInt("pistaID");
            }
        }catch (SQLException throwables){
            System.out.println("Error pistaID");
        }
        return id;
    }

    public static int getPilotoID(String nome, String sobrenome){
        String sql = "SELECT pilotoID FROM PilotoRegistro WHERE pilotoRegistroNome = '" + nome + "' AND pilotoRegistroSobrenome = '"+sobrenome+"'";
        int id = 0;
        try {
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                id = resultado.getInt("pilotoID");
            }
        }catch (SQLException throwables){
            System.out.println("Error pilotoID");
        }
        return id;
    }

    public static int getEquipeID(String equipeNome){
        String sql = "SELECT equipeID FROM EquipeRegistro WHERE equipeRegistroNome = '" + equipeNome + "'";
        int id = 0;
        try {
            Statement declaracao = con.createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            while(resultado.next()){
                id = resultado.getInt("equipeID");
            }
        }catch (SQLException throwables){
            System.out.println("Error equipeID");
        }
        return id;
    }
}
