package domain;

public interface Pagable {
	double calcularPrecioTotal();
	boolean procesarPago(double PrecioTotal);
}
