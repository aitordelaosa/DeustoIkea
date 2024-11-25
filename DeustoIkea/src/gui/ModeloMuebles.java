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
import domain.Baño;
import domain.Bide;
import domain.Barbacoa;
import domain.Fregadero;
import domain.Ducha;
import domain.Encimera;

public class ModeloMuebles extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	protected ArrayList<Producto> productos;
	protected ArrayList<String> titulos = new ArrayList<>(Arrays.asList("PESO", "PRECIO", "MATERIAL", "ID PRODUCTO", "IMAGEN"));
	
	public ModeloMuebles(ArrayList<Producto> p) {
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
	public void setValueAt(Object aValue, int row, int column) {
	    Producto p = productos.get(row);
	    
	    if (p instanceof Silla) {
	        Silla silla = (Silla) p;
	        switch (column) {
	            case 1:
	                silla.setPrecio((Double) aValue);
	                break;
	            case 2:
	                silla.setMaterial((String) aValue);
	                break;
	        }
	    } else if (p instanceof Mesa) {
	        Mesa mesa = (Mesa) p;
	        switch (column) {
	            case 1:
	                mesa.setPrecio((Double) aValue);
	                break;
	            case 2:
	                mesa.setMaterial((String) aValue);
	                break;
	        }
	    } else if (p instanceof Mesa) {
	        Sofa sofa = (Sofa) p;
	        switch (column) {
	            case 1:
	                sofa.setPrecio((Double) aValue);
	                break;
	            case 2:
	                sofa.setMaterial((String) aValue);
	                break;
	        }
	    } else if (p instanceof Bide) {
	        Bide baño = (Bide) p;
	        switch (column) {
	            case 1:
	                baño.setPrecio((Double) aValue);
	                break;
	            case 2:
	                baño.setMaterialB((String) aValue);
	                break;
	        }
	    } else if (p instanceof Encimera) {
	    	Encimera b = (Encimera) p;
	        switch (column) {
	            case 1:
	                b.setPrecio((Double) aValue);
	                break;
	            case 2:
	                b.setMaterialC((String) aValue);
	                break;
	        }
	    } else if (p instanceof Fregadero) {
	    	Fregadero f = (Fregadero) p;
	        switch (column) {
	            case 1:
	                f.setPrecio((Double) aValue);
	                break;
	            case 2:
	                f.setMaterialC((String) aValue);
	                break;
	        }
	    } else if (p instanceof Ducha) {
	    	Ducha d = (Ducha) p;
	        switch (column) {
	            case 1:
	                d.setPrecio((Double) aValue);
	                break;
	            case 2:
	                d.setMaterialB((String) aValue);
	                break;
	        }
	    } else {
	    	Armario armario = (Armario) p;
	        switch (column) {
	            case 1:
	                armario.setPrecio((Double) aValue);
	                break;
	            case 2:
	                armario.setMaterial((String) aValue);
	                break;
	        }
	    }

	    fireTableCellUpdated(row, column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
	    Producto p = productos.get(row);

	    if (p instanceof Silla) {
	        Silla silla = (Silla) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = silla.getImagen();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return silla.getPeso();
	            case 1: return silla.getPrecio();
	            case 2: return silla.getMaterial();
	            case 3: return silla.getIdProducto();
	                
	            default: return null;
	        }
	    } else if (p instanceof Mesa) {
	        Mesa mesa = (Mesa) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = mesa.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            case 0: return mesa.getPeso();
	            case 1: return mesa.getPrecio();
	            case 2: return mesa.getMaterial();
	            case 3: return mesa.getIdProducto();
	            default: return null;
	        }
	    } else if (p instanceof Sofa) {
	        Sofa sofa = (Sofa) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = sofa.getImagen();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return sofa.getPeso();
	            case 1: return sofa.getPrecio();
	            case 2: return sofa.getMaterial();
	            case 3: return sofa.getIdProducto();
	            default: return null;
	        }
	    } else if (p instanceof Bide) {
	    	Bide b = (Bide) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = b.getImagenB();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return b.getPeso();
	            case 1: return b.getPrecio();
	            case 2: return b.getMaterialB();
	            case 3: return b.getIdProducto();
	            default: return null;
	        }
	    } else if (p instanceof Ducha) {
	    	Ducha d = (Ducha) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = d.getImagenB();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return d.getPeso();
	            case 1: return d.getPrecio();
	            case 2: return d.getMaterialB();
	            case 3: return d.getIdProducto();
	            default: return null;
	        }
	    } else if (p instanceof Encimera) {
	    	Encimera b = (Encimera) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = b.getImagenC();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return b.getPeso();
	            case 1: return b.getPrecio();
	            case 2: return b.getMaterialC();
	            case 3: return b.getIdProducto();
	            default: return null;
	        }
	    } else if (p instanceof Fregadero) {
	    	Fregadero f = (Fregadero) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = f.getImagenC();
                if (image != null) {
                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
                }
                return null;
	            case 0: return f.getPeso();
	            case 1: return f.getPrecio();
	            case 2: return f.getMaterialC();
	            case 3: return f.getIdProducto();
	            default: return null;
	        }
	    } else {
	        Armario armario = (Armario) p;
	        switch (column) {
	            case 4: 
	            	ImageIcon image = armario.getImagen();
	                if (image != null) {
	                    return new ImageIcon(image.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
	                }
	                return null;
	            case 0: return armario.getPeso();
	            case 1: return armario.getPrecio();
	            case 2: return armario.getMaterial();
	            case 3: return armario.getIdProducto();
	            default: return null;
	        }
	    }
	}
	
	public void addProducto(Producto producto) {
	    productos.add(producto);
	    fireTableRowsInserted(productos.size() - 1, productos.size() - 1);
	}
	
}

