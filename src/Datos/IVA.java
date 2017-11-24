/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author 
 */
public class IVA {
    
    private Double saldo;
    
    
    public IVA(Double saldo) {
        this.saldo = saldo;
       
    }
   
    public  Double calcular_iva()
    { 
        double iva;               
        iva = saldo*0.13;
        return iva;
        
    }
    }

