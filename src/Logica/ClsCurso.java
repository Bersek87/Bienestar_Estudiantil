package Logica;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ClsCurso {
   public int idcurso;
   public String nombrecurso;
   public int idprofesor;
   //Constructor
   public ClsCurso(){
   }
   
   /**Lógica-Método para Registrar Persona*/
public boolean RegistrarCurso(){
     boolean respuesta=true;
     
    try {
         Datos.ClsCurso curso=new Datos.ClsCurso();
         curso.idcurso=this.idcurso;
         curso.nombrecurso=this.nombrecurso;
         curso.idprofesor=this.idprofesor;
         respuesta=curso.RegistrarCurso(); //ejecuta el método que registra persona
                 
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}
   /**Lógica-Método para Actualizar Persona*/
public boolean ActualizarCurso(){
     boolean respuesta=true;
     
    try {
         Datos.ClsCurso curso=new Datos.ClsCurso();
         curso.idcurso=this.idcurso;
         curso.nombrecurso=this.nombrecurso;
         curso.idprofesor=this.idprofesor;
         
        respuesta=curso.ActualizarCurso(); //ejecuta el método que actualiza curso
                 
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}

public boolean EliminarCurso(int idcurso){
     boolean respuesta=true;
     
    try {
         Datos.ClsCurso curso=new Datos.ClsCurso();
         respuesta=curso.EliminarCurso(idcurso); //ejecuta el método que elimina persona         
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}

/** Lógica-Convierte fecha de String a Date*/
    public Date ConvertirFecha(String Fecha){
    //System.out.println(Fecha);
    Date fechaDevolver=null;
    SimpleDateFormat FormatoFecha= new SimpleDateFormat("dd/MM/yyyy");
        try {
            fechaDevolver=(Date)FormatoFecha.parse(Fecha);
            //System.out.println(fechaDevolver);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    return fechaDevolver;
}
     /**Lógica- Listar Personas*/
       public TableModel ListarCursos(){
        TableModel modelo = new DefaultTableModel();

        try {
                       Datos.ClsCurso curso=new Datos.ClsCurso();
                       modelo=curso.ListarCurso();
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }

      /**Lógica- Busca Personas*/
       public TableModel BusquedaCursos(String Nombrecurso){
        TableModel modelo = new DefaultTableModel();

        try {
                       Datos.ClsCurso curso=new Datos.ClsCurso();
                       modelo=curso.BusquedaCurso(Nombrecurso);
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }
       
       
       
       
       
       
       public ComboBoxModel ListarNombrecurso(){
                  ComboBoxModel modelo = new DefaultComboBoxModel();

        try {
                       Datos.ClsCurso curso=new Datos.ClsCurso();
                       modelo=curso.ListarNombrecurso();
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }
}
