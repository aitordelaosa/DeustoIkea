package domain;

import java.util.Objects;
import javax.swing.ImageIcon;

public class Baño extends Producto {
    protected String materialB;
    protected String descripcionB;
    protected ImageIcon imagenB;

    // Constructor vacío
    public Baño() {
        super();
        this.materialB = "";
        this.descripcionB = "";
        this.imagenB = null;
    }

    // Constructor con parámetros
    public Baño(int idProducto, int numeroProductos, double peso, double precio, 
                String materialB, String descripcionB, String rutaImagen) {
        super(idProducto, numeroProductos, peso, precio);
        
        this.materialB = materialB;
        this.descripcionB = descripcionB;
        this.imagenB = new ImageIcon(rutaImagen);
    }

    // Getters y Setters
    public String getMaterialB() {
        return materialB;
    }

    public void setMaterialB(String materialB) {
        this.materialB = materialB;
    }

    public String getDescripcionB() {
        return descripcionB;
    }

    public void setDescripcionB(String descripcionB) {
        this.descripcionB = descripcionB;
    }

    public ImageIcon getImagenB() {
        return imagenB;
    }

    public void setImagenB(ImageIcon imagenB) {
        this.imagenB = imagenB;
    }

    // Sobrescribir hashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(descripcionB, imagenB, materialB);
        return result;
    }

    // Sobrescribir equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Baño other = (Baño) obj;
        return Objects.equals(descripcionB, other.descripcionB) && Objects.equals(imagenB, other.imagenB)
                && Objects.equals(materialB, other.materialB);
    }

    // Sobrescribir toString
    @Override
    public String toString() {
        return "Baño [material=" + materialB + ", descripcion=" + descripcionB + "]";
    }
}
