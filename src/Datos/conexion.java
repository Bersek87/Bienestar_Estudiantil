
package Datos;
import java.sql.*;
import javax.swing.JOptionPane;
public class conexion {
    
    //conexion con php myadmin
    private String driver="com.mysql.jdbc.Driver";
    private String connectString="jdbc:mysql://localhost:3306/bienestar_estudiantil";
    private String user="root";
    private String password="liberal";
    public Connection con;
        
    public conexion(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user , password);
            
            
        }catch ( Exception e ){
            System.out.println("error: no se pudo conectar a la base de datos: "+e.getMessage());
        }
    }
    
}
