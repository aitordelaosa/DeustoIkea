package gui;

import java.util.List;


import javax.swing.table.DefaultTableModel;

import domain.Armario;
import domain.Baño;
import domain.Tipo;



import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Modelo extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    private List<Object[]> data;
    private List<String> titulos;

    public Modelo(List<Object[]> data, List<String> titulos) {
        this.data = data;
        this.titulos = titulos;
    }

    @Override
    public int getRowCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getColumnCount() {
        return titulos == null ? 0 : titulos.size();
    }

    @Override
    public String getColumnName(int column) {
        return titulos.get(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data.get(row)[column];
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }

    public void removeRow(int row) {
        if (row >= 0 && row < data.size()) {
            data.remove(row);
            fireTableRowsDeleted(row, row); 
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + row);
        }
    }

    
    public void actualizarDatos(List<Object[]> nuevosDatos, List<String> nuevosTitulos) {
        this.data = nuevosDatos;
        this.titulos = nuevosTitulos;
        fireTableStructureChanged(); 
    }
}
