package domain;

public class Datos {
	protected Mesa mesa;
	protected Silla silla;
	protected Armario armario;
	protected Sofa sofa;
	
	public Mesa getMesa() {
        return mesa;
    }

    public Silla getSilla() {
        return silla;
    }

    public Armario getArmario() {
        return armario;
    }

    public Sofa getSofa() {
        return sofa;
    }
	
	public Datos() {
//		mesa: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, double altura, int capacidad
//		silla: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, double altura, double ancho, double capacidadDeCarga
//		armario: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, int numeroDePuertas, double altura, double anchura, double profundidad
//		sofa: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, int capacidadDeAsientos
     
        mesa = new Mesa(1, 10, 20.0, 150.0, "Madera", "Marrón", "Mesa de comedor para seis personas, hecha de madera de alta calidad.", null, 75.0, 6);
        silla = new Silla(2, 15, 5.0, 75.0, "Plástico", "Negro", "Silla ergonómica con respaldo ajustable, perfecta para oficina o comedor.", null, 1.0, 0.5, 120.0);
        armario = new Armario(3, 8, 50.0, 200.0, "Madera", "Blanco", "Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.", null, 2, 200.0, 100.0, 50.0);
        sofa = new Sofa(4, 5, 40.0, 350.0, "Tela", "Gris", "Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.", null, 3);
        //Mirar si coincide la descripcion con la foto
        //Cambiar html a txt en el String.format
    }
	
	public String obtenerDetallesMueble(Mueble m) {
        String detalles = "";
        
        if (m instanceof Mesa) {
            detalles = String.format("<html><b>Mesa</b><br>Id: %d<br>Precio: $%.2f<br>Peso: %.2fkg<br>Material: %s<br>Color: %s<br>Descripción: %s<br>Altura: %.2fm<br>Capacidad: %d personas</html>",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), mesa.getMaterial(), mesa.getColor(), mesa.getDescripcion(), mesa.getAltura(), mesa.getCapacidad());
        } else if (m instanceof Silla) {
            detalles = String.format("<html><b>Silla</b><br>Id: %d<br>Precio: $%.2f<br>Peso: %.2fkg<br>Material: %s<br>Color: %s<br>Descripción: %s<br>Altura: %.2fm<br>Ancho: %.2fm<br>Capacidad de carga: %.2fkg</html>",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), silla.getMaterial(), silla.getColor(), silla.getDescripcion(), silla.getAltura(), silla.getAncho(), silla.getCapacidadDeCarga());
        } else if (m instanceof Armario) {
            detalles = String.format("<html><b>Armario</b><br>Id: %d<br>Precio: $%.2f<br>Peso: %.2fkg<br>Material: %s<br>Color: %s<br>Descripción: %s<br>Número de puertas: %d<br>Altura: %.2fm<br>Anchura: %.2fm<br>Profundidad: %.2fm</html>",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), armario.getMaterial(), armario.getColor(), armario.getDescripcion(), armario.getNumeroDePuertas(), armario.getAltura(), armario.getAnchura(), armario.getProfundidad());
        } else if (m instanceof Sofa) {
            detalles = String.format("<html><b>Sofá</b><br>Id: %d<br>Precio: $%.2f<br>Peso: %.2fkg<br>Material: %s<br>Color: %s<br>Descripción: %s<br>Capacidad de asientos: %d</html>",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), sofa.getMaterial(), sofa.getColor(), sofa.getDescripcion(), sofa.getCapacidadDeAsientos());
        }

        return detalles;
    }
}
