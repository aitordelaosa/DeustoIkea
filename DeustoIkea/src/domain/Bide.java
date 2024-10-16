package domain;

public class Bide {
	 private String material; // Material del Bide
	    private boolean incluyeGrifo;

	    // Constructor vacío
	    public Bide() {
	        this.material = "Desconocido";
	        this.incluyeGrifo = false;
	    }

	    // Constructor con parámetros
	    public Bide(String material, boolean incluyeGrifo) {
	        this.material = material;
	        this.incluyeGrifo = incluyeGrifo;
	    }

	    // Constructor de copia
	    public Bide(Bide otroBide) {
	        this.material = otroBide.material;
	        this.incluyeGrifo = otroBide.incluyeGrifo;
	    }

	    // Getters y Setters
	    public String getMaterial() {
	        return material;
	    }

	    public void setMaterial(String material) {
	        this.material = material;
	    }

	    public boolean isIncluyeGrifo() {
	        return incluyeGrifo;
	    }

	    public void setIncluyeGrifo(boolean incluyeGrifo) {
	        this.incluyeGrifo = incluyeGrifo;
	    }

	    @Override
	    public String toString() {
	        return "Bide de " + material + ", Incluye grifo: " + (incluyeGrifo ? "Sí" : "No");
	    }
}
