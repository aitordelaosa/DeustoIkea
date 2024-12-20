package domain;

import java.time.LocalDate;
public class Cliente extends Persona {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	protected LocalDate ultimoLogin;
	//protected List<Reserva> listaReservasCliente; O alguna otra cosa iria aqui y se implementaría abajo
	protected Descuento descuento;
	
	public Cliente(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, int id, LocalDate ultimoLogin, Descuento descuento) {
		super(dni, genero, nombre, apellido, email, direccion, fNacimiento, contraseña, telefono,id);
		
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
	    return "Cliente{" +
	           "DNI='" + dni + '\'' +
	           ", Género='" + genero + '\'' +
	           ", Nombre='" + nombre + '\'' +
	           ", Apellido='" + apellido + '\'' +
	           ", Email='" + email + '\'' +
	           ", Dirección='" + direccion + '\'' +
	           ", Fecha de Nacimiento='" + fNacimiento + '\'' +
	           ", Teléfono='" + telefono + '\'' +
	           ", Último Login='" + ultimoLogin + '\'' +
	           ", Descuento='" + descuento + '\'' +
	           '}';
	}
	
}