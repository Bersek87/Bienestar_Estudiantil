package Datos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ClsCurso {
   public int idcurso;
   public String nombrecurso;
   public int idprofesor;
   
   
   /**Constructor*/
   public ClsCurso(){
   }
  
/**Datos - Metodo para registrar persona*/
public boolean RegistrarCurso(){
     boolean respuesta=true;
    try {
          this.idcurso=GenerarIDCurso();
          operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
          respuesta=consulta.InsercionBase("insert into curso values ( '" + this.idcurso +"','"+this.nombrecurso +"','"+this.idprofesor+"')"); //     
          consulta.getStmt().close(); 
        } catch (SQLException ex) {
               System.out.println(ex);
        }
    return respuesta;
}

/**Datos - Metodo para actualizar persona*/
public boolean ActualizarCurso(){
     boolean respuesta=true;
    try {

          operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
          //UPDATE persona set apellidos='',nombres='',fechanacimiento='12/12/2010',sexo='',estadocivil='' where idpersona=1;
          respuesta=consulta.ActualizacionBase("UPDATE curso set Nombrecurso='" + this.nombrecurso+"',Idprofesor='"+this.idprofesor+"' where  IdCurso="+this.idcurso+"");    
          consulta.getStmt().close(); 
        } catch (SQLException ex) {
               System.out.println(ex);
        }
    return respuesta;
}

/**Datos - Metodo para Eliminar persona*/
public boolean EliminarCurso(int idCurso){
     boolean respuesta=true;
    try {

          operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
          //delete from persona where idpersona=13;
          respuesta=consulta.ActualizacionBase("delete from curso where Idcurso="+idCurso+"");    
          consulta.getStmt().close(); 
        } catch (SQLException ex) {
               System.out.println(ex);
        }
    return respuesta;
}


   /**Datos - Genera la secuancia de ID de pérsona*/
   public static int GenerarIDCurso(){
    int IDGenerado=0;
    
    try {
            operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
            ResultSet resultado=consulta.ConsultaBase("select max(Idcurso) as id from curso"); //
                        
            //Recorremos los resultados y se imprime campo por campo
            while (resultado.next()){
                //Concatenamos el texto
                System.out.println(resultado.getInt("id"));
                IDGenerado=resultado.getInt("id");
            }
            //Cierra la declaración que sirve para pasar la consulta
            consulta.getStmt().close(); 
            
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    
    return IDGenerado+1;
   }
   
   /**Datos-Metodo para Listar Personas*/
   //select * from persona where apellidos ||' ' || nombres like 'Diaz Perez Jo%'
   public TableModel ListarCurso(){
        DefaultTableModel modelo = new DefaultTableModel();
        try {
             //Para ejecutar la consulta
             operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
             ResultSet resultado=consulta.ConsultaBase("select c.Idcurso,c.Nombrecurso,p.Idprofesor,p.Nombres,p.Apellidos from curso c,profesor p where c.Idprofesor=p.Idprofesor"); //
                                    
             //Obteniendo la informacion de las columnas que estan siendo consultadas
             ResultSetMetaData Columnas = resultado.getMetaData();
             //La cantidad de columnas que tiene la consulta
             int cantidadColumnas = Columnas.getColumnCount();
             
             //Establecer como cabezeras el nombre de las columnas
             for (int i = 1; i <= cantidadColumnas; i++) {
                    modelo.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
             }
             //Extrae las filas del resultSet y los para al modelo
                while (resultado.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=resultado.getObject(i+1);
                        }
                 modelo.addRow(fila); //agregamos al modelo la fila extraida.
                }
                consulta.getStmt().close(); //Cierra el preparador  de sentencias SQL
                return modelo; 
        }
        //
        catch (SQLException ex) {
             System.out.println(ex);
        }
    return modelo;
  }
   
    /**Datos-Metodo para Buscar Personas*/
   public TableModel BusquedaCurso(String Nombrecurso){
        DefaultTableModel modelo = new DefaultTableModel();
        try {
             //Para ejecutar la consulta
               //select * from persona where apellidos ||' ' || nombres like 'Diaz Perez Jo%'
             operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
             ResultSet resultado=consulta.ConsultaBase("select c.Idcurso,c.Nombrecurso,p.Idprofesor,p.Nombres,p.Apellidos from curso c,profesor p where c.Idprofesor=p.Idprofesor and Nombrecurso like '" +Nombrecurso +"%'"); 
                                    
             //Obteniendo la informacion de las columnas que estan siendo consultadas
             ResultSetMetaData Columnas = resultado.getMetaData();
             //La cantidad de columnas que tiene la consulta
             int cantidadColumnas = Columnas.getColumnCount();
             
             //Establecer como cabezeras el nombre de las columnas
             for (int i = 1; i <= cantidadColumnas; i++) {
                    modelo.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
             }
             //Extrae las filas del resultSet y los para al modelo
                while (resultado.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=resultado.getObject(i+1);
                        }
                 modelo.addRow(fila); //agregamos al modelo la fila extraida.
                }
                consulta.getStmt().close(); //Cierra el preparador  de sentencias SQL
                return modelo; 
        }
        //
        catch (SQLException ex) {
             System.out.println(ex);
        }
    return modelo;
  }
 

    /**Datos-Metodo para Buscar Personas*/
   public ComboBoxModel ListarNombrecurso(){
        //DefaultTableModel modelo = new DefaultTableModel();
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        try {
             //Para ejecutar la consulta
             operaciones consulta=new operaciones(); //crea un objeto de la clase consulta
             ResultSet resultado=consulta.ConsultaBase("select * from curso"); 
                                    
             //Obteniendo la informacion de las columnas que estan siendo consultadas
             ResultSetMetaData Columnas = resultado.getMetaData();
             //La cantidad de columnas que tiene la consulta
             int cantidadColumnas = Columnas.getColumnCount();
             
             //Establecer como cabezeras el nombre de las columnas
//             for (int i = 1; i <= cantidadColumnas; i++) {
//                    modelo.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
//             }
             //Extrae las filas del resultSet y los para al modelo
                while (resultado.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=resultado.getObject(i+1);
                        }
                 modelo.addElement(fila); //agregamos al modelo el item extraida.
                }
                consulta.getStmt().close(); //Cierra el preparador  de sentencias SQL
                return modelo; 
        }
        //
        catch (SQLException ex) {
             System.out.println(ex);
        }
    return modelo;
  }   
   
   
}

