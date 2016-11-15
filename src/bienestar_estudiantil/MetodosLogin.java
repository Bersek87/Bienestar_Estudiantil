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
import Datos.conexion;
import static bienestar_estudiantil.Denuncias.cb_tipo_denuncia_ge;
import static bienestar_estudiantil.Denuncias.txt_denunciado;
import static bienestar_estudiantil.Denuncias.txt_denunciante;
import bienestar_estudiantil.Login;
import com.sun.istack.internal.Pool;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class MetodosLogin {
    



public int validar_usuario(String user,  String pass){

    String usuario = user;
    String clave = pass;
    String nombre="Invitado";
     int resultado=0;
    
    String SSQL="SELECT * FROM usuario WHERE idusuario='"+usuario+"' AND passwordd=sha1('"+clave+"')";

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

public int registrar_usuario(int user, String nombre, String pass){

    int usuario = user;
    String clave = pass;
    String nombre_usu=nombre;
     int resultado=0;
    
   

    
    try {
        Connection conect = null;
PreparedStatement ps = null;
        conect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
        conect.setAutoCommit(false);
        Statement st = conect.createStatement();
         String SSQL="insert into usuario (idUsuario,nombre_usuario, passwordd) values (?,?,?)";
         ps = conect.prepareStatement(SSQL);
 
 ps.setInt(1, usuario);
 ps.setString(2, nombre_usu);
 ps.setString(3, "sha1("+clave+")");
 
 ps.executeUpdate();
 conect.commit();
resultado=1;
            

       

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);

    }
return resultado;

}

String nombre;

}
