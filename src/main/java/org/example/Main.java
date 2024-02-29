package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static  java.sql.Connection con;
    public static void main(String[] args) throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network";
        con = java.sql.DriverManager.getConnection( host );
        insertUser();
    }
    private static void crearTabla() throws SQLException{
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE T2 (c1 VARCHAR(50))");
        st.close();
    }
    private static void leerUsuarios() throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
        while (rs.next()){
            System.out.println("ID: " + rs.getInt(1));
        }
    }
    private static void insertUser(){
        Statement st = null;
        String sql = "INSERT INTO usuarios (nombre, apellidos) VALUES ('Janet', 'Espinosa')";
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println("Se ha producido un error al insertar el usuario. Mensaje: " + e.getMessage());
        }finally {
            try {
                if (st !=null) st.close();
            }catch (java.sql.SQLException ex){
                System.out.println("Se ha producido un error: " + ex.getMessage());
            }
        }
    }

}
