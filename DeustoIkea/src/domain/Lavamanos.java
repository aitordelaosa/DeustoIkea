package domain;

public class Lavamanos extends Baño {
    private String tipoGrifo;
    private boolean tieneAlmacenamiento;

    // Constructor vacío
    public Lavamanos() {
        super();
        this.tipoGrifo = "";
        this.tieneAlmacenamiento = false;
    }
    
    public Lavamanos(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
		
    }

    // Constructor con parámetros
    public Lavamanos(int idProducto, int numeroProductos, double peso, double precio, 
                     String materialB, String descripcionB, String rutaImagen, 
                     String tipoGrifo, boolean tieneAlmacenamiento) {
        super(idProducto, numeroProductos, peso, precio, materialB, descripcionB, rutaImagen);
        this.tipoGrifo = tipoGrifo;
        this.tieneAlmacenamiento = tieneAlmacenamiento;
    }

    // Getters y Setters
    public String getTipoGrifo() {
        return tipoGrifo;
    }

    public void setTipoGrifo(String tipoGrifo) {
        this.tipoGrifo = tipoGrifo;
    }

    public boolean isTieneAlmacenamiento() {
        return tieneAlmacenamiento;
    }

    public void setTieneAlmacenamiento(boolean tieneAlmacenamiento) {
        this.tieneAlmacenamiento = tieneAlmacenamiento;
    }

    @Override
    public String toString() {
        return super.toString() + " Lavamanos [tipoGrifo=" + tipoGrifo + ", tieneAlmacenamiento=" + tieneAlmacenamiento + "]";
    }
}
