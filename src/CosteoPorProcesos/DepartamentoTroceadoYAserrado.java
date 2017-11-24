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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yeseliz
 */
public class DepartamentoTroceadoYAserrado extends javax.swing.JInternalFrame {
public productoTableModel departamentoTModel = new productoTableModel();
private Conexion mysql = new Conexion();
private Connection cn = mysql.conectar();
private String sentenciaSql = "";

    /**
     * Creates new form DepartamentoTroceadoYAserrado
     */
    public DepartamentoTroceadoYAserrado() {
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
        tabladep2.setColumnModel(tColumnModel);
    }
    
       private void consultaInicial() {
        try {
            sentenciaSql = "SELECT * FROM producto WHERE proceso ='Corte y Costura' ORDER BY codigoProducto asc";
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
            tabladep2.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }
    
     private void UpdateJTable() {
        departamentoTModel.productos.clear();
        try {
            PreparedStatement statement = null;
            sentenciaSql = "SELECT * FROM producto WHERE proceso ='Corte y Costura' ORDER BY codigoProducto asc";
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
            tabladep2.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }
    
     
     void ocultarColumnas() { //ocultar las columnas que no quiero que se muestren
        
        tabladep2.getColumnModel().getColumn(2).setMaxWidth(0);
        tabladep2.getColumnModel().getColumn(2).setMinWidth(0);
        tabladep2.getColumnModel().getColumn(2).setPreferredWidth(0);
        
        tabladep2.getColumnModel().getColumn(4).setMaxWidth(0);
        tabladep2.getColumnModel().getColumn(4).setMinWidth(0);
        tabladep2.getColumnModel().getColumn(4).setPreferredWidth(0);
  
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabladep2 = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Producto en proceso");
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corte y Costura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 153, 153)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 102)));

        tabladep2.setModel(departamentoTModel
        );
        jScrollPane2.setViewportView(tabladep2);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 460, 310));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("DEPARTAMENTO 2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, 20));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/depart2.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-8, -25, 720, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

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
                tr= new TableRowSorter(tabladep2.getModel());
                tabladep2.setRowSorter(tr); 
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabladep2;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
