 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Yeseliz
 */
public class Cuentas {
    
    private int idCuenta;
    private long codigoCuenta;
    private String nombreCuenta;
    private String tipo;
    private float saldo;
    private long padre;
    private float saldoDeudor;
    private float saldoAcreedor;
    

    public Cuentas() {
    }

    public Cuentas(int idCuenta, long codigoCuenta, String nombreCuenta, String tipo, float saldo, long padre, float saldoDeudor, float saldoAcreedor) {
        this.idCuenta = idCuenta;
        this.codigoCuenta = codigoCuenta;
        this.nombreCuenta = nombreCuenta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.padre = padre;
        this.saldoDeudor = saldoDeudor;
        this.saldoAcreedor = saldoAcreedor;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public long getPadre() {
        return padre;
    }

    public void setPadre(long padre) {
        this.padre = padre;
    }

   
    public float getSaldoDeudor() {
        return saldoDeudor;
    }

    public void setSaldoDeudor(float saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }

    public float getSaldoAcreedor() {
        return saldoAcreedor;
    }

    public void setSaldoAcreedor(float saldoAcreedor) {
        this.saldoAcreedor = saldoAcreedor;
    }


    public long getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(long codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
}
