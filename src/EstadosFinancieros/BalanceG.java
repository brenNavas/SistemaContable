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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Yeseliz
 */
public class BalanceG extends javax.swing.JInternalFrame {

    public CuentaTableModel bg1TModel = new CuentaTableModel();
    public CuentaTableModel bg2TModel = new CuentaTableModel();
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sentenciaSql = "";

    /**
     * Creates new form BalanceG
     */
    public BalanceG() {
        initComponents();
        setResizable(false); //no se maximice la pantalla
        setTitle("Balance General"); //Título del Frame
        inicializarColumnas();
        inicializarColumnas2();
        ocultarColumnas1();
        ocultarColumnas2();
        consultaInicial1();
        consultaInicial2();
        txtFecha.setText(fechaActual());
        sumatoriaDeSaldos();

    }

    void ocultarColumnas1() { //ocultar las columnas que no quiero que se muestren

        //ACTIVOS
        tablaBG1.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaBG1.getColumnModel().getColumn(0).setMinWidth(0);
        tablaBG1.getColumnModel().getColumn(0).setPreferredWidth(0);

        tablaBG1.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaBG1.getColumnModel().getColumn(1).setMinWidth(0);
        tablaBG1.getColumnModel().getColumn(1).setPreferredWidth(0);

        tablaBG1.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaBG1.getColumnModel().getColumn(3).setMinWidth(0);
        tablaBG1.getColumnModel().getColumn(3).setPreferredWidth(0);
    }

    void ocultarColumnas2() {
        //PASIVOS Y CAPITAL
        tablaBG2.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaBG2.getColumnModel().getColumn(0).setMinWidth(0);
        tablaBG2.getColumnModel().getColumn(0).setPreferredWidth(0);

        tablaBG2.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaBG2.getColumnModel().getColumn(1).setMinWidth(0);
        tablaBG2.getColumnModel().getColumn(1).setPreferredWidth(0);

        tablaBG2.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaBG2.getColumnModel().getColumn(3).setMinWidth(0);
        tablaBG2.getColumnModel().getColumn(3).setPreferredWidth(0);

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
        tablaBG1.setColumnModel(tColumnModel);
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
        tablaBG2.setColumnModel(tColumnModel);
    }

    private void consultaInicial1() {
        try {
            sentenciaSql = "SELECT * FROM Cuenta WHERE tipo = 'Activo' AND saldoDeudor>0 OR tipo = 'Activo' AND saldoAcreedor>0 ORDER BY 'idCuenta'";
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
                this.bg1TModel.cuentas.add(cuenta);
            }
            tablaBG1.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

    private void consultaInicial2() {
        try {
            sentenciaSql = "SELECT * FROM Cuenta WHERE tipo = 'Pasivo' AND saldoDeudor>0 OR tipo = 'Pasivo' AND saldoAcreedor>0 OR tipo='Capital' AND saldoDeudor>0 OR tipo = 'Capital' AND saldoAcreedor>0 ORDER BY 'idCuenta'";
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
                this.bg2TModel.cuentas.add(cuenta);
            }
            tablaBG2.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }

    private void sumatoriaDeSaldos(){
        try {
           //Para cumplir dualidad economica
            //Activos
            String sql1 = "SELECT SUM(saldoDeudor) from cuenta WHERE tipo='Activo'";
            PreparedStatement statement1 = null;
            statement1 = this.cn.prepareStatement(sql1);
            ResultSet resultado1 = statement1.executeQuery();
            //Pasivos
            String sql2 = "SELECT SUM(saldoAcreedor) from cuenta WHERE tipo='Pasivo'";
            PreparedStatement statement2 = null;
            statement2 = this.cn.prepareStatement(sql2);
            ResultSet resultado2 = statement2.executeQuery();
            //Capital
            String sql3= "SELECT SUM(saldoAcreedor) from cuenta WHERE tipo='Capital'";
            PreparedStatement stamStatement3= null;
            stamStatement3= this.cn.prepareStatement(sql3);
            ResultSet resultado3=stamStatement3.executeQuery();
            
            while(resultado1.next() && resultado2.next() && resultado3.next()){
                //Total de activos
                String totalActivos = resultado1.getString("SUM(saldoDeudor)");
                Double totalDebe= Double.parseDouble(totalActivos);
                txtTotalA.setText(String.valueOf(totalDebe));
                //Total de pasivos
                String totalPacivos = resultado2.getString("SUM(saldoAcreedor)");
                //Total capital
                String totalCapital=resultado3.getString("SUM(saldoAcreedor)");
                
                //calculo
                Double capital= Double.parseDouble(totalCapital);
                Double pasivo= Double.parseDouble(totalPacivos);
                Double totalHaber=capital+pasivo;
                txtTotalP.setText(String.valueOf(totalHaber));
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }
    }
    /*private void total(){
     try {
     //Para cumplir dualidad economica
     //Activos
     String sql1 = "SELECT SUM(saldoDeudor) from cuenta WHERE cuenta.tipo='Activo'";
     PreparedStatement statement1 = null;
     statement1 = this.cn.prepareStatement(sql1);
     ResultSet resultado1 = statement1.executeQuery();
     //Pasivos
     String sql2 = "SELECT SUM(saldoAcreedor) from cuenta WHERE cuenta.tipo='Pasivo'";
     PreparedStatement statement2 = null;
     statement2 = this.cn.prepareStatement(sql2);
     ResultSet resultado2 = statement2.executeQuery();
            
            
     } catch (Exception e) {
     }
    
     }*/
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBG2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtTotalA = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaBG1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotalP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("BALANCE GENERAL");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 49, -1, -1));

        tablaBG2.setBackground(new java.awt.Color(204, 204, 204));
        tablaBG2.setModel(bg2TModel);
        tablaBG2.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        tablaBG2.setRowHeight(12);
        jScrollPane1.setViewportView(tablaBG2);
        tablaBG2.getAccessibleContext().setAccessibleParent(tablaBG2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 136, 498, 401));

        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Total de activos: $");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 546, -1, -1));

        txtTotalA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAActionPerformed(evt);
            }
        });
        jPanel2.add(txtTotalA, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 543, 87, -1));

        tablaBG1.setBackground(new java.awt.Color(204, 204, 204));
        tablaBG1.setModel(bg1TModel);
        tablaBG1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaBG1.setAutoscrolls(false);
        tablaBG1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        tablaBG1.setRequestFocusEnabled(false);
        tablaBG1.setRowHeight(12);
        tablaBG1.setRowMargin(2);
        tablaBG1.setSelectionForeground(new java.awt.Color(51, 0, 51));
        tablaBG1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tablaBG1);
        tablaBG1.getAccessibleContext().setAccessibleParent(tablaBG1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 136, 482, 401));

        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Total de participaciones: $");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(805, 546, -1, -1));
        jPanel2.add(txtTotalP, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 543, 87, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TAPICERIA BELEN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 26, -1, -1));

        txtFecha.setBackground(new java.awt.Color(51, 51, 51));
        txtFecha.setForeground(new java.awt.Color(204, 204, 204));
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel2.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 70, 88, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Al");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 72, 17, -1));

        jButton1.setText("Guardar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1060, 590));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtTotalAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAActionPerformed

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
            Image image = null;
            try {
                
                image = Image.getInstance("src\\Imagenes\\LOGO1.png");
            } catch (BadElementException ex) {
                Logger.getLogger(BalanceG.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BalanceG.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.add(image);

            document.addTitle("Reporte Pdf");
            document.addSubject("iText");

            String tex=jLabel3.getText();
            String tex1=jLabel2.getText();
            String tex3=jLabel6.getText()+" "+txtFecha.getText();

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
            Integer numColumns = bg1TModel.getColumnCount();
            Integer numRows=bg1TModel.getRowCount();
            //Integer col=1;

            String [][] data = new String[numRows][6];
            //int count=0;
            for (int a = 0; a < numRows; a++) {
                int count=0;
                for (int b = 0; b < numColumns; b++) {
                    if((b==2)||(b==4)||(b==5)){
                        data[a][count]=bg1TModel.getValueAt(a, b).toString();
                        count++;
                    }
                }
            }

            for (int a = 0; a < numRows; a++) {
                int count=3;
                for (int b = 0; b < numColumns; b++) {
                    if((b==2)||(b==4)||(b==5)){
                        data[a][count]="0.00";
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
            String tex4=jLabel4.getText()+" "+txtTotalA.getText()+"                        "
            +jLabel5.getText()+" "+txtTotalP.getText();
            Paragraph p4 = new Paragraph(tex4);
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);
            document.add(new Paragraph ("  "));
            document.add(new Paragraph ("  "));
            document.add(new Paragraph ("  "));
            document.add(new Paragraph ("F. ___________________        F.________________"
                + "               F.___________________"));
        document.add(new Paragraph ("        Contador                                     Auditor"
            + "                                     Representante Legal"));
    document.close();
    System.out.println("Se ha generado tu hoja PDF!");
    } catch (DocumentException documentException) {
        System.out.println("Se ha producido un error al generar un documento" + documentException);
        }        // TODO add your handling code here:ehandling code here:
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
            java.util.logging.Logger.getLogger(BalanceG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaBG1;
    private javax.swing.JTable tablaBG2;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTotalA;
    private javax.swing.JTextField txtTotalP;
    // End of variables declaration//GEN-END:variables
}
