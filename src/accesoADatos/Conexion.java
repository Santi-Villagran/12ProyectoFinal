/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

/**
 *
 * @author santi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author santi
 */
public class Conexion {
    
    private static final String URL= "jdbc:mariadb://localhost:3306/";
    private static final String BD= "paquete_turistico";
    private static final String USER="root";
    private static String CONT="";
    private static Conexion connection;
    //private Object DriveManager;

    private Conexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Clase Conexion: Error al cargar Driver");
        }
    }
   
    public static Connection getConexion() {
        Connection aux=null;

        if (connection == null) {
            connection=new Conexion();
        }    
            try {
                //Class.forName("org.mariadb.jdbc.Driver");//.newInstance();
                aux=DriverManager.getConnection(URL+BD+"?useLegacyDatetimeCode=false&serverTimezone=UTC"
                        +"&user="+ USER+"&password="+ CONT);
                //JOptionPane.showMessageDialog(null,"hola");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error de conexion "+ ex.getMessage());
            }
        
        return aux;
    }
    
}
