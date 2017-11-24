/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Datos.Cuentas;
import Logica.Conexion;
import Logica.CuentaTableModel;
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
import javax.swing.JOptionPane;
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
public class Cuenta extends javax.swing.JInternalFrame {
    
public CuentaTableModel cuentaTModel = new CuentaTableModel();
private Conexion mysql = new Conexion();
private Connection cn = mysql.conectar();
private String sentenciaSql = "";
Cuentas cuentaActual;
boolean guardar=true;
    /**
     * Creates new form Articulos
     */
    public Cuenta() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Gestionar cuentass"); //Título del Frame
        inicializarColumnas();
        consultaInicial();
        ocultarColumnas();
        inhabilitar();
        
    }
    
    private void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 6; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Id");
                    break;
                case 1:
                    col.setHeaderValue("Código");
                    break;
                case 2:
                    col.setHeaderValue("Nombre");
                    break;
                case 3:
                    col.setHeaderValue("Tipo");
                    break;
                case 4:
                    col.setHeaderValue("Saldo Deudor");
                    break;
                case 5:
                    col.setHeaderValue("Saldo Acreedor");
            }
            tColumnModel.addColumn(col);
        }
        tablaCuentas.setColumnModel(tColumnModel);
    }
    
     private void consultaInicial() {
        try {
            sentenciaSql = "SELECT * FROM cuenta order by idCuenta";
            Statement statement = this.cn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Cuentas cuenta = new Cuentas();
               cuenta.idCuenta = resultado.getInt("idCuenta");
                cuenta.codigoCuenta = resultado.getLong("codigoCuenta");
                cuenta.nombreCuenta = resultado.getString("nombreCuenta");
                cuenta.tipo = resultado.getString("tipo");
                cuenta.saldoDeudor = resultado.getDouble("saldoDeudor");
                cuenta.saldoAcreedor = resultado.getDouble("saldoAcreedor");             
             this.cuentaTModel.cuentas.add(cuenta);
            }
            tablaCuentas.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }
     
     
     private void llenarComboBox(){
         this.boxTipo.removeAllItems();
         try{
            PreparedStatement statement = null;
            sentenciaSql = "SELECT * FROM cuenta";
            statement = this.cn.prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                this.boxTipo.addItem(resultado.getString("tipo"));
            } 
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
      private void UpdateJTable() {
        cuentaTModel.cuentas.clear();
        try {
            PreparedStatement statement = null;
            sentenciaSql = "SELECT * FROM cuenta ORDER BY idCuenta asc";
            statement = this.cn.prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Cuentas cuenta = new Cuentas();
                cuenta.idCuenta = resultado.getInt("idCuenta");
                cuenta.codigoCuenta = resultado.getLong("codigoCuenta");
                cuenta.nombreCuenta = resultado.getString("nombreCuenta");
                cuenta.tipo = resultado.getString("tipo");
                cuenta.saldoDeudor = resultado.getDouble("saldoDeudor");
                cuenta.saldoAcreedor = resultado.getDouble("saldoAcreedor");             
             this.cuentaTModel.cuentas.add(cuenta);
            }
            tablaCuentas.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

      
  
  
  
    
    void ocultarColumnas() { //ocultar las columnas que no quiero que se muestren
        
        tablaCuentas.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaCuentas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaCuentas.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        tablaCuentas.getColumnModel().getColumn(4).setMaxWidth(0);
        tablaCuentas.getColumnModel().getColumn(4).setMinWidth(0);
        tablaCuentas.getColumnModel().getColumn(4).setPreferredWidth(0);

        tablaCuentas.getColumnModel().getColumn(5).setMaxWidth(0);
        tablaCuentas.getColumnModel().getColumn(5).setMinWidth(0);
        tablaCuentas.getColumnModel().getColumn(5).setPreferredWidth(0);
  
    }
    
    private String accion = "guardar";
    
    void inhabilitar() {
  
        txtId.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        boxTipo.setEnabled(false);
      
        
        btnAgregar.setEnabled(false);
       
        
        txtCodigo.setText("");
        txtNombre.setText("");
    }
    
    void habilitar() {
  
        txtId.setEnabled(true);
        txtCodigo.setEnabled(true);
        txtNombre.setEnabled(true);
        boxTipo.setEnabled(true);
    
        
        btnAgregar.setEnabled(true);
       
        
        txtCodigo.setText("");
        txtNombre.setText("");
    }
    
   
    //método de filtro de datos
    private TableRowSorter tr;
    
    public void filtro(){
        tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),1,2));
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        boxTipo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCuentas.setBackground(new java.awt.Color(204, 204, 204));
        tablaCuentas.setModel(cuentaTModel);
        tablaCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuentas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 118, 587, 220));

        txtBuscar.setBackground(new java.awt.Color(204, 204, 204));
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 73, 587, -1));

        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Digite el código de la cuenta que desea buscar:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 48, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 11, 627, 410));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Código:");

        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Nombre:");

        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Tipo:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Pasivo", "Capital", "Cuentas de Resultado Deudoras", "Cuentas de Resultado Acreedoras", "Cuentas de Orden Deudor", "Cuentas de Orden Acreedoras" }));
        boxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("AGREGAR CUENTA");

        btnAgregar.setBackground(new java.awt.Color(102, 102, 102));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(102, 102, 102));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(0, 102, 102));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Id:");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevo)
                            .addGap(43, 43, 43)
                            .addComponent(btnAgregar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel8)
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnAgregar))
                .addGap(70, 70, 70))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 11, 310, 410));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 980, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try{
         if(guardar){   
            Cuentas cuenta= new Cuentas();
            cuenta.idCuenta=Integer.parseInt(txtId.getText());
            cuenta.codigoCuenta= Long.parseLong(txtCodigo.getText());
            cuenta.nombreCuenta=txtNombre.getText();
            cuenta.tipo = boxTipo.getSelectedItem().toString();
          
            
            sentenciaSql="INSERT INTO cuenta( idCuenta, codigoCuenta ,nombreCuenta, tipo) VALUES" + "(?,?,?,?)";
            
            PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, cuenta.idCuenta);
            preparedStatement.setLong(2,cuenta.codigoCuenta);
            preparedStatement.setString(3,cuenta.nombreCuenta);
            preparedStatement.setString(4,cuenta.tipo);
           
            preparedStatement.execute();
            cuentaTModel.cuentas.add(cuenta);
        
         } else{
             sentenciaSql="UPDATE cuenta SET  nombreCuenta=?, tipo=? where codigoCuenta=? ";
             PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
             preparedStatement.setString(1,txtNombre.getText());
             preparedStatement.setString(2,boxTipo.getSelectedItem().toString());
             preparedStatement.setString(3,txtCodigo.getText());
             
             preparedStatement.executeUpdate();
             cuentaActual.nombreCuenta=txtCodigo.getText();
             cuentaActual.tipo=boxTipo.getSelectedItem().toString();
             cuentaActual.codigoCuenta=Long.parseLong(txtCodigo.getText());
             
         }
            tablaCuentas.repaint();
            UpdateJTable();
            JOptionPane.showMessageDialog(this, "Cuenta guardada con exito");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"Error al guardar el cuenta");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

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
                tr= new TableRowSorter(tablaCuentas.getModel());
                tablaCuentas.setRowSorter(tr); 
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
        txtCodigo.transferFocus();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
        txtNombre.transferFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void boxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoActionPerformed
        // TODO add your handling code here:
        boxTipo.transferFocus();
    }//GEN-LAST:event_boxTipoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //botón Nuevo
        habilitar();
        btnAgregar.setText("Guardar");
        accion = "guardar";
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaCuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentasMouseClicked
        int clics = evt.getClickCount();
        int row = tablaCuentas.rowAtPoint(evt.getPoint());
        if (clics == 2) {
            Cuentas c = cuentaTModel.cuentas.get(row);
            cuentaActual = c;
            txtCodigo.setText(String.valueOf(c.codigoCuenta.longValue()));           
            txtNombre.setText(c.nombreCuenta);
            boxTipo.getSelectedItem().toString();
            guardar = false;
        }
    }//GEN-LAST:event_tablaCuentasMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxTipo;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCuentas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
