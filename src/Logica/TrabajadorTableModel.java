/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Trabajador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yeseliz
 */
public class TrabajadorTableModel extends AbstractTableModel{

    
   public List<Trabajador> trabajadores = new ArrayList<Trabajador>();

    @Override
    public int getRowCount() {
        return trabajadores.size();
    }

    @Override
    public int getColumnCount() {
        return 13;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trabajador trabajador = trabajadores.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = trabajador.idPersona;
                break;
            case 1:
                valor = trabajador.nombrePersona;
                break;
            case 2:
                valor = trabajador.a_paterno;
                break;
            case 3:
                valor = trabajador.a_materno;
                break;
            case 4:
                valor = trabajador.num_dui;
                break;
            case 5:
                valor = trabajador.direccion;
                break;
            case 6:
                valor = trabajador.telefono;
                break;
            case 7:
                valor = trabajador.email;
                break;
            case 8:
                valor = trabajador.sueldo;
                break;
            case 9:
                valor = trabajador.nombreUsuario;
                break;
            case 10:
                valor = trabajador.password;
                break;
            case 11:
                valor = trabajador.cargo;
                break;
            case 12:
                valor = trabajador.tipo_acceso;
                
        }
        return valor;
    }
}


