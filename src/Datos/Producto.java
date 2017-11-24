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
public class Producto {
    
    public Integer codigo;
    public String nombre;
    public String tipo;
    public String uMedida;
    public String proceso;

    public Producto() {
    }

    public Producto(Integer codigo, String nombre, String tipo, String uMedida, String proceso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.uMedida = uMedida;
        this.proceso = proceso;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getuMedida() {
        return uMedida;
    }

    public void setuMedida(String uMedida) {
        this.uMedida = uMedida;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

}