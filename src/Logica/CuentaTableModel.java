/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Cuentas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yeseliz
 */
public class CuentaTableModel extends AbstractTableModel{
    
   public List<Cuentas> cuentas = new ArrayList<Cuentas>();

    @Override
    public int getRowCount() {
        return cuentas.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuentas cuenta = cuentas.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = cuenta.idCuenta;
                break;
            case 1:
                valor = cuenta.codigoCuenta;
                break;
            case 2:
                valor = cuenta.nombreCuenta;
                break;
            case 3:
                valor = cuenta.tipo;
                break;
            case 4:
                valor = cuenta.saldoDeudor;
                break;
            case 5:
                valor = cuenta.saldoAcreedor;
                
        }
        return valor;
    }
}



