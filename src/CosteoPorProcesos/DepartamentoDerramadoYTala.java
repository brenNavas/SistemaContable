/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CosteoPorProcesos;

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
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Rodriguez
 */
public class DepartamentoDerramadoYTala extends javax.swing.JInternalFrame {
public productoTableModel departamentoTModel = new productoTableModel();
private Conexion mysql = new Conexion();
private Connection cn = mysql.conectar();
private String sentenciaSql = "";
    
    
    public DepartamentoDerramadoYTala() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        inicializarColumnas();
        consultaInicial();
        ocultarColumnas();
        
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
        tabladep1.setColumnModel(tColumnModel);
    }
    
       private void consultaInicial() {
        try {
            sentenciaSql = "SELECT * FROM producto WHERE proceso ='Desensamble' ORDER BY codigoProducto asc";
            Statement statement = this.cn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Producto producto = new Producto();
               producto.codigo = resultado.getInt("codigoProducto");
                producto.nombre = resultado.getString("nombreProducto");
                producto.tipo = resultado.getString("tipoProducto");
                producto.uMedida = resultado.getString("unidadMedida");
                producto.proceso = resultado.getString("proceso");
                       
             this.departamentoTModel.productos.add(producto);
            }
            tabladep1.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }
    
     private void UpdateJTable() {
        departamentoTModel.productos.clear();
        try {
            PreparedStatement statement = null;
            sentenciaSql = "SELECT * FROM producto WHERE proceso ='Desensamble' ORDER BY codigoProducto asc";
            statement = this.cn.prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.codigo = resultado.getInt("codigoProducto");
                producto.nombre = resultado.getString("nombreProducto");
                producto.tipo = resultado.getString("tipoProducto");
                producto.uMedida = resultado.getString("unidadMedida");
                producto.proceso=resultado.getString("proceso");
                       
             this.departamentoTModel.productos.add(producto);
            }
            tabladep1.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }
    
    void ocultarColumnas() { //ocultar las columnas que no quiero que se muestren
        
        tabladep1.getColumnModel().getColumn(2).setMaxWidth(0);
        tabladep1.getColumnModel().getColumn(2).setMinWidth(0);
        tabladep1.getColumnModel().getColumn(2).setPreferredWidth(0);
        
        tabladep1.getColumnModel().getColumn(4).setMaxWidth(0);
        tabladep1.getColumnModel().getColumn(4).setMinWidth(0);
        tabladep1.getColumnModel().getColumn(4).setPreferredWidth(0);
  
    }
    
    
    
    
     //método de filtro de datos
    private TableRowSorter tr;
    
    public void filtro(){
        tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),0,1,2));
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabladep1 = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Producto en proceso");
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desensamble", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 153)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 102)));

        tabladep1.setModel(departamentoTModel);
        jScrollPane1.setViewportView(tabladep1);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 470, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("DEPARTAMENTO 1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/derramado y tala_1.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 280, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        
         // filtro de datos en la tabla
        txtBuscar.addKeyListener(new KeyAdapter (){
            public void keyReleased(final KeyEvent e){
                String cadena = (txtBuscar.getText()).toUpperCase();
                txtBuscar.setText(cadena);
                repaint();
                filtro();
            }
      });
                tr= new TableRowSorter(tabladep1.getModel());
                tabladep1.setRowSorter(tr); 
    }//GEN-LAST:event_txtBuscarKeyTyped

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
            java.util.logging.Logger.getLogger(DepartamentoDerramadoYTala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartamentoDerramadoYTala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartamentoDerramadoYTala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartamentoDerramadoYTala.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new DepartamentoDerramadoYTala().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabladep1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
