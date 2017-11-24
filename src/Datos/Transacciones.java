/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Yeseliz
 */
public class Transacciones extends Periodo{
    
    public Integer idTransaccion;
    public String fecha;
    public String descripcion;
    public Double monto;
    public Double debe;
    public Double haber;
    public String tipo;
  //  public Periodo periodo;
    public Cuentas cuenta;
    private String sentenciaSql="";
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();



    public Transacciones() {
    }

    public Transacciones(int idTransaccion,  String descripcion, Double monto) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
       /* this.debe = debe;
        this.haber = haber;*/
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

  
  

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }


 
    
    
    

    public void almacenar() {
         Conexion con = new Conexion();
        try {
            
            sentenciaSql = "insert into transaccion values ('"+idTransaccion+"','"+monto+"','"+fecha+"','"+descripcion+"')";
            Statement statement = this.cn.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSql);
/*
          Statement stm = con.conectar().createStatement();
          stm.execute("insert into transaccion values ('"+idTransaccion+"','"+monto+"','"+fecha+"','"+descripcion+"')");
           */
        } catch (SQLException ex) {
          Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
          
           
        }

    }
 
}
