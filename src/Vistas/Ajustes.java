/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Datos.Ajuste;
import Datos.Cuentas;
import Datos.Mensajeria;
import Datos.MovimientoCuenta;
import Datos.Periodo;
import Datos.Transacciones;
import Logica.Conexion;
import Logica.CuentaTableModel;
import Logica.DiseñoTexField;
import Logica.MyTableCellEditor;
import java.sql.Date;
import java.util.Calendar;


import Logica.TransaccionTModel;


import static Vistas.CGeneral.inicioCG;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author Yeseliz
 */
public class Ajustes extends javax.swing.JInternalFrame {
public TransaccionTModel transaccionTModel = new TransaccionTModel();
public TransaccionTModel transaccionesTModel = new TransaccionTModel();
public List<MovimientoCuenta> movimientos = new ArrayList<MovimientoCuenta>();
public CuentaTableModel cuentaTModel = new CuentaTableModel();


    DefaultTableModel m;
    static double total;
    double sub_total;
    private Object[][] dtCuenta;
    static double total2;
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sentenciaSql = "";
    private String sentenciaSql2 ="";
    Mensajeria mensaje;
    DiseñoTexField diseño;
    private String accion = "guardar";
    Double debe =0.0;
    Double haber = 0.0;
    //Otros
    Connection con;
    PreparedStatement p;
    ResultSet resultado;
    
    
 //Constante del IVA.
    protected final static double IVA = 0.13;
    

    /**
     * Creates new form NuevaTransacción
     */
    public Ajustes() {
        initComponents();
        
        setResizable(false); //no se maximice la pantalla
        setTitle("Ajustes"); //Título del Frame
      txtMonto1.setEnabled(false);
      autoCompletarNombre();
      autoCompletarCodigo();
     
        Calendar c2 = new GregorianCalendar();
        fechaDate.setCalendar(c2);
        inhabilitarGuardar();
        //Actualizar_Tabla();
       total = 0.0;
       total2= 0.0;
       /*
       
        //oculta columna ID
        tablaCuentasAgregadas.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaCuentasAgregadas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tablaCuentasAgregadas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        tablaCuentasAgregadas.getColumnModel().getColumn(3).setMaxWidth(3);
        tablaCuentasAgregadas.getColumnModel().getColumn(3).setMinWidth(3);
        tablaCuentasAgregadas.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(3);
        tablaCuentasAgregadas.getTableHeader().getColumnModel().getColumn(3).setMinWidth(3);
        //editor de celdas
        
        tablaCuentasAgregadas.getColumnModel().getColumn( 1 ).setCellEditor(new MyTableCellEditor(mysql,"codigoCuenta"));//Columna código
        tablaCuentasAgregadas.getColumnModel().getColumn( 2 ).setCellEditor(new MyTableCellEditor(mysql,"nombreCuenta"));//Columna nombre
        tablaCuentasAgregadas.getColumnModel().getColumn( 4 ).setCellEditor(new MyTableCellEditor(mysql,"saldoDeudor"));//Columna saldoDeudor
        tablaCuentasAgregadas.getColumnModel().getColumn( 5 ).setCellEditor(new MyTableCellEditor(mysql,"saldoAcreedor"));//Columna saldoAcreedor
    
      //Indicaciones de los textField
       mensaje = new Mensajeria();
       diseño = new DiseñoTexField();
       Iniciar();*/
    }
    
    public void Iniciar() {
        diseño.Mensaje(txtNombreCuenta, mensaje.getCodigo(), 0);
        diseño.Mensaje(txtSaldoDebe, mensaje.getDebe(), 0);
        diseño.Mensaje(txtSaldoHaber, mensaje.getHaber(), 0);
    }
    
    public void autoCompletarNombre() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtNombreCuenta);

        sentenciaSql = "SELECT  nombreCuenta FROM cuenta ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSql);
            while (rs.next()) {
           
                textAutoC.addItem(rs.getString("nombreCuenta"));
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo autocompletar nombres de las cuentas ");
         
        }
   }
    
    void inhabilitarGuardar(){
        btnGuardar.setEnabled(false);
    }
    
    //Método para que el botón guardar solo se active cuando debe = haber
    
    void activarOpcionGuardar(){
        if(txtDebe1.getText().equals(txtHaber2.getText()) && Double.parseDouble(txtDebe1.getText())!=0.0 ){
            partidaDoble.setText("Estado partida doble: Si");
           
            btnGuardar.setEnabled(true);
            
            txtMonto1.setText(txtDebe1.getText());
        }else{
            partidaDoble.setText("Estado partida doble: No");
            btnGuardar.setEnabled(false);
        }
    }
    
    
    void agregarEnTabla() { //mostrar datos en la tabla
        DefaultTableModel modelo = (DefaultTableModel)  tablaCuentasAgregadas.getModel();
        Object []datosCuenta = new Object[4];
        datosCuenta[0]=txtCodigoCuenta.getText();
        datosCuenta[1]=txtNombreCuenta.getText();
        datosCuenta[2]=Double.parseDouble(txtSaldoDebe.getText());
        datosCuenta[3]=Double.parseDouble(txtSaldoHaber.getText());
        modelo.addRow(datosCuenta);
        tablaCuentasAgregadas.setModel(modelo);
    }
          
        /*
        
        if(boxTipoT.getSelectedItem().toString()=="Gastos Pagados por Adelantado"){ 
            
            double ivaPagadoActual=0.0;
            int m=-1;
            for(int p=0;p<tablaCuentasAgregadas.getRowCount();p++)
            {
                if(tablaCuentasAgregadas.getValueAt(p,0).toString()=="217002")
                {
                    ivaPagadoActual=Double.parseDouble(tablaCuentasAgregadas.getValueAt(p,2).toString());
                    m=p;
                    break;
                    
                }
                
            }
            Double tiempo = Double.valueOf(txtTiempo1.getText());
            Double caja = Double.valueOf(txtSaldoHaber.getText());
            if(ivaPagadoActual==0.0){
                datosCuenta[0]="217002"; //codigo de la cuenta 
                datosCuenta[1]="Gastos Pagados por Adelantado";//cuenta activo 
                datosCuenta[2]=Double.parseDouble(txtSaldoHaber.getText()); //lado debe
                datosCuenta[3]=Double.parseDouble(txtSaldoHaber.getText())/tiempo;//lado haber
                
                
                
                modelo.addRow(datosCuenta);
                tablaCuentasAgregadas.setModel(modelo);   
            }
            else
            {   
                
                tablaCuentasAgregadas.setValueAt(Double.parseDouble(txtSaldoDebe.getText())+ivaPagadoActual,m,2);
            }
            
        }
        
        if(boxTipoT.getSelectedItem().toString()=="Depreciacion"){ 
            
            double ivaCobradoActual=0.0;
            int m=-1;
            for(int p=0;p<tablaCuentasAgregadas.getRowCount();p++)
            {
                if(tablaCuentasAgregadas.getValueAt(p,0).toString()=="122")
                {
                    ivaCobradoActual=Double.parseDouble(tablaCuentasAgregadas.getValueAt(p,3).toString());
                    m=p;
                    break;
                    
                }
                
            }
            if(ivaCobradoActual==0.0){
                Double tiempo = Double.valueOf(txtTiempo1.getText());
                
                
                datosCuenta[0]="122"; //codigo de la cuenta 
                datosCuenta[1]="Depreciación Acumulada";
                datosCuenta[2]=Double.parseDouble("0.0");//lado debe
                datosCuenta[3]=Double.parseDouble(txtSaldoHaber.getText())*1/12*tiempo; //lado haber
                modelo.addRow(datosCuenta);
                tablaCuentasAgregadas.setModel(modelo);   
            }
            else
            {
                tablaCuentasAgregadas.setValueAt(Double.parseDouble(txtSaldoHaber.getText())+ivaCobradoActual,m,3);
            }
            
        }
    }
        */
       
                    
            void agregarMovimiento(){
            for (int i = 0; i < movimientos.size(); i++) {
                Conexion.ejecutarActualizacion("movimientocuenta", "monto, tipo, transaccion, cuenta", "Double, int, int, long", new String[]{Double.toString(movimientos.get(i).getMonto()), Integer.toString(movimientos.get(i).getTipo()), Integer.toString(movimientos.get(i).getTransaccion().getIdTransaccion()), Long.toString(movimientos.get(i).getCuenta().getCodigoCuenta())});
                Cuentas cuentaAfectada = movimientos.get(i).getCuenta();

                if (movimientos.get(i).getTipo() == 0) {//si es abono
                    String sql = "UPDATE cuenta SET saldoDeudor=" + cuentaAfectada.getSaldoDeudor().doubleValue() + " WHERE codigoCuenta=" + cuentaAfectada.getCodigoCuenta();
                    Conexion.ejecutarActualizacion(sql);
                } else {//si es cargo
                    String sql = "UPDATE cuenta SET saldoAcreedor=" + cuentaAfectada.getSaldoAcreedor().doubleValue() + " WHERE codigoCuenta=" + cuentaAfectada.getCodigoCuenta();
                    Conexion.ejecutarActualizacion(sql);
                }
                String sql = "UPDATE cuenta SET saldo=" + cuentaAfectada.getSaldo().doubleValue() + " WHERE codigoCuenta=" + cuentaAfectada.getCodigoCuenta();
                Conexion.ejecutarActualizacion(sql);
            }
            movimientos.clear();
            //ret = true;
          // return ret;
        }
  
    //metodo para calcular suma total
    public void calcular(){
    Double totalDebe = 0.0;
    Double celdaDebe = 0.0;
    Double celdaHaber= 0.0;
    Double totalHaber = 0.0;
    for (int i = 0; i < tablaCuentasAgregadas.getRowCount(); i++) {
        celdaDebe = Double.parseDouble(String.valueOf(tablaCuentasAgregadas.getValueAt(i,2)));
        totalDebe=totalDebe+celdaDebe;
        
        celdaHaber=Double.parseDouble(String.valueOf(tablaCuentasAgregadas.getValueAt(i,3)));
        totalHaber=totalHaber+celdaHaber;
    }
    txtDebe1.setText(String.valueOf(totalDebe));
    txtHaber2.setText(String.valueOf(totalHaber));
}
    
    
    
    
    


    //eliminar cuentas agregadas en la tabla
    private void eliminarMovimiento() {
        DefaultTableModel modelo = (DefaultTableModel)  tablaCuentasAgregadas.getModel();
        Object []datosCuenta = new Object[4];
        
        int a = tablaCuentasAgregadas.getSelectedRow();
        if(a<0){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila de la tabla" );
        }
        else{
            int confirmar=JOptionPane.showConfirmDialog(null,"Esta seguro que desea Eliminar el registro? ");
            if(JOptionPane.OK_OPTION==confirmar){
                
                
                if(boxTipoT.getSelectedItem().toString()=="Gastos Pagados por Adelantado"){ 
            
                    double ivaPagadoActual=0.0;
                    int m=-1;
                    for(int p=0;p<tablaCuentasAgregadas.getRowCount();p++)
                    {
                        if(tablaCuentasAgregadas.getValueAt(p,0).toString()=="115")
                        {
                            ivaPagadoActual=Double.parseDouble(tablaCuentasAgregadas.getValueAt(p,2).toString());
                            m=p;
                            tablaCuentasAgregadas.setValueAt(ivaPagadoActual-Double.parseDouble(tablaCuentasAgregadas.getValueAt(a,2).toString())*0.13,m,2);
                            break;  
                        }
                    }
  
                }
                if(boxTipoT.getSelectedItem().toString()=="Venta"){ //Si es compra
            
                    double ivaCobradoActual=0.0;
                    int m=-1;
                    for(int p=0;p<tablaCuentasAgregadas.getRowCount();p++)
                    {
                        if(tablaCuentasAgregadas.getValueAt(p,0).toString()=="213")
                        {
                            ivaCobradoActual=Double.parseDouble(tablaCuentasAgregadas.getValueAt(p,3).toString()); 
                            m=p;
                            tablaCuentasAgregadas.setValueAt(ivaCobradoActual-Double.parseDouble(tablaCuentasAgregadas.getValueAt(a,3).toString())*0.13,m,3);
                            break;  
                        }
                    }
  
                }
                modelo.removeRow(a);
                JOptionPane.showMessageDialog(null,"Registro Eliminado" );
            } 
        }
        
        
    }

    public void autoCompletarCodigo() {
        TextAutoCompleter textAutoC = new TextAutoCompleter(txtCodigoCuenta);

        sentenciaSql = "SELECT codigoCuenta FROM cuenta ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSql);
            while (rs.next()) {

                textAutoC.addItem(rs.getString("codigoCuenta"));
                
            }
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo autocompletar codigo ");

        }      
    }
    
    public double buscarIVACobrado(){
        double ivaCobradoActual=0.0;
        for(int p=0;p<tablaCuentasAgregadas.getRowCount();p++)
        {
            if(tablaCuentasAgregadas.getValueAt(p,0).toString()=="213")
            {
                ivaCobradoActual=Double.parseDouble(tablaCuentasAgregadas.getValueAt(p,2).toString());
                break;
            }
        }  
        return ivaCobradoActual;
    }
    
 
    
/*
    public void TotalTabla() {
        String a = "";
        double total = 0.0;
        double b = 0.0;

        for (int fila = 0; fila < tablaCuentasAgregadas.getRowCount(); fila++) {

            a = String.valueOf(tablaCuentasAgregadas.getValueAt(fila, 2));
            b = Double.valueOf(a);
            total = total + b;
        }
        txtDebe1.setText(String.valueOf(total));
    }

    double sumatoria = 0.0;
    double sumatoria1 = 0.0;

    public void sumarTotal1() {
        //Aquí sumo los valores de la fila para colocarlos en el JTextField totalCampo
        int totalRow = tablaCuentasAgregadas.getRowCount();
        totalRow = - 1;
        for (int i = 0; i <= (totalRow); i++) {
            sumatoria = Float.parseFloat(String.valueOf(tablaCuentasAgregadas.getValueAt(i, 2)));
            sumatoria1 += sumatoria;
        }
        txtDebe1.setText(String.valueOf(sumatoria1));
    }

    public void sumarTotal2() {

    }*/
    
     public boolean isCellEditable(int row, int column) {
       //Only the third column
       return true;
   }
     
     void ocultarColumnas() {
        tablaCuentasAgregadas.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(0).setMinWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        tablaCuentasAgregadas.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(1).setMinWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        
        tablaCuentasAgregadas.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(3).setMinWidth(0);
        tablaCuentasAgregadas.getColumnModel().getColumn(3).setPreferredWidth(0);
        
      /*  tablaTransaccion.getColumnModel().getColumn(4).setMaxWidth(0);
        tablaTransaccion.getColumnModel().getColumn(4).setMinWidth(0);
        tablaTransaccion.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        tablaTransaccion.getColumnModel().getColumn(5).setMaxWidth(0);
        tablaTransaccion.getColumnModel().getColumn(5).setMinWidth(0);
        tablaTransaccion.getColumnModel().getColumn(5).setPreferredWidth(0);
       */
     
        
        
     }
     
     //Método ejecutarQuery
    private ResultSet ejecutarQuery(String sql) throws SQLException{
        
        Statement statement = cn.createStatement();
        ResultSet resultado = statement.executeQuery(sql);
        
        return resultado;
    }//Fin de método ejecutarQuery
    
  
       //Método ejectuarQueryDeActualizacion. cuentas
   private int ejecutarQueryDeActualizacion(String sql,  long codigoCuenta, String nombreCuenta,Double saldo, Double saldoDeudor, Double saldoAcreedor){
        try{
            PreparedStatement pStatement = cn.prepareStatement(sql);
            int resultado = 0;
            
            pStatement.setLong(1, codigoCuenta);
            pStatement.setString(2, nombreCuenta);
            pStatement.setDouble(3, saldo);
            pStatement.setDouble(4, saldoDeudor);
            pStatement.setDouble(5, saldoAcreedor);
            
            resultado = pStatement.executeUpdate();
            
            return resultado;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }//Fin del método ejectuarQueryDeActualizacion.

   //Variable para el código auxiliar en el cálculo del IVA.
      boolean ayudaIva = false;
      
     

     
    
      
    
     
      
       //método de filtro de datos
    private TableRowSorter tr;
    
    public void filtro(){
        tr.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(),1,2));
    }
/*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transaccion2 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaConsultarT = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentasAgregadas = new javax.swing.JTable();
        txtDebe1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        fechaDate = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnEliminarCuenta = new javax.swing.JButton();
        txtHaber2 = new javax.swing.JTextField();
        txtSaldoHaber = new javax.swing.JTextField();
        txtNombreCuenta = new javax.swing.JTextField();
        txtSaldoDebe = new javax.swing.JTextField();
        btnAgregarCuenta = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMonto1 = new javax.swing.JTextField();
        boxTipoT = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtCodigoCuenta = new javax.swing.JTextField();
        partidaDoble = new javax.swing.JLabel();
        btnNuevoMovimiento = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTiempo1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        transaccion2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 7));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("CONSULTAR TRANSACCIÓN");

        tablaConsultarT.setBackground(new java.awt.Color(204, 204, 204));
        tablaConsultarT.setModel(transaccionesTModel);
        jScrollPane2.setViewportView(tablaConsultarT);

        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Digite el código o la descripción de la transacción:");

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

        jButton1.setText("Eliminar Transacción");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(0, 36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(215, 215, 215))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(225, 225, 225))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        transaccion2.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 630, 500));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        transaccion2.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 550));

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setFrameIcon(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Cuentas Afectadas:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 20));

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        jPanel2.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 440, 70));

        tablaCuentasAgregadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Debe", "Haber"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaCuentasAgregadas.setColumnSelectionAllowed(true);
        tablaCuentasAgregadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentasAgregadasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuentasAgregadas);
        tablaCuentasAgregadas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 312, 440, 120));

        txtDebe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDebe1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtDebe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 70, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Total Debe:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(153, 153, 153));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, -1, -1));

        fechaDate.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(fechaDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 180, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 102));
        jLabel15.setText("Monto:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setText("Descripción:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        btnEliminarCuenta.setBackground(new java.awt.Color(153, 153, 153));
        btnEliminarCuenta.setText("Eliminar Movimiento");
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 130, -1));
        jPanel2.add(txtHaber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 70, -1));

        txtSaldoHaber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSaldoHaberFocusLost(evt);
            }
        });
        txtSaldoHaber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSaldoHaberMouseClicked(evt);
            }
        });
        jPanel2.add(txtSaldoHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 89, -1));

        txtNombreCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreCuentaFocusLost(evt);
            }
        });
        txtNombreCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreCuentaMouseClicked(evt);
            }
        });
        txtNombreCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCuentaActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombreCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 140, -1));

        txtSaldoDebe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSaldoDebeFocusLost(evt);
            }
        });
        txtSaldoDebe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSaldoDebeMouseClicked(evt);
            }
        });
        jPanel2.add(txtSaldoDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 89, -1));

        btnAgregarCuenta.setText("Agregar Movimiento");
        btnAgregarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCuentaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("Fecha:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        txtMonto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonto1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtMonto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 90, -1));

        boxTipoT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gastos Pagados por Adelantado", "Depreciación", "Gastos Acumulados", "Ingresos Acumulados", "Ingresos No Devengados" }));
        boxTipoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoTActionPerformed(evt);
            }
        });
        jPanel2.add(boxTipoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 220, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("Tipo:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        txtCodigoCuenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoCuentaFocusLost(evt);
            }
        });
        txtCodigoCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoCuentaMouseClicked(evt);
            }
        });
        txtCodigoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCuentaActionPerformed(evt);
            }
        });
        jPanel2.add(txtCodigoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 90, -1));

        partidaDoble.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        partidaDoble.setForeground(new java.awt.Color(255, 51, 51));
        partidaDoble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(partidaDoble, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 250, 20));

        btnNuevoMovimiento.setText("Nuevo Movimiento");
        btnNuevoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMovimientoActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevoMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("Código:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 102));
        jLabel12.setText("Nombre:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 102));
        jLabel14.setText("Debe:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 0, 102));
        jLabel16.setText("Haber:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 0, 102));
        jLabel17.setText("Total Haber:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setText("Meses:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));
        jPanel2.add(txtTiempo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 30, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 542, 539));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 560, 560));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
     if(txtDescripcion.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese la descripción");
            txtDescripcion.requestFocus();
            return;
         } else if(txtCodigoCuenta.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el código de cuenta");
            txtNombreCuenta.requestFocus();
            return;
         } else if(txtNombreCuenta.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la cuenta");
            txtSaldoDebe.requestFocus();
            return;
         } else if(txtSaldoDebe.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el saldo deudor");
            txtSaldoHaber.requestFocus();
            return;
         } else if(txtSaldoHaber.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el saldo acreedor");      
            return;
         }else{
        
        try{
            Ajuste t = new Ajuste();
            sentenciaSql = "insert into ajustes ( monto, tipo, descripcion, fecha) values (?,?,?,?)";
            PreparedStatement preparedStatement=cn.prepareStatement(sentenciaSql);
          
            preparedStatement.setString(1, txtMonto1.getText());
            preparedStatement.setString(2, boxTipoT.getSelectedItem().toString());
            preparedStatement.setString(3, txtDescripcion.getText());
            preparedStatement.setString(4,((JTextField)fechaDate.getDateEditor().getUiComponent()).getText());
           
            preparedStatement.executeUpdate();
            //agregarMovimiento();
            JOptionPane.showMessageDialog(null, "AJUSTE GUARDADO");
            /*
            sentenciaSql2="insert into cuenta(saldoDebe, saldoHaber) values (?,?)";
            PreparedStatement prepared = cn.prepareStatement(sentenciaSql2);
            prepared.setString(1, txtSaldoDebe.getText());
            prepared.setString(2, txtSaldoHaber.getText());
            prepared.executeUpdate();*/
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
         }
     
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
        txtDescripcion.transferFocus();
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed

        eliminarMovimiento();
        calcular();
        activarOpcionGuardar();
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void txtDebe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDebe1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtDebe1ActionPerformed

    private void tablaCuentasAgregadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentasAgregadasMouseClicked
        // TODO add your handling code here:
         int fila = tablaCuentasAgregadas.rowAtPoint(evt.getPoint());
        
         
    }//GEN-LAST:event_tablaCuentasAgregadasMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
         this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreCuentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCuentaFocusLost
        // TODO add your handling code here:
       //  diseño.Mensaje(txtNombreCuenta, mensaje.getCodigo(), txtNombreCuenta.getText().trim().length());
    }//GEN-LAST:event_txtNombreCuentaFocusLost

    private void txtNombreCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreCuentaMouseClicked
        // TODO add your handling code here:
      //  diseño.Clic(txtNombreCuenta,  mensaje.getCodigo());
    }//GEN-LAST:event_txtNombreCuentaMouseClicked

    private void txtSaldoDebeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSaldoDebeFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtSaldoDebeFocusLost

    private void txtSaldoDebeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSaldoDebeMouseClicked
        // TODO add your handling code here:
    
    }//GEN-LAST:event_txtSaldoDebeMouseClicked

    private void txtSaldoHaberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSaldoHaberFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSaldoHaberFocusLost

    private void txtSaldoHaberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSaldoHaberMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtSaldoHaberMouseClicked

    private void btnAgregarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCuentaActionPerformed
if(txtCodigoCuenta.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el código de cuenta");
            txtNombreCuenta.requestFocus();
            return;
         } else if(txtNombreCuenta.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la cuenta");
            txtSaldoDebe.requestFocus();
            return;
         } else if(txtSaldoDebe.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el saldo deudor");
            txtSaldoHaber.requestFocus();
            return;
         } else if(txtSaldoHaber.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Ingrese el saldo acreedor");      
            return;
         }else{
        agregarEnTabla();
        calcular();
        activarOpcionGuardar();
         }
        /*boolean entrada = false;
  Double iva;
        if(checkIva.isSelected())
            {
                 entrada = true;   
             }*/
        
        
        /*
               String sentenciaSql = "SELECT * FROM cuenta ";//SENTENCIA SQL
               try {
                   con=Conexion.getConection();
                    Statement st= con.createStatement();//CONEXION
                    ResultSet  rs = st.executeQuery(sentenciaSql);
               while(rs.next()){
                   
               }
               }
        if(txtSaldoDebe.getText().equals("")||Double.parseDouble(txtSaldoDebe.getText())<0){
                   txtSaldoDebe.setText("0.00");
        }
         
        if(txtSaldoHaber.getText().equals("")||Integer.parseInt(txtSaldoHaber.getText())<0){
                   txtSaldoHaber.setText("0");
        }
        
        try { //entrada de datos
           con = Conexion.getConection();
           p = con.prepareStatement("INSERT INTO cuenta (codigoCuenta, nombreCuenta, saldoDebe, saldoHaber) VALUES (?,?,?,?)");
           p.setString(1,txtCodigoCuenta.getText());
           p.setString(2,txtNombreCuenta.getText());
           p.setString(3,txtSaldoDebe.getText());
           p.setString(4, txtSaldoHaber.getText());
           JOptionPane.showMessageDialog(this, "Movimiento ingresado correctamente");
           mostrar("");
           
           
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar cuenta");
        }    
     
           
           
           
           if(entrada == true)//compra
           {
           } 
           
         // iva = Double.parseDouble(txtSaldoDebe.getText()); //para obtener el iva de las compras
          
                */   
        //Código original
        /*
          try {
                sentenciaSql = "select * from cuenta where codigoCuenta='" + txtCodigoCuenta.getText() + "'";
                PreparedStatement preparedStatement = cn.prepareStatement(sentenciaSql);
                ResultSet resultado = preparedStatement.executeQuery(sentenciaSql);
                resultado.next();
                Cuentas cuenta = new Cuentas(resultado.getLong("codigoCuenta"), resultado.getString("nombreCuenta"), resultado.getString("tipo"), resultado.getDouble("saldo"), resultado.getDouble("saldoDeudor"), resultado.getDouble("saldoAcreedor"));
                MovimientoCuenta m = new MovimientoCuenta();
                m.setCuenta(cuenta);
                if (txtSaldoDebe.getText().isEmpty()) { //Haber
                    cuenta.setSaldoAcreedor(cuenta.getSaldoAcreedor() + Double.parseDouble(txtSaldoHaber.getText()));
                    m.setMonto(Double.parseDouble(txtSaldoHaber.getText()));
                    m.setTipo(1);
                    m.setAbono(txtSaldoHaber.getText());
                    txtHaber2.setText(String.valueOf(Double.parseDouble(txtHaber2.getText() + Double.parseDouble(txtSaldoHaber.getText()))));
                } else {//Haber
                    cuenta.setSaldoDeudor(cuenta.getSaldoDeudor() + Double.parseDouble(txtSaldoDebe.getText()));
                    m.setMonto(Double.parseDouble(txtSaldoDebe.getText()));
                    m.setTipo(0);
                    m.setCargo(txtSaldoDebe.getText());
                    txtDebe1.setText(String.valueOf(Double.parseDouble(txtDebe1.getText() + Double.parseDouble(txtSaldoDebe.getText()))));
                }
                cuenta.setSaldo(Math.abs(cuenta.getSaldoAcreedor() - cuenta.getSaldoDeudor()));
                movimientos.add(m);
                txtMonto1.setText(String.valueOf(Double.parseDouble(txtDebe1.getText())));
               
               
                JOptionPane.showMessageDialog(this, "Movimiento Ingresado Correctamente");
                
                 
               
                 
                //labelMovimiento.setText("Movimiento Ingresado Correctamente");
                activarOpcionGuardar();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al ingresar este el movimiento");
            }
            // }else{
            // labelMovimiento.setText("La cuenta ya ha sido usada");
            // }
      //  } else {
             // labelMovimiento.setText("Los Datos son erroneos");
     //   }
    
        
        
        */
        
    }//GEN-LAST:event_btnAgregarCuentaActionPerformed

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
        tr= new TableRowSorter(tablaConsultarT.getModel());
        tablaConsultarT.setRowSorter(tr);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCodigoCuentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoCuentaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCuentaFocusLost

    private void txtCodigoCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoCuentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCuentaMouseClicked

    private void btnNuevoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMovimientoActionPerformed
        // TODO add your handling code here:
        txtCodigoCuenta.setText("");
        txtCodigoCuenta.setEditable(true);
        txtNombreCuenta.setText("");
        txtNombreCuenta.setEditable(true);
        txtSaldoDebe.setText("");
        txtSaldoHaber.setText("");
    }//GEN-LAST:event_btnNuevoMovimientoActionPerformed

    private void txtMonto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonto1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtMonto1ActionPerformed

    private void txtCodigoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCuentaActionPerformed
        // TODO add your handling code here:
        sentenciaSql = "SELECT nombreCuenta FROM cuenta WHERE codigoCuenta="+"'"+txtCodigoCuenta.getText()+"'";
        if(txtCodigoCuenta.getText().isEmpty()){
            
        }
        else{
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sentenciaSql);
                while(rs.next())
                {
                    txtNombreCuenta.setText(rs.getString("nombreCuenta")); 
                }
                txtNombreCuenta.setEditable(false);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el nombre de la cuenta ");

            }
        }
    }//GEN-LAST:event_txtCodigoCuentaActionPerformed

    private void txtNombreCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCuentaActionPerformed
        // TODO add your handling code here:
          sentenciaSql = "SELECT codigoCuenta FROM cuenta WHERE nombreCuenta="+"'"+txtNombreCuenta.getText()+"'";
        if(txtNombreCuenta.getText().isEmpty()){
            
        }
        else{
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sentenciaSql);
                while(rs.next())
                {
                    txtCodigoCuenta.setText(rs.getString("codigoCuenta")); 
                }
                txtCodigoCuenta.setEditable(false);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el codigo de la cuenta ");

            }
        }
    }//GEN-LAST:event_txtNombreCuentaActionPerformed

    private void boxTipoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxTipoTActionPerformed

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
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ajustes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Ajustes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTipoT;
    private javax.swing.JButton btnAgregarCuenta;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevoMovimiento;
    private com.toedter.calendar.JDateChooser fechaDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel partidaDoble;
    private javax.swing.JTable tablaConsultarT;
    public static javax.swing.JTable tablaCuentasAgregadas;
    private javax.swing.JDialog transaccion2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoCuenta;
    private javax.swing.JTextField txtDebe1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtHaber2;
    private javax.swing.JTextField txtMonto1;
    private javax.swing.JTextField txtNombreCuenta;
    private javax.swing.JTextField txtSaldoDebe;
    private javax.swing.JTextField txtSaldoHaber;
    private javax.swing.JTextField txtTiempo1;
    // End of variables declaration//GEN-END:variables

    private boolean ingresarTransaccion(String text, String text0, String text1, List<Cuentas> cuentas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
