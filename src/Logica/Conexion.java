
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Periodo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Yeseliz
 */
public class Conexion { //localhost 808

    public String db = "sistemacontable";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String user = "root";
    public String pass = "";

    public Conexion() {
    }
    
    
    public static final String URL = "jdbc:mysql://127.0.0.1/sistemacontable";
    public static final String USER = "root";
      public static final String PASS = "";
      
     public static Connection getConection()
      {
          Connection con = null;
          try {
              Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(URL, USER, PASS);
              
          } 
          catch (Exception e) {
              System.out.println("error clasae conexion"+e);
          }
          return con;
      }


    public Connection conectar() {
        Connection link = null; //instancia a la clase conecction

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return link;
    }
    
  //actualizar los valores de la base de datos desde las tablas
    public Object[][] Select_Cuenta() {
        int registros = 0;
        String consulta = "Select * FROM cuenta ";
        String consulta2 = "Select count(*) as total from cuenta ";
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = conectar().prepareStatement(consulta2);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] registro = new String[registros][6];
        //realizamos la consulta sql y llenamos los datos en la matriz "Object"
        try {
            PreparedStatement pstm = conectar().prepareStatement(consulta);
            ResultSet rs = pstm.executeQuery();
            int i = 0;
            while (rs.next()) {
              
                registro[i][0] = rs.getString("idCuenta");
                registro[i][1] = rs.getString("codigoCuenta");
                registro[i][2]= rs.getString("nombreCuenta");
                registro[i][3] = rs.getString("tipo");
                registro[i][4] = rs.getString("saldoDeudor");
                registro[i][5] = rs.getString("saldoAcreedor");
                i++;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return registro;
    }

    public boolean update(String valores, String id) {
        boolean res = false;
        String q = " UPDATE cuenta SET " + valores + " WHERE idCuenta= " + id;
        try {
            PreparedStatement pstm = conectar().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return res;
    }

    public static int ejecutarActualizacion(String table, String valuesExpression, String typeValues,String[] values){
        int r=0;
        try {
          //  conectar();
            String[] columnas=valuesExpression.split(",");
            int cant=columnas.length;
            
            String sql="INSERT INTO "+table+" ("+valuesExpression+") VALUES (";
            for(int i=0;i<cant;i++){
                sql=sql+"?";
                if(i!=cant-1){
                    sql=sql+",";
                }
            }
            sql=sql+")";
            
            System.out.println(sql);
            
            PreparedStatement st= getConection().prepareStatement(sql);
            
            String[] types=typeValues.split(",");
            
            
            for(int i=0;i<cant;i++){
                String type=types[i].trim();
                String value=values[i].trim();
                switch (type){
                    case "int":
                        st.setInt(i+1, Integer.parseInt(value));
                        break;
                    case "double":
                        st.setDouble(i+1, Double.parseDouble(value));
                        break;
                    case "string":
                        st.setString(i+1, value);
                        break;
                }
            }
            
            r=st.executeUpdate();
            System.out.println("id: "+r);
            System.out.println("registro agregado correctamente");
            //desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static ResultSet ejecutarConsulta(String sql){
        ResultSet rs=null;
        
        try {
            //conectar();
            PreparedStatement st= getConection().prepareStatement(sql);
            rs=st.executeQuery();
            //desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public static int ejecutarActualizacion(String sql){
        int r=0;//0 indicaria que no se afecto nada
        try {
            //conectar();
            PreparedStatement st= getConection().prepareStatement(sql);
            r=st.executeUpdate();
            //desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    

     
   
    
public static Double datos_totalDebe(String campo, String sql){
    double data =0;
      try{
         PreparedStatement pstm = getConection().prepareStatement(sql);
          ResultSet rs = pstm.executeQuery();
         while(rs.next()){
            data = rs.getDouble(campo);
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
         
    }
    return data;
    }
        

public static Double datos_totalHaber(String campo, String sql){
    double data =0;
      try{
         PreparedStatement pstm = getConection().prepareStatement(sql);
          ResultSet rs = pstm.executeQuery();
         while(rs.next()){
            data = rs.getDouble(campo);
         }
         rs.close();
          }catch(SQLException e){
         System.out.println(e);
    }
    return data;
    }
        



    
}