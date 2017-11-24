/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadosFinancieros;

import Logica.Conexion;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Yeseliz
 */
public class EResultados extends javax.swing.JInternalFrame {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sentenciaSql = "";
    public static Double utilidadReinvertida;

    /**
     * Creates new form EResultados
     */
    public EResultados() {
        initComponents();
        resultado();
        fecha1.setText(fechaActual());
        fecha2.setText(fechaActual());
    }

    public void consulta() {

    }

    private void resultado() {
        try {
            //Ventas netas
            String sql1 = "SELECT saldoAcreedor FROM cuenta WHERE codigoCuenta='510'";
            PreparedStatement statement1 = null;
            statement1 = this.cn.prepareStatement(sql1);
            ResultSet resultado1 = statement1.executeQuery();

            String sql2 = "SELECT saldoAcreedor FROM cuenta WHERE codigoCuenta='510002'";
            PreparedStatement statement2 = null;
            statement2 = this.cn.prepareStatement(sql2);
            ResultSet resultado2 = statement2.executeQuery();

            String sql3 = "SELECT saldoDeudor from cuenta where codigoCuenta='414'";
            PreparedStatement statement3 = null;
            statement3 = this.cn.prepareStatement(sql3);
            ResultSet resultado3 = statement3.executeQuery();
            
            //Costo de lo vendido
            String sql4 = "SELECT saldoDeudor from cuenta WHERE codigoCuenta='411'";
            PreparedStatement statement4 = null;
            statement4 = this.cn.prepareStatement(sql4);
            ResultSet resultado4 = statement4.executeQuery();
            //Gastos de operacion
            String sql5= "SELECT saldoDeudor from cuenta WHERE codigoCuenta='41'";
            PreparedStatement statement5 = null;
            statement5 = this.cn.prepareStatement(sql5);
            ResultSet resultado5 = statement5.executeQuery();
            //Gastos financieros
            String sql6="SELECT saldoDeudor from cuenta WHERE codigoCuenta='417'";
            PreparedStatement statement6 = null;
            statement6 = this.cn.prepareStatement(sql6);
            ResultSet resultado6 = statement6.executeQuery();
            //Otros ingresos
            String sql7="SELECT saldoAcreedor from cuenta WHERE codigoCuenta='520'";
            PreparedStatement statement7 = null;
            statement7 = this.cn.prepareStatement(sql7);
            ResultSet resultado7 = statement7.executeQuery();
            //Impuestos
            String sql8="SELECT saldoDeudor from cuenta WHERE codigoCuenta='415010'";
            PreparedStatement statement8 = null;
            statement8 = this.cn.prepareStatement(sql8);
            ResultSet resultado8 = statement8.executeQuery();
            
            while (resultado1.next() && resultado2.next() && resultado3.next() && resultado4.next() && resultado5.next() && resultado6.next() && resultado7.next() && resultado8.next()) {
                //Utilidad bruta
                String ventasAlContado = resultado1.getString("saldoAcreedor");
                String ventasAlCredito = resultado2.getString("saldoAcreedor");
                String rebajas = resultado3.getString("saldoDeudor");
                Double vContado = Double.parseDouble(ventasAlContado);
                Double vCredito = Double.parseDouble(ventasAlCredito);
                Double rebajaVenta = Double.parseDouble(rebajas);
                Double ventasNeta = vContado + vCredito - rebajaVenta;
                txtVentasNetas.setText(String.valueOf(ventasNeta));

                String costoDeLovendido = resultado4.getString("saldoDeudor");
                //Calculo
                Double CostoVendido = Double.parseDouble(costoDeLovendido);
                txtCostoVendido.setText(String.valueOf(CostoVendido));
                
                Double UtilidadBruta = ventasNeta - CostoVendido;
                //Resultado
                txtUtilidadBruta.setText(String.valueOf(UtilidadBruta));
                
                //Utilidad de operacion
                String gastoOperacion= resultado5.getString("SaldoDeudor");              
                //Calculo
                Double gastoOp= Double.parseDouble(gastoOperacion);
                txtGastosO.setText(String.valueOf(gastoOp));
                
                Double UtilidadOperacion= UtilidadBruta-gastoOp;
                txtUtilidadO.setText(String.valueOf(UtilidadOperacion));
                
                //Utilidad antes de  otros ingreso
                String gastoFin= resultado6.getString("SaldoDeudor");
                //Calculo
                Double gastoFinanciero= Double.parseDouble(gastoFin);
                txtGastosFinancieros.setText(String.valueOf(gastoFinanciero));
                
                Double UtilidadAntesDeOtroIngresos= UtilidadOperacion-gastoFinanciero;
                txtUtilidadAI.setText(String.valueOf(UtilidadAntesDeOtroIngresos));
                
                //Utilidad antes de impuesto
                String OtroIngreso= resultado7.getString("saldoAcreedor");
                //Calculo
                Double OtrosIngresosNetos= Double.parseDouble(OtroIngreso);
                txtOtrosIngresosNetos.setText(String.valueOf(OtrosIngresosNetos));
                
                Double UtilidadAntesDeImpuesto=UtilidadAntesDeOtroIngresos+OtrosIngresosNetos;
                txtUAI.setText(String.valueOf(UtilidadAntesDeImpuesto));
                
                //Utilidad neta
                String imp= resultado8.getString("saldoDeudor");
                Double impuestos= Double.parseDouble(imp);
                txtImpuestos.setText(String.valueOf(impuestos));
                
                Double UtilidadNeta=UtilidadAntesDeImpuesto-impuestos;
                txtUtilidadNeta.setText(String.valueOf(UtilidadNeta));
                //Para Estado de capital
                UtilidadNeta=utilidadReinvertida;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los cuentas de la base de datos");
            ex.printStackTrace();
        }

    }

    public static String fechaActual(){
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fecha1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fecha2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtVentasNetas = new javax.swing.JTextField();
        txtGastosO = new javax.swing.JTextField();
        txtCostoVendido = new javax.swing.JTextField();
        txtUtilidadBruta = new javax.swing.JTextField();
        txtUtilidadO = new javax.swing.JTextField();
        txtGastosFinancieros = new javax.swing.JTextField();
        txtUtilidadAI = new javax.swing.JTextField();
        txtOtrosIngresosNetos = new javax.swing.JTextField();
        txtUAI = new javax.swing.JTextField();
        txtImpuestos = new javax.swing.JTextField();
        txtUtilidadNeta = new javax.swing.JTextField();
        ImprimirPDF = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 7));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("ESTADO DE RESULTADOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 39, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TAPICERIA BELEN");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 18, -1, -1));

        fecha1.setBackground(new java.awt.Color(51, 51, 51));
        fecha1.setForeground(new java.awt.Color(255, 255, 255));
        fecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha1ActionPerformed(evt);
            }
        });
        jPanel1.add(fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 65, 66, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Del");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 67, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("al");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 67, -1, -1));

        fecha2.setBackground(new java.awt.Color(51, 51, 51));
        fecha2.setForeground(new java.awt.Color(255, 255, 255));
        fecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha2ActionPerformed(evt);
            }
        });
        jPanel1.add(fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 65, 66, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("- Costo de lo vendido");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 181, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Ventas Netas");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 155, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("= Utilidad Bruta");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 214, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("- Gastos de Operación");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("= Utilidad de operación");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 285, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 206, 229, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 266, 229, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("- Impuestos");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 436, -1, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 333, 232, 7));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("= Utilidad antes de otros ingresos");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 346, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("+ Otros ingresos netos");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 372, -1, -1));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 462, 232, 7));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("= Utilidad antes de impuestos");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 410, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("- Gastos Financieros");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 307, -1, -1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 398, 227, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("= Utilidad Neta");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 475, -1, -1));

        txtVentasNetas.setBackground(new java.awt.Color(153, 153, 153));
        txtVentasNetas.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtVentasNetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 155, 75, -1));

        txtGastosO.setBackground(new java.awt.Color(153, 153, 153));
        txtGastosO.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtGastosO, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 240, 75, -1));

        txtCostoVendido.setBackground(new java.awt.Color(153, 153, 153));
        txtCostoVendido.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtCostoVendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 181, 75, -1));

        txtUtilidadBruta.setBackground(new java.awt.Color(153, 153, 153));
        txtUtilidadBruta.setForeground(new java.awt.Color(255, 255, 255));
        txtUtilidadBruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUtilidadBrutaActionPerformed(evt);
            }
        });
        jPanel1.add(txtUtilidadBruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 214, 75, -1));

        txtUtilidadO.setBackground(new java.awt.Color(153, 153, 153));
        txtUtilidadO.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtUtilidadO, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 282, 75, -1));

        txtGastosFinancieros.setBackground(new java.awt.Color(153, 153, 153));
        txtGastosFinancieros.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtGastosFinancieros, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 307, 75, -1));

        txtUtilidadAI.setBackground(new java.awt.Color(153, 153, 153));
        txtUtilidadAI.setForeground(new java.awt.Color(255, 255, 255));
        txtUtilidadAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUtilidadAIActionPerformed(evt);
            }
        });
        jPanel1.add(txtUtilidadAI, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 346, 75, -1));

        txtOtrosIngresosNetos.setBackground(new java.awt.Color(153, 153, 153));
        txtOtrosIngresosNetos.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtOtrosIngresosNetos, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 372, 75, -1));

        txtUAI.setBackground(new java.awt.Color(153, 153, 153));
        txtUAI.setForeground(new java.awt.Color(255, 255, 255));
        txtUAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUAIActionPerformed(evt);
            }
        });
        jPanel1.add(txtUAI, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 75, -1));

        txtImpuestos.setBackground(new java.awt.Color(153, 153, 153));
        txtImpuestos.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtImpuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 436, 75, -1));

        txtUtilidadNeta.setBackground(new java.awt.Color(153, 153, 153));
        txtUtilidadNeta.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(txtUtilidadNeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 475, 75, -1));

        ImprimirPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        jPanel1.add(ImprimirPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 30, 90, -1));

        jButton2.setText("Guargar Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 580, 550));

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha1ActionPerformed

    private void fecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha2ActionPerformed

    private void txtUtilidadBrutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidadBrutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadBrutaActionPerformed

    private void txtUAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUAIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUAIActionPerformed

    private void txtUtilidadAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUtilidadAIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUtilidadAIActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
           
           
            String tex=jLabel4.getText();
            String tex1=jLabel2.getText();
            String tex3=jLabel3.getText()+" "+fecha1.getText()+" "+jLabel5.getText()+" "+fecha2.getText();
            
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
                        
            
        String [][] reporte = {{jLabel7.getText(), txtVentasNetas.getText()},
        {jLabel6.getText(), txtCostoVendido.getText()},
        {jLabel8.getText(), txtUtilidadBruta.getText()},
        {jLabel9.getText(), txtGastosO.getText()},
        {jLabel10.getText(), txtUtilidadO.getText()},
        {jLabel15.getText(), txtGastosFinancieros.getText()},
        {jLabel12.getText(), txtUtilidadAI.getText()},
        {jLabel13.getText(), txtOtrosIngresosNetos.getText()},
        {jLabel14.getText(), txtUAI.getText()},
        {jLabel11.getText(), txtImpuestos.getText()},
        {jLabel16.getText(), txtUtilidadNeta.getText()}};
        
        
        Integer numColumns = 2;
            Integer numRows=11;
            PdfPTable table = new PdfPTable(2); 
            // Now we fill the PDF table 
            // Ahora llenamos la tabla del PDF
            PdfPCell columnHeader;
             String[] c= {" "," "};
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int column = 0; column <2; column++) {
                columnHeader = new PdfPCell(new Phrase(c[column].toString()));
                //columnHeader = new PdfPCell(new Phrase("COL " + column));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            // Fill table rows (rellenamos las filas de la tabla).                
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < 2; column++) {
                table.addCell(reporte[row][column]);
                }
            }
                        
            document.add(table);
            document.close();
            System.out.println("Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento" + documentException);
        
        } catch (IOException ex) {
            Logger.getLogger(EResultados.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(EResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EResultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EResultados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ImprimirPDF;
    private javax.swing.JTextField fecha1;
    private javax.swing.JTextField fecha2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txtCostoVendido;
    private javax.swing.JTextField txtGastosFinancieros;
    private javax.swing.JTextField txtGastosO;
    private javax.swing.JTextField txtImpuestos;
    private javax.swing.JTextField txtOtrosIngresosNetos;
    private javax.swing.JTextField txtUAI;
    private javax.swing.JTextField txtUtilidadAI;
    private javax.swing.JTextField txtUtilidadBruta;
    private javax.swing.JTextField txtUtilidadNeta;
    private javax.swing.JTextField txtUtilidadO;
    private javax.swing.JTextField txtVentasNetas;
    // End of variables declaration//GEN-END:variables
}
