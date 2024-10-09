package domain;

import java.util.Objects;
import clases.Producto;

public class Producto {
	private static final long serialVersionUID = 1L;
	
	protected int idProducto;
	protected int numeroProductos;
	protected double peso;
	protected double precio;
	
	public Producto(int idProducto, int numeroProductos, double peso, double precio) {
		super();
		this.idProducto = idProducto;
		this.numeroProductos = numeroProductos;
		this.peso = peso;
		this.precio = precio;
	}
	
	public Producto() {
		super();
		this.idProducto = 0;
		this.numeroProductos = 0;
		this.peso = 0.0;
		this.precio = 0.0;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getNumeroProductos() {
		return numeroProductos;
	}

	public void setNumeroProductos(int numeroProductos) {
		this.numeroProductos = numeroProductos;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProducto, numeroProductos, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return idProducto == other.idProducto && numeroProductos == other.numeroProductos
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

	@Override
	public String toString() {
		return String.format("Id %s, Numero %s, Peso %s", idProducto, numeroProductos, peso);
	}
	
}
