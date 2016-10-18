/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bienestar_estudiantil;

/**
 *
 * @author Eden
 */
import bienestar_estudiantil.Login;
import com.sun.istack.internal.Pool;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class MetodosLogin {
    



public int validar_ingreso(String user, String pass){

    String usuario = user;
    String clave = pass;
    String nombre="Invitado";
     int resultado=0;
    
    String SSQL="SELECT * FROM usuario WHERE idusuario='"+usuario+"' AND password=sha1('"+clave+"')";

    Connection conect = null;

    try {

        conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
        Statement st = conect.createStatement();
        ResultSet rs = st.executeQuery(SSQL);

        if(rs.next()){
            nombre=rs.getString("nombre_usuario");
            resultado=1;
            

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

    }finally{


        try {

            conect.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "Error de desconexión", JOptionPane.ERROR_MESSAGE);

        }

    }

return resultado;

}

String nombre;

}
