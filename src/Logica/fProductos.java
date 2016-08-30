/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Producto;
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
public class fProductos {
    
    private Conexion mysql = new Conexion();
    private Connection cn= mysql.conectar(); 
    private String sentenciaSql = "";
    public Integer totalR;
    
    public DefaultTableModel mostrar(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"Código","Nombre","Tipo","Unidad de Medida"}; //guardar los títulos de la columna
       String [] registro =new String [4]; //almacena los registros de cada título
       totalR=0;
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM producto ORDER BY codigoProducto asc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("codigoProducto");
               registro[1] = rs.getString("nombreProducto");
               registro[2] = rs.getString("tipoProducto");
               registro[3] = rs.getString("unidadMedida");
               
               totalR= totalR+1;
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
    


public boolean insertar(Producto a ){ //insertar Articulos
           sentenciaSql = "insert into producto(codigoProducto, nombreProducto, tipoProducto, unidadMedida)"+
                   "values (?,?,?,?)";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setInt(1, a.getCodigo());
              ps.setString(2, a.getNombre());
              ps.setString(3, a.getTipo());
              ps.setString(4, a.getuMedida());
              
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

 public boolean editar(Producto a){ //editar Articulos
           sentenciaSql= "update producto set nombreProducto=?, tipoProducto=?, unidadMedida=? "+
                   "where codigoProducto=?";
           
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
         
              ps.setString(1, a.getNombre());
              ps.setString(2, a.getTipo());
              ps.setString(3, a.getuMedida());
              ps.setInt(4, a.getCodigo());
              
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
        
  public boolean eliminar(Producto a){
            sentenciaSql= "delete from producto where codigoProducto =?";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setInt(1, a.getCodigo());
             
              
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