package Logica;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ClsNotas {
   public int idnota;
   public int idcurso;
   public int idalumno;
   public String unidad;
   public int promedio;
   //Constructor
   public ClsNotas(){
   }
   
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
   
   /**Lógica-Método para Registrar Persona*/
public boolean RegistrarNota(){
     boolean respuesta=true;
     
    try {
         Datos.ClsNotas nota=new Datos.ClsNotas();
         nota.idnota=this.idnota;
         nota.idcurso=this.idcurso;
         nota.idalumno=this.idalumno;
         nota.unidad=this.unidad;
         nota.promedio=this.promedio;
         respuesta=nota.RegistrarNota(); //ejecuta el método que registra persona
                 
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}
   /**Lógica-Método para Actualizar Persona*/
public boolean ActualizarNota(){
     boolean respuesta=true;
     
    try {
         Datos.ClsNotas nota=new Datos.ClsNotas();
         nota.idnota=this.idnota;
         nota.idcurso=this.idcurso;
         nota.idalumno=this.idalumno;
         nota.unidad=this.unidad;
         nota.promedio=this.promedio;
         
        respuesta=nota.ActualizarNota(); //ejecuta el método que actualiza curso
                 
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}

public boolean EliminarNota(int idnota){
     boolean respuesta=true;
     
    try {
         Datos.ClsNotas nota=new Datos.ClsNotas();
         respuesta=nota.EliminarNota(idnota); //ejecuta el método que elimina persona         
    }catch(Exception ex){
        System.out.println("Se ha presentado el siguiente Error: "+ex);
    }

    return respuesta;
}

/** Lógica-Convierte fecha de String a Date*/
   

     /**Lógica- Listar Personas*/
       public TableModel ListarNota(){
        TableModel modelo = new DefaultTableModel();

        try {
                       Datos.ClsNotas nota=new Datos.ClsNotas();
                       modelo=nota.ListarNota();
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }

      /**Lógica- Busca Personas*/
       public TableModel BusquedaNotas(String NombreNota){
        TableModel modelo = new DefaultTableModel();

        try {
                       Datos.ClsNotas nota=new Datos.ClsNotas();
                       modelo=nota.BusquedaNota(NombreNota);
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }
       
       public ComboBoxModel ListarUnidadNota(){
                  ComboBoxModel modelo = new DefaultComboBoxModel();

        try {
                       Datos.ClsNotas nota=new Datos.ClsNotas();
                       modelo=nota.ListarUnidadNota();
            
        } catch(Exception ex) {
             System.out.println(ex);        
        }
        return modelo;
       }
}
