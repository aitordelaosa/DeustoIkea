package domain;

import java.util.Objects;

public class Silla extends Mueble {
	protected double altura;
    protected double ancho;
    protected double capacidadDeCarga;
	
    public Silla(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String descripcion, String rutaImagen, double altura, double ancho, double capacidadDeCarga) {
		super(idProducto, numeroProductos, peso, precio, material, color, descripcion, rutaImagen);
		this.altura = altura;
		this.ancho = ancho;
		this.capacidadDeCarga = capacidadDeCarga;
	}

	public Silla() {
		super();
		this.altura = 0.0;
		this.ancho = 0.0;
		this.capacidadDeCarga = 0.0;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getCapacidadDeCarga() {
		return capacidadDeCarga;
	}

	public void setCapacidadDeCarga(double capacidadDeCarga) {
		this.capacidadDeCarga = capacidadDeCarga;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, ancho, capacidadDeCarga);
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
		Silla other = (Silla) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(ancho) == Double.doubleToLongBits(other.ancho)
				&& Double.doubleToLongBits(capacidadDeCarga) == Double.doubleToLongBits(other.capacidadDeCarga);
	}

	@Override
	public String toString() {
		return "Silla [altura=" + altura + ", ancho=" + ancho + ", capacidadDeCarga=" + capacidadDeCarga + "]";
	}
	
}
