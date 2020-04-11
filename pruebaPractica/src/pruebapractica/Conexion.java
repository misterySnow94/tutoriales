package pruebapractica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
   private static Connection cnx = null;
   
  public Connection connectDatabase(String host, String port, String database,
        String user, String password) {
    String url = null;
    try {
        // We register the MySQL driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }
       url = "jdbc:mysql://localhost:3306/practica";
       Properties info = new Properties(); 
       info.put("user", "root"); 
       info.put("password", "");
        // Database connect
        // Conectamos con la base de datos
        cnx = DriverManager.getConnection(url, info);
        boolean valid = cnx.isValid(50000);
        System.out.println(valid ? "TEST OK" : "TEST FAIL");
        return cnx;
    } catch (java.sql.SQLException sqle) {
        System.out.println("Error al conectar con la base de datos de MySQL (" + url + "): " + sqle);
    }
    return cnx;
  }
  
   public static void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}