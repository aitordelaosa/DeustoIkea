package domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BD {
	private static Connection con;

	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void initBD(String nombreBD) {
	    con = null;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
	        System.out.println("Conexión establecida con la base de datos: " + nombreBD);
	    } catch (ClassNotFoundException e) {
	        System.err.println("El controlador SQLite no se encontró.");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.err.println("Error al conectar con la base de datos.");
	        e.printStackTrace();
	    }
	}

	public static void closeBD() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void crearTablas() {
		String sql = "CREATE TABLE IF NOT EXISTS Cliente(Dni String, Genero String, Nombre String, Apellido String, Email String, Direccion String, Fecha long, Contraseña String, Telefono String, id int, UltimoLogin long, Descuento String)";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Trabajador(Dni String, Genero String, Nombre String, Apellido String, Email String, Direccion String, fNacimiento long, Contraseña String, Telefono String, id int, Salario double, HorasTrabajadas int)";
			stmt.executeUpdate(sql);
			
			//Tabla para la Clase Producto
			sql = "CREATE TABLE IF NOT EXISTS Producto(idProducto INT PRIMARY KEY, NumeroProductos int, Peso double, Precio double)";
			stmt.executeUpdate(sql);
			
			
			//Tablas para las clases que heredan de Producto
			sql = "CREATE TABLE IF NOT EXISTS Mueble(idProducto INT PRIMARY KEY, Material String, Color String, Descripcion String, RutaImagen String, FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
			stmt.executeUpdate(sql);	
			sql = "CREATE TABLE IF NOT EXISTS Cocina(idProducto INT PRIMARY KEY, Material String, Descripcion String, RutaImagen String, FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
			stmt.executeUpdate(sql);	
			sql = "CREATE TABLE IF NOT EXISTS Baño(idProducto INT PRIMARY KEY, Material String, Descripcion String, RutaImagen String, FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Jardineria(idProducto INT PRIMARY KEY, esExterior String, Material String, Descripcion String, RutaImagem String, FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
			stmt.executeUpdate(sql);
			
			
			//tablas para clases que heredan de Mueble
			
			sql = "CREATE TABLE IF NOT EXISTS Armario(idProducto INT PRIMARY KEY, NumeroDePuertas int, Altura double, Anchura double, Profundidad double, FOREIGN KEY (idProducto) REFERENCES Mueble(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Mesa(idProducto INT PRIMARY KEY, Altura double, Capacidad int, FOREIGN KEY (idProducto) REFERENCES Mueble(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Silla(idProducto INT PRIMARY KEY, Altura double, Anchura double, CapacidadDeCarga double, FOREIGN KEY (idProducto) REFERENCES Mueble(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Sofa(idProducto INT PRIMARY KEY, CapacidadDeAsientos int, FOREIGN KEY (idProducto) REFERENCES Mueble(idProducto))";
			stmt.executeUpdate(sql);
			
			//Tablas para las clases que heredan de Jardineria
			
			sql = "CREATE TABLE IF NOT EXISTS Barbacoa(idProducto INT PRIMARY KEY, tipoCombustible String, superficieCoccion double, tieneTapa String, FOREIGN KEY (idProducto) REFERENCES Jardineria(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Planta(idProducto INT PRIMARY KEY,Altura double, TipoDePlanta String, Diametro double, FOREIGN KEY (idProducto) REFERENCES Jardineria(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Maceta(idProducto INT PRIMARY KEY, Diametro double, FOREIGN KEY (idProducto) REFERENCES Jardineria(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Herramienta(idProducto INT PRIMARY KEY, Tipo String, FOREIGN KEY (idProducto) REFERENCES Jardineria(idProducto))";
			stmt.executeUpdate(sql);
			
			//Tablas para las clases que heredan de Baño
			
			sql = "CREATE TABLE IF NOT EXISTS Inodoro(idProducto INT PRIMARY KEY, tipoDescarga String, tieneAsientoCalefaccionado String, FOREIGN KEY (idProducto) REFERENCES Baño(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Lavamanos(idProducto INT PRIMARY KEY, tipoGrifo String,  tieneAlmacenamiento String, FOREIGN KEY (idProducto) REFERENCES Baño(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Ducha(idProducto INT PRIMARY KEY, tieneMampara String, FOREIGN KEY (idProducto) REFERENCES Baño(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Bide(idProducto INT PRIMARY KEY, tieneCalefaccion String, esElectrico String, FOREIGN KEY (idProducto) REFERENCES Baño(idProducto))";
			stmt.executeUpdate(sql);
			
			//Tablas para las clases que heredan de Cocina
			
			sql = "CREATE TABLE IF NOT EXISTS Encimera(idProducto INT PRIMARY KEY, resistenciaCalor String, Grosor String, Color String, FOREIGN KEY (idProducto) REFERENCES Cocina(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Fregadero(idProducto INT PRIMARY KEY, numCubetas int, Profundidad double, Grifo String, FOREIGN KEY (idProducto) REFERENCES Cocina(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Horno(idProducto INT PRIMARY KEY, Altura double, Anchura double, Profundida double, Potencia int, NumeroBandejas int, FOREIGN KEY (idProducto) REFERENCES Cocina(idProducto))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Nevera(idProducto INT PRIMARY KEY, Altura double, Anchura double, Profundida double, Capacidad double, tipoNevera String, FOREIGN KEY (idProducto) REFERENCES Cocina(idProducto))";
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTabla(Connection con) throws SQLException{
		String sqlCliente = "DROP TABLE IF EXISTS Cliente";
		String sqlTrabajador= "DROP TABLE IF EXISTS Trabajador";
		
		String sqlProducto= "DROP TABLE IF EXISTS Producto";
		
		String sqlMueble= "DROP TABLE IF EXISTS Mueble";
		String sqlCocina= "DROP TABLE IF EXISTS Cocina";
		String sqlBaño= "DROP TABLE IF EXISTS Baño";
		String sqlJardineria= "DROP TABLE IF EXISTS Jardineria";
		
		String sqlArmario= "DROP TABLE IF EXISTS Armario";
		String sqlSilla= "DROP TABLE IF EXISTS Silla";
		String sqlSofa= "DROP TABLE IF EXISTS Sofa";
		String sqlMesa= "DROP TABLE IF EXISTS Mesa";
		
		String sqlBarbacoa= "DROP TABLE IF EXISTS Barbacoa";
		String sqlPlanta= "DROP TABLE IF EXISTS Planta";
		String sqlMaceta= "DROP TABLE IF EXISTS Maceta";
		String sqlHerramienta= "DROP TABLE IF EXISTS Herramienta";
		
		String sqlInodoro= "DROP TABLE IF EXISTS Inodoro";
		String sqlLavamanos= "DROP TABLE IF EXISTS Lavamanos";
		String sqlDucha= "DROP TABLE IF EXISTS Ducha";
		String sqlBide= "DROP TABLE IF EXISTS Bide";
		
		String sqlEncimera= "DROP TABLE IF EXISTS Encimera";
		String sqlFregadero= "DROP TABLE IF EXISTS Fregadero";
		String sqlHorno= "DROP TABLE IF EXISTS Horno";
		String sqlNevera= "DROP TABLE IF EXISTS Nevera";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sqlCliente);
			st.executeUpdate(sqlTrabajador);
			
			st.executeUpdate(sqlProducto);
			
			st.executeUpdate(sqlMueble);
			st.executeUpdate(sqlCocina);
			st.executeUpdate(sqlBaño);
			st.executeUpdate(sqlJardineria);
			
			st.executeUpdate(sqlArmario);
			st.executeUpdate(sqlSilla);
			st.executeUpdate(sqlSofa);
			st.executeUpdate(sqlMesa);
			
			st.executeUpdate(sqlBarbacoa);
			st.executeUpdate(sqlPlanta);
			st.executeUpdate(sqlMaceta);
			st.executeUpdate(sqlHerramienta);
			
			st.executeUpdate(sqlInodoro);
			st.executeUpdate(sqlLavamanos);
			st.executeUpdate(sqlDucha);
			st.executeUpdate(sqlBide);
			
			st.executeUpdate(sqlEncimera);
			st.executeUpdate(sqlFregadero);
			st.executeUpdate(sqlHorno);
			st.executeUpdate(sqlNevera);
			
			
		} catch (SQLException e) {
			throw e;
		}
	}
	
	//Nose si será necesario este método
	public static List<Producto> obtenerListaProductos(Connection con) {
	    String sql = "SELECT * FROM Producto";
	    List<Producto> listaProductos = new ArrayList<>();

	    try (Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");

	            Producto producto = new Producto(idProducto, numeroProductos, peso, precio);

	            listaProductos.add(producto);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaProductos;
	}
	
	//                                                                       Tipos de Muebles
	
	public static List<Armario> obtenerListaArmarios(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               m.Material, m.Color, m.Descripcion, m.RutaImagen,
	               a.NumeroDePuertas, a.Altura, a.Anchura, a.Profundidad
	        FROM Producto p
	        INNER JOIN Mueble m ON p.idProducto = m.idProducto
	        INNER JOIN Armario a ON m.idProducto = a.idProducto
	    """;
	    List<Armario> listaArmarios = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String color = rs.getString("Color");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            int numeroDePuertas = rs.getInt("NumeroDePuertas");
	            double altura = rs.getDouble("Altura");
	            double anchura = rs.getDouble("Anchura");
	            double profundidad = rs.getDouble("Profundidad");

	            Armario armario = new Armario(
	                idProducto, numeroProductos, peso, precio,
	                material, color, descripcion, rutaImagen,
	                numeroDePuertas, altura, anchura, profundidad
	            );

	            listaArmarios.add(armario);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaArmarios;
	}
	
	
	public static List<Mesa> obtenerListaMesas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               m.Material, m.Color, m.Descripcion, m.RutaImagen,
	               me.Altura, me.Capacidad
	        FROM Producto p
	        INNER JOIN Mueble m ON p.idProducto = m.idProducto
	        INNER JOIN Mesa me ON m.idProducto = me.idProducto
	    """;
	    List<Mesa> listaMesas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String color = rs.getString("Color");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double altura = rs.getDouble("Altura");
	            int capacidad = rs.getInt("Capacidad");

	            Mesa mesa = new Mesa(
	                idProducto, numeroProductos, peso, precio,
	                material, color, descripcion, rutaImagen,
	                altura, capacidad
	            );

	            listaMesas.add(mesa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaMesas;
	}
	
	
	public static List<Silla> obtenerListaSillas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               m.Material, m.Color, m.Descripcion, m.RutaImagen,
	               s.Altura, s.Anchura, s.CapacidadDeCarga
	        FROM Producto p
	        INNER JOIN Mueble m ON p.idProducto = m.idProducto
	        INNER JOIN Silla s ON m.idProducto = s.idProducto
	    """;
	    List<Silla> listaSillas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String color = rs.getString("Color");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double altura = rs.getDouble("Altura");
	            double anchura = rs.getDouble("Anchura");
	            double capacidadDeCarga = rs.getDouble("CapacidadDeCarga");

	            Silla silla = new Silla(
	                idProducto, numeroProductos, peso, precio,
	                material, color, descripcion, rutaImagen,
	                altura, anchura, capacidadDeCarga
	            );

	            listaSillas.add(silla);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaSillas;
	}

	public static List<Sofa> obtenerListaSofas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               m.Material, m.Color, m.Descripcion, m.RutaImagen,
	               s.CapacidadDeAsientos
	        FROM Producto p
	        INNER JOIN Mueble m ON p.idProducto = m.idProducto
	        INNER JOIN Sofa s ON m.idProducto = s.idProducto
	    """;
	    List<Sofa> listaSofas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String color = rs.getString("Color");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            int capacidadDeAsientos = rs.getInt("CapacidadDeAsientos");

	            Sofa sofa = new Sofa(
	                idProducto, numeroProductos, peso, precio,
	                material, color, descripcion, rutaImagen,
	                capacidadDeAsientos
	            );

	            listaSofas.add(sofa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaSofas;
	}

	//                                                                         Tipos de Cocina
	public static List<Encimera> obtenerListaEncimeras(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               e.resistenciaCalor, e.Grosor, e.Color
	        FROM Producto p
	        INNER JOIN Cocina c ON p.idProducto = c.idProducto
	        INNER JOIN Encimera e ON c.idProducto = e.idProducto
	    """;
	    List<Encimera> listaEncimeras = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double resistenciaCalor = rs.getDouble("resistenciaCalor");
	            double grosor = rs.getDouble("Grosor");
	            String color = rs.getString("Color");

	            Encimera encimera = new Encimera(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                resistenciaCalor, grosor, color
	            );

	            listaEncimeras.add(encimera);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaEncimeras;
	}
	
	public static List<Fregadero> obtenerListaFregaderos(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               f.numCubetas, f.Profundidad, f.Grifo
	        FROM Producto p
	        INNER JOIN Cocina c ON p.idProducto = c.idProducto
	        INNER JOIN Fregadero f ON c.idProducto = f.idProducto
	    """;
	    List<Fregadero> listaFregaderos = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            int numCubetas = rs.getInt("numCubetas");
	            double profundidad = rs.getDouble("Profundidad");
	            boolean grifo = rs.getBoolean("Grifo");  

	            Fregadero fregadero = new Fregadero(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                numCubetas, profundidad, grifo
	            );

	            listaFregaderos.add(fregadero);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaFregaderos;
	}

	public static List<Horno> obtenerListaHornos(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               h.Altura, h.Anchura, h.Profundidad, h.Potencia, h.NumeroBandejas
	        FROM Producto p
	        INNER JOIN Cocina c ON p.idProducto = c.idProducto
	        INNER JOIN Horno h ON c.idProducto = h.idProducto
	    """;
	    List<Horno> listaHornos = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double altura = rs.getDouble("Altura");
	            double anchura = rs.getDouble("Anchura");
	            double profundidad = rs.getDouble("Profundidad");
	            int potencia = rs.getInt("Potencia");
	            int numeroBandejas = rs.getInt("NumeroBandejas");

	            Horno horno = new Horno(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                altura, anchura, profundidad, potencia, numeroBandejas
	            );

	            listaHornos.add(horno);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaHornos;
	}
	
	public static List<Nevera> obtenerListaNeveras(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               n.Altura, n.Anchura, n.Profundidad, n.Capacidad, n.tipoNevera
	        FROM Producto p
	        INNER JOIN Cocina c ON p.idProducto = c.idProducto
	        INNER JOIN Nevera n ON c.idProducto = n.idProducto
	    """;
	    List<Nevera> listaNeveras = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double altura = rs.getDouble("Altura");
	            double anchura = rs.getDouble("Anchura");
	            double profundidad = rs.getDouble("Profundidad");
	            double capacidad = rs.getDouble("Capacidad");
	            String tipoNevera = rs.getString("tipoNevera");

	            Nevera nevera = new Nevera(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                altura, anchura, profundidad, capacidad, tipoNevera
	            );

	            listaNeveras.add(nevera);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaNeveras;
	    
	}
 //                                                                                 Tipos de Baño
	
	public static List<Inodoro> obtenerListaInodoros(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               b.Material, b.Descripcion, b.RutaImagen,
	               i.tipoDescarga, i.tieneAsientoCalefaccionado
	        FROM Producto p
	        INNER JOIN Baño b ON p.idProducto = b.idProducto
	        INNER JOIN Inodoro i ON b.idProducto = i.idProducto
	    """;
	    List<Inodoro> listaInodoros = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            String tipoDescarga = rs.getString("tipoDescarga");
	            boolean tieneAsientoCalefaccionado = rs.getBoolean("tieneAsientoCalefaccionado");

	            
	            Inodoro inodoro = new Inodoro(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                tipoDescarga, tieneAsientoCalefaccionado
	            );

	            listaInodoros.add(inodoro);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaInodoros;
	}
	
	public static List<Lavamanos> obtenerListaLavamanos(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               b.Material, b.Descripcion, b.RutaImagen,
	               l.tipoGrifo, l.tieneAlmacenamiento
	        FROM Producto p
	        INNER JOIN Baño b ON p.idProducto = b.idProducto
	        INNER JOIN Lavamanos l ON b.idProducto = l.idProducto
	    """;
	    List<Lavamanos> listaLavamanos = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            String tipoGrifo = rs.getString("tipoGrifo");
	            boolean tieneAlmacenamiento = rs.getBoolean("tieneAlmacenamiento");

	            Lavamanos lavamanos = new Lavamanos(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                tipoGrifo, tieneAlmacenamiento
	            );

	            listaLavamanos.add(lavamanos);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaLavamanos;
	}

	public static List<Ducha> obtenerListaDuchas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               b.Material, b.Descripcion, b.RutaImagen,
	               d.tipoRociador, d.tieneMampara
	        FROM Producto p
	        INNER JOIN Baño b ON p.idProducto = b.idProducto
	        INNER JOIN Ducha d ON b.idProducto = d.idProducto
	    """;
	    List<Ducha> listaDuchas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            String tipoRociador = rs.getString("tipoRociador");
	            boolean tieneMampara = rs.getBoolean("tieneMampara");

	            Ducha ducha = new Ducha(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                tipoRociador, tieneMampara
	            );

	            listaDuchas.add(ducha);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaDuchas;
	}
	
	public static List<Bide> obtenerListaBides(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               b.Material, b.Descripcion, b.RutaImagen,
	               bi.tieneCalefaccion, bi.esElectrico
	        FROM Producto p
	        INNER JOIN Baño b ON p.idProducto = b.idProducto
	        INNER JOIN Bide bi ON b.idProducto = bi.idProducto
	    """;
	    List<Bide> listaBides = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            boolean tieneCalefaccion = rs.getBoolean("tieneCalefaccion");
	            boolean esElectrico = rs.getBoolean("esElectrico");
	            
	            Bide bide = new Bide(
	                idProducto, numeroProductos, peso, precio,
	                material, descripcion, rutaImagen,
	                tieneCalefaccion, esElectrico
	            );

	            listaBides.add(bide);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaBides;
	}
//                                                                             Tipos de Jardineria
	
	public static List<Barbacoa> obtenerListaBarbacoas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagen,
	               b.tipoCombustible, b.superficieCoccion, b.tieneTapa
	        FROM Producto p
	        INNER JOIN Jardineria j ON p.idProducto = j.idProducto
	        INNER JOIN Barbacoa b ON j.idProducto = b.idProducto
	    """;
	    List<Barbacoa> listaBarbacoas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            boolean esExterior = rs.getBoolean("esExterior");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            String tipoCombustible = rs.getString("tipoCombustible");
	            double superficieCoccion = rs.getDouble("superficieCoccion");
	            boolean tieneTapa = rs.getBoolean("tieneTapa");

	            Barbacoa barbacoa = new Barbacoa(
	                idProducto, numeroProductos, peso, precio, 
	                esExterior, material, descripcion, rutaImagen,
	                tipoCombustible, superficieCoccion, tieneTapa
	            );

	            listaBarbacoas.add(barbacoa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaBarbacoas;
	}
	
	public static List<Planta> obtenerListaPlantas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagen,
	               pl.Altura, pl.Diametro, pl.TipoDePlanta
	        FROM Producto p
	        INNER JOIN Jardineria j ON p.idProducto = j.idProducto
	        INNER JOIN Planta pl ON j.idProducto = pl.idProducto
	    """;
	    List<Planta> listaPlantas = new ArrayList<>();

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            boolean esExterior = rs.getBoolean("esExterior");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double altura = rs.getDouble("Altura");
	            double diametro = rs.getDouble("Diametro");
	            String tipoDePlanta = rs.getString("TipoDePlanta");

	            Planta planta = new Planta(
	                idProducto, numeroProductos, peso, precio, 
	                esExterior, material, descripcion, rutaImagen, 
	                altura, tipoDePlanta, diametro
	            );

	            listaPlantas.add(planta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaPlantas;
	}
	
	public static List<Maceta> obtenerListaMacetas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagen, 
	               m.Diametro
	        FROM Producto p
	        INNER JOIN Jardineria j ON p.idProducto = j.idProducto
	        INNER JOIN Maceta m ON j.idProducto = m.idProducto
	    """;

	    List<Maceta> listaMacetas = new ArrayList<>();

	    try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            boolean esExterior = rs.getBoolean("esExterior");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            double diametro = rs.getDouble("Diametro");

	            Maceta maceta = new Maceta(idProducto, numeroProductos, peso, precio, 
	                                       esExterior, material, descripcion, 
	                                       rutaImagen, diametro);
	            listaMacetas.add(maceta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaMacetas;
	}
	
	public static List<Herramienta> obtenerListaHerramientas(Connection con) {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagen, 
	               h.Tipo
	        FROM Producto p
	        INNER JOIN Jardineria j ON p.idProducto = j.idProducto
	        INNER JOIN Herramienta h ON j.idProducto = h.idProducto
	    """;

	    List<Herramienta> listaHerramientas = new ArrayList<>();

	    try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
	        while (rs.next()) {
	            int idProducto = rs.getInt("idProducto");
	            int numeroProductos = rs.getInt("NumeroProductos");
	            double peso = rs.getDouble("Peso");
	            double precio = rs.getDouble("Precio");
	            boolean esExterior = rs.getBoolean("esExterior");
	            String material = rs.getString("Material");
	            String descripcion = rs.getString("Descripcion");
	            String rutaImagen = rs.getString("RutaImagen");
	            String tipo = rs.getString("Tipo");

	            Herramienta herramienta = new Herramienta(idProducto, numeroProductos, peso, precio, 
	                                                      esExterior, material, descripcion, 
	                                                      rutaImagen, tipo);
	            listaHerramientas.add(herramienta);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaHerramientas;
	}


	public static void insertarCliente(String dni, String genero, String nombre, String apellido, String email, 
            String direccion, LocalDate fechaNacimiento, String contraseña, 
            String telefono, int id, LocalDate ultimoLogin, Descuento descuento) {
			String sql = "INSERT INTO Cliente (Dni, Genero, Nombre, Apellido, Email, Direccion, Fecha, Contraseña, " +
						"Telefono, id, UltimoLogin, Descuento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, dni);
					pstmt.setString(2, genero);
					pstmt.setString(3, nombre);
					pstmt.setString(4, apellido);
					pstmt.setString(5, email);
					pstmt.setString(6, direccion);
					pstmt.setLong(7, convertirFecha(fechaNacimiento));
					pstmt.setString(8, contraseña);
					pstmt.setString(9, telefono);
					pstmt.setInt(10, id);
					pstmt.setLong(11, convertirFecha(ultimoLogin));
					pstmt.setString(12, descuento.name());
					
					pstmt.executeUpdate();
					System.out.println("Cliente insertado correctamente en la base de datos.");
				} catch (SQLException e) {
					System.err.println("Error al insertar el cliente: " + e.getMessage());
				}
	}
	
	public static int obtenerUltimoIdCliente() throws SQLException {
	    String sql = "SELECT MAX(id) AS ultimoId FROM Cliente";
	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        if (rs.next()) {
	            return rs.getInt("ultimoId");
	        } else {
	            return 0; 
	        }
	    }
	}
	

	private static long convertirFecha(LocalDate fecha) {
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
	}
	
	public static Cliente buscarCliente(String user, String password) {
	    String sql = "SELECT * FROM Cliente WHERE (Dni = ? OR Email = ? OR Telefono = ?) AND Contraseña = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, user);
	        stmt.setString(2, user);
	        stmt.setString(3, user);
	        stmt.setString(4, password);

	        var rs = stmt.executeQuery();

	        if (rs.next()) {
	            String dni = rs.getString("Dni");
	            String genero = rs.getString("Genero");
	            String nombre = rs.getString("Nombre");
	            String apellido = rs.getString("Apellido");
	            String email = rs.getString("Email");
	            String direccion = rs.getString("Direccion");
	            LocalDate fechaNacimiento = Instant.ofEpochMilli(rs.getLong("Fecha")).atZone(ZoneId.systemDefault()).toLocalDate();
	            String telefono = rs.getString("Telefono");
	            int id = rs.getInt("id");
	            LocalDate ultimoLogin = Instant.ofEpochMilli(rs.getLong("UltimoLogin")).atZone(ZoneId.systemDefault()).toLocalDate();
	            Descuento descuento = Descuento.valueOf(rs.getString("Descuento"));

	            return new Cliente(dni, genero, nombre, apellido, email, direccion,
	                    fechaNacimiento, password, telefono, id, ultimoLogin, descuento);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static void actualizarUltimoLogin(String dni) {
	    String sql = "UPDATE Cliente SET UltimoLogin = ? WHERE Dni = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setLong(1, LocalDate.now().toEpochDay());
	        stmt.setString(2, dni);
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static boolean actualizarCliente(Cliente cliente) {
	    String sql = "UPDATE Cliente SET Nombre = ?, Telefono = ?, Direccion = ? WHERE Dni = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, cliente.getNombre());
	        stmt.setString(2, cliente.getTelefono());
	        stmt.setString(3, cliente.getDireccion());
	        stmt.setString(4, cliente.getDni());
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static List<Cliente> ObtenerListaCliente() {
	    String sql = "SELECT * FROM Cliente";
	    List<Cliente> lC = new ArrayList<>();
	    try {
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String dni = rs.getString("Dni");
	            String genero = rs.getString("Genero");
	            String nombre = rs.getString("Nombre");
	            String apellido = rs.getString("Apellido");
	            String email = rs.getString("Email");
	            String direccion = rs.getString("Direccion");
	            LocalDate fNacimiento = Instant.ofEpochMilli(rs.getLong("Fecha")).atZone(ZoneId.systemDefault()).toLocalDate();
	            String contraseña = rs.getString("Contraseña");
	            String telefono = rs.getString("Telefono");
	            int id = rs.getInt("id");
	            LocalDate ultimoLogin = Instant.ofEpochMilli(rs.getLong("UltimoLogin")).atZone(ZoneId.systemDefault()).toLocalDate();
	            Descuento descuento = Descuento.valueOf(rs.getString("Descuento"));

	            Cliente cliente = new Cliente(dni, genero, nombre, apellido, email, direccion, fNacimiento, 
	                                           contraseña, telefono, id, ultimoLogin, descuento);
	            lC.add(cliente);
	        }
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lC;
	}
	public static List<Trabajador> ObtenerListaTrabajador() {
	    String sql = "SELECT * FROM Trabajador";
	    List<Trabajador> lT = new ArrayList<>();
	    try {
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        while (rs.next()) {
	            String dni = rs.getString("Dni");
	            String genero = rs.getString("Genero");
	            String nombre = rs.getString("Nombre");
	            String apellido = rs.getString("Apellido");
	            String email = rs.getString("Email");
	            String direccion = rs.getString("Direccion");
	            LocalDate fNacimiento = Instant.ofEpochMilli(rs.getLong("fNacimiento")).atZone(ZoneId.systemDefault()).toLocalDate();
	            String contraseña = rs.getString("Contraseña");
	            String telefono = rs.getString("Telefono");
	            int id = rs.getInt("id");
	            double salario = rs.getDouble("Salario");
	            int horasTrabajadas = rs.getInt("HorasTrabajadas");
	            Trabajador trabajador= new Trabajador(dni, genero, nombre, apellido, email, direccion, fNacimiento, contraseña, telefono, id, salario, horasTrabajadas);
	            lT.add(trabajador);
	        }
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lT;
	}  
}


