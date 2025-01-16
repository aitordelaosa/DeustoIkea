package domain;

public class Sofa extends Mueble {
	protected int capacidadDeAsientos;

	public Sofa(int idProducto, int numeroProductos, double peso, double precio, String material, String color,
			String descripcion, String rutaImagen, int capacidadDeAsientos) {
		super(idProducto, numeroProductos, peso, precio, material, color, descripcion, rutaImagen);
		this.capacidadDeAsientos = capacidadDeAsientos;
	}
	
	public Sofa(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "", "");
		
    }


	public Sofa() {
		super();
		this.capacidadDeAsientos = 0;
	}

	public int getCapacidadDeAsientos() {
		return capacidadDeAsientos;
	}

	public void setCapacidadDeAsientos(int capacidadDeAsientos) {
		this.capacidadDeAsientos = capacidadDeAsientos;
	}

	@Override
	public String toString() {
		return "Sofa [capacidadDeAsientos=" + capacidadDeAsientos + "]";
	}
	
}
