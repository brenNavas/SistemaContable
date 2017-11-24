/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Ajuste;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yeseliz
 */
public class AjusteTModel extends AbstractTableModel {
    public List<Ajuste> ajustes = new ArrayList<Ajuste>();

    @Override
    public int getRowCount() {
        return ajustes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ajuste ajuste = ajustes.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = ajuste.idAjuste;    
                break;
            case 1:
                valor = ajuste.fecha;
                break;
            case 2:
                valor = ajuste.descripcion;
                break;
            case 3:
                valor = ajuste.monto;
                break;
            case 4:
                valor = ajuste.tipo;
            
                
        }
        return valor;
    }
}







