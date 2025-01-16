package domain;

import java.util.Objects;

public class Fregadero extends Cocina {
	protected int numCubetas;
	protected double profundidad;
	protected boolean grifo;
	
	
	public Fregadero() {
		super();
		
		this.numCubetas = 0;
		this.profundidad = 0.0;
		this.grifo = false;
	}
	
	public Fregadero(Producto p) {
		super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
	}
	
	public Fregadero(int idProducto, int numeroProductos, double peso, double precio, String materialC,
			String descripcionC, String rutaImagen,int numCubetas, double profundidad, boolean grifo) {
		super(idProducto, numeroProductos, peso, precio, materialC, descripcionC, rutaImagen);
		
		this.numCubetas = numCubetas;
		this.profundidad = profundidad;
		this.grifo = grifo;
	}
	
	public int getNumCubetas() {
		return numCubetas;
	}
	
	public void setNumCubetas(int numCubetas) {
		this.numCubetas = numCubetas;
	}
	
	public double getProfundidad() {
		return profundidad;
	}
	
	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}
	
	public boolean isGrifo() {
		return grifo;
	}
	
	public void setGrifo(boolean grifo) {
		this.grifo = grifo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(grifo, numCubetas, profundidad);
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
		Fregadero other = (Fregadero) obj;
		return grifo == other.grifo && numCubetas == other.numCubetas
				&& Double.doubleToLongBits(profundidad) == Double.doubleToLongBits(other.profundidad);
	}
	
	@Override
	public String toString() {
		return "Fregadero [numCubetas=" + numCubetas + ", profundidad=" + profundidad + ", grifo=" + grifo + "]";
	}

	
}
