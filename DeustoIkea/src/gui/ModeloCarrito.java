package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Producto;

@SuppressWarnings("serial")
public class ModeloCarrito extends DefaultTableModel{
	private List<String> titulos = Arrays.asList("ID", "NOMBRE", "CANTIDAD", "PRECIO");
	private List<Producto> lProductos;

	public ModeloCarrito(List<Producto> lProductos) {
		this.lProductos = lProductos;	
	}
	
	@Override
	public int getColumnCount() {
		return titulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}

	@Override
	public int getRowCount() {
		if(lProductos == null) {
			return 0;
		} else {
			return lProductos.size();
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Producto p = lProductos.get(row);
		switch(column) {
			case 0: return p.getIdProducto();
			case 1: return p.getClass().getSimpleName();
			case 2: return p.getNumeroProductos();
			case 3:double total = p.getPrecio() * p.getNumeroProductos();
            	return String.format("%.2f", total);
			default: return null;
		}
	}
	
	 public void eliminarProducto(Producto producto) {
        lProductos.remove(producto);
        fireTableDataChanged(); 
    }
	 
    public void actualizarTabla() {
        fireTableDataChanged(); 
    }
    

    public Producto getProductoAtRow(int row) {
        return lProductos.get(row);
    }
}
