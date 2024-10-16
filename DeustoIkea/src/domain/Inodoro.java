package domain;

public class Inodoro {
	private String tipo; // Suspendido, de tanque bajo, etc.
	private double capacidadTanque; // En litros

	// Constructor vacío
	public Inodoro() {
		this.tipo = "Desconocido";
		this.capacidadTanque = 0.0;
	}

	// Constructor con parámetros
	public Inodoro(String tipo, double capacidadTanque) {
		this.tipo = tipo;
		this.capacidadTanque = capacidadTanque;
	}

	// Constructor de copia
	public Inodoro(Inodoro otroInodoro) {
		this.tipo = otroInodoro.tipo;
		this.capacidadTanque = otroInodoro.capacidadTanque;
	}

	// Getters y Setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getCapacidadTanque() {
		return capacidadTanque;
	}

	public void setCapacidadTanque(double capacidadTanque) {
		this.capacidadTanque = capacidadTanque;
	}

	@Override
	public String toString() {
		return "Inodoro: " + tipo + ", Capacidad: " + capacidadTanque + " litros.";
	}
}
