/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadosFinancieros;

import Datos.Cuentas;
import Logica.Conexion;
import Logica.CuentaTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import EstadosFinancieros.EResultados;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
/**
 *
 * @author Yeseliz
 */
public class EstadoC extends javax.swing.JInternalFrame {

    CuentaTableModel ec1TModel = new CuentaTableModel();
    CuentaTableModel ec2TModel = new CuentaTableModel();
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sentenciaSql = "";

    /**
     * Creates new form EstadoC
     */
    public EstadoC() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Estado Capital"); //Título del Frame
        inicializarColumnas();
        inicializarColumnas2();
        ocultarColumnas();
        ocultarColumnas2();
        consultaInicial();
        consultaInicial2();
        fecha1.setText(fechaActual());
        fecha2.setText(fechaActual());
        capital();
    }

    void ocultarColumnas() { //ocultar las columnas que no quiero que se muestren

        //INVERSIONES
        tablaEC1.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaEC1.getColumnModel().getColumn(0).setMinWidth(0);
        tablaEC1.getColumnModel().getColumn(0).setPreferredWidth(0);

        tablaEC1.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaEC1.getColumnModel().getColumn(1).setMinWidth(0);
        tablaEC1.getColumnModel().getColumn(1).setPreferredWidth(0);

        tablaEC1.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaEC1.getColumnModel().getColumn(3).setMinWidth(0);
        tablaEC1.getColumnModel().getColumn(3).setPreferredWidth(0);

    }

    void ocultarColumnas2() {
        //DESINVERSIONES
        tablaEC2.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaEC2.getColumnModel().getColumn(0).setMinWidth(0);
        tablaEC2.getColumnModel().getColumn(0).setPreferredWidth(0);

        tablaEC2.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaEC2.getColumnModel().getColumn(1).setMinWidth(0);
        tablaEC2.getColumnModel().getColumn(1).setPreferredWidth(0);

        tablaEC2.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaEC2.getColumnModel().getColumn(3).setMinWidth(0);
        tablaEC2.getColumnModel().getColumn(3).setPreferredWidth(0);

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
        tablaEC1.setColumnModel(tColumnModel);
    }

    private void inicializarColumnas2() {
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
        tablaEC2.setColumnModel(tColumnModel);
    }

    private void consultaInicial() {
        try {

            sentenciaSql = "SELECT * FROM cuenta WHERE codigoCuenta = 31 AND saldoDeudor>0 OR codigoCuenta =31 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 310 AND saldoDeudor>0 OR codigoCuenta =310 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 32 AND saldoDeudor>0 OR codigoCuenta =32 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 320 AND saldoDeudor>0 OR codigoCuenta =320 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 33 AND saldoDeudor>0 OR codigoCuenta =33 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 330 AND saldoDeudor>0 OR codigoCuenta =330 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 331 AND saldoDeudor>0 OR codigoCuenta =331 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 331001 AND saldoDeudor>0 OR codigoCuenta =331001 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 331002 AND saldoDeudor>0 OR codigoCuenta =331002 AND saldoAcreedor>0 ORDER BY idCuenta";
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
                this.ec1TModel.cuentas.add(cuenta);
                //Para el capital contable
                //txtInversiones.setText(String.valueOf(Double.parseDouble(txtInversiones.getText()+cuenta.getSaldoAcreedor())));
                txtInversiones.setText(String.valueOf(Double.parseDouble(txtInversiones.getText())+cuenta.getSaldoAcreedor()));
            }
            tablaEC1.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

    private void consultaInicial2() {
        try {
            sentenciaSql = "SELECT * FROM cuenta WHERE codigoCuenta = 332 AND saldoDeudor>0 or codigoCuenta = 332 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 332001 AND saldoDeudor>0 or codigoCuenta = 332001 AND saldoAcreedor>0 "
                    + "OR codigoCuenta = 332002 AND saldoDeudor>0 or codigoCuenta = 332002 AND saldoAcreedor>0 "
                    + "order by idCuenta";
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
                this.ec2TModel.cuentas.add(cuenta);
                //Para el capital contable
                txtDesinversiones.setText(String.valueOf(Double.parseDouble(txtDesinversiones.getText()+cuenta.getSaldoDeudor())));
            }
            tablaEC2.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

    private void capital() {
        try {
            //txtCapitalC.setText("0.0");
            //txtDesinversiones.setText("0.0");
            //txtInversiones.setText("0.0");
            //String inversiones= txtInversiones.getText();
            //String desinversiones=txtDesinversiones.getText();
            
            double inversion= Double.parseDouble(txtInversiones.getText());
            double desinversion= Double.parseDouble(txtDesinversiones.getText());
            double capitalContable=inversion-desinversion;
            txtCapitalC.setText(String.valueOf(capitalContable));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        jPanel2 = new javax.swing.JPanel();
        fecha2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEC1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtCapitalC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEC2 = new javax.swing.JTable();
        txtDesinversiones = new javax.swing.JTextField();
        txtInversiones = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fecha1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 7));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fecha2.setBackground(new java.awt.Color(51, 51, 51));
        fecha2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 70, 78, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("ESTADO DE CAPITAL");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 49, -1, -1));

        tablaEC1.setBackground(new java.awt.Color(204, 204, 204));
        tablaEC1.setModel(ec1TModel);
        jScrollPane1.setViewportView(tablaEC1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 108, 360, 357));

        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Capital contable: $");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 524, -1, -1));

        txtCapitalC.setText("0.0");
        jPanel2.add(txtCapitalC, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 521, 77, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TAPICERIA BELEN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 26, -1, -1));

        tablaEC2.setBackground(new java.awt.Color(204, 204, 204));
        tablaEC2.setModel(ec2TModel);
        jScrollPane2.setViewportView(tablaEC2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 108, 372, 357));

        txtDesinversiones.setText("0.0");
        txtDesinversiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDesinversionesActionPerformed(evt);
            }
        });
        jPanel2.add(txtDesinversiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 77, -1));

        txtInversiones.setText("0.0");
        txtInversiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInversionesActionPerformed(evt);
            }
        });
        jPanel2.add(txtInversiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 74, -1));

        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Total de Inversiones: $");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Total de Desinversiones: $");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Del");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 72, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("al");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 72, -1, -1));

        fecha1.setBackground(new java.awt.Color(51, 51, 51));
        fecha1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 70, 78, -1));

        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 810, 570));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtInversionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInversionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInversionesActionPerformed

    private void txtDesinversionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDesinversionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesinversionesActionPerformed

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
            String tex3=jLabel7.getText()+" "+fecha1.getText()+" "+jLabel8.getText()+" "+fecha2.getText();

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
            Integer numColumns = ec1TModel.getColumnCount();
            Integer numRows=ec1TModel.getRowCount();
            //Integer col=1;

            String [][] data = new String[numRows][6];
            //int count=0;
            for (int a = 0; a < numRows; a++) {
                int count=0;
                for (int b = 0; b < numColumns; b++) {
                    if((b==2)||(b==4)||(b==5)){
                        data[a][count]=ec1TModel.getValueAt(a, b).toString();
                        count++;
                    }
                }
            }

            for (int a = 0; a < numRows; a++) {
                int count=3;
                for (int b = 0; b < numColumns; b++) {
                    if((b==2)||(b==4)||(b==5)){
                        data[a][count]=null;
                        count++;
                    }
                }
            }
            // We create the table (Creamos la tabla).
            PdfPTable table = new PdfPTable(6);
            // Now we fill the PDF table
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader;
            String[] c= {"Nombre Cuenta","Debe", "Haber",
                "Nombre Cuenta","Debe", "Haber",};
            // Fill table rows (rellenamos las filas de la tabla).
            for (int column = 0; column <6; column++) {
                columnHeader = new PdfPCell(new Phrase(c[column].toString()));
                //columnHeader = new PdfPCell(new Phrase("COL " + column));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < 6; column++) {
                    table.addCell(data[row][column]);
                }
            }

            float[] medidaCeldas = {2f, 0.55f, 0.55f, 2f, 0.55f, 0.55f};
            table.setWidths(medidaCeldas);

            document.add(table);

            document.add(new Paragraph ("  "));
            String tex4=jLabel5.getText()+" "+txtInversiones.getText()+"                        "
            +jLabel6.getText()+" "+txtDesinversiones.getText();
            String tex5=jLabel4.getText()+" "+txtCapitalC.getText();
            Paragraph p4 = new Paragraph(tex4);
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);
            document.add(new Paragraph ("  "));
            Paragraph p5 = new Paragraph(tex5);
            p5.setAlignment(Element.ALIGN_CENTER);
            document.add(p5);
            document.close();
            System.out.println("Se ha generado tu hoja PDF!");
        }catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento" + documentException);
        
        } catch (IOException ex) {
            Logger.getLogger(EstadoC.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(EstadoC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstadoC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstadoC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstadoC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstadoC().setVisible(true);
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEC1;
    private javax.swing.JTable tablaEC2;
    private javax.swing.JTextField txtCapitalC;
    private javax.swing.JTextField txtDesinversiones;
    private javax.swing.JTextField txtInversiones;
    // End of variables declaration//GEN-END:variables
}
