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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;

public class ClaseObjetoParaComboBox 
{
  private String nombreDeEjemplo;
  private int codigo;
  public MySQL db;
  
    public ClaseObjetoParaComboBox(int codigo, String nombreDeEjemplo)
      {
        this.codigo=codigo;
        this.nombreDeEjemplo=nombreDeEjemplo;
      }

    ClaseObjetoParaComboBox(String area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getNombreDeEjemplo() {
        return nombreDeEjemplo;
    }

    public void setNombreDeEjemplo(String nombreDeEjemplo) {
        this.nombreDeEjemplo = nombreDeEjemplo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
  @Override
    public String toString()
    {
     return nombreDeEjemplo;
    
    }
private DefaultComboBoxModel modeloCombo;
 
/* Establecemos establecemos en una variable llamar a la base de datos */


private String t; 

    /**
     *
     * @param tabla
     */
 public ClaseObjetoParaComboBox() {
 
/* Ahora, dentro de nuestra clase, llamamos a nuestro modelo de esta forma */
modeloCombo = new DefaultComboBoxModel(new String [] {});
t="Area"; 
initComponents();
MySQL n= new MySQL(); 
/* Con llenaComboBox();  vamos a ejecutar nuestro código que llenará nuestro ComboBox */
      try {
          llenaComboBox(t);
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(ClaseObjetoParaComboBox.class.getName()).log(Level.SEVERE, null, ex);
      }
 
 }
 
private void llenaComboBox(String tabla) throws ClassNotFoundException{
 
try {
 
/* Realizamos la consulta a la base de datos*/
 
String sql = "SELECT * FROM "+ tabla;
 
/* Se prepara la consulta */
Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal"); 
PreparedStatement verUsuarios = con.prepareStatement(sql);
 
/* Y se ejecuta en la siguiente línea */
 
ResultSet ver = verUsuarios.executeQuery();
 
/* while recorremos el resultado generado por la consulta */
 
while(ver.next()){
 
/* fijate como con nuestro modelo y su método addElement vamos a agregar cada resultado a nuestro ComboBox, en lo personal concatene el resultado */
 
modeloCombo.addElement(ver.getString(1)+" "+ver.getString(2) + " "+ver.getString(3));
 
     }
 
 } catch (SQLException ex) {Logger.getLogger(ComboBox.class.getName()).log(Level.SEVERE, null, ex);
 
 }
 
  
}

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}