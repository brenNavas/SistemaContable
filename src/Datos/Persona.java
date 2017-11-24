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
public class Persona {
    
    
    public Integer idPersona;
    public String nombrePersona;
    public String a_materno;
    public String a_paterno;
    public String num_dui;
    public String direccion;
    public String telefono;
    public String email;
    public Double sueldo;
   // private Empresa empresa;

    public Persona() {
    }

    public Persona(int idPersona, String nombrePersona, String a_materno, String a_paterno, String num_dui, String direccion, String telefono, String email, Double sueldo) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.a_materno = a_materno;
        this.a_paterno = a_paterno;
        this.num_dui = num_dui;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.sueldo = sueldo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getA_materno() {
        return a_materno;
    }

    public void setA_materno(String a_materno) {
        this.a_materno = a_materno;
    }

    public String getA_paterno() {
        return a_paterno;
    }

    public void setA_paterno(String a_paterno) {
        this.a_paterno = a_paterno;
    }

    public String getNum_dui() {
        return num_dui;
    }

    public void setNum_dui(String num_dui) {
        this.num_dui = num_dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
    
    

}