package domain;

import java.util.Objects;

import javax.swing.ImageIcon;

public class Cocina extends Producto {
	protected String materialC;
	protected String descripcionC;
	protected ImageIcon imagenC;
	
	public Cocina() {
		super();
		this.materialC = "";
		this.descripcionC= "";
		this.imagenC= null;
	}
	
	public Cocina(int idProducto, int numeroProductos, double peso, double precio, 
			String materialC ,String descripcionC, String rutaImagen) {
		super(idProducto, numeroProductos, peso, precio);
		
		this.materialC = materialC;
		this.descripcionC = descripcionC;
		this.imagenC = new ImageIcon(rutaImagen);
		
	}
	
	public String getMaterialC() {
		return materialC;
	}
	
	public void setMaterialC(String materialC) {
		this.materialC = materialC;
	}
	
	public String getDescripcionC() {
		return descripcionC;
	}
	
	public void setDescripcionC(String descripcionC) {
		this.descripcionC = descripcionC;
	}
	
	public ImageIcon getImagenC() {
		return imagenC;
	}
	
	public void setImagenC(ImageIcon imagenC) {
		this.imagenC = imagenC;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descripcionC, imagenC, materialC);
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
		Cocina other = (Cocina) obj;
		return Objects.equals(descripcionC, other.descripcionC) && Objects.equals(imagenC, other.imagenC)
				&& Objects.equals(materialC, other.materialC);
	}
	
	@Override
	public String toString() {
		return "Cocina [material=" + materialC + ", descripcion=" + descripcionC+"]";
	}
	

}
