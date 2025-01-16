package domain;

public class Ducha extends Baño {
    private String tipoRociador;
    private boolean tieneMampara;

    // Constructor vacío
    public Ducha() {
        super();
        this.tipoRociador = "";
        this.tieneMampara = false;
    }
    
    public Ducha(Producto p) {
    	super(p.idProducto, p.numeroProductos, p.peso, p.precio, "", "", "");
		
    }

    // Constructor con parámetros
    public Ducha(int idProducto, int numeroProductos, double peso, double precio, 
                 String materialB, String descripcionB, String rutaImagen, 
                 String tipoRociador, boolean tieneMampara) {
        super(idProducto, numeroProductos, peso, precio, materialB, descripcionB, rutaImagen);
        this.tipoRociador = tipoRociador;
        this.tieneMampara = tieneMampara;
    }

    // Getters y Setters
    public String getTipoRociador() {
        return tipoRociador;
    }

    public void setTipoRociador(String tipoRociador) {
        this.tipoRociador = tipoRociador;
    }

    public boolean isTieneMampara() {
        return tieneMampara;
    }

    public void setTieneMampara(boolean tieneMampara) {
        this.tieneMampara = tieneMampara;
    }

    @Override
    public String toString() {
        return super.toString() + " Ducha [tipoRociador=" + tipoRociador + ", tieneMampara=" + tieneMampara + "]";
    }
}