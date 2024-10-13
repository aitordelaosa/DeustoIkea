package domain;

public class Datos {
	//Muebles
	protected Mesa mesa;
	protected Silla silla;
	protected Armario armario;
	protected Sofa sofa;
	//Cocina
	protected Nevera nevera;
	protected Horno horno;
	protected Encimera encimera;
	protected Fregadero fregadero;
	
	
	//Muebles
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
    
    //Cocina
    public Nevera getNevera() {
        return nevera;
    }
    
    public Horno getHorno() {
        return horno;
    }
    
    public Encimera getEncimera() {
        return encimera;
    }
    
    public Fregadero getFregadero() {
        return fregadero;
    }
	
	public Datos() {
//		mesa: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, double altura, int capacidad
//		silla: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, double altura, double ancho, double capacidadDeCarga
//		armario: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, int numeroDePuertas, double altura, double anchura, double profundidad
//		sofa: int idProducto, int numeroProductos, double peso, double precio, String material, String color, String descripcion, String rutaImagen, int capacidadDeAsientos

//		int idProducto, int numeroProductos, double peso, double precio, String materialC, String descripcionC, String rutaImagen, double altura, double anchura, double profundidad, double capacidad, String tipoNevera
//		int idProducto, int numeroProductos, double peso, double precio, String materialC, String descripcionC, String rutaImagen, double altura, double anchura, double profundidad, int potencia,int numeroBandejas
//		int idProducto, int numeroProductos, double peso, double precio, String materialC, String descripcionC, String rutaImagen, double resistenciaCalor, double grosor, String color
//		int idProducto, int numeroProductos, double peso, double precio, String materialC, String descripcionC, String rutaImagen, int numCubetas, double profundidad, boolean grifo
		
		//Muebles
        mesa = new Mesa(1, 10, 20.0, 150.0, "Madera", "Marrón", "Mesa de comedor para seis personas, hecha de madera de alta calidad.", null, 140.0, 6);
        silla = new Silla(2, 15, 5.0, 75.0, "Plástico", "Negro", "Silla ergonómica, perfecta para oficina o comedor.", null, 120.3, 50, 120.0);
        armario = new Armario(3, 8, 50.0, 200.0, "Madera", "Blanco", "Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.", null, 2, 200.0, 100.0, 50.0);
        sofa = new Sofa(4, 5, 40.0, 350.0, "Tela", "Gris", "Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.", null, 3);
        
        //Cocina
        nevera = new Nevera(5, 12, 81.5, 699.90, "Aluminio", "Nevera con dos puertas, ideal para familias", null, 198.8, 70.2, 57.2, 150, "Nevera con congelador");
        horno = new Horno(6, 4, 67.2, 250.8, "Metal", "Horno con gran capacidad para tus mejores recetas", null, 59.5, 59.5, 56.9, 95, 2);
        encimera = new Encimera(7, 9, 34, 190.95, "Granito", "Elegante encimera para darle un toque moderno a tu cocina", null, 80.8, 23.9, "Blanco");
        fregadero = new Fregadero(8, 9, 12.5, 280.87, "Metal", "Fregadero con dos cubetas y grifo incluido", null, 2, 28.6, true);
    }
	//Muebles
	public String obtenerDetallesMueble(Mueble m) {
        String detalles = "";
        
        if (m instanceof Mesa) {
            detalles = String.format("Mesa\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nCapacidad: %d personas",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), mesa.getMaterial(), mesa.getColor(), mesa.getDescripcion(), mesa.getAltura(), mesa.getCapacidad());
        } else if (m instanceof Silla) {
            detalles = String.format("Silla\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nAncho: %.2fm\nCapacidad de carga: %.2fkg",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), silla.getMaterial(), silla.getColor(), silla.getDescripcion(), silla.getAltura(), silla.getAncho(), silla.getCapacidadDeCarga());
        } else if (m instanceof Armario) {
            detalles = String.format("Armario\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nNúmero de puertas: %d\nAltura: %.2fm\nAnchura: %.2fm\nProfundidad: %.2fm",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), armario.getMaterial(), armario.getColor(), armario.getDescripcion(), armario.getNumeroDePuertas(), armario.getAltura(), armario.getAnchura(), armario.getProfundidad());
        } else if (m instanceof Sofa) {
            detalles = String.format("Sofá\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nCapacidad de asientos: %d",
                    m.getIdProducto(), m.getPrecio(), m.getPeso(), sofa.getMaterial(), sofa.getColor(), sofa.getDescripcion(), sofa.getCapacidadDeAsientos());
        }
       

        return detalles;
    }
	//Cocina
	public String obtenerDetallesCocina(Cocina c) {
		String detalles = "";
		
		if (c instanceof Nevera) {
			detalles = String.format("Nevera\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nAltura: %.2f cm\nAnchura: %.2f cm\nProfundidad: %.2f cm\nCapacidad: %.2f litros\nTipo de Nevera: %s",
									nevera.getIdProducto(),nevera.getPrecio(),nevera.getPeso(),nevera.getMaterialC(),nevera.getDescripcionC(),nevera.getAltura(),nevera.getAnchura(),nevera.getProfundidad(),nevera.getCapacidad(),nevera.getTipoNevera());
		}else if(c instanceof Horno) {
			detalles = String.format("Horno\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nAltura: %.2f cm\nAnchura: %.2f cm\nProfundidad: %.2f cm\nPotencia: %d KW\nNumero de bandejas: %d", 
									horno.getIdProducto(),horno.getPrecio(),horno.getPeso(),horno.getMaterialC(),horno.getDescripcionC(),horno.getAltura(),horno.getAnchura(),horno.getProfundidad(),horno.getPotencia(),horno.getNumeroBandejas());
		}else if (c instanceof Encimera) {
			detalles = String.format("Encimera\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nResistencia al calor: %.2f grados\nGrosor: %.2f cm\nColor: %s ", 
					encimera.getIdProducto(),encimera.getPrecio(),encimera.getPeso(),encimera.getMaterialC(),encimera.getDescripcionC(),encimera.getResistenciaCalor(),encimera.getGrosor(),encimera.getColor());
		}else if (c instanceof Fregadero) {
			detalles = String.format("Fregadero\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nNumero de cubetas: %d \nProfundidad: %.2f cm\nGrifo: %b ", 
					fregadero.getIdProducto(),fregadero.getPrecio(),fregadero.getPeso(),fregadero.getMaterialC(),fregadero.getDescripcionC(),fregadero.getNumCubetas(),fregadero.getProfundidad(),fregadero.isGrifo());
		}
		
		return detalles;
	}
}
