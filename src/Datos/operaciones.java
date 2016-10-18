package Datos;
   import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class operaciones {
    private Statement stmt;

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
       
    public operaciones() throws SQLException{
    }
    
    public ResultSet ConsultaBase(String consulta) {
        ResultSet resultado=null;
        try {
            
            conexion conexion=new conexion();
            stmt = conexion.con.createStatement();
            resultado=stmt.executeQuery(consulta);
             
            
            return resultado;
            
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return resultado;
    }
    
    
        public boolean InsercionBase(String consulta) {
            boolean respuesta=true;
        try {
            
            conexion conexion=new conexion();
            stmt = conexion.con.createStatement();
            respuesta=stmt.execute(consulta);
            conexion.con.close();
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
        
         public boolean ActualizacionBase(String consulta) {
            boolean respuesta=true;
        try {
            
            conexion conexion=new conexion();
            stmt = conexion.con.createStatement();
            respuesta=stmt.execute(consulta);
            conexion.con.close();
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }       
        
}
