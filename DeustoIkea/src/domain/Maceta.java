package domain;

import java.util.Objects;

public class Maceta extends Jardineria {
    private double diametro;

    public Maceta(int idProducto, int numeroProductos, double peso, double precio, 
    		boolean esExterior, String material, String descripcion, String rutaimagen, double diametro) {
        super(idProducto, numeroProductos, peso, precio, esExterior, material, descripcion, rutaimagen );
        this.diametro = diametro;
    }

    public Maceta() {
        super();
        this.diametro = 0.0;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(diametro);
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
		Maceta other = (Maceta) obj;
		return Double.doubleToLongBits(diametro) == Double.doubleToLongBits(other.diametro);
	}

	@Override
	public String toString() {
		return "Maceta [diametro=" + diametro + "]";
	}

 
}