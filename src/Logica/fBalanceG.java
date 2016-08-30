/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yeseliz
 */
public class fBalanceG {
    
    private Conexion mysql = new Conexion();
    private Connection cn= mysql.conectar(); 
    private String sentenciaSql = "";
    public Integer totalR;
    
     public DefaultTableModel mostrar1(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"Código","Nombre","Tipo","Saldo", "SaldoDeudor", "Saldo Acreedor"}; //guardar los títulos de la columna
       String [] registro =new String [6]; //almacena los registros de cada título
       totalR=0;
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM Cuenta WHERE tipo = 'Activo' ORDER BY 'codigoCuenta'";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("codigoCuenta");
               registro[1] = rs.getString("nombreCuenta");
               registro[2] = rs.getString("tipo");
               registro[3] = rs.getString("saldo");
               registro[4] = rs.getString("saldoDeudor");
               registro[5] = rs.getString("saldoAcreedor");
               
               totalR= totalR+1;
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
  
  
     public DefaultTableModel mostrar2(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"Código","Nombre","Tipo","Saldo", "SaldoDeudor", "Saldo Acreedor"}; //guardar los títulos de la columna
       String [] registro =new String [6]; //almacena los registros de cada título
       totalR=0;
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM Cuenta WHERE tipo = 'Pasivo' OR tipo = 'Capital' ORDER BY 'codigoCuenta'";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("codigoCuenta");
               registro[1] = rs.getString("nombreCuenta");
               registro[2] = rs.getString("tipo");
               registro[3] = rs.getString("saldo");
               registro[4] = rs.getString("saldoDeudor");
               registro[5] = rs.getString("saldoAcreedor");
               
               totalR= totalR+1;
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
   
     
     
}
