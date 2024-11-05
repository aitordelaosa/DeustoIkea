package domain;


import java.util.Objects;

import javax.swing.ImageIcon;

public class Jardineria extends Producto {
    protected boolean esExterior;
    protected String material;
    protected String descripcion;
	protected ImageIcon imagen;

    public Jardineria(int idProducto, int numeroProductos, double peso, double precio, 
    		boolean esExterior, String material, String descripcion, String rutaImagen) {
        super(idProducto, numeroProductos, peso, precio);
        this.esExterior = esExterior;
        this.descripcion = descripcion;
        this.material = material;
		this.imagen = new ImageIcon(rutaImagen);
	}
    
    
    public Jardineria() {
        super();
        this.esExterior = false;
        this.material = "";
        this.descripcion = "";
        this.imagen = null;
    }

    public boolean isEsExterior() {
        return esExterior;
    }

    public void setEsExterior(boolean esExterior) {
        this.esExterior = esExterior;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descripcion, esExterior, imagen, material);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jardineria other = (Jardineria) obj;
		return Objects.equals(descripcion, other.descripcion) && esExterior == other.esExterior
				&& Objects.equals(imagen, other.imagen) && Objects.equals(material, other.material);
	}


	@Override
	public String toString() {
		return "Jardineria [esExterior=" + esExterior + ", material=" + material + ", descripcion=" + descripcion
				+ ", imagen=" + imagen + "]";
	}

    
    
}