/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arthur
 */
public class MostrarPlanilla {
    
    
     
    public DefaultTableModel mostrarP(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"idPersona","Nombre","Direccion","email","num_dui","Telefono","Sueldo"}; //guardar los títulos de la columna
       String [] registro =new String [7]; //almacena los registros de cada título
  
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
        String sentenciaSql = "SELECT idPersona,apellido_ma,apellido_pa,nombrePersona,telefono,sueldo,num_dui,email,direccion FROM persona WHERE nombrePersona like '%"+ buscar + "%'  ORDER BY idPersona asc";
       
       try {
           Statement st= Conexion.getConection().createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
               registro[0] = rs.getString("idPersona");
               registro[1] = rs.getString("apellido_pa")+rs.getString("apellido_ma")+rs.getString("nombrePersona");
               registro[2] = rs.getString("direccion");
               registro[3] = rs.getString("email");
               registro[4] = rs.getString("num_dui");
               registro[5] = rs.getString("telefono");
               registro[6] = "$ "+rs.getString("Sueldo");
               
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error mostrar tabla"+e);
        return null;
       } 
     }
    
    
    public DefaultTableModel mostrarSal(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
       String [] titulos = {"idPersona","Nombre","Sueldo Mensual","AFP","ISSS","Renta","TOTAL"}; //guardar los títulos de la columna
       String [] registro =new String [7]; //almacena los registros de cada título
  
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
        String sentenciaSql = "SELECT idPersona,apellido_ma,apellido_pa,nombrePersona,telefono,sueldo FROM persona WHERE nombrePersona like '%"+ buscar + "%'  ORDER BY idPersona asc";
       Double n,m,r,x,sal;
       try {
           Statement st= Conexion.getConection().createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
           
           DecimalFormat decimales = new DecimalFormat("0.00");
           
           
           
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("idPersona");
               registro[1] = rs.getString("apellido_pa")+" "+rs.getString("apellido_ma")+" "+rs.getString("nombrePersona");
               
               
               registro[2] = "$ "+rs.getString("sueldo");
               
               sal=Double.parseDouble(rs.getString("sueldo"));
               
               //AFP
               n=0.0675*sal;
               registro[3] = "$ "+String.valueOf(decimales.format(n));//saco el iss y lo dejo con formato de 2 cifras
               
               
               //ISSS
               m=0.03*sal;  
               registro[4] = "$ "+String.valueOf(decimales.format(m)); 
               
               //RENTA
               r=calculoRenta(sal);
               registro[5] = "$ "+String.valueOf(decimales.format(r)); 
               
               
               //TOTAL
               x=sal-n-m-r; 
               registro[6] = "$ "+String.valueOf(decimales.format(x));
               
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, "error mostrar tabla SAL"+e);
        return null;
       } 
     }
    
    
    public Double calculoRenta(Double xx)
    {
        Double renta=0.00;
        if(xx>0.01 && xx<472.00)//primer tramo segun Decreto numero 95
        {
            //sin retencion
            return renta;
        }
        
       if(xx>472.01 && xx<895.24)
       {
           renta=((xx-472)*0.10)+17.67;
           return renta;
       }
       
       if(xx>895.25 && xx<2038.10)
       {
           renta=((xx-895.24)*0.20)+60.00;
           return renta;
       }
       
       if(xx>2038.11)
       {
           renta=((xx-2038.10)*0.30)+288.57;
           return renta;
       }
        
        
        return renta;
    }
    
    
}
