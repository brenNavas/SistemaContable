/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Datos.Trabajador;
import Datos.Trabajador;
import Logica.Conexion;
import Logica.TrabajadorTableModel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yeseliz
 */

public class Trabajador_usuario extends javax.swing.JInternalFrame {
TrabajadorTableModel trabajadorTModel = new TrabajadorTableModel();
private Conexion mysql = new Conexion();
private Connection cn = mysql.conectar();
private String sentenciaSql = "";
private String sentenciaSql2 = "";
 private String accion = "guardar";
Trabajador trabajadorActual;
boolean guardar=true;


    /**
     * Creates new form Trabajador
     */
    public Trabajador_usuario() {
        initComponents();
         initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Gestionar Trabajador"); //Título del Frame
        inicializarColumnas();
        consultaInicial();
        inhabilitar();
    }

    //método de filtro de datos
    private TableRowSorter tr;
    
    public void filtro(){
        tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),0,1,2));
    }
    
     public void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 13; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Id");
                    break;
                case 1:
                    col.setHeaderValue("Nombre");
                    break;
                case 2:
                    col.setHeaderValue("Apellido Materno");
                    break;
                case 3:
                    col.setHeaderValue("Apellido Paterno");
                    break;
                case 4:
                    col.setHeaderValue("Dui");
                    break;
                case 5:
                    col.setHeaderValue("Dirección");
                     break;
                case 6:
                    col.setHeaderValue("Teléfono");
                     break;
                case 7:
                    col.setHeaderValue("Email");
                     break;
                case 8:
                    col.setHeaderValue("Sueldo");
                    break;
                case 9:
                    col.setHeaderValue("Nombre de Usuario");
                    break;
                case 10:
                    col.setHeaderValue("Contraseña");
                    break;
                case 11:
                    col.setHeaderValue("Cargo");
                    break;
                case 12:
                    col.setHeaderValue("Tipo de Acceso");
            }
            tColumnModel.addColumn(col);
        }
        tablaTrabajadores.setColumnModel(tColumnModel);
    }
    
    public void consultaInicial() {
        try {
        sentenciaSql = "SELECT p.idPersona, p.nombrePersona, p.apellido_pa, p.apellido_ma, p.num_dui, p.direccion, p.telefono, p.email, p.sueldo, t.cargo,"+
                       "t.tipo_acceso, t.nombreUsuario, t.password FROM persona p inner join trabajador t on p.idPersona = t.idPersona";
            Statement statement = this.cn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                
                Trabajador trabajador = new Trabajador();
                
                trabajador.idPersona = resultado.getInt("idPersona");
                trabajador.nombrePersona = resultado.getString("nombrePersona");
                trabajador.a_paterno = resultado.getString("apellido_pa");
                trabajador.a_materno = resultado.getString("apellido_ma");
                trabajador.num_dui = resultado.getString("num_dui");
                trabajador.direccion = resultado.getString("direccion");
                trabajador.telefono = resultado.getString("telefono");
                trabajador.email = resultado.getString("email");
                trabajador.sueldo = resultado.getDouble("sueldo");
                trabajador.cargo = resultado.getString("cargo");
                trabajador.tipo_acceso = resultado.getString("tipo_acceso");
                trabajador.nombreUsuario = resultado.getString("nombreUsuario");
                trabajador.password = resultado.getString("password");
                
               
              
             this.trabajadorTModel.trabajadores.add(trabajador);
            }
            tablaTrabajadores.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los empleados de la base de datos");
            ex.printStackTrace();
        }
    }
     
     public void UpdateJTable() {
        trabajadorTModel.trabajadores.clear();
        try {
            PreparedStatement statement = null;
            sentenciaSql = "SELECT p.idPersona, p.nombrePersona, p.apellido_pa, p.apellido_ma, p.num_dui, p.direccion, p.telefono, p.email, p.sueldo, t.cargo,"+
                       "t.tipo_acceso, t.nombreUsuario, t.password FROM persona p inner join trabajador t on p.idPersona = t.idPersona";
             
            statement = this.cn.prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                
                Trabajador trabajador = new Trabajador();
                
                trabajador.idPersona = resultado.getInt("idPersona");
                trabajador.cargo = resultado.getString("cargo");
                trabajador.tipo_acceso = resultado.getString("tipo_acceso");
                trabajador.nombreUsuario = resultado.getString("nombreUsuario");
                trabajador.password = resultado.getString("password");
                trabajador.nombrePersona = resultado.getString("nombrePersona");
                trabajador.a_paterno = resultado.getString("apellido_pa");
                trabajador.a_materno = resultado.getString("apellido_ma");
                trabajador.num_dui = resultado.getString("num_dui");
                trabajador.direccion = resultado.getString("direccion");
                trabajador.telefono = resultado.getString("telefono");
                trabajador.email = resultado.getString("email");
                trabajador.sueldo = resultado.getDouble("sueldo");
                
            this.trabajadorTModel.trabajadores.add(trabajador);
            }
            tablaTrabajadores.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los empleados de la base de datos");
            ex.printStackTrace();
        }
    }
 
     void inhabilitar() {
  
        txtId.setEnabled(false);
        txtContraseña.setEnabled(false);
        txtCargo.setEnabled(false);
        boxTipoU.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellidoPa.setEnabled(false);
        txtApellidoMa.setEnabled(false);
        txtDui.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtSueldo.setEnabled(false);
      
        
    }
    
    void habilitar() {
        txtId.setEnabled(true);
        txtContraseña.setEnabled(true);
        txtCargo.setEnabled(true);
        boxTipoU.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellidoPa.setEnabled(true);
        txtApellidoMa.setEnabled(true);
        txtDui.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtEmail.setEnabled(true);
        txtSueldo.setEnabled(true);
        
    }
    
    
     public boolean eliminar(Trabajador t) {
        sentenciaSql = "delete from trabajador where idPersona=?";
        sentenciaSql2 = "delete from persona where idPersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sentenciaSql);
            PreparedStatement pst2 = cn.prepareStatement(sentenciaSql2);

            
            pst.setInt(1, t.getIdPersona());

            
            pst2.setInt(1, t.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean insertar(Trabajador t) {
        sentenciaSql = "insert into persona (idPersona, nombrePersona, apellido_pa, apellido_ma, num_dui, direccion, telefono, email, sueldo)"
                + "values (?,?,?,?,?,?,?,?,?)";
        sentenciaSql2 = "insert into trabajador (idPersona, cargo, tipo_acceso, nombreUsuario, password)"
                + "values ((select idPersona from persona order by idPersona desc limit 1),?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sentenciaSql);
            PreparedStatement pst2 = cn.prepareStatement(sentenciaSql2);
            
            pst.setInt(1, t.getIdPersona());
            pst.setString(2, t.getNombrePersona());
            pst.setString(3, t.getA_paterno());
            pst.setString(4, t.getA_materno());
            pst.setString(5, t.getNum_dui());
            pst.setString(6, t.getDireccion());
            pst.setString(7, t.getTelefono());
            pst.setString(8, t.getEmail());
            pst.setDouble(9, t.getSueldo());

            pst2.setString(1, t.getCargo());
            pst2.setString(2, t.getTipo_acceso());
            pst2.setString(3, t.getNombreUsuario());
            pst2.setString(4, t.getPassword());
        
            
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

     public boolean editar(Trabajador t) {
        sentenciaSql = "update persona set nombrePersona=?, apellido_pa=?, apellido_ma=?, num_dui=?,"
                + " direccion=?, telefono=?, email=?, sueldo=? where idPersona=?";
        
        sentenciaSql2 = "update trabajador set cargo=?, tipo_acceso=?, nombreUsuario=?, password=?"
                + " where idPersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sentenciaSql);
            PreparedStatement pst2 = cn.prepareStatement(sentenciaSql2);

           pst.setString(1, t.getNombrePersona());
            pst.setString(2, t.getA_paterno());
            pst.setString(3, t.getA_materno());
            pst.setString(4, t.getNum_dui());
            pst.setString(5, t.getDireccion());
            pst.setString(6, t.getTelefono());
            pst.setString(7, t.getEmail());
            pst.setDouble(8, t.getSueldo());
            pst.setInt(9, t.getIdPersona());

            pst2.setString(1, t.getCargo());
            pst2.setString(2, t.getTipo_acceso());
            pst2.setString(3, t.getNombreUsuario());
            pst2.setString(4, t.getPassword());
            pst2.setInt(5, t.getIdPersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        boxTipoU = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtApellidoMa = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDui = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNombreU1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTrabajadores = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 4, true), "Trabajador", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Id Persona:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Cargo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        boxTipoU.setBackground(new java.awt.Color(204, 204, 204));
        boxTipoU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario", " " }));
        boxTipoU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoUActionPerformed(evt);
            }
        });
        jPanel1.add(boxTipoU, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("Telefono:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        txtDireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 130, 40));

        txtContraseña.setBackground(new java.awt.Color(204, 204, 204));
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 130, -1));

        txtCargo.setBackground(new java.awt.Color(204, 204, 204));
        txtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 130, -1));

        btnNuevo.setBackground(new java.awt.Color(102, 102, 102));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 102));
        btnGuardar.setForeground(new java.awt.Color(0, 102, 102));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Tipo de usuario:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("Nombre:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 60, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("Apellido Materno:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 120, -1));

        txtId.setBackground(new java.awt.Color(204, 204, 204));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 130, -1));

        txtNombre.setBackground(new java.awt.Color(204, 204, 204));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 130, -1));

        txtApellidoPa.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidoPa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPaActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoPa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 130, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setText("Dui:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 102));
        jLabel12.setText("Dirección:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setText("Sueldo:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        txtApellidoMa.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMaActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoMa, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 130, -1));

        txtSueldo.setBackground(new java.awt.Color(204, 204, 204));
        txtSueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueldoActionPerformed(evt);
            }
        });
        jPanel1.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 130, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 102));
        jLabel14.setText("Apellido Paterno:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 110, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 102));
        jLabel15.setText("Email:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, -1, -1));

        txtDui.setBackground(new java.awt.Color(204, 204, 204));
        txtDui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuiActionPerformed(evt);
            }
        });
        jPanel1.add(txtDui, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 130, -1));

        txtTelefono.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 130, -1));

        txtEmail.setBackground(new java.awt.Color(204, 204, 204));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 130, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("Nombre de usuario:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtNombreU1.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreU1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombreU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 130, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 330, 520));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 4, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaTrabajadores.setModel(trabajadorTModel);
        tablaTrabajadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTrabajadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTrabajadores);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 97, 790, 161));

        txtBuscar.setBackground(new java.awt.Color(204, 204, 204));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 66, 790, -1));

        btnEliminar.setBackground(new java.awt.Color(102, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, -1, -1));

        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Digite el código  del usuario que desea buscar:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 33, -1, 27));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 840, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if (!txtId.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Estás seguro de Eliminar el Trabajador?","Confirmar",2);

            if (confirmacion==0) {
              //  ftrabajador func = new ftrabajador();
                Trabajador dts= new Trabajador();

                dts.setIdPersona(Integer.parseInt(txtId.getText()));
                eliminar(dts);
                UpdateJTable();
               

            }
    }
        /*
        int[] indices = tablaTrabajadores.getSelectedRows();//hacer una sola sentencia SQL
        List<Trabajador> aEliminar = new ArrayList<Trabajador>();
        
        for (int i : indices) {
            Trabajador trabajador = trabajadorTModel.trabajadores.get(i);
            sentenciaSql = "DELETE persona, trabajador FROM persona inner join trabajador on trabajador.idPersona = persona.idPersona where persona.idPersona=?";
            aEliminar.add(trabajador);
            try {
                PreparedStatement prepStat = cn.prepareStatement(sentenciaSql);
                prepStat.setInt(1, trabajador.idPersona);
                prepStat.executeUpdate();
                JOptionPane.showMessageDialog(this, "Elimino correctamente " + trabajador.idPersona);
                UpdateJTable();
            } catch (SQLException ex) {
                Logger.getLogger(Trabajador_usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tablaTrabajadores.repaint();*/
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
  
        if (txtId.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un identificador para el Trabajador");
            txtId.requestFocus();
            return;
        }
         if (txtNombreU1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Nombre de Usuario para el Trabajador");
            txtNombreU1.requestFocus();
            return;
        }
        if (txtContraseña.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar una contraseña para el Trabajador");
            txtContraseña.requestFocus();
            return;
        }
        
        if (txtCargo.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un cargo para el Trabajador");
            txtCargo.requestFocus();
            return;
        }
        

        if (txtNombre.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un nombre para el Trabajador");
            txtNombre.requestFocus();
            return;
        }
        
        if (txtApellidoPa.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un apellido paterno para el trabajador");
            txtApellidoPa.requestFocus();
            return;
        }
        if (txtApellidoMa.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un apellido materno para el trabajador");
            txtApellidoMa.requestFocus();
            return;
        }
        if (txtDui.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar el n° de dui ");
            txtDui.requestFocus();
            return;
        }
        if (txtDireccion.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar la dirección del trabajador");
            txtDireccion.requestFocus();
            return;
        }
        if (txtTelefono.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar el teléfono del trabajador");
            txtTelefono.requestFocus();
            return;
        }
        
        

        Trabajador dts = new Trabajador();
       // ftrabajador func = new ftrabajador();

        dts.setIdPersona(Integer.parseInt(txtId.getText()));
        dts.setNombreUsuario(txtNombreU1.getText());
        dts.setPassword(txtContraseña.getText());
        dts.setCargo(txtCargo.getText());
       // dts.setTipo_acceso(boxTipoU.getSelectedItem().toString());
        dts.setNombrePersona(txtNombre.getText());
        dts.setA_paterno(txtApellidoPa.getText());
        dts.setA_materno(txtApellidoMa.getText());
        dts.setNum_dui(txtDui.getText());
        dts.setDireccion(txtDireccion.getText());
        dts.setTelefono(txtTelefono.getText());
        dts.setEmail(txtEmail.getText());
        dts.setSueldo(Double.parseDouble(txtSueldo.getText()));
        int seleccionado = boxTipoU.getSelectedIndex();

        seleccionado=boxTipoU.getSelectedIndex();
        dts.setTipo_acceso((String)boxTipoU.getItemAt(seleccionado));
        
      //  seleccionado=cboestado.getSelectedIndex();
      //  dts.setEstado((String)cboestado.getItemAt(seleccionado));
        
        if (accion.equals("guardar")) {
            if (insertar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "el trabajador fue registrado satisfactoriamente");
                UpdateJTable();
                inhabilitar();

            }

        }
        else if (accion.equals("editar")){
            dts.setIdPersona(Integer.parseInt(txtId.getText()));

            if (editar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "El Trabajador fue Editado satisfactoriamente");
                UpdateJTable();
                inhabilitar();
            }
        }
                            
        
        
        /* try{
         if(guardar){   
            Trabajador trabajador= new Trabajador();
            trabajador.idPersona = Integer.parseInt(txtId.getText());
            trabajador.nombrePersona = txtNombre.getText();
            trabajador.a_paterno = txtApellidoPa.getText();
            trabajador.a_materno = txtApellidoMa.getText();
            trabajador.num_dui =txtDui.getText();
            trabajador.direccion = txtDireccion.getText();
            trabajador.telefono = txtTelefono.getText();
            trabajador.email = txtEmail.getText();
            trabajador.sueldo = Double.parseDouble(txtSueldo.getText());
            trabajador.nombreUsuario = txtNombreU1.getText();
            trabajador.password = txtContraseña.getText();
            trabajador.cargo = txtCargo.getText();
            trabajador.tipo_acceso = boxTipoU.getSelectedItem().toString();
   
            sentenciaSql="INSERT INTO persona( idPersona, nombrePersona, apellido_pa, apellido_ma, num_dui, direccion, telefono, email, sueldo) VALUES" + "(?,?,?,?,?,?,?,?,?)";
            sentenciaSql2 = "INSERT INTO trabajador(nombreUsuario, password, tipo_acceso, cargo) VALUES" + "(?,?,?,?)";
            
            PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
            PreparedStatement p=cn.prepareStatement(sentenciaSql2);
            
            preparedStatement.setInt(1, trabajador.idPersona);
            preparedStatement.setString(2, trabajador.nombrePersona);
            preparedStatement.setString(3, trabajador.a_paterno);
            preparedStatement.setString(4, trabajador.a_materno);
            preparedStatement.setString(5, trabajador.num_dui);
            preparedStatement.setString(6, trabajador.direccion);
            preparedStatement.setString(7, trabajador.telefono);
            preparedStatement.setString(8, trabajador.email);
            preparedStatement.setDouble(9, trabajador.sueldo);
            p.setString(1, trabajador.nombreUsuario);
            p.setString(2, trabajador.password);
            p.setString(3, trabajador.tipo_acceso);
            p.setString(4, trabajador.cargo);
            
           
            preparedStatement.execute();
            p.execute();
            trabajadorTModel.trabajadores.add(trabajador);
        
         } else{
             sentenciaSql="UPDATE persona SET idPersona=?, nombrePersona=?, apellido_pa=?, apellido_ma=?, num_dui=?, direccion=?, telefono=?, email=?, sueldo=? where idPersona=? ";
             sentenciaSql2 = "UPDATE trabajador nombreUsuario=?, password=?, tipo_acceso=?, cargo=? where idPersona=?";
             PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
             PreparedStatement p=cn.prepareStatement(sentenciaSql2);
             
             preparedStatement.setInt(1, Integer.parseInt(txtId.getText()));
             preparedStatement.setString(2, txtNombre.getText());
             preparedStatement.setString(3, txtApellidoPa.getText());
             preparedStatement.setString(4, txtApellidoMa.getText());
             preparedStatement.setString(5, txtDui.getText());
             preparedStatement.setString(6, txtDireccion.getText());
             preparedStatement.setString(7, txtTelefono.getText());
             preparedStatement.setString(8, txtEmail.getText());
             preparedStatement.setDouble(9, Double.parseDouble(txtSueldo.getText()));
             
             p.setString(1, txtNombreU1.getText());
             p.setString(2, txtContraseña.getText());
             p.setString(3, boxTipoU.getSelectedItem().toString());
             p.setString(4, txtCargo.getText());
             
            
         }
            tablaTrabajadores.repaint();
            UpdateJTable();
            JOptionPane.showMessageDialog(this, "Empleadp guardado con exito");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"Error al guardar el Empleado");
            ex.printStackTrace();
        }*/
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
         // filtro de datos en la tabla
        txtBuscar.addKeyListener(new KeyAdapter (){
            public void keyReleased(final KeyEvent e){
                String cadena = (txtBuscar.getText()).toUpperCase();
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
      });
                tr= new TableRowSorter(tablaTrabajadores.getModel());
                tablaTrabajadores.setRowSorter(tr); 
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here: 
        txtDireccion.transferFocus();
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
         txtContraseña.transferFocus();
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void txtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargoActionPerformed
        // TODO add your handling code here:
         txtCargo.transferFocus();
    }//GEN-LAST:event_txtCargoActionPerformed

    private void boxTipoUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoUActionPerformed
        // TODO add your handling code here:
         boxTipoU.transferFocus();
    }//GEN-LAST:event_boxTipoUActionPerformed

    private void tablaTrabajadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTrabajadoresMouseClicked
         btnGuardar.setText("Editar");
         habilitar();
         btnEliminar.setEnabled(true);
         
         accion="editar";
/*
        int fila = tablaTrabajadores.rowAtPoint(evt.getPoint());
        
        txtId.setText(tablaTrabajadores.getValueAt(fila, 0).toString());
        txtNombreU1.setText(tablaTrabajadores.getValueAt(fila, 1).toString());
        txtContraseña.setText(tablaTrabajadores.getValueAt(fila, 2).toString());
        txtCargo.setText(tablaTrabajadores.getValueAt(fila, 3).toString());
        boxTipoU.setSelectedItem(tablaTrabajadores.getValueAt(fila, 4).toString());
        txtNombre.setText(tablaTrabajadores.getValueAt(fila, 5).toString());
        txtApellidoPa.setText(tablaTrabajadores.getValueAt(fila, 6).toString());
        txtApellidoMa.setText(tablaTrabajadores.getValueAt(fila, 7).toString());
        txtDui.setText(tablaTrabajadores.getValueAt(fila, 8).toString());
        txtDireccion.setText(tablaTrabajadores.getValueAt(fila, 9).toString());
        txtTelefono.setText(tablaTrabajadores.getValueAt(fila, 10).toString());
        txtEmail.setText(tablaTrabajadores.getValueAt(fila, 11).toString());
        txtSueldo.setText(tablaTrabajadores.getValueAt(fila, 12).toString());
        
*/
         
        
        int clics = evt.getClickCount();
        int row = tablaTrabajadores.rowAtPoint(evt.getPoint());
        if (clics == 2) {
            Trabajador c = trabajadorTModel.trabajadores.get(row);
            trabajadorActual = c;
            txtId.setText(String.valueOf(c.idPersona.intValue()));
            txtNombre.setText(c.nombrePersona);
            txtApellidoPa.setText(c.a_paterno);
            txtApellidoMa.setText(c.a_materno);
            txtDui.setText(c.num_dui);
            txtDireccion.setText(c.direccion);
            txtTelefono.setText(c.telefono);
            txtEmail.setText(c.email);
            txtSueldo.setText(String.valueOf(c.sueldo.doubleValue()));
            txtNombreU1.setText(c.nombreUsuario);
            txtContraseña.setText(c.password);
            txtCargo.setText(c.cargo);
            boxTipoU.getSelectedItem().toString();
            
            
            //guardar = false;
        }
       
    }//GEN-LAST:event_tablaTrabajadoresMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       habilitar();
        btnGuardar.setText("Guardar");
        accion = "guardar";
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoPaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaActionPerformed

    private void txtApellidoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMaActionPerformed

    private void txtSueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSueldoActionPerformed

    private void txtDuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuiActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtNombreU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreU1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreU1ActionPerformed

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
            java.util.logging.Logger.getLogger(Trabajador_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trabajador_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trabajador_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trabajador_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trabajador_usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTipoU;
    private javax.swing.JToggleButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tablaTrabajadores;
    private javax.swing.JTextField txtApellidoMa;
    private javax.swing.JTextField txtApellidoPa;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDui;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreU1;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
