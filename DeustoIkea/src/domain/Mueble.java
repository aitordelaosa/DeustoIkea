package domain;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Mueble extends Producto{
	protected String material;
    protected String color;
    protected String descripcion;
    protected ImageIcon imagen;
	
    public Mueble(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String descripcion, String rutaImagen) {
		super(idProducto, numeroProductos, peso, precio);
		this.material = material;
		this.color = color;
		this.descripcion = descripcion;
		this.imagen = new ImageIcon(rutaImagen);
	}
    
    public Mueble() {
		super();
		this.material = "";
		this.color = "";
		this.descripcion = "";
		this.imagen = null;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		result = prime * result + Objects.hash(color, descripcion, material);
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
		Mueble other = (Mueble) obj;
		return Objects.equals(color, other.color) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(material, other.material);
	}

	@Override
	public String toString() {
		return "Mueble [material=" + material + ", color=" + color + ", descripcion=" + descripcion + "]";
	}
    
}
