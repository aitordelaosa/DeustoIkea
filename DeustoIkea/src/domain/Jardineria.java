package domain;


import java.util.Objects;

public class Jardineria extends Producto {
    protected boolean esExterior;
    protected String material;

    public Jardineria(int idProducto, int numeroProductos, double peso, double precio, boolean esExterior, String material) {
        super(idProducto, numeroProductos, peso, precio);
        this.esExterior = esExterior;
        this.material = material;
    }

    public Jardineria() {
        super();
        this.esExterior = false;
        this.material = "";
    }

    public boolean isEsExterior() {
        return esExterior;
    }

    public void setEsExterior(boolean esExterior) {
        this.esExterior = esExterior;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(esExterior, material);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jardineria other = (Jardineria) obj;
        return esExterior == other.esExterior && Objects.equals(material, other.material);
    }

    @Override
    public String toString() {
        return "Jardineria [esExterior=" + esExterior + ", material=" + material + "]";
    }
}