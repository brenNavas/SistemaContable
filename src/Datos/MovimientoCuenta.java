/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.Cuentas;


/**
 *
 * @author Yeseliz
 */
public class MovimientoCuenta {
    public Double monto;
    public Integer tipo;//1 si es abono, 0 si es cargo
    public Transacciones transaccion;
   // private Periodo periodo;
    public Cuentas cuenta;
    
    /*propiedades auxiliares: usados por la tabla*/
    private String cargo;
    private String abono;

    public MovimientoCuenta() {
    }
    
    

    public MovimientoCuenta(Double monto, Integer tipo, Transacciones transaccion, Cuentas cuenta) {
        this.monto = monto;
        this.tipo = tipo;
        this.transaccion = transaccion;
        this.cuenta = cuenta;
        cargo=new String("");
        abono=new String("");
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Transacciones getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transacciones transaccion) {
        this.transaccion = transaccion;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAbono() {
        return abono;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    

}