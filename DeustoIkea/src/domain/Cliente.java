package domain;

import java.time.LocalDate;
public class Cliente extends Persona {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	protected LocalDate ultimoLogin;
	//protected List<Reserva> listaReservasCliente; O alguna otra cosa iria aqui y se implementaría abajo
	protected Descuento descuento;
	
	public Cliente(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, LocalDate ultimoLogin, Descuento descuento) {
		super(dni, genero, nombre, apellido, email, direccion, fNacimiento, contraseña, telefono);
		
		this.ultimoLogin = ultimoLogin;
		this.descuento = descuento;
	}
	
	public Cliente() {
		super();
		this.ultimoLogin = LocalDate.now();
		this.descuento = Descuento.Descuento_10;
	}

	public LocalDate getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(LocalDate ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
	
	 public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

	@Override
	public String toString() {
		return "Cliente";
	}
	
}