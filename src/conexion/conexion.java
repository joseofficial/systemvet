/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.StatementEvent;

/**
 *
 * @author ALUMNO
 */
public class conexion {

    static Connection conn = null;
    static Statement st = null;

    static String bd = "facturacion";
    static String usuario = "root";
    static String clave = "";
    static String url = "jdbc:mysql://localhost/" + bd;

    public static Connection Enlace(Connection conn) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
            System.out.print("Conexion exitosa");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error, Clase no Encontrada");
        }
        return conn;
    }

    public static Statement sta(Statement st) throws SQLException {
        conn = Enlace(conn);
        st = conn.createStatement();
        return st;
    }
}
