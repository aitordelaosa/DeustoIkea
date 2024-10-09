package domain;

import java.time.LocalDate;

import clases.Persona;

public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	
	protected LocalDate ultimoLogin;
	//protected List<Reserva> listaReservasCliente; O alguna otra cosa iria aqui y se implementaría abajo
	
	
	public Cliente(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, LocalDate ultimoLogin) {
		super(dni, genero, nombre, apellido, email, direccion, fNacimiento, contraseña, telefono);
		
		this.ultimoLogin = ultimoLogin;
	}
	
	public Cliente() {
		super();
		this.ultimoLogin = LocalDate.now();
	}

	public LocalDate getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(LocalDate ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	@Override
	public String toString() {
		return "Cliente";
	}
	
}