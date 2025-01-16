package domain;

public class Bide extends Baño {
    private boolean tieneCalefaccion;
    private boolean esElectrico;

    // Constructor vacío
    public Bide() {
        super();
        this.tieneCalefaccion = false;
        this.esElectrico = false;
    }
    public Bide(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
		
    }

    // Constructor con parámetros
    public Bide(int idProducto, int numeroProductos, double peso, double precio, 
                String materialB, String descripcionB, String rutaImagen, 
                boolean tieneCalefaccion, boolean esElectrico) {
        super(idProducto, numeroProductos, peso, precio, materialB, descripcionB, rutaImagen);
        this.tieneCalefaccion = tieneCalefaccion;
        this.esElectrico = esElectrico;
    }

    // Getters y Setters
    public boolean isTieneCalefaccion() {
        return tieneCalefaccion;
    }

    public void setTieneCalefaccion(boolean tieneCalefaccion) {
        this.tieneCalefaccion = tieneCalefaccion;
    }

    public boolean isEsElectrico() {
        return esElectrico;
    }

    public void setEsElectrico(boolean esElectrico) {
        this.esElectrico = esElectrico;
    }

    @Override
    public String toString() {
        return super.toString() + " Bide [tieneCalefaccion=" + tieneCalefaccion + ", esElectrico=" + esElectrico + "]";
    }
}
