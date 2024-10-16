package domain;

public class Ducha {
	   private String tipo; // Plato de ducha, bañera, etc.
	    private String tipoMampara; // Corrediza, abatible, etc.
	    private double largo; // En metros
	    private double ancho; // En metros

	    // Constructor vacío
	    public Ducha() {
	        this.tipo = "Desconocido";
	        this.tipoMampara = "Desconocido";
	        this.largo = 0.0;
	        this.ancho = 0.0;
	    }

	    // Constructor con parámetros
	    public Ducha(String tipo, String tipoMampara, double largo, double ancho) {
	        this.tipo = tipo;
	        this.tipoMampara = tipoMampara;
	        this.largo = largo;
	        this.ancho = ancho;
	    }

	    // Constructor de copia
	    public Ducha(Ducha otraDucha) {
	        this.tipo = otraDucha.tipo;
	        this.tipoMampara = otraDucha.tipoMampara;
	        this.largo = otraDucha.largo;
	        this.ancho = otraDucha.ancho;
	    }

	    // Getters y Setters
	    public String getTipo() {
	        return tipo;
	    }

	    public void setTipo(String tipo) {
	        this.tipo = tipo;
	    }

	    public String getTipoMampara() {
	        return tipoMampara;
	    }

	    public void setTipoMampara(String tipoMampara) {
	        this.tipoMampara = tipoMampara;
	    }

	    public double getLargo() {
	        return largo;
	    }

	    public void setLargo(double largo) {
	        this.largo = largo;
	    }

	    public double getAncho() {
	        return ancho;
	    }

	    public void setAncho(double ancho) {
	        this.ancho = ancho;
	    }

	    @Override
	    public String toString() {
	        return "Ducha tipo " + tipo + ", Mampara: " + tipoMampara + ", Dimensiones: " + largo + "x" + ancho + " metros.";
	    }

}
