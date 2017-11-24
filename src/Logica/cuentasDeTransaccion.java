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
public class cuentasDeTransaccion {
    private Conexion mysql = new Conexion();
    private Connection cn= mysql.conectar(); 
    private String sentenciaSql = "";
    
    public DefaultTableModel mostrar(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"ID", "Codigo", "Nombre", "Saldo Deudor","Saldo Acreedor"}; //guardar los títulos de la columna
       String [] registro =new String [5]; //almacena los registros de cada título
  
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM cuenta ORDER BY idCuenta asc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("idCuenta");
               registro[1] = rs.getString("codigoCuenta");
               registro[2] = rs.getString("nombreCuenta");
               registro[3] = rs.getString("saldoDeudor");
               registro[4] = rs.getString("saldoAcreedor");
              
           
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
    
    
    
    
    
    
}
