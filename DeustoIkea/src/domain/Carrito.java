package domain;

public class Carrito {
	private int idP;
	private String nomP;
	private int cant;
	private float precio;
	private String dni;
	public Carrito() {
		super();
	}
	
	public Carrito(int idP, String nomP, int cant, float precio, String dni) {
		super();
		this.idP = idP;
		this.nomP = nomP;
		this.cant = cant;
		this.precio = precio;
		this.dni = dni;
	}

	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public String getNomP() {
		return nomP;
	}
	public void setNomP(String nomP) {
		this.nomP = nomP;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Carrito [idP=" + idP + ", nomP=" + nomP + ", cant=" + cant + ", precio=" + precio + ", dni=" + dni
				+ "]";
	}
	
	
	
}
