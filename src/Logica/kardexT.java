/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Vistas.Kardex;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arthur
 */
public class kardexT {
    
      
    public DefaultTableModel mostrar(String buscar){ //mostrar tabla 
        
       
       DefaultTableModel modelo;
       Double cu,total;
       Integer cant;
       
       String [] titulos = {"codigo","Fecha", "Cantidad","Costo Unitario","Total","Cantidad","Costo Unitario","Total","Cantidad","Costo Unitario","Total"}; //guardar los títulos de la columna
       String [] registro =new String [11]; //almacena los registros de cada título
  
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       String sentenciaSql = "SELECT * FROM kardex2 ORDER BY fecha asc";
      
       
       try {
           Statement st= Conexion.getConection().createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
           
          
           
           while(rs.next()){ //guardar en cada registro
              
               
               registro[0]=rs.getString("codigoKardex");
               registro[1] = rs.getString("fecha");
               
               //***************ENTRADA*****************************
             if((rs.getString("cantidadE").equals("0")&&rs.getString("costoUe").equals("0.00"))||(rs.getString("cantidadE")==null&&rs.getString("costoUe")==null))
              {
              registro[2] = " ";
              registro[3] = " ";
              registro[4] = " ";
              }
                else{
               registro[2] = rs.getString("cantidadE");
               registro[3] = "$ "+rs.getString("costoUe");
               
               cu=Double.parseDouble(rs.getString("costoUe"));
               cant=Integer.parseInt(rs.getString("cantidadE"));
               total=cu*cant;
               
               registro[4] = "$ "+String.valueOf(total);
                }
              //***************************************************** 
              
              
             
              
              
            
              //****************SALIDA*******************************
              if((rs.getString("cantidadS").equals("0")&&rs.getString("costoUs").equals("0.00"))||(rs.getString("cantidadS")==null&&rs.getString("costoUs")==null))
              {
              registro[5] = " ";
              registro[6] = " ";
              registro[7] = " ";
              }
              else{
              registro[5] = rs.getString("cantidadS");
              registro[6] = "$ "+rs.getString("costoUs");
              cu=Double.parseDouble(rs.getString("costoUs"));
              cant=Integer.parseInt(rs.getString("cantidadS"));
              total=cu*cant;
              registro[7] = "$ "+String.valueOf(total);
              }
             
              
              //******************************************************
              
              //***************TOTAL**********************************
               if((rs.getString("cantidadT").equals("0")&&rs.getString("costoUt").equals("0.00"))||(rs.getString("cantidadT")==null&&rs.getString("costoUt")==null))
              {
              registro[8] = " ";
              registro[9] = " ";
              registro[10] = " ";
              }
               else{
                   
              cu=Double.parseDouble(rs.getString("costoUt"));
              cant=Integer.parseInt(rs.getString("cantidadT"));
              total=cu*cant;     
             // cu=cu/cant;
              
              registro[8] = rs.getString("cantidadT");
              registro[9] = "$ "+rs.getString("costoUt");
              
              
              
              registro[10] = "$ "+String.valueOf(total);
               }
              //******************************************************
            
             
               
               
               
               
               
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error mostrar tabla "+e);
        return null;
       } 
     }
    
    
    

   
    
   
    
}
