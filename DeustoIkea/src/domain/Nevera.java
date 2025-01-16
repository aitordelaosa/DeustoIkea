package domain;

import java.util.Objects;

public class Nevera extends Cocina {
	protected double altura;
	protected double anchura;
	protected double profundidad;
	protected double capacidad;
	protected String tipoNevera;
	
	public Nevera() {
		super();
		this.altura = 0.0;
		this.anchura = 0.0;
		this.profundidad = 0.0;
		this.capacidad = 0.0;
		this.tipoNevera = "";
	}
	
	public Nevera(Producto p) {
		super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
	}
	
	public Nevera(int idProducto, int numeroProductos, double peso, double precio, String materialC,
			String descripcionC, String rutaImagen, double altura,double anchura,
			double profundidad, double capacidad, String tipoNevera) {
		super(idProducto, numeroProductos, peso, precio, materialC, descripcionC, rutaImagen);
		
		this.altura = altura;
		this.anchura = anchura;
		this.profundidad = profundidad;
		this.capacidad = capacidad;
		this.tipoNevera = tipoNevera;
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
	
	public double getCapacidad() {
		return capacidad;
	}
	
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}
	
	public String getTipoNevera() {
		return tipoNevera;
	}
	
	public void setTipoNevera(String tipoNevera) {
		this.tipoNevera = tipoNevera;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, anchura, capacidad, profundidad, tipoNevera);
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
		Nevera other = (Nevera) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(anchura) == Double.doubleToLongBits(other.anchura)
				&& Double.doubleToLongBits(capacidad) == Double.doubleToLongBits(other.capacidad)
				&& Double.doubleToLongBits(profundidad) == Double.doubleToLongBits(other.profundidad)
				&& Objects.equals(tipoNevera, other.tipoNevera);
	}
	@Override
	public String toString() {
		return "Nevera [altura=" + altura + ", anchura=" + anchura + ", profundidad=" + profundidad + ", capacidad="
				+ capacidad + ", tipoNevera=" + tipoNevera + "]";
	}
	
	
	
	
}
