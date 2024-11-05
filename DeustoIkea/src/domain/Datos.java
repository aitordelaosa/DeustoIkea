package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Datos {

	protected List<Mueble> lMuebles;
	protected List<Cocina> lCocina;
	protected List<Jardineria> lJardineria;
	protected List<Trabajador> lTrabajador;
	protected List<Cliente> lCliente;
	protected List<Baño> lBaño;
	// Muebles
	protected Mesa mesa;
	protected Silla silla;
	protected Armario armario;
	protected Sofa sofa;
	// Cocina
	protected Nevera nevera;
	protected Horno horno;
	protected Encimera encimera;
	protected Fregadero fregadero;
	
	//Jardineria
	protected Maceta maceta;
	protected Barbacoa barbacoa;
	protected Planta planta;
	protected Herramienta herramienta;


	// Baño
	protected Bide bide;
	protected Inodoro inodoro;
	protected Ducha ducha;
	protected Lavamanos lavamanos;

	protected Cliente cliente1, cliente2, cliente3, cliente4, cliente5;
	protected Trabajador trabajador1, trabajador2, trabajador3, trabajador4, trabajador5;

	// Muebles
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

	// Cocina
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
	
	//Jardineria
	
	public Maceta getMaceta() {
		return maceta;
	}

	public Barbacoa getBarbacoa() {
		return barbacoa;
	}

	public Planta getPlanta() {
		return planta;
	}

	public Herramienta getHerramienta() {
		return herramienta;
	}


	// Baño
	public Ducha getDucha() {
		return ducha;
	}

	
	public Inodoro getInodoro() {
		return inodoro;
	}

	public Lavamanos getLavamanos() {
		return lavamanos;
	}

	public Bide getBide() {
		return bide;
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

		// Listas
		lMuebles = new ArrayList<>();
		lCocina = new ArrayList<>();
		lTrabajador = new ArrayList<>();
		lCliente = new ArrayList<>();
		lBaño = new ArrayList<>();

		// Muebles
		mesa = new Mesa(1, 10, 20.0, 150.0, "Madera", "Marrón",
				"Mesa de comedor para seis personas, hecha de madera de alta calidad.", "src/Imagenes/mesa.jpeg", 140.0,
				6);
		silla = new Silla(2, 15, 5.0, 75.0, "Plástico", "Negro", "Silla ergonómica, perfecta para oficina o comedor.",
				"src/Imagenes/silla.jpeg", 120.3, 50, 120.0);
		armario = new Armario(3, 8, 50.0, 200.0, "Madera", "Blanco",
				"Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.",
				"src/Imagenes/Armario.jpeg", 2, 200.0, 100.0, 50.0);
		sofa = new Sofa(4, 5, 40.0, 350.0, "Tela", "Gris",
				"Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.", "src/Imagenes/sofa.jpeg",
				3);
		lMuebles.add(mesa);
		lMuebles.add(silla);
		lMuebles.add(armario);
		lMuebles.add(sofa);

		// Baño
		lavamanos = new Lavamanos(1, 10, 5.5, 150.0, "Cerámica", "Lavamanos moderno", "src/Imagenes/lavamanos.jpg",
				"Monomando", true);

		ducha = new Ducha(2, 5, 7.0, 250.0, "Acero inoxidable", "Ducha con rociador ajustable",
				"src/Imagenes/ducha.jpg", "Circular", true);

		bide = new Bide(3, 8, 4.0, 120.0, "Porcelana", "Bide compacto", "src/Imagenes/bide.jpeg", true, false);

		inodoro = new Inodoro(4, 6, 10.0, 300.0, "Cerámica", "Inodoro eficiente", "src/Imagenes/inodoro.jpg", "Doble",
				false);

		lBaño.add(bide);
		lBaño.add(ducha);
		lBaño.add(lavamanos);
		lBaño.add(inodoro);
		
		//Jardineria
		
		maceta = new Maceta(21, 3, 3.2, 10.75, true, "Cerámica", "Maceta para plantas tamaño mediano", 
				"src/Imagenes/Maceta.jpg", 17.5);
		
		barbacoa = new Barbacoa(22, 5, 150000, 50.99, true, "Hormigón", "resistente y duradera, ideal para exteriores. Incluye parrilla ajustable y espacio de almacenamiento,perfecta para disfrutar asados en el jardín.", 
				"src/Imagenes/Barbacoa.jpg", "Carbón", 19.0, false);
		
		planta = new Planta(23, 8, 20, 21.99, true, "madera", "Es una planta de exterior de tamaño medio, ideal para jardines y patios. Con follaje perenne y flores en tonos rosa, blanco o rojo", 
				"src/Imagenes/adelfas-colores.jpg", 2.5, false, "Adelfa", 27.0);
		
		herramienta = new Herramienta(24, 4, 1, 7.5, false, "Madera", "herramienta resistente con cabeza de acero para cavar y mover tierra, y mango de madera ergonómico para un agarre cómodo",
				"src/Imagenes/Pala.jpg", "Pala");
		
		lJardineria.add(maceta);
		lJardineria.add(barbacoa);
		lJardineria.add(planta);
		lJardineria.add(herramienta);
		
		

		// Cocina
		nevera = new Nevera(5, 12, 81.5, 699.90, "Aluminio", "Nevera con dos puertas, ideal para familias",
				"src/Imagenes/Nevera.jpg", 198.8, 70.2, 57.2, 150, "Nevera con congelador");
		horno = new Horno(6, 4, 67.2, 250.8, "Metal", "Horno con gran capacidad para tus mejores recetas",
				"src/Imagenes/horno.jpg", 59.5, 59.5, 56.9, 95, 2);
		encimera = new Encimera(7, 9, 34, 190.95, "Granito",
				"Elegante encimera para darle un toque moderno a tu cocina", "src/Imagenes/encimera.jpg", 80.8, 23.9,
				"Blanco");
		fregadero = new Fregadero(8, 9, 12.5, 280.87, "Metal", "Fregadero con dos cubetas y grifo incluido",
				"src/Imagenes/fregadero.jpg", 2, 28.6, true);
		lCocina.add(nevera);
		lCocina.add(horno);
		lCocina.add(encimera);
		lCocina.add(fregadero);

		// Trabajadores
		trabajador1 = new Trabajador("1A", "Masculino", "Juan", "Pérez", "juan.perez@example.com", "Calle Falsa 123",
				LocalDate.of(1985, 5, 20), "123", "600123456", 2500.0, 40);
		trabajador2 = new Trabajador("87654321B", "Femenino", "Ana", "García", "ana.garcia@example.com",
				"Avenida Real 456", LocalDate.of(1990, 11, 15), "123", "672200294", 2300.0, 35);
		trabajador3 = new Trabajador("45678912C", "Masculino", "Carlos", "López", "carlos.lopez@example.com",
				"Plaza Mayor 789", LocalDate.of(1978, 8, 10), "password789", "670456789", 2800.0, 45);
		trabajador4 = new Trabajador("23456789D", "Femenino", "Lucía", "Ramírez", "lucia.ramirez@example.com",
				"Calle Olmo 987", LocalDate.of(1993, 3, 12), "luciaPass987", "610321654", 2100.0, 30);
		trabajador5 = new Trabajador("34567890E", "Masculino", "Diego", "Morales", "diego.morales@example.com",
				"Avenida Verde 654", LocalDate.of(1982, 7, 25), "diegoPass654", "620654987", 2700.0, 50);
		lTrabajador.add(trabajador1);
		lTrabajador.add(trabajador2);
		lTrabajador.add(trabajador3);
		lTrabajador.add(trabajador4);
		lTrabajador.add(trabajador5);

		// Clientes
		cliente1 = new Cliente("98765432A", "Femenino", "María", "Fernández", "maria.fernandez@example.com",
				"Calle Sol 123", LocalDate.of(1992, 2, 5), "mariaPass123", "620123456", LocalDate.of(2024, 1, 15));
		cliente2 = new Cliente("23456789B", "Masculino", "Pedro", "Martínez", "pedro.martinez@example.com",
				"Avenida Luna 456", LocalDate.of(1987, 6, 30), "pedroPass456", "610987654", LocalDate.of(2023, 12, 1));
		cliente3 = new Cliente("34567890C", "Femenino", "Lucía", "Sánchez", "lucia.sanchez@example.com",
				"Plaza Nueva 789", LocalDate.of(2000, 4, 18), "luciaPass789", "630456789", LocalDate.of(2024, 2, 20));
		cliente4 = new Cliente("45678901D", "Masculino", "Luis", "Domínguez", "luis.dominguez@example.com",
				"Calle Azul 333", LocalDate.of(1995, 9, 10), "luisPass333", "640987321", LocalDate.of(2023, 11, 25));
		cliente5 = new Cliente("1A", "Femenino", "Sara", "Rodríguez", "sara.rodriguez@example.com",
				"Avenida Amarilla 555", LocalDate.of(1988, 12, 3), "123", "650321987", LocalDate.of(2024, 3, 1));
		lCliente.add(cliente1);
		lCliente.add(cliente2);
		lCliente.add(cliente3);
		lCliente.add(cliente4);
		lCliente.add(cliente5);
	}

	// Muebles
	public String obtenerDetallesMueble(Mueble m) {
		String detalles = "";

		if (m instanceof Mesa) {
			detalles = String.format(
					"Mesa\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nCapacidad: %d personas",
					m.getIdProducto(), m.getPrecio(), m.getPeso(), mesa.getMaterial(), mesa.getColor(),
					mesa.getDescripcion(), mesa.getAltura(), mesa.getCapacidad());
		} else if (m instanceof Silla) {
			detalles = String.format(
					"Silla\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nAncho: %.2fm\nCapacidad de carga: %.2fkg",
					m.getIdProducto(), m.getPrecio(), m.getPeso(), silla.getMaterial(), silla.getColor(),
					silla.getDescripcion(), silla.getAltura(), silla.getAncho(), silla.getCapacidadDeCarga());
		} else if (m instanceof Armario) {
			detalles = String.format(
					"Armario\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nNúmero de puertas: %d\nAltura: %.2fm\nAnchura: %.2fm\nProfundidad: %.2fm",
					m.getIdProducto(), m.getPrecio(), m.getPeso(), armario.getMaterial(), armario.getColor(),
					armario.getDescripcion(), armario.getNumeroDePuertas(), armario.getAltura(), armario.getAnchura(),
					armario.getProfundidad());
		} else if (m instanceof Sofa) {
			detalles = String.format(
					"Sofá\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nCapacidad de asientos: %d",
					m.getIdProducto(), m.getPrecio(), m.getPeso(), sofa.getMaterial(), sofa.getColor(),
					sofa.getDescripcion(), sofa.getCapacidadDeAsientos());
		}

		return detalles;
	}

	// Cocina
	public String obtenerDetallesCocina(Cocina c) {
		String detalles = "";

		if (c instanceof Nevera) {
			detalles = String.format(
					"Nevera\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nAltura: %.2f cm\nAnchura: %.2f cm\nProfundidad: %.2f cm\nCapacidad: %.2f litros\nTipo de Nevera: %s",
					nevera.getIdProducto(), nevera.getPrecio(), nevera.getPeso(), nevera.getMaterialC(),
					nevera.getDescripcionC(), nevera.getAltura(), nevera.getAnchura(), nevera.getProfundidad(),
					nevera.getCapacidad(), nevera.getTipoNevera());
		} else if (c instanceof Horno) {
			detalles = String.format(
					"Horno\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nAltura: %.2f cm\nAnchura: %.2f cm\nProfundidad: %.2f cm\nPotencia: %d KW\nNumero de bandejas: %d",
					horno.getIdProducto(), horno.getPrecio(), horno.getPeso(), horno.getMaterialC(),
					horno.getDescripcionC(), horno.getAltura(), horno.getAnchura(), horno.getProfundidad(),
					horno.getPotencia(), horno.getNumeroBandejas());
		} else if (c instanceof Encimera) {
			detalles = String.format(
					"Encimera\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nResistencia al calor: %.2f grados\nGrosor: %.2f cm\nColor: %s ",
					encimera.getIdProducto(), encimera.getPrecio(), encimera.getPeso(), encimera.getMaterialC(),
					encimera.getDescripcionC(), encimera.getResistenciaCalor(), encimera.getGrosor(),
					encimera.getColor());
		} else if (c instanceof Fregadero) {
			detalles = String.format(
					"Fregadero\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción: %s\nNumero de cubetas: %d \nProfundidad: %.2f cm\nGrifo: %b ",
					fregadero.getIdProducto(), fregadero.getPrecio(), fregadero.getPeso(), fregadero.getMaterialC(),
					fregadero.getDescripcionC(), fregadero.getNumCubetas(), fregadero.getProfundidad(),
					fregadero.isGrifo());
		}

		return detalles;
	}

	//jardineria
	
	public String obtenerDetallesJardineria(Jardineria j) {
		String detalles = "";

		
		if (j instanceof Maceta) {
			detalles = String.format(
					"Maceta\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nCapacidad: %d personas",
					j.getIdProducto(), j.getPrecio(), j.getPeso(), mesa.getMaterial(), mesa.getColor(),
					maceta.getDescripcion(), mesa.getAltura(), mesa.getCapacidad());
		} else if (j instanceof Barbacoa) {
			detalles = String.format(
					"Barbacoa\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nAltura: %.2fm\nAncho: %.2fm\nCapacidad de carga: %.2fkg",
					j.getIdProducto(), j.getPrecio(), j.getPeso(), silla.getMaterial(), silla.getColor(),
					silla.getDescripcion(), silla.getAltura(), silla.getAncho(), silla.getCapacidadDeCarga());
		} else if (j instanceof Planta) {
			detalles = String.format(
					"Planta\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nNúmero de puertas: %d\nAltura: %.2fm\nAnchura: %.2fm\nProfundidad: %.2fm",
					j.getIdProducto(), j.getPrecio(), j.getPeso(), armario.getMaterial(), armario.getColor(),
					armario.getDescripcion(), armario.getNumeroDePuertas(), armario.getAltura(), armario.getAnchura(),
					armario.getProfundidad());
		} else if (j instanceof Herramienta) {
			detalles = String.format(
					"Herramienta\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nColor: %s\nDescripción: %s\nCapacidad de asientos: %d",
					j.getIdProducto(), j.getPrecio(), j.getPeso(), sofa.getMaterial(), sofa.getColor(),
					sofa.getDescripcion(), sofa.getCapacidadDeAsientos());
		}

		return detalles;
	}
	// Baño
	public String obtenerDetallesBaño(Baño b) {
		String detalles;

		switch (b.getClass().getSimpleName()) {
		case "Ducha":
			Ducha ducha = (Ducha) b; // Hacer el cast a Ducha
			detalles = String.format(
					"Ducha\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción:  %s\nTipo de rociador: %s\nTiene mampara: %s",
					ducha.getIdProducto(), ducha.getPrecio(), ducha.getPeso(), ducha.getMaterialB(),
					ducha.getDescripcionB(), ducha.getTipoRociador(), ducha.isTieneMampara());
			break;

		case "Bide":
			Bide bide = (Bide) b; // Hacer el cast a Bide
			detalles = String.format(
					"Bide\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción:  %s\nTiene calefacción: %s\nEs eléctrico: %s",
					bide.getIdProducto(), bide.getPrecio(), bide.getPeso(), bide.getMaterialB(), bide.getDescripcionB(),
					bide.isTieneCalefaccion(), bide.isEsElectrico());
			break;

		case "Inodoro":
			Inodoro inodoro = (Inodoro) b; // Hacer el cast a Inodoro
			detalles = String.format(
					"Inodoro\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción:  %s\nTipo de descarga: %s\nTiene asiento calefaccionado: %s",
					inodoro.getIdProducto(), inodoro.getPrecio(), inodoro.getPeso(), inodoro.getMaterialB(),
					inodoro.getDescripcionB(), inodoro.getTipoDescarga(), inodoro.isTieneAsientoCalefaccionado());
			break;

		case "Lavamanos":
			Lavamanos lavamanos = (Lavamanos) b; // Hacer el cast a Lavamanos
			detalles = String.format(
					"Lavamanos\nId: %d\nPrecio: $%.2f\nPeso: %.2fkg\nMaterial: %s\nDescripción:  %s\nTipo de grifo: %s\nTiene almacenamiento: %s",
					lavamanos.getIdProducto(), lavamanos.getPrecio(), lavamanos.getPeso(), lavamanos.getMaterialB(),
					lavamanos.getDescripcionB(), lavamanos.getTipoGrifo(), lavamanos.isTieneAlmacenamiento());
			break;

		default:
			detalles = "El objeto proporcionado no es una ducha, bide, inodoro o lavamanos.";
			break;
		}

		return detalles;
	}

	// Iniciar sesion
	public Cliente buscarCliente(String user) {
		Cliente[] clientes = { cliente1, cliente2, cliente3, cliente4, cliente5 };

		for (Cliente c : clientes) {
			if (user.equals(c.getDni()) || user.equals(c.getEmail()) || user.equals(c.getTelefono())) {
				return c;
			}
		}
		return null;
	}
	//Iniciar sesion como trabajador
		public Trabajador buscarTrabajador(String user) {
			Trabajador[] trabajadores = { trabajador1, trabajador2, trabajador3, trabajador4, trabajador5};
			
			for(Trabajador t : trabajadores) {
				if(user.equals(t.dni)) {
					return t;
				}
				
			}
			return null;
			
		}
	
}