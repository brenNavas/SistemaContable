/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadosFinancieros;

import Datos.Cuentas;
import Logica.Conexion;
import Logica.CuentaTableModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
public class BalanceC extends javax.swing.JInternalFrame {

    CuentaTableModel bcTModel = new CuentaTableModel();
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sentenciaSql = "";

    /**
     * Creates new form BalanceC
     */
    public BalanceC() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Balance de Comprobación"); //Título del Frame
        inicializarColumnas();
        consultaInicial();
        ocultarColumnas();
        fecha1.setText(fechaActual());
        fecha2.setText(fechaActual());
        sumatoriaDeSaldos();

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
        tablaBC.setColumnModel(tColumnModel);
    }

    private void consultaInicial() {
        try {
            sentenciaSql = "SELECT * FROM cuenta WHERE saldoDeudor>0 or saldoAcreedor>0 order by idCuenta";
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
                this.bcTModel.cuentas.add(cuenta);
            }
            tablaBC.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }
    
    private void sumatoriaDeSaldos(){
        try {
             //Para cumplir partida doble
            String sql1 = "SELECT SUM(saldoDeudor) from cuenta";
            PreparedStatement statement1 = null;
            statement1 = this.cn.prepareStatement(sql1);
            ResultSet resultado1 = statement1.executeQuery();
            String sql2 = "SELECT SUM(saldoAcreedor) from cuenta";
            PreparedStatement statement2 = null;
            statement2 = this.cn.prepareStatement(sql2);
            ResultSet resultado2 = statement2.executeQuery();
            
            while (resultado1.next() && resultado2.next()) {
                //Total Debe
                String debe=resultado1.getString("SUM(saldoDeudor)");
                Double Debe= Double.parseDouble(debe);
                txtDebe.setText(String.valueOf(Debe));
                //Total Haber
                String haber=resultado2.getString("SUM(saldoAcreedor)");
                Double Haber= Double.parseDouble(haber);
                txtHaber.setText(String.valueOf(Haber));
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

    void ocultarColumnas() { //ocultar las columnas que no quiero que se muestren
        tablaBC.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaBC.getColumnModel().getColumn(0).setMinWidth(0);
        tablaBC.getColumnModel().getColumn(0).setPreferredWidth(0);

        tablaBC.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaBC.getColumnModel().getColumn(1).setMinWidth(0);
        tablaBC.getColumnModel().getColumn(1).setPreferredWidth(0);

        tablaBC.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaBC.getColumnModel().getColumn(3).setMinWidth(0);
        tablaBC.getColumnModel().getColumn(3).setPreferredWidth(0);

    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        fecha1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBC = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fecha2 = new javax.swing.JTextField();
        txtDebe = new javax.swing.JTextField();
        txtHaber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 7));

        fecha1.setBackground(new java.awt.Color(51, 51, 51));
        fecha1.setForeground(new java.awt.Color(255, 255, 255));
        fecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("BALANCE DE COMPROBACIÓN");

        tablaBC.setBackground(new java.awt.Color(204, 204, 204));
        tablaBC.setModel(bcTModel);
        jScrollPane1.setViewportView(tablaBC);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TAPICERIA BELEN");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Del");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("al");

        fecha2.setBackground(new java.awt.Color(51, 51, 51));
        fecha2.setForeground(new java.awt.Color(255, 255, 255));
        fecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha2ActionPerformed(evt);
            }
        });

        txtDebe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDebeActionPerformed(evt);
            }
        });

        txtHaber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHaberActionPerformed(evt);
            }
        });

        jLabel6.setText("Debe:");

        jLabel7.setText("Haber:");

        jButton1.setText("Guardar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(299, 299, 299))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 730, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha1ActionPerformed

    private void fecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha2ActionPerformed

    private void txtDebeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDebeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDebeActionPerformed

    private void txtHaberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHaberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHaberActionPerformed

    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String f1=null;
        //String a=null, b=null, c=null;
        JFileChooser dlg = new JFileChooser();
        int option = dlg.showSaveDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION){
            File f= dlg.getSelectedFile();
            f1 =f.toString();
        } 
        
        try {
            Document document = new Document();
            try {

                PdfWriter.getInstance(document, new FileOutputStream(f1+".pdf"));

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("(No se encontró el fichero para generar el pdf)" 
                        + fileNotFoundException);
            }
            document.open();
           Image image = Image.getInstance("src\\Imagenes\\LOGO1.png");
            document.add(image);
    
            document.addTitle("Reporte Pdf");
            document.addSubject("iText");
           
           
            String tex=jLabel3.getText();
            String tex1=jLabel2.getText();
            String tex3=jLabel4.getText()+" "+fecha1.getText()+" "+jLabel5.getText()+" "+fecha2.getText();
            
        try{
           Paragraph p = new Paragraph(tex);
           Paragraph p2 = new Paragraph(tex1);
           Paragraph p3 = new Paragraph(tex3);
           p.setAlignment(Element.ALIGN_CENTER);
           p2.setAlignment(Element.ALIGN_CENTER);
           p3.setAlignment(Element.ALIGN_CENTER);
           document.add(p);
           document.add(p2);
           document.add(p3);
           document.add(new Paragraph (" "));
           JOptionPane.showMessageDialog(null, "Documento creado");
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "error"+ e);

        }
            Integer numColumns = bcTModel.getColumnCount();
            Integer numRows=bcTModel.getRowCount();
            //Integer col=1;
            
            String [][] data = new String[numRows][3];
            //int count=0;
            for (int a = 0; a < numRows; a++) {
                int count=0;
                for (int b = 0; b < numColumns; b++) {
                   if((b==2)||(b==4)||(b==5)){
                data[a][count]=bcTModel.getValueAt(a, b).toString();
                   count++;
                           }
                }
            }
           
            // We create the table (Creamos la tabla).
            PdfPTable table = new PdfPTable(3); 
            // Now we fill the PDF table 
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader;
             String[] c= {"Nombre Cuenta","SaldoDeudor", "SaldoAcreedor"};
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int column = 0; column <3; column++) {
                columnHeader = new PdfPCell(new Phrase(c[column].toString()));
                //columnHeader = new PdfPCell(new Phrase("COL " + column));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < 3; column++) {
                table.addCell(data[row][column]);
                }
            }
           /*float[] medidaCeldas = {2f, 0.55f, 0.55f};
            table.setWidths(medidaCeldas);*/
            
            document.add(table);
            document.add(new Paragraph ("  "));
            String tex4="                     "+jLabel6.getText()+" "+txtDebe.getText()+"                         "
                   +jLabel7.getText()+" "+txtHaber.getText();
            Paragraph p4 = new Paragraph(tex4);
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);
            document.close();
            System.out.println("Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento" + documentException);
        } catch (IOException ex) {
            Logger.getLogger(BalanceC.class.getName()).log(Level.SEVERE, null, ex);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BalanceC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fecha1;
    private javax.swing.JTextField fecha2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBC;
    private javax.swing.JTextField txtDebe;
    private javax.swing.JTextField txtHaber;
    // End of variables declaration//GEN-END:variables
}
