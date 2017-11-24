/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Transacciones;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yeseliz
 */
public class TransaccionTModel extends AbstractTableModel {
    public List<Transacciones> transacciones = new ArrayList<Transacciones>();

    @Override
    public int getRowCount() {
        return transacciones.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transacciones transaccion = transacciones.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = transaccion.idTransaccion;    
                break;
            case 1:
                valor = transaccion.fecha;
                break;
            case 2:
                valor = transaccion.descripcion;
                break;
            case 3:
                valor = transaccion.monto;
                break;
            case 4:
                valor = transaccion.tipo;
            
                
        }
        return valor;
    }
}







