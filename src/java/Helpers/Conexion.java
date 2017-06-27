package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexion {
     private Connection con;
    private static ResourceBundle rb=ResourceBundle.getBundle("jdbc");
   
    public Conexion(){
        String driver = rb.getString("driver");

        //Enchufar el driver
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            System.out.println("Error no se puede cargar el driver..."
                   +e.getMessage());
        }

        //Para establecer conexion
        try{
            String usuario = rb.getString("usr");
            String pass = rb.getString("pwd");
            String url = rb.getString("url");
            con=DriverManager.getConnection(url,usuario,pass);
        }catch(SQLException e){
            System.out.println("Error al conectar: "+e.getMessage());
        }
    }
    
    public Connection getConexion(){
        return con;
    }
    
    public void cerrarConexion(Connection con){
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexión "+e.getMessage());
        }
    }
}
