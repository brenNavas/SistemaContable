/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Datos.Producto;
import Logica.Conexion;
import Logica.productoTableModel;

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
public class Productos extends javax.swing.JInternalFrame {
public productoTableModel productoTModel = new productoTableModel();
private Conexion mysql = new Conexion();
private Connection cn = mysql.conectar();
private String sentenciaSql = "";
Producto productoActual;
boolean guardar=true;
private String accion = "guardar";
    
    /**
     * Creates new form Articulos
     */
    public Productos() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Gestionar Productos"); //Título del Frame
        inicializarColumnas();
        consultaInicial();
        inhabilitar();
      
    }

    
    void inhabilitar() {
  
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        boxTipo.setEnabled(false);
        boxUM.setEnabled(false);
        
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        txtCodigo.setText("");
        txtNombre.setText("");
    }
    
    void habilitar() {
  
        txtCodigo.setEnabled(true);
        txtNombre.setEnabled(true);
        boxTipo.setEnabled(true);
        boxUM.setEnabled(true);
        
        btnAgregar.setEnabled(true);
        btnEliminar.setEnabled(true);
        
        txtCodigo.setText("");
        txtNombre.setText("");
    }

    //método de filtro de datos
    private TableRowSorter tr;
    
    public void filtro(){
        tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),0,1,2));
    }
    
    private void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 5; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Código");
                    break;
                case 1:
                    col.setHeaderValue("Nombre");
                    break;
                case 2:
                    col.setHeaderValue("Tipo");
                    break;
                case 3:
                    col.setHeaderValue("Unidad de Medida");
                    break;
                case 4:
                    col.setHeaderValue("Proceso");
              
            }
            tColumnModel.addColumn(col);
        }
        tablaProducto.setColumnModel(tColumnModel);
    }
    
    private void consultaInicial() {
        try {
            sentenciaSql = "SELECT * FROM producto ORDER BY codigoProducto asc";
            Statement statement = this.cn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Producto producto = new Producto();
               producto.codigo = resultado.getInt("codigoProducto");
                producto.nombre = resultado.getString("nombreProducto");
                producto.tipo = resultado.getString("tipoProducto");
                producto.uMedida = resultado.getString("unidadMedida");
                producto.proceso = resultado.getString("proceso");
                       
             this.productoTModel.productos.add(producto);
            }
            tablaProducto.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }
    
     private void UpdateJTable() {
        productoTModel.productos.clear();
        try {
            PreparedStatement statement = null;
            sentenciaSql = "SELECT * FROM producto ORDER BY codigoProducto asc";
            statement = this.cn.prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.codigo = resultado.getInt("codigoProducto");
                producto.nombre = resultado.getString("nombreProducto");
                producto.tipo = resultado.getString("tipoProducto");
                producto.uMedida = resultado.getString("unidadMedida");
                producto.proceso=resultado.getString("proceso");
                       
             this.productoTModel.productos.add(producto);
            }
            tablaProducto.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        boxTipo = new javax.swing.JComboBox();
        boxUM = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnAgregar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        boxDepartamento = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tablaProducto.setBackground(new java.awt.Color(204, 204, 204));
        tablaProducto.setModel(productoTModel);
        tablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProducto);

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

        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Digite el código o nombre del producto que desea buscar:");

        btnEliminar.setBackground(new java.awt.Color(102, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 0));
        jLabel9.setText("ELIMINAR PRODUCTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel3)))
                .addGap(40, 40, 40))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setForeground(new java.awt.Color(153, 0, 102));
        jLabel4.setText("Código:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 100, -1, -1));

        jLabel5.setForeground(new java.awt.Color(153, 0, 102));
        jLabel5.setText("Nombre:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 154, -1, -1));

        jLabel6.setForeground(new java.awt.Color(153, 0, 153));
        jLabel6.setText("Tipo:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 204, -1, -1));

        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Unidad de Medida:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 257, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 97, 126, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 151, 126, -1));

        boxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Materia Prima", "Producto en Proceso", "Producto Terminado" }));
        boxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoActionPerformed(evt);
            }
        });
        jPanel3.add(boxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 201, 126, -1));

        boxUM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yarda", "Metro", "Pulgada", "Pliego", "Unidad"}));
        boxUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxUMActionPerformed(evt);
            }
        });
        jPanel3.add(boxUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 254, 126, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("AGREGAR PRODUCTO");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 35, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(102, 102, 102));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 355, -1, -1));

        btnAgregar1.setBackground(new java.awt.Color(102, 102, 102));
        btnAgregar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAgregar1.setForeground(new java.awt.Color(0, 102, 102));
        btnAgregar1.setText("Nuevo");
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 355, -1, -1));

        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("(Sólo si es producto en proceso)");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, -1, -1));

        boxDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desensamble", "Corte y Costura", "Ensamblado", "vacio" }));
        jPanel3.add(boxDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 292, -1, -1));

        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("Departamento:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 292, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 820, 460));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       try{
         if(guardar){   
            Producto producto= new Producto();
            producto.codigo=Integer.parseInt(txtCodigo.getText());
            producto.nombre=txtNombre.getText();
            producto.tipo = boxTipo.getSelectedItem().toString();
            producto.uMedida = boxUM.getSelectedItem().toString();
            producto.proceso =boxDepartamento.getSelectedItem().toString();
            
            sentenciaSql="INSERT INTO producto(codigoProducto ,nombreProducto, tipoProducto, unidadMedida, proceso) VALUES" + "(?,?,?,?,?)";
            
            PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, producto.codigo);
            preparedStatement.setString(2,producto.nombre);
            preparedStatement.setString(3,producto.tipo);
             preparedStatement.setString(4,producto.uMedida);
            preparedStatement.setString(5, producto.proceso);
           
            preparedStatement.execute();
            productoTModel.productos.add(producto);
        
         } else{
             sentenciaSql="UPDATE producto SET  nombreProducto=?, tipoProducto=?, unidadMedida=?, proceso=? where codigoProducto=? ";
             PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
             preparedStatement.setString(1,txtNombre.getText());
             preparedStatement.setString(2,boxTipo.getSelectedItem().toString());
             preparedStatement.setString(3,boxUM.getSelectedItem().toString());
             preparedStatement.setString(4, boxDepartamento.getSelectedItem().toString());
             preparedStatement.setString(5,txtCodigo.getText());
             
             preparedStatement.executeUpdate();
             productoActual.nombre=txtCodigo.getText();
             productoActual.tipo=boxTipo.getSelectedItem().toString();
             productoActual.uMedida=boxUM.getSelectedItem().toString();
             productoActual.proceso = boxDepartamento.getSelectedItem().toString();
             productoActual.codigo=Integer.parseInt(txtCodigo.getText());
             
         }
            tablaProducto.repaint();
            UpdateJTable();
            JOptionPane.showMessageDialog(this, "Producto guardado con exito");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"Error al guardar el Producto");
            ex.printStackTrace();
        }
     
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int[] indices = tablaProducto.getSelectedRows();
        List<Producto> aEliminar = new ArrayList<Producto>();
        
        for (int i : indices) {
            Producto producto = productoTModel.productos.get(i);
             sentenciaSql = "DELETE FROM producto WHERE codigoProducto = ?";
            aEliminar.add(producto);
            try {
                PreparedStatement prepStat = cn.prepareStatement(sentenciaSql);
                prepStat.setInt(1, producto.codigo);
                prepStat.executeUpdate();
                JOptionPane.showMessageDialog(this, "Elimino correctamente " + producto.codigo);
                UpdateJTable();
            } catch (SQLException ex) {
                Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tablaProducto.repaint();
    }//GEN-LAST:event_btnEliminarActionPerformed

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
                tr= new TableRowSorter(tablaProducto.getModel());
                tablaProducto.setRowSorter(tr); 
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

    private void boxUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxUMActionPerformed
        // TODO add your handling code here:
        boxUM.transferFocus();
    }//GEN-LAST:event_boxUMActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        habilitar();
        btnAgregar.setText("Guardar");
        accion = "guardar";
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
       int clics = evt.getClickCount();
        int row = tablaProducto.rowAtPoint(evt.getPoint());
        if (clics == 2) {
            Producto p = productoTModel.productos.get(row);
            productoActual = p;
            txtCodigo.setText(String.valueOf(p.codigo.intValue()));           
            txtNombre.setText(p.nombre);
            boxTipo.getSelectedItem().toString();
            boxUM.getSelectedItem().toString();
            guardar = false;
        }
    }//GEN-LAST:event_tablaProductoMouseClicked

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxDepartamento;
    private javax.swing.JComboBox boxTipo;
    private javax.swing.JComboBox boxUM;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JToggleButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
