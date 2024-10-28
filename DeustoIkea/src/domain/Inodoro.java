package domain;

public class Inodoro extends Baño {
    private String tipoDescarga;
    private boolean tieneAsientoCalefaccionado;

    // Constructor vacío
    public Inodoro() {
        super();
        this.tipoDescarga = "";
        this.tieneAsientoCalefaccionado = false;
    }

    // Constructor con parámetros
    public Inodoro(int idProducto, int numeroProductos, double peso, double precio, 
                   String materialB, String descripcionB, String rutaImagen, 
                   String tipoDescarga, boolean tieneAsientoCalefaccionado) {
        super(idProducto, numeroProductos, peso, precio, materialB, descripcionB, rutaImagen);
        this.tipoDescarga = tipoDescarga;
        this.tieneAsientoCalefaccionado = tieneAsientoCalefaccionado;
    }

    // Getters y Setters
    public String getTipoDescarga() {
        return tipoDescarga;
    }

    public void setTipoDescarga(String tipoDescarga) {
        this.tipoDescarga = tipoDescarga;
    }

    public boolean isTieneAsientoCalefaccionado() {
        return tieneAsientoCalefaccionado;
    }

    public void setTieneAsientoCalefaccionado(boolean tieneAsientoCalefaccionado) {
        this.tieneAsientoCalefaccionado = tieneAsientoCalefaccionado;
    }

    @Override
    public String toString() {
        return super.toString() + " Inodoro [tipoDescarga=" + tipoDescarga + ", tieneAsientoCalefaccionado=" + tieneAsientoCalefaccionado + "]";
    }
}
