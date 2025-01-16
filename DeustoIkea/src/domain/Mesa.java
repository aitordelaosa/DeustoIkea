package domain;

import java.util.Objects;

public class Mesa extends Mueble {
    protected double altura;
    protected int capacidad;
	
    
    public Mesa(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String descripcion, String rutaImagen, double altura, int capacidad) {
		super(idProducto, numeroProductos, peso, precio, material, color, descripcion, rutaImagen);
		this.altura = altura;
		this.capacidad = capacidad;
	}
    
    public Mesa(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "", "");
		
    }
    
    public Mesa() {
		super();
		this.altura = 0.0;
		this.capacidad = 0;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, capacidad);
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
		Mesa other = (Mesa) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura) && capacidad == other.capacidad;
	}

	@Override
	public String toString() {
		return "Mesa [altura=" + altura + ", capacidad=" + capacidad + "]";
	}
    
}
