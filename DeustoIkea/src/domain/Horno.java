package domain;

import java.util.Objects;

public class Horno extends Cocina{
	protected double altura;
	protected double anchura;
	protected double profundidad; 
	protected int potencia;
	protected int numeroBandejas;
	
	public Horno(Producto p) {
		super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
	}
	public Horno() {
		super();
		
		this.altura = 0.0;
		this.anchura = 0.0;
		this.profundidad = 0.0;
		this.potencia = 0;
		this.numeroBandejas = 0;
	}
	
	public Horno(int idProducto, int numeroProductos, double peso, double precio, String materialC, String descripcionC,
			String rutaImagen,double altura, double anchura, double profundidad, int potencia, int numeroBandejas) {
		super(idProducto, numeroProductos, peso, precio, materialC, descripcionC, rutaImagen);
		
		this.altura = altura;
		this.anchura = anchura;
		this.profundidad = profundidad;
		this.potencia = potencia;
		this.numeroBandejas = numeroBandejas;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getAnchura() {
		return anchura;
	}

	public void setAnchura(double anchura) {
		this.anchura = anchura;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getNumeroBandejas() {
		return numeroBandejas;
	}

	public void setNumeroBandejas(int numeroBandejas) {
		this.numeroBandejas = numeroBandejas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, anchura, numeroBandejas, potencia, profundidad);
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
		Horno other = (Horno) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(anchura) == Double.doubleToLongBits(other.anchura)
				&& numeroBandejas == other.numeroBandejas && potencia == other.potencia
				&& Double.doubleToLongBits(profundidad) == Double.doubleToLongBits(other.profundidad);
	}

	@Override
	public String toString() {
		return "Horno [altura=" + altura + ", anchura=" + anchura + ", profundidad=" + profundidad + ", potencia="
				+ potencia + ", numeroBandejas=" + numeroBandejas + "]";
	}
	
	
	
	

}
