package domain;

import java.time.LocalDate;
import java.util.Objects;



public class Persona {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	protected String dni;
	protected String genero;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected String direccion;
	protected LocalDate fNacimiento;
	protected String contrasenia;
	protected String telefono;
	protected int id;
	static int contador = 1;
	
	public Persona(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contrasenia, String telefono,int id) {
		super();
		this.dni = dni;
		this.genero = genero;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.fNacimiento = fNacimiento;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.id = contador;
		contador++;
	}
	
	public Persona() {
		super();
		this.dni = "";
		this.genero = "";
		this.nombre = "";
		this.apellido = "";
		this.email = "";
		this.direccion = "";
		this.fNacimiento = LocalDate.now();
		this.contrasenia = "";
		this.telefono = "";
		this.id = contador;
		contador++;
	}
	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Persona.contador = contador;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContraseñni(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, contrasenia, direccion, dni, email, fNacimiento, genero, id, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(dni, other.dni)
				&& Objects.equals(email, other.email) && Objects.equals(fNacimiento, other.fNacimiento)
				&& Objects.equals(genero, other.genero) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", genero=" + genero + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", direccion=" + direccion + ", fNacimiento=" + fNacimiento + ", contrasenia="
				+ contrasenia + ", telefono=" + telefono + ", id=" + id + "]";
	}

	
	
}