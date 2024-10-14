package gui;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import domain.Armario;
import domain.Mesa;
import domain.Producto;
import domain.Silla;
import domain.Sofa;

public class ModeloMuebles extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Producto> productos;
	protected ArrayList<String> titulos = new ArrayList<>(Arrays.asList("ID PRODUCTO", "PESO", "PRECIO", "MATERIAL", "IMAGEN"));
	
	public ModeloMuebles(ArrayList<Producto> p) {
//		productos = p;
		productos = (p != null) ? p : new ArrayList<>();
	}
	
	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}
	
	@Override
	public int getColumnCount() {
		return titulos.size();
	}
	
	@Override
	public int getRowCount() {
		if(productos == null)
			return 0;
		return productos.size();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
	    Producto p = productos.get(row);

	    if (p instanceof Silla) {
	        Silla silla = (Silla) p;
	        switch (column) {
	            case 0: return silla.getIdProducto();
	            case 1: return silla.getPeso();
	            case 2: return silla.getPrecio();
	            case 3: return silla.getMaterial();
	            case 4:
	                ImageIcon image = silla.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            default: return null;
	        }
	    } else if (p instanceof Mesa) {
	        Mesa mesa = (Mesa) p;
	        switch (column) {
	            case 0: return mesa.getIdProducto();
	            case 1: return mesa.getPeso();
	            case 2: return mesa.getPrecio();
	            case 3: return mesa.getMaterial();
	            case 4:
	            	ImageIcon image = mesa.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            default: return null;
	        }
	    } else if (p instanceof Sofa) {
	        Sofa sofa = (Sofa) p;
	        switch (column) {
	            case 0: return sofa.getIdProducto();
	            case 1: return sofa.getPeso();
	            case 2: return sofa.getPrecio();
	            case 3: return sofa.getMaterial();
	            case 4:
	            	ImageIcon image = sofa.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            default: return null;
	        }
	    } else {
	        Armario armario = (Armario) p;
	        switch (column) {
	            case 0: return armario.getIdProducto();
	            case 1: return armario.getPeso();
	            case 2: return armario.getPrecio();
	            case 3: return armario.getMaterial();
	            case 4:
	            	ImageIcon image = armario.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            default: return null;
	        }
	    }
	}
	
	public void addProducto(Producto producto) {
	    productos.add(producto);
	    fireTableRowsInserted(productos.size() - 1, productos.size() - 1); // Notificar a la tabla que se ha añadido un nuevo producto
	}
	
}

