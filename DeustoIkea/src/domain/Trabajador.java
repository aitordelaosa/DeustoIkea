package domain;

import java.time.LocalDate;

public class Trabajador extends Persona{
	private static final long serialVersionUID = 1L;
	
	protected double salario;
	protected int horasTrabajadas;
	
	public Trabajador(String dni, String genero, String nombre, String apellido, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, double salario, int horasTrabajadas) {
		super(dni, genero, nombre, apellido, email, direccion, fNacimiento, contraseña, telefono);
		this.salario = salario;
		this.horasTrabajadas = horasTrabajadas;
	}
	
	public Trabajador() {
		super();
		this.salario = 0.0;
		this.horasTrabajadas = 0;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		if(salario < 0) {
			this.salario = salario;
		}
	}
	
	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}
	
	public void setHorasTrabajadas(int horasTrabajadas) {
		if(horasTrabajadas < 0 ) {
			this.horasTrabajadas = horasTrabajadas;
		}
	}

	@Override
	public String toString() {
		return "Trabajador";
	}
		
}