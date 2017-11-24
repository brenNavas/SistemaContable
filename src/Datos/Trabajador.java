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
public class Trabajador extends Persona {
    
    public String nombreUsuario;
    public String password;
    public String cargo;
    public String tipo_acceso;

    public Trabajador() {
    }

    public Trabajador(String nombreUsuario, String password, String cargo, String tipo_acceso) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.cargo = cargo;
        this.tipo_acceso = tipo_acceso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipo_acceso() {
        return tipo_acceso;
    }

    public void setTipo_acceso(String tipo_acceso) {
        this.tipo_acceso = tipo_acceso;
    }

    
    
    
}