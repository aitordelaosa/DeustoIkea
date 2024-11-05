package domain;

import java.util.Objects;

public class Planta extends Jardineria {
    private double altura;
    private double diametro;
    private String tipoDePlanta;

    public Planta(int idProducto, int numeroProductos, double peso, double precio, boolean esExterior, String material, String descripcion, String rutaImagen, double altura, boolean esFrutal, String tipoDePlanta, double diametro) {
        super(idProducto, numeroProductos, peso, precio, esExterior, material, descripcion, rutaImagen);
        this.altura = altura;
        this.diametro = diametro;
        this.tipoDePlanta = tipoDePlanta;
    }

    public Planta() {
        super();
        this.altura = 0.0;
        
        this.tipoDePlanta = "";
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getDiametro() {
		return diametro;
	}

	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}
   
    public String getTipoDePlanta() {
        return tipoDePlanta;
    }

    public void setTipoDePlanta(String tipoDePlanta) {
        this.tipoDePlanta = tipoDePlanta;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, diametro, tipoDePlanta);
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
		Planta other = (Planta) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(diametro) == Double.doubleToLongBits(other.diametro)
				&& Objects.equals(tipoDePlanta, other.tipoDePlanta);
	}

	@Override
	public String toString() {
		return "Planta [altura=" + altura + ", diametro=" + diametro + ", tipoDePlanta=" + tipoDePlanta + "]";
	}


} 