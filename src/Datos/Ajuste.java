/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Conexion;
import java.sql.Connection;

/**
 *
 * @author Yeseliz
 */
public class Ajuste {
     public Integer idAjuste;
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

    public Integer getIdTransaccion() {
        return idAjuste;
    }

    public void setIdTransaccion(Integer idAjuste) {
        this.idAjuste = idAjuste;
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

    public String getSentenciaSql() {
        return sentenciaSql;
    }

    public void setSentenciaSql(String sentenciaSql) {
        this.sentenciaSql = sentenciaSql;
    }

    public Conexion getMysql() {
        return mysql;
    }

    public void setMysql(Conexion mysql) {
        this.mysql = mysql;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    
    
}
