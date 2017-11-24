/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yeseliz
 */
public class productoTableModel extends AbstractTableModel{
     public List<Producto> productos = new ArrayList<Producto>();

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = productos.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = producto.codigo;    
                break;
            case 1:
                valor = producto.nombre;
                break;
            case 2:
                valor = producto.tipo;
                break;
            case 3:
                valor = producto.uMedida;
                break;
            case 4:
                valor = producto.proceso;
 
                
        }
        return valor;
    }
}





