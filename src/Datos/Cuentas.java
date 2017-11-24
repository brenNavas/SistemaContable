 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Yeseliz
 */
public class Cuentas {
    
    public Integer idCuenta;
    public Long codigoCuenta;
    public String nombreCuenta;
    public String tipo;
    public Double saldo;
    public Double saldoDeudor;
    public Double saldoAcreedor;
    Transacciones transaccion;
    


    public Cuentas() {
    }

    public Cuentas(long codigoCuenta, String nombreCuenta, String tipo, Double saldo, Double saldoDeudor, Double saldoAcreedor) {
        this.idCuenta = idCuenta;
        this.codigoCuenta = codigoCuenta;
        this.nombreCuenta = nombreCuenta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.saldoDeudor = saldoDeudor;
        this.saldoAcreedor = saldoAcreedor;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getSaldoDeudor() {
        return saldoDeudor;
    }

    public void setSaldoDeudor(Double saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }

    public Double getSaldoAcreedor() {
        return saldoAcreedor;
    }

    public void setSaldoAcreedor(Double saldoAcreedor) {
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
  public Transacciones crearTransaccion(){
        transaccion  = new Transacciones();
        
        return transaccion;
    }
    
    public void transaccionCuenta(Transacciones trans){
        transaccion = trans;
    }
    
      
    
   /*
        public void acutalizarSaldo(Cuentas cuenta, Connection conexion2){
        
            try {
            //String sentenciaSql3 = "INSERT INTO catalogo_cuentas(codigo,nombre,saldo)VALUES (?,?,?) "; 
            String sentenciaSql3 = "UPDATE catalogo_cuentas SET saldo=? where codigo=?"; 
            PreparedStatement preparedStatement = conexion2.prepareStatement(sentenciaSql3);
            
           // preparedStatement.setString(1,cuenta.nombre);
            preparedStatement.setDouble(1, cuenta.getSaldo());
            preparedStatement.setLong(2,cuenta.getCodigoCuenta());
            preparedStatement.execute();
            
          /*  preparedStatement.setString(1, cuenta.codigo);
            preparedStatement.setString(2,cuenta.nombre);
            preparedStatement.setDouble(3,cuenta.saldo);
            
            preparedStatement.execute();
           
            
    */

  
    
}


