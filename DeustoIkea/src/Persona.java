

import java.time.LocalDate;
import java.util.Objects;

public class Persona {
	private static final long serialVersionUID = 1L;
	
	protected String dni;
	protected String genero;
	protected String nombre;
	protected String apellido;
	protected String email;
	protected String direccion;
	protected LocalDate fNacimiento;
	protected String contraseña;
	protected String telefono;
	
	public Persona(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono) {
		super();
		this.dni = dni;
		this.genero = genero;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.fNacimiento = fNacimiento;
		this.contraseña = contraseña;
		this.telefono = telefono;
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
		this.contraseña = "";
		this.telefono = "";
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, fNacimiento, nombre);
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
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(fNacimiento, other.fNacimiento)
				&& Objects.equals(nombre, other.nombre);
	}
	
}