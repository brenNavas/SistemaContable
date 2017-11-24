
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yeseliz
 */
public class Conexion_Bryan{ //localhost 808

    public String db = "sistemacontable";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String user = "root";
    public String pass = "";

    public Conexion_Bryan() {
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




    

    
}