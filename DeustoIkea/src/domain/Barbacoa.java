package domain;

import java.util.Objects;

public class Barbacoa extends Jardineria {
    private String tipoCombustible;  // Ej: carbón, gas, eléctrico
    private double superficieCoccion;  // Superficie de cocción en metros cuadrados
    private boolean tieneTapa;  // Indica si la barbacoa tiene tapa

    public Barbacoa(int idProducto, int numeroProductos, double peso, double precio, boolean esExterior, String material, String tipoCombustible, double superficieCoccion, boolean tieneTapa) {
        super(idProducto, numeroProductos, peso, precio, esExterior, material);
        this.tipoCombustible = tipoCombustible;
        this.superficieCoccion = superficieCoccion;
        this.tieneTapa = tieneTapa;
    }

    public Barbacoa() {
        super();
        this.tipoCombustible = "";
        this.superficieCoccion = 0.0;
        this.tieneTapa = false;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getSuperficieCoccion() {
        return superficieCoccion;
    }

    public void setSuperficieCoccion(double superficieCoccion) {
        this.superficieCoccion = superficieCoccion;
    }

    public boolean isTieneTapa() {
        return tieneTapa;
    }

    public void setTieneTapa(boolean tieneTapa) {
        this.tieneTapa = tieneTapa;
    }

    @Override
    public String toString() {
        return "Barbacoa [tipoCombustible=" + tipoCombustible + ", superficieCoccion=" + superficieCoccion + " m², tieneTapa=" + tieneTapa + "]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(superficieCoccion, tieneTapa, tipoCombustible);
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
		Barbacoa other = (Barbacoa) obj;
		return Double.doubleToLongBits(superficieCoccion) == Double.doubleToLongBits(other.superficieCoccion)
				&& tieneTapa == other.tieneTapa && Objects.equals(tipoCombustible, other.tipoCombustible);
	}
    
}