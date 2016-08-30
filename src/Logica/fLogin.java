/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Trabajador;
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
public class fLogin {
    /*
    
    private Conexion mysql = new Conexion();
    private Connection cn= mysql.conectar(); 
    private String sentenciaSql = "";
    public Integer totalR;
    
    public DefaultTableModel mostrar(String buscar){ //mostrar tabla 
       DefaultTableModel modelo;
       
        String [] titulos = {"ID persona","NombreUsuario ","Contraseña","tipo de acceso","sueldo"}; //guardar los títulos de la columna
       String [] registro =new String [5]; //almacena los registros de cada título
       totalR=0;
       modelo = new DefaultTableModel(null,titulos); //para guardar los títulos
       
       sentenciaSql= "SELECT * FROM trabajador ORDER BY idPersona asc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet  rs = st.executeQuery(sentenciaSql);
       
           while(rs.next()){ //guardar en cada registro
    
               registro[0] = rs.getString("idPersona");
               registro[1] = rs.getString("nombreUsuario");
               registro[2] = rs.getString("contraseña");
               registro[3] = rs.getString("tipo_acceso");
               registro[4] = rs.getString("sueldo");
               
               totalR= totalR+1;
               modelo.addRow(registro);
           }
           return modelo;
        
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        return null;
       } 
     }
  
     
     
       public boolean insertar(Trabajador l){ //insertar Trabajador
           sentenciaSql = "insert into trabajador(nombreUsuario, contraseña, tipo_acceso, sueldo)"+
                   "values (?,?,?,?)";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setString(1, l.getNombreUsuario());
              ps.setString(2, l.getContraseña());
              ps.setString(3, l.getTipo_acceso());
              ps.setFloat(4, l.getSueldo());
              
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
       
        public boolean editar(Trabajador l){ //editar Trabajador
           sentenciaSql= "update trabajador set nombreUsuario=?, contraseña=?, =? "+
                   "where tipo=?";
           
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setString(1, l.getNombreUsuario());
              ps.setString(2, l.getContraseña());
              ps.setFloat(3, l.getSueldo());
              ps.setString(4, l.getTipo_acceso());
              
              
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
        
        public boolean eliminar(Trabajador l){ //eliminar usuario
            sentenciaSql= "delete from login where nombreUsuario =?";
           try{
              PreparedStatement ps=cn.prepareStatement(sentenciaSql);
              ps.setString(1, l.getNombreUsuario());
             
              
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
       
       public DefaultTableModel login(String login,String password) {
        DefaultTableModel modelo;
        
        String[] titulos = {"Nombre", "Contraseña", "Acceso", "Sueldo"};

        String[] registro = new String[4];

        totalR = 0;
        modelo = new DefaultTableModel(null, titulos);
        
        sentenciaSql = ""; //falta sentencia para anexar la contraseña con el usuario
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSql);

            while (rs.next()) {
                registro[0] = rs.getString("nombreUsuario");
                registro[1] = rs.getString("contraseña");
                registro[2] = rs.getString("tipo_acceso");
                registro[3] = rs.getString("sueldo");
                
                totalR = totalR + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

       }
*/
}


