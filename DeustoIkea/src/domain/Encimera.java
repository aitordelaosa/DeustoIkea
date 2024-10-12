package domain;

import java.util.Objects;

public class Encimera extends Cocina{
	protected double resistenciaCalor;
	protected double grosor;
	protected String color;
	
	public Encimera() {
		super();
		
		this.resistenciaCalor = 0.0;
		this.grosor = 0.0;
		this.color = "";
	}
	public Encimera(int idProducto, int numeroProductos, double peso, double precio, String materialC,
			String descripcionC, String rutaImagen,double resistenciaCalor, double grosor, String color) {
		super(idProducto, numeroProductos, peso, precio, materialC, descripcionC, rutaImagen);
		
		this.resistenciaCalor = resistenciaCalor;
		this.grosor = grosor;
		this.color = color;
	}
	
	public double getResistenciaCalor() {
		return resistenciaCalor;
	}
	
	public void setResistenciaCalor(double resistenciaCalor) {
		this.resistenciaCalor = resistenciaCalor;
	}
	
	public double getGrosor() {
		return grosor;
	}
	
	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(color, grosor, resistenciaCalor);
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
		Encimera other = (Encimera) obj;
		return Objects.equals(color, other.color)
				&& Double.doubleToLongBits(grosor) == Double.doubleToLongBits(other.grosor)
				&& Double.doubleToLongBits(resistenciaCalor) == Double.doubleToLongBits(other.resistenciaCalor);
	}
	
	@Override
	public String toString() {
		return "Encimera [resistenciaCalor=" + resistenciaCalor + ", grosor=" + grosor + ", color=" + color + "]";
	}
	

}
