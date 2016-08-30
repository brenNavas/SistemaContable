/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Cuentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yeseliz
 */
public class fCuentas {
    
    private Conexion mysql = new Conexion();
    private Connection cn= mysql.conectar(); 
    private String sentenciaSql = "";
    public Integer totalR;
     
    
    public DefaultTableModel mostrar(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"Id Cuenta", "Código","Nombre","Tipo","Saldo", "Rubro", "Saldo Deudor", "SaldoAcreedor"}; //guardar los títulos de la columna
       String [] registro =new String [8]; //almacena los registros de cada título
       totalR=0;
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM cuenta ORDER BY idCuenta asc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("idCuenta");
               registro[1] = rs.getString("codigoCuenta");
               registro[2] = rs.getString("nombreCuenta");
               registro[3] = rs.getString("tipo");
               registro[4] = rs.getString("saldo");
               registro[5] = rs.getString("padre");
               registro[6] = rs.getString("saldoDeudor");
               registro[7] = rs.getString("saldoAcreedor");
               
               totalR= totalR+1;
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
    


public boolean insertar(Cuentas c ){ //insertar Cuentas
           sentenciaSql = "insert into cuenta( idCuenta, codigoCuenta, nombreCuenta, tipo, saldo, padre, saldoDeudor, saldoAcreedor)"+
                   "values (?,?,?,?,?,?,?,?)";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              
              ps.setInt(1, c.getIdCuenta());
              ps.setLong(2, c.getCodigoCuenta());
              ps.setString(3, c.getNombreCuenta());
              ps.setString(4, c.getTipo());
              ps.setFloat(5, c.getSaldo());
              ps.setLong(6, c.getPadre());
              ps.setFloat(7, c.getSaldoDeudor());
              ps.setFloat(8, c.getSaldoAcreedor());
              
             int n=ps.executeUpdate(); 
             
             if( n!=0){
                 return true;
             }else{
                 return false;
             }
              
            }catch (Exception e){
              JOptionPane.showMessageDialog(null, e);
              return false;
           }
       }

 public boolean editar(Cuentas c){ //editar Cuentas
           sentenciaSql= "update cuenta  set idCuenta=? nombreCuenta=?, tipo=?, saldo=?, padre=?, saldoDeudor=?, saldoAcreedor=? "+
                   "where codigoCuenta=?";
           
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
         
              ps.setInt(1, c.getIdCuenta());
              ps.setString(2, c.getNombreCuenta());
              ps.setString(3, c.getTipo());
              ps.setFloat(4, c.getSaldo());
              ps.setLong(5, c.getPadre());
              ps.setFloat(6, c.getSaldoDeudor());
              ps.setFloat(7, c.getSaldoAcreedor());
              ps.setLong(8, c.getCodigoCuenta());
             
              
             int n=ps.executeUpdate(); 
             if(n!=0){
                 return true;
             }else{
                 return false;
             }
           }catch (Exception e){
              JOptionPane.showMessageDialog(null, e);
              return false;
           }
       }
        
  public boolean eliminar(Cuentas c){ //eliminar Cuenta (ADMINISTRADOR)
            sentenciaSql= "delete from cuenta where codigoCuenta =?";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setLong(1, c.getCodigoCuenta());
             
              
             int n=ps.executeUpdate(); 
             
             if( n!=0){
                 return true;
             }else{
                 return false;
             }
           }catch (Exception e){
              JOptionPane.showMessageDialog(null, e);
              return false;
           }
       }
       
}