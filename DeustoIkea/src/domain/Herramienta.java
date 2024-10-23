package domain;

import java.util.Objects;

public class Herramienta extends Jardineria {
    private String tipo;

    public Herramienta(int idProducto, int numeroProductos, double peso, double precio, boolean esExterior, String material, String tipo) {
        super(idProducto, numeroProductos, peso, precio, esExterior, material);
        this.tipo = tipo;
    }

    public Herramienta() {
        super();
        this.tipo = "";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Herramienta [tipo=" + tipo + "]";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tipo);
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
		Herramienta other = (Herramienta) obj;
		return Objects.equals(tipo, other.tipo);
	}
    
}