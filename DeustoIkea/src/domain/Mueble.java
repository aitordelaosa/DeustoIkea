package domain;

import java.util.Objects;

public class Mueble extends Producto{
	protected String material;
    protected String color;
    protected String estilo;
	
    public Mueble(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String estilo) {
		super(idProducto, numeroProductos, peso, precio);
		this.material = material;
		this.color = color;
		this.estilo = estilo;
	}
    
    public Mueble() {
		super();
		this.material = "";
		this.color = "";
		this.estilo = "";
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

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(color, estilo, material);
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
		return Objects.equals(color, other.color) && Objects.equals(estilo, other.estilo)
				&& Objects.equals(material, other.material);
	}

	@Override
	public String toString() {
		return "Mueble [material=" + material + ", color=" + color + ", estilo=" + estilo + "]";
	}
    
}
