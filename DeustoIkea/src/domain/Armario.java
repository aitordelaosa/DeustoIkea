package domain;

import java.util.Objects;

public class Armario extends Mueble {
	protected int numeroDePuertas;
    protected double altura;
    protected double anchura;
    protected double profundidad;
    
    public Armario(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "", "");
		
    }
	public Armario(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String descripcion, String rutaImagen, int numeroDePuertas, double altura, double anchura,
			double profundidad) {
		super(idProducto, numeroProductos, peso, precio, material, color, descripcion, rutaImagen);
		this.numeroDePuertas = numeroDePuertas;
		this.altura = altura;
		this.anchura = anchura;
		this.profundidad = profundidad;
	}
	
	public Armario() {
		super();
		this.numeroDePuertas = 0;
		this.altura = 0.0;
		this.anchura = 0.0;
		this.profundidad = 0.0;
	}
	
	public int getNumeroDePuertas() {
		return numeroDePuertas;
	}
	
	public void setNumeroDePuertas(int numeroDePuertas) {
		this.numeroDePuertas = numeroDePuertas;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, anchura, numeroDePuertas, profundidad);
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
		Armario other = (Armario) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(anchura) == Double.doubleToLongBits(other.anchura)
				&& numeroDePuertas == other.numeroDePuertas
				&& Double.doubleToLongBits(profundidad) == Double.doubleToLongBits(other.profundidad);
	}
	
	@Override
	public String toString() {
		return "Armario [numeroDePuertas=" + numeroDePuertas + ", altura=" + altura + ", anchura=" + anchura
				+ ", profundidad=" + profundidad + "]";
	}
    
    
}
