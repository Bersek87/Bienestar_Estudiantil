/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bienestar_estudiantil;


import Datos.conexion;
import Datos.operaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DropMode;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Eden
 */
public class Denuncias extends javax.swing.JFrame {

    /**
     * Creates new form Denuncias
     */
    public Denuncias() {
        initComponents();
        NoDenuncia();
        llenarTablaDenuncia();
        mostrarTipoDenuncia();
        setLocationRelativeTo(null);
                
    }
     public static String denunciante;
     public static String denunciadoN;
    
    /**
     *
     * 
     */
    public static void denunciante(String cuenta){
      denunciante=cuenta;
      txt_denunciante.setText(cuenta);
      }
     public static void denunciado(String cuenta){
      denunciadoN=cuenta;
      txt_denunciado.setText(cuenta);
      }
    public static String getDenunciante(){
    return denunciante;
    }
  
    public void llenarTablaDenuncia(){
        try {
            operaciones op = new operaciones();
            ResultSet rs = op.ConsultaBase("select * from denuncia");
            ResultSetMetaData Columnas = rs.getMetaData();
            DefaultTableModel denun=new DefaultTableModel();
            int cantidadColumnas = Columnas.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                    denun.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
             }
             //Extrae las filas del resultSet y los para al modelo
                while (rs.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=rs.getObject(i+1);
                        }
                 denun.addRow(fila); //agregamos al modelo la fila extraida.
                }
                
                tabla_denuncia.setModel(denun);
            
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void buscarParaTablaDenuncia(String noCuenta){
       if(CB_tipo_denuncia_con.getSelectedIndex()==0){ 
        try {
            operaciones op = new operaciones();
            
            
            ResultSet rs = op.ConsultaBase("SELECT noCuenta as 'Numero de Cuenta',concat(a.`nombreAlumno`,\" \", a.`apellidoAlumno`) as 'Nombre Completo', d.`motivoDenuncia` as 'Razones de la Denuncia',d.tipo_denuncia FROM Denuncia d, alumno a where d.denunciado like '%"+noCuenta+"%' or d.remitido like '%"+noCuenta+"%'");
            
            
            ResultSetMetaData Columnas = rs.getMetaData();
            
            DefaultTableModel denun=new DefaultTableModel();
            int cantidadColumnas = Columnas.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                    denun.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
             }
             //Extrae las filas del resultSet y los para al modelo
                while (rs.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=rs.getObject(i+1);
                        }
                 denun.addRow(fila); //agregamos al modelo la fila extraida.
                }
                
            tabla_denuncia.setModel(denun);
            
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else try {
            operaciones op = new operaciones();
            String cb = CB_tipo_denuncia_con.getSelectedItem().toString();
            String sql="SELECT noCuenta as 'Numero de Cuenta',concat(a.`nombreAlumno`,\" \", a.`apellidoAlumno`) as 'Nombre Completo', d.`motivoDenuncia` as 'Razones de la Denuncia',d.tipo_denuncia FROM Denuncia d, alumno a where d.denunciado like '%"+noCuenta+"%' or d.remitido like '%"+noCuenta+"%' HAVING d.tipo_denuncia in (\""+cb+"\")";
            ResultSet rs = op.ConsultaBase(sql);                  
            
            
            ResultSetMetaData Columnas = rs.getMetaData();
            
            DefaultTableModel denun=new DefaultTableModel();
            int cantidadColumnas = Columnas.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
                    denun.addColumn(Columnas.getColumnLabel(i)); //Agrega columnas al modelo con el titulo extraido de la metadata
             }
             //Extrae las filas del resultSet y los para al modelo
                while (rs.next()) {
                       Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                                 fila[i]=rs.getObject(i+1);
                        }
                 denun.addRow(fila); //agregamos al modelo la fila extraida.
                }
                
            tabla_denuncia.setModel(denun);
            
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
        
    
     private void mostrarDenuncias(String nombre) {
       Connection conn = null;     
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;     
       
       try {
             
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
              st = conn.createStatement();
              rs = st.executeQuery ("SELECT concat(a.`nombreAlumno`,\" \", a.`apellidoAlumno`) as 'Nombre Completo', d.`motivoDenuncia` as 'Razones de la Denuncia',d.tipo_denuncia FROM Denuncia d, alumno a where d.denunciado=a.`"+nombre+"` or d.remitido=`"+nombre+"`");
     
          while(rs.next()){
               String tmpStrObtenido = rs.getString("nombreTipoDenuncia");
               
               CB_tipo_denuncia_con.addItem(tmpStrObtenido);
               cb_tipo_denuncia_ge.addItem(tmpStrObtenido);
               
           }
          conn.close();
         } catch (Exception e) {
             System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
             e.printStackTrace();
             return;
         }
    }
    private void mostrarTipoDenuncia() {
       Connection conn = null;     
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;     
       
       try {
             
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
              st = conn.createStatement();
              rs = st.executeQuery ("SELECT nombreTipoDenuncia FROM TipoDenuncia");
     
          while(rs.next()){
               String tmpStrObtenido = rs.getString("nombreTipoDenuncia");
               CB_tipo_denuncia_con.addItem(tmpStrObtenido);
               cb_tipo_denuncia_ge.addItem(tmpStrObtenido);
               
           }
          conn.close();
         } catch (Exception e) {
             System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
             e.printStackTrace();
             return;
         }
    }
    public void NoDenuncia(){
         Date myDate = new Date();
         txt_fecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
         try {
            // TODO add your handling code here:
            Connection conn = null;
            String   sql = null;
            ResultSet rs = null;
            Statement  st = null;
            int contador=0;
            String texto=null;
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
            st = conn.createStatement();
            rs = st.executeQuery ("select count(*)+1 as 'numero' from denuncia;");
            while(rs.next()){
                //Aca le digo que muestre el valor en un JtextFiel
                if (rs.getString("numero")==null){
                    texto="1"; 
                    txt_no_denuncia.setText(texto);
              }
                else {
                    texto=rs.getString("numero");
                    txt_no_denuncia.setText(texto);
                                  
                }
                txt_no_denuncia.setText(texto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void generarFecha(){
      /*  String fecha = new SimpleDateFormat("dd-MM-yyyy").format(DateChooser.getDate());
        txt_denunciado.setText(fecha);
      */
    }
       int ultimoIndiceSeleccionado;
    public void elementoCambiado() {
                int index = cb_tipo_denuncia_ge.getSelectedIndex();
                if(ultimoIndiceSeleccionado != 0 && index == 0){
                        int opc = JOptionPane.showConfirmDialog(this, "¿Realmente desea Seleccionar este?",
                                "Cambio de tipo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
                       
                        if(opc == JOptionPane.NO_OPTION){
                                cb_tipo_denuncia_ge.setSelectedIndex(ultimoIndiceSeleccionado);
                        } else{
                                ultimoIndiceSeleccionado = index;
                        }
                } else{
                        ultimoIndiceSeleccionado = index;
                }
        }
    
    private void guardarDatos() throws ClassNotFoundException, SQLException{
    String insert = "INSERT INTO `bienestar_estudiantil`.`denuncia` (`noDenuncia`, `fechaDenuncia`, `tipo_denuncia`, `remitido`, `denunciado`, `motivoDenuncia`) VALUES (?,?,?,?,?,?)";
    PreparedStatement ps = null;
Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bienestar_estudiantil","root","liberal");
            conexion.setAutoCommit(false);
   
 
 ps = conexion.prepareStatement(insert);
 
 ps.setString(1, txt_no_denuncia.getText());
 ps.setString(2, txt_fecha.getText());
 ps.setString(3, cb_tipo_denuncia_ge.getSelectedItem().toString());
 ps.setString(4, txt_denunciante.getText());
 ps.setString(5, txt_denunciado.getText());
 ps.setString(6, contex_motivo.getText());
 ps.executeUpdate();
 conexion.commit();
 JOptionPane.showMessageDialog(null, "Denuncia guardada Exitosamente");
    
        limpiar();
        
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }

 
 
 
    
    }
    private void limpiar(){
    NoDenuncia();
    cb_tipo_denuncia_ge.setSelectedIndex(0);
    txt_denunciado.setText("");
    txt_denunciante.setText("");
    txt_no_denuncia.setText("");
    contex_motivo.setText("");
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        btn_cancel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_denunciante = new javax.swing.JTextField();
        txt_denunciado = new javax.swing.JTextField();
        cb_tipo_denuncia_ge = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        btn_select_denunciante = new javax.swing.JButton();
        btn_select_denunciado = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLFecha = new javax.swing.JLabel();
        txt_no_denuncia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        contex_motivo = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_denuncia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_id_denun = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_buscar_denun = new javax.swing.JButton();
        btn_nueva_consulta = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        CB_tipo_denuncia_con = new javax.swing.JComboBox();
        txt_nombre_denun = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();
        DChFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerrando(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrando(evt);
            }
        });

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jLabel3.setText("Denunciante");

        jLabel4.setText("Denunciado");

        txt_denunciante.setEditable(false);
        txt_denunciante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_denunciante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_denuncianteActionPerformed(evt);
            }
        });

        txt_denunciado.setEditable(false);
        txt_denunciado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_denunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_denunciadoActionPerformed(evt);
            }
        });

        cb_tipo_denuncia_ge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione el tipo" }));

        jLabel5.setText("Tipo de Denuncia");

        btn_select_denunciante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btn_select_denunciante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_select_denuncianteActionPerformed(evt);
            }
        });

        btn_select_denunciado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btn_select_denunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_select_denunciadoActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/new.png"))); // NOI18N
        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLFecha.setText("Fecha");

        txt_no_denuncia.setEditable(false);
        txt_no_denuncia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_no_denunciaActionPerformed(evt);
            }
        });
        txt_no_denuncia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_no_denunciaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_no_denunciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_no_denunciaKeyTyped(evt);
            }
        });

        jLabel6.setText("No de Denuncia");

        txt_fecha.setEditable(false);
        txt_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(contex_motivo);

        jLabel7.setText("Motivos de la Denucia");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/del.png"))); // NOI18N
        jButton6.setText("Cancelar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btn_cancelLayout = new javax.swing.GroupLayout(btn_cancel);
        btn_cancel.setLayout(btn_cancelLayout);
        btn_cancelLayout.setHorizontalGroup(
            btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cancelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_cancelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_cancelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_cancelLayout.createSequentialGroup()
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(btn_cancelLayout.createSequentialGroup()
                                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(btn_cancelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_no_denuncia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(btn_cancelLayout.createSequentialGroup()
                                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_denunciante, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, btn_cancelLayout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cb_tipo_denuncia_ge, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_select_denunciante, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLFecha))
                                .addGap(18, 18, 18)
                                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(btn_cancelLayout.createSequentialGroup()
                                        .addComponent(txt_denunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_select_denunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addGap(40, 40, 40))))
        );
        btn_cancelLayout.setVerticalGroup(
            btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_cancelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btn_cancelLayout.createSequentialGroup()
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_no_denuncia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(15, 15, 15)
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cb_tipo_denuncia_ge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_denunciante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_select_denunciante)))
                    .addGroup(btn_cancelLayout.createSequentialGroup()
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLFecha))
                        .addGap(18, 18, 18)
                        .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_denunciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_select_denunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(btn_cancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Generar Dununcia", btn_cancel);

        tabla_denuncia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_denuncia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No Denuncia", "Remitido por:", "Fecha de la Denuncia", "Denunciado:", "Motivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_denuncia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_denunciaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_denuncia);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Identificador del Denunciado");

        txt_id_denun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id_denun.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tipo de de Denuncia");

        btn_buscar_denun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/find.png"))); // NOI18N
        btn_buscar_denun.setText("Buscar Denuncia");
        btn_buscar_denun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_denunActionPerformed(evt);
            }
        });

        btn_nueva_consulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/new.png"))); // NOI18N
        btn_nueva_consulta.setText("Nueva Consulta");
        btn_nueva_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nueva_consultaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Fecha de Denuncia");

        CB_tipo_denuncia_con.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CB_tipo_denuncia_con.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un tipo" }));
        CB_tipo_denuncia_con.setEnabled(false);

        txt_nombre_denun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombre_denun.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Nombre del Denunciado");

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/door.png"))); // NOI18N
        Salir.setText("   Salir                 ");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nombre_denun, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(CB_tipo_denuncia_con, 0, 179, Short.MAX_VALUE)
                            .addComponent(txt_id_denun, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(DChFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_nueva_consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_buscar_denun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btn_nueva_consulta)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_id_denun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(CB_tipo_denuncia_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btn_buscar_denun)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_nombre_denun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(2, 2, 2)))))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar Denuncia", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscar_denunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_denunActionPerformed
        // Busca con id y set en la tabla
        String noCuenta=txt_id_denun.getText();
        if (noCuenta!=""){
        buscarParaTablaDenuncia(noCuenta);}
        
        
    }//GEN-LAST:event_btn_buscar_denunActionPerformed

    private void btn_nueva_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nueva_consultaActionPerformed
        // TODO add your handling code here:
        limpiar();
        txt_id_denun.setEnabled(true);
        CB_tipo_denuncia_con.setEnabled(true);
        DChFecha.setEnabled(true);
        llenarTablaDenuncia();
        txt_id_denun.requestFocus();
    }//GEN-LAST:event_btn_nueva_consultaActionPerformed

    private void Cierre(java.awt.event.WindowEvent evt) {                        
        int op = JOptionPane.showConfirmDialog(this, "Seguro que desea salir","Salir",JOptionPane.YES_NO_OPTION);
        if(op==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    
    private void txt_denuncianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_denuncianteActionPerformed

    }//GEN-LAST:event_txt_denuncianteActionPerformed

    private void txt_denunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_denunciadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_denunciadoActionPerformed

    private void btn_select_denuncianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_select_denuncianteActionPerformed
        // TODO add your handling code here:

        if (cb_tipo_denuncia_ge.getSelectedIndex()==1){
            SelectAlumno m=new SelectAlumno();
            m.setVisible(true);}
        if (cb_tipo_denuncia_ge.getSelectedIndex()==2||cb_tipo_denuncia_ge.getSelectedIndex()==3){
            SeleccionarCatedratico n=new SeleccionarCatedratico();
            n.setVisible(true);
        }
        if (cb_tipo_denuncia_ge.getSelectedIndex()==4){
            SelectAlumno m=new SelectAlumno();
            m.setVisible(true);
        }

    }//GEN-LAST:event_btn_select_denuncianteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            guardarDatos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiar();
        NoDenuncia();
        llenarTablaDenuncia();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_no_denunciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_no_denunciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_no_denunciaActionPerformed

    private void txt_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void btn_select_denunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_select_denunciadoActionPerformed
        // TODO add your handling code here:
               if (cb_tipo_denuncia_ge.getSelectedIndex()==1||cb_tipo_denuncia_ge.getSelectedIndex()==4){
            SeleccionarCatedratico n=new SeleccionarCatedratico();
            n.setVisible(true);
        }
               if (cb_tipo_denuncia_ge.getSelectedIndex()==2){
            SelectAlumno m=new SelectAlumno();
            m.setVisible(true);}
        
    }//GEN-LAST:event_btn_select_denunciadoActionPerformed
    
    
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed
    public Connection con;
    private Statement stmt;
    private void tabla_denunciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_denunciaMousePressed
        // Seleccion denuncia de la tabla
        int i=tabla_denuncia.getSelectedRow();
        DChFecha.setDate((Date) tabla_denuncia.getValueAt( i, 1));
        System.out.println(tabla_denuncia.getValueAt(i, 2).toString());
        CB_tipo_denuncia_con.setSelectedItem(tabla_denuncia.getValueAt(i, 2));
        
        try {
            
            String sql="Select noCuenta as 'id', concat(nombreAlumno, \" \",  apellidoAlumno) as n from alumno where noCuenta='"+tabla_denuncia.getValueAt(i, 3)+"'";
            System.out.println(sql);
            
            conexion con = new conexion();
            stmt= con.con.createStatement();
            
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            String nombre=rs.getString("n");
            
            
            System.out.println(nombre);
            txt_nombre_denun.setText(nombre);
            txt_id_denun.setText(tabla_denuncia.getValueAt(i, 3).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tabla_denunciaMousePressed
    
    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed
    boolean visible;
    private void cerrando(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrando
        // TODO add your handling code here:

        int op = JOptionPane.showConfirmDialog(this, "Seguro que desea salir","Salir",JOptionPane.YES_NO_OPTION);
        if(op==JOptionPane.YES_OPTION){
            visible=false;
            System.exit(0);
        }
        else{
            Denuncias m = new Denuncias();
        m.setVisible(true);
        visible=true;
        } 
    }//GEN-LAST:event_cerrando

    private void txt_no_denunciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_no_denunciaKeyPressed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txt_no_denunciaKeyPressed

    private void txt_no_denunciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_no_denunciaKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_no_denunciaKeyTyped

    private void txt_no_denunciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_no_denunciaKeyReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_txt_no_denunciaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Denuncias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Denuncias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Denuncias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Denuncias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Denuncias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB_tipo_denuncia_con;
    private com.toedter.calendar.JDateChooser DChFecha;
    private javax.swing.JButton Salir;
    private javax.swing.JButton btn_buscar_denun;
    private javax.swing.JPanel btn_cancel;
    private javax.swing.JButton btn_nueva_consulta;
    private javax.swing.JButton btn_select_denunciado;
    private javax.swing.JButton btn_select_denunciante;
    public static javax.swing.JComboBox cb_tipo_denuncia_ge;
    private javax.swing.JTextPane contex_motivo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla_denuncia;
    public static javax.swing.JTextField txt_denunciado;
    public static javax.swing.JTextField txt_denunciante;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_id_denun;
    private javax.swing.JTextField txt_no_denuncia;
    private javax.swing.JTextField txt_nombre_denun;
    // End of variables declaration//GEN-END:variables
}
