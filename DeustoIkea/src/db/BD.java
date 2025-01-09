package db;

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

import domain.Armario;
import domain.Barbacoa;
import domain.Bide;
import domain.Carrito;
import domain.Cliente;
import domain.Descuento;
import domain.Ducha;
import domain.Encimera;
import domain.Fregadero;
import domain.Herramienta;
import domain.Horno;
import domain.Inodoro;
import domain.Lavamanos;
import domain.Maceta;
import domain.Mesa;
import domain.Nevera;
import domain.Planta;
import domain.Producto;
import domain.Silla;
import domain.Sofa;
import domain.Trabajador;

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
			sql = "CREATE TABLE IF NOT EXISTS Mueble(idProducto INT PRIMARY KEY, Material String, Color String, Descripcion String, RutaImagen String, FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)) ";
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
			sql = "CREATE TABLE IF NOT EXISTS Ducha(idProducto INT PRIMARY KEY, tipoRociador String, tieneMampara String, FOREIGN KEY (idProducto) REFERENCES Baño(idProducto))";
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
			
			//Tablas para el carrito
			sql = "CREATE TABLE IF NOT EXISTS Carrito(idProducto INT PRIMARY KEY, nombreP STRING, cantidad INT, precio FLOAT, dniC STRING"
					+ ")";
			stmt.executeUpdate(sql);
				
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertarCarrito(Carrito c) {
	    String checkSql = "SELECT cantidad FROM Carrito WHERE idProducto = ? AND dniC = ?";
	    String updateSql = "UPDATE Carrito SET cantidad = cantidad + ? WHERE idProducto = ? AND dniC = ?";
	    String insertSql = "INSERT INTO Carrito VALUES(?,?,?,?,?)";

	    try {
	        // Paso 1: Verificar si el producto ya está en el carrito
	        PreparedStatement checkPs = con.prepareStatement(checkSql);
	        checkPs.setInt(1, c.getIdP());
	        checkPs.setString(2, c.getDni());
	        ResultSet rs = checkPs.executeQuery();

	        if (rs.next()) {
	            // Paso 2: El producto ya está en el carrito, actualizar la cantidad
	            PreparedStatement updatePs = con.prepareStatement(updateSql);
	            updatePs.setInt(1, c.getCant());
	            updatePs.setInt(2, c.getIdP());
	            updatePs.setString(3, c.getDni());
	            updatePs.executeUpdate();
	            updatePs.close();
	        } else {
	            // Paso 3: El producto no está en el carrito, insertar un nuevo registro
	            PreparedStatement insertPs = con.prepareStatement(insertSql);
	            insertPs.setInt(1, c.getIdP());
	            insertPs.setString(2, c.getNomP());
	            insertPs.setInt(3, c.getCant());
	            insertPs.setFloat(4, c.getPrecio());
	            insertPs.setString(5, c.getDni());
	            insertPs.execute();
	            insertPs.close();
	        }

	        checkPs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ArrayList<Carrito> recuperarCarrito(String dni) {
		ArrayList<Carrito> al = new ArrayList<Carrito>();
		String sql = "SELECT * FROM Carrito WHERE dniC = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int codP = rs.getInt(1);
				String nomP = rs.getString(2);
				int cant = rs.getInt(3);
				float prec = rs.getFloat(4);
				Carrito c = new Carrito(codP, nomP, cant, prec, dni);
				System.out.println(c);
				al.add(c);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public static void borrarCarrito(String dni) {
		String sql = "DELETE FROM Carrito WHERE dniC = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dni);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarProducto(String dni, int idP) {
	    String sql = "DELETE FROM Carrito WHERE dniC = ? AND idProducto = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, dni);
	        ps.setInt(2, idP);
	        ps.executeUpdate(); 
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void borrarTabla() throws SQLException{
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
	public static List<Producto> obtenerListaProductos() {
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
	
	public static List<Producto> obtenerListaMuebles(){
		List<Producto> lista = new ArrayList<Producto>(obtenerListaArmarios());
		lista.addAll(obtenerListaMesas());
		lista.addAll(obtenerListaSillas());
		lista.addAll(obtenerListaSofas());
		return lista;
	}
	public static List<Armario> obtenerListaArmarios() {
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
	
	
	public static List<Mesa> obtenerListaMesas() {
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
	
	
	public static List<Silla> obtenerListaSillas() {
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

	public static List<Sofa> obtenerListaSofas() {
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
	public static List<Producto> obtenerListaCocinas(){
		List<Producto> lista = new ArrayList<Producto>(obtenerListaHornos());
		lista.addAll(obtenerListaEncimeras());
		lista.addAll(obtenerListaFregaderos());
		lista.addAll(obtenerListaNeveras());
		return lista;
	}
	public static List<Encimera> obtenerListaEncimeras() {
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
	
	public static List<Fregadero> obtenerListaFregaderos() {
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

	public static List<Horno> obtenerListaHornos() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               h.Altura, h.Anchura, h.Profundida, h.Potencia, h.NumeroBandejas
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
	            double profundidad = rs.getDouble("Profundida");
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
	
	public static List<Nevera> obtenerListaNeveras() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               c.Material, c.Descripcion, c.RutaImagen,
	               n.Altura, n.Anchura, n.Profundida, n.Capacidad, n.tipoNevera
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
	            double profundidad = rs.getDouble("Profundida");
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
	public static List<Producto> obtenerListaBaños(){
		List<Producto> lista = new ArrayList<Producto>(obtenerListaInodoros());
		lista.addAll(obtenerListaLavamanos());
		lista.addAll(obtenerListaDuchas());
		lista.addAll(obtenerListaBides());
		return lista;
	}
	public static List<Inodoro> obtenerListaInodoros() {
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
	
	public static List<Lavamanos> obtenerListaLavamanos() {
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

	public static List<Ducha> obtenerListaDuchas() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio,
	               b.Material, b.Descripcion, b.RutaImagen, d.tipoRociador,
	               d.tieneMampara
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
	
	public static List<Bide> obtenerListaBides() {
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
	public static List<Producto> obtenerListaMJardinerias(){
		List<Producto> lista = new ArrayList<Producto>(obtenerListaBarbacoas());
		lista.addAll(obtenerListaPlantas());
		lista.addAll(obtenerListaMacetas());
		lista.addAll(obtenerListaHerramientas());
		return lista;
	}
	
	public static List<Barbacoa> obtenerListaBarbacoas() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagem,
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
	            String rutaImagen = rs.getString("RutaImagem");
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
	
	public static List<Planta> obtenerListaPlantas() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagem,
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
	            String rutaImagen = rs.getString("RutaImagem");
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
	
	public static List<Maceta> obtenerListaMacetas() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagem, 
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
	            String rutaImagen = rs.getString("RutaImagem");
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
	
	public static List<Herramienta> obtenerListaHerramientas() {
	    String sql = """
	        SELECT p.idProducto, p.NumeroProductos, p.Peso, p.Precio, 
	               j.esExterior, j.Material, j.Descripcion, j.RutaImagem, 
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
	            String rutaImagen = rs.getString("RutaImagem");
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
	public static int obtenerSiguienteIdProducto() {
	    String sql = "SELECT MAX(idProducto) AS ultimoId FROM Producto";
	    int siguienteId = 1;

	    try (Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        if (rs.next()) {
	            int ultimoId = rs.getInt("ultimoId");
	            siguienteId = ultimoId + 1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return siguienteId;
	}
	
	public static void insertarArmario(int idProducto, int numProductos, double peso, double precio,
            String material, String color, String descripcion, String rutaImagen,
            int numeroDePuertas, double altura, double anchura, double profundidad) {
			String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
			String sqlMueble = "INSERT INTO Mueble (idProducto, Material, Color, Descripcion, RutaImagen) VALUES (?, ?, ?, ?, ?)";
			String sqlArmario = "INSERT INTO Armario (idProducto, NumeroDePuertas, Altura, Anchura, Profundidad) VALUES (?, ?, ?, ?, ?)";

			try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
					PreparedStatement stmtMueble = con.prepareStatement(sqlMueble);
					PreparedStatement stmtArmario = con.prepareStatement(sqlArmario)) {

			// Insertar en Producto
			stmtProducto.setInt(1, idProducto);
			stmtProducto.setInt(2, numProductos);
			stmtProducto.setDouble(3, peso);
			stmtProducto.setDouble(4, precio);
			stmtProducto.executeUpdate();
			
			// Insertar en Mueble
			stmtMueble.setInt(1, idProducto);
			stmtMueble.setString(2, material);
			stmtMueble.setString(3, color);
			stmtMueble.setString(4, descripcion);
			stmtMueble.setString(5, rutaImagen);
			stmtMueble.executeUpdate();
			
			// Insertar en Armario
			stmtArmario.setInt(1, idProducto);
			stmtArmario.setInt(2, numeroDePuertas);
			stmtArmario.setDouble(3, altura);
			stmtArmario.setDouble(4, anchura);
			stmtArmario.setDouble(5, profundidad);
			stmtArmario.executeUpdate();
			
			} catch (SQLException e) {
			e.printStackTrace();
			}
}
	
	public static void insertarMesa(int idProducto, int numProductos, double peso, double precio,
	        String material, String color, String descripcion, String rutaImagen,
	        double altura, int capacidad) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlMueble = "INSERT INTO Mueble (idProducto, Material, Color, Descripcion, RutaImagen) VALUES (?, ?, ?, ?, ?)";
	    String sqlMesa = "INSERT INTO Mesa (idProducto, Altura, Capacidad) VALUES (?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtMueble = con.prepareStatement(sqlMueble);
	         PreparedStatement stmtMesa = con.prepareStatement(sqlMesa)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Mueble
	        stmtMueble.setInt(1, idProducto);
	        stmtMueble.setString(2, material);
	        stmtMueble.setString(3, color);
	        stmtMueble.setString(4, descripcion);
	        stmtMueble.setString(5, rutaImagen);
	        stmtMueble.executeUpdate();

	        // Insertar en Mesa
	        stmtMesa.setInt(1, idProducto);
	        stmtMesa.setDouble(2, altura);
	        stmtMesa.setInt(3, capacidad);
	        stmtMesa.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarBarbacoa(int idProducto, int numProductos, double peso, double precio,
	        String esExterior, String material, String descripcion, String rutaImagen,
	        String tipoCombustible, double superficieCoccion, String tieneTapa) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlJardineria = "INSERT INTO Jardineria (idProducto, esExterior, Material, Descripcion, RutaImagem) VALUES (?, ?, ?, ?, ?)";
	    String sqlBarbacoa = "INSERT INTO Barbacoa (idProducto, tipoCombustible, superficieCoccion, tieneTapa) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtJardineria = con.prepareStatement(sqlJardineria);
	         PreparedStatement stmtBarbacoa = con.prepareStatement(sqlBarbacoa)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Jardinería
	        stmtJardineria.setInt(1, idProducto);
	        stmtJardineria.setString(2, esExterior);
	        stmtJardineria.setString(3, material);
	        stmtJardineria.setString(4, descripcion);
	        stmtJardineria.setString(5, rutaImagen);
	        stmtJardineria.executeUpdate();

	        // Insertar en Barbacoa
	        stmtBarbacoa.setInt(1, idProducto);
	        stmtBarbacoa.setString(2, tipoCombustible);
	        stmtBarbacoa.setDouble(3, superficieCoccion);
	        stmtBarbacoa.setString(4, tieneTapa);
	        stmtBarbacoa.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarInodoro(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        String tipoDescarga, String tieneAsientoCalefaccionado) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlBaño = "INSERT INTO Baño (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlInodoro = "INSERT INTO Inodoro (idProducto, tipoDescarga, tieneAsientoCalefaccionado) VALUES (?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtBaño = con.prepareStatement(sqlBaño);
	         PreparedStatement stmtInodoro = con.prepareStatement(sqlInodoro)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Baño
	        stmtBaño.setInt(1, idProducto);
	        stmtBaño.setString(2, material);
	        stmtBaño.setString(3, descripcion);
	        stmtBaño.setString(4, rutaImagen);
	        stmtBaño.executeUpdate();

	        // Insertar en Inodoro
	        stmtInodoro.setInt(1, idProducto);
	        stmtInodoro.setString(2, tipoDescarga);
	        stmtInodoro.setString(3, tieneAsientoCalefaccionado);
	        stmtInodoro.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarSilla(int idProducto, int numProductos, double peso, double precio,
	        String material, String color, String descripcion, String rutaImagen,
	        double altura, double anchura, double capacidadDeCarga) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlMueble = "INSERT INTO Mueble (idProducto, Material, Color, Descripcion, RutaImagen) VALUES (?, ?, ?, ?, ?)";
	    String sqlSilla = "INSERT INTO Silla (idProducto, Altura, Anchura, CapacidadDeCarga) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtMueble = con.prepareStatement(sqlMueble);
	         PreparedStatement stmtSilla = con.prepareStatement(sqlSilla)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Mueble
	        stmtMueble.setInt(1, idProducto);
	        stmtMueble.setString(2, material);
	        stmtMueble.setString(3, color);
	        stmtMueble.setString(4, descripcion);
	        stmtMueble.setString(5, rutaImagen);
	        stmtMueble.executeUpdate();

	        // Insertar en Silla
	        stmtSilla.setInt(1, idProducto);
	        stmtSilla.setDouble(2, altura);
	        stmtSilla.setDouble(3, anchura);
	        stmtSilla.setDouble(4, capacidadDeCarga);
	        stmtSilla.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarPlanta(int idProducto, int numProductos, double peso, double precio,
	        String esExterior, String material, String descripcion, String rutaImagen,
	        double altura, String tipoDePlanta, double diametro) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlJardineria = "INSERT INTO Jardineria (idProducto, esExterior, Material, Descripcion, RutaImagem) VALUES (?, ?, ?, ?, ?)";
	    String sqlPlanta = "INSERT INTO Planta (idProducto, Altura, TipoDePlanta, Diametro) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtJardineria = con.prepareStatement(sqlJardineria);
	         PreparedStatement stmtPlanta = con.prepareStatement(sqlPlanta)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Jardinería
	        stmtJardineria.setInt(1, idProducto);
	        stmtJardineria.setString(2, esExterior);
	        stmtJardineria.setString(3, material);
	        stmtJardineria.setString(4, descripcion);
	        stmtJardineria.setString(5, rutaImagen);
	        stmtJardineria.executeUpdate();

	        // Insertar en Planta
	        stmtPlanta.setInt(1, idProducto);
	        stmtPlanta.setDouble(2, altura);
	        stmtPlanta.setString(3, tipoDePlanta);
	        stmtPlanta.setDouble(4, diametro);
	        stmtPlanta.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarBide(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        String tieneCalefaccion, String esElectrico) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlBaño = "INSERT INTO Baño (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlBide = "INSERT INTO Bide (idProducto, tieneCalefaccion, esElectrico) VALUES (?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtBaño = con.prepareStatement(sqlBaño);
	         PreparedStatement stmtBide = con.prepareStatement(sqlBide)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Baño
	        stmtBaño.setInt(1, idProducto);
	        stmtBaño.setString(2, material);
	        stmtBaño.setString(3, descripcion);
	        stmtBaño.setString(4, rutaImagen);
	        stmtBaño.executeUpdate();

	        // Insertar en Bidé
	        stmtBide.setInt(1, idProducto);
	        stmtBide.setString(2, tieneCalefaccion);
	        stmtBide.setString(3, esElectrico);
	        stmtBide.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarHorno(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        double altura, double anchura, double profundidad, int potencia, int numeroBandejas) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlCocina = "INSERT INTO Cocina (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlHorno = "INSERT INTO Horno (idProducto, Altura, Anchura, Profundida, Potencia, NumeroBandejas) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtCocina = con.prepareStatement(sqlCocina);
	         PreparedStatement stmtHorno = con.prepareStatement(sqlHorno)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Cocina
	        stmtCocina.setInt(1, idProducto);
	        stmtCocina.setString(2, material);
	        stmtCocina.setString(3, descripcion);
	        stmtCocina.setString(4, rutaImagen);
	        stmtCocina.executeUpdate();

	        // Insertar en Horno
	        stmtHorno.setInt(1, idProducto);
	        stmtHorno.setDouble(2, altura);
	        stmtHorno.setDouble(3, anchura);
	        stmtHorno.setDouble(4, profundidad);
	        stmtHorno.setInt(5, potencia);
	        stmtHorno.setInt(6, numeroBandejas);
	        stmtHorno.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarEncimera(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        String resistenciaCalor, String grosor, String color) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlCocina = "INSERT INTO Cocina (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlEncimera = "INSERT INTO Encimera (idProducto, resistenciaCalor, Grosor, Color) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtCocina = con.prepareStatement(sqlCocina);
	         PreparedStatement stmtEncimera = con.prepareStatement(sqlEncimera)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Cocina
	        stmtCocina.setInt(1, idProducto);
	        stmtCocina.setString(2, material);
	        stmtCocina.setString(3, descripcion);
	        stmtCocina.setString(4, rutaImagen);
	        stmtCocina.executeUpdate();

	        // Insertar en Encimera
	        stmtEncimera.setInt(1, idProducto);
	        stmtEncimera.setString(2, resistenciaCalor);
	        stmtEncimera.setString(3, grosor);
	        stmtEncimera.setString(4, color);
	        stmtEncimera.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public static void insertarLavamanos(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        String tipoGrifo, String tieneAlmacenamiento) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlBaño = "INSERT INTO Baño (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlLavamanos = "INSERT INTO Lavamanos (idProducto, tipoGrifo, tieneAlmacenamiento) VALUES (?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtBaño = con.prepareStatement(sqlBaño);
	         PreparedStatement stmtLavamanos = con.prepareStatement(sqlLavamanos)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Baño
	        stmtBaño.setInt(1, idProducto);
	        stmtBaño.setString(2, material);
	        stmtBaño.setString(3, descripcion);
	        stmtBaño.setString(4, rutaImagen);
	        stmtBaño.executeUpdate();

	        // Insertar en Lavamanos
	        stmtLavamanos.setInt(1, idProducto);
	        stmtLavamanos.setString(2, tipoGrifo);
	        stmtLavamanos.setString(3, tieneAlmacenamiento);
	        stmtLavamanos.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarMaceta(int idProducto, int numProductos, double peso, double precio,
	        String esExterior, String material, String descripcion, String rutaImagen,
	        double diametro) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlJardineria = "INSERT INTO Jardineria (idProducto, esExterior, Material, Descripcion, RutaImagem) VALUES (?, ?, ?, ?, ?)";
	    String sqlMaceta = "INSERT INTO Maceta (idProducto, Diametro) VALUES (?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtJardineria = con.prepareStatement(sqlJardineria);
	         PreparedStatement stmtMaceta = con.prepareStatement(sqlMaceta)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Jardinería
	        stmtJardineria.setInt(1, idProducto);
	        stmtJardineria.setString(2, esExterior);
	        stmtJardineria.setString(3, material);
	        stmtJardineria.setString(4, descripcion);
	        stmtJardineria.setString(5, rutaImagen);
	        stmtJardineria.executeUpdate();

	        // Insertar en Maceta
	        stmtMaceta.setInt(1, idProducto);
	        stmtMaceta.setDouble(2, diametro);
	        stmtMaceta.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarSofa(int idProducto, int numProductos, double peso, double precio,
	        String material, String color, String descripcion, String rutaImagen,
	        int capacidadDeAsientos) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlMueble = "INSERT INTO Mueble (idProducto, Material, Color, Descripcion, RutaImagen) VALUES (?, ?, ?, ?, ?)";
	    String sqlSofa = "INSERT INTO Sofa (idProducto, CapacidadDeAsientos) VALUES (?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtMueble = con.prepareStatement(sqlMueble);
	         PreparedStatement stmtSofa = con.prepareStatement(sqlSofa)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Mueble
	        stmtMueble.setInt(1, idProducto);
	        stmtMueble.setString(2, material);
	        stmtMueble.setString(3, color);
	        stmtMueble.setString(4, descripcion);
	        stmtMueble.setString(5, rutaImagen);
	        stmtMueble.executeUpdate();

	        // Insertar en Sofá
	        stmtSofa.setInt(1, idProducto);
	        stmtSofa.setInt(2, capacidadDeAsientos);
	        stmtSofa.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarHerramienta(int idProducto, int numProductos, double peso, double precio,
	        String esExterior, String material, String descripcion, String rutaImagen,
	        String tipo) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlJardineria = "INSERT INTO Jardineria (idProducto, esExterior, Material, Descripcion, RutaImagem) VALUES (?, ?, ?, ?, ?)";
	    String sqlHerramienta = "INSERT INTO Herramienta (idProducto, Tipo) VALUES (?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtJardineria = con.prepareStatement(sqlJardineria);
	         PreparedStatement stmtHerramienta = con.prepareStatement(sqlHerramienta)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Jardinería
	        stmtJardineria.setInt(1, idProducto);
	        stmtJardineria.setString(2, esExterior);
	        stmtJardineria.setString(3, material);
	        stmtJardineria.setString(4, descripcion);
	        stmtJardineria.setString(5, rutaImagen);
	        stmtJardineria.executeUpdate();

	        // Insertar en Herramienta
	        stmtHerramienta.setInt(1, idProducto);
	        stmtHerramienta.setString(2, tipo);
	        stmtHerramienta.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarDucha(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        String tipoRociador, String tieneMampara) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlBaño = "INSERT INTO Baño (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlDucha = "INSERT INTO Ducha (idProducto, tipoRociador, tieneMampara) VALUES (?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtBaño = con.prepareStatement(sqlBaño);
	         PreparedStatement stmtDucha = con.prepareStatement(sqlDucha)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Baño
	        stmtBaño.setInt(1, idProducto);
	        stmtBaño.setString(2, material);
	        stmtBaño.setString(3, descripcion);
	        stmtBaño.setString(4, rutaImagen);
	        stmtBaño.executeUpdate();

	        // Insertar en Ducha
	        stmtDucha.setInt(1, idProducto);
	        stmtDucha.setString(2, tipoRociador);
	        stmtDucha.setString(3, tieneMampara);
	        stmtDucha.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarNevera(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        double altura, double anchura, double profundidad, double capacidad, String tipoNevera) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlCocina = "INSERT INTO Cocina (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlNevera = "INSERT INTO Nevera (idProducto, Altura, Anchura, Profundida, Capacidad, tipoNevera) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtCocina = con.prepareStatement(sqlCocina);
	         PreparedStatement stmtNevera = con.prepareStatement(sqlNevera)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Cocina
	        stmtCocina.setInt(1, idProducto);
	        stmtCocina.setString(2, material);
	        stmtCocina.setString(3, descripcion);
	        stmtCocina.setString(4, rutaImagen);
	        stmtCocina.executeUpdate();

	        // Insertar en Nevera
	        stmtNevera.setInt(1, idProducto);
	        stmtNevera.setDouble(2, altura);
	        stmtNevera.setDouble(3, anchura);
	        stmtNevera.setDouble(4, profundidad);
	        stmtNevera.setDouble(5, capacidad);
	        stmtNevera.setString(6, tipoNevera);
	        stmtNevera.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void insertarFregadero(int idProducto, int numProductos, double peso, double precio,
	        String material, String descripcion, String rutaImagen,
	        int numCubetas, double profundidad, String grifo) {
	    String sqlProducto = "INSERT INTO Producto (idProducto, NumeroProductos, Peso, Precio) VALUES (?, ?, ?, ?)";
	    String sqlCocina = "INSERT INTO Cocina (idProducto, Material, Descripcion, RutaImagen) VALUES (?, ?, ?, ?)";
	    String sqlFregadero = "INSERT INTO Fregadero (idProducto, numCubetas, Profundidad, Grifo) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtProducto = con.prepareStatement(sqlProducto);
	         PreparedStatement stmtCocina = con.prepareStatement(sqlCocina);
	         PreparedStatement stmtFregadero = con.prepareStatement(sqlFregadero)) {

	        // Insertar en Producto
	        stmtProducto.setInt(1, idProducto);
	        stmtProducto.setInt(2, numProductos);
	        stmtProducto.setDouble(3, peso);
	        stmtProducto.setDouble(4, precio);
	        stmtProducto.executeUpdate();

	        // Insertar en Cocina
	        stmtCocina.setInt(1, idProducto);
	        stmtCocina.setString(2, material);
	        stmtCocina.setString(3, descripcion);
	        stmtCocina.setString(4, rutaImagen);
	        stmtCocina.executeUpdate();

	        // Insertar en Fregadero
	        stmtFregadero.setInt(1, idProducto);
	        stmtFregadero.setInt(2, numCubetas);
	        stmtFregadero.setDouble(3, profundidad);
	        stmtFregadero.setString(4, grifo);
	        stmtFregadero.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
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
	
	public static boolean existeCliente(String user, String password) {
	    Cliente cliente = buscarCliente(user, password);

	    return cliente != null;
	}
	
	public static void actualizarUltimoLogin(String dni) {
	    String sql = "UPDATE Cliente SET UltimoLogin = ? WHERE Dni = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setDate(1, Date.valueOf(LocalDate.now()));
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
	
	public static List<Object> obtenerTrabajadoresYClientes() {
	    String sqlClientes = "SELECT * FROM Cliente";
	    String sqlTrabajadores = "SELECT * FROM Trabajador";
	    List<Object> listaPersonas = new ArrayList<>();

	    try {
	        Statement stClientes = con.createStatement();
	        ResultSet rsClientes = stClientes.executeQuery(sqlClientes);
	        while (rsClientes.next()) {
	            String dni = rsClientes.getString("Dni");
	            String genero = rsClientes.getString("Genero");
	            String nombre = rsClientes.getString("Nombre");
	            String apellido = rsClientes.getString("Apellido");
	            String email = rsClientes.getString("Email");
	            String direccion = rsClientes.getString("Direccion");
	            LocalDate fNacimiento = Instant.ofEpochMilli(rsClientes.getLong("Fecha"))
	                    .atZone(ZoneId.systemDefault()).toLocalDate();
	            String contraseña = rsClientes.getString("Contraseña");
	            String telefono = rsClientes.getString("Telefono");
	            int id = rsClientes.getInt("id");
	            LocalDate ultimoLogin = Instant.ofEpochMilli(rsClientes.getLong("UltimoLogin"))
	                    .atZone(ZoneId.systemDefault()).toLocalDate();
	            Descuento descuento = Descuento.valueOf(rsClientes.getString("Descuento"));

	            Cliente cliente = new Cliente(dni, genero, nombre, apellido, email, direccion, fNacimiento,
	                    contraseña, telefono, id, ultimoLogin, descuento);
	            listaPersonas.add(cliente);
	        }
	        rsClientes.close();
	        stClientes.close();

	        Statement stTrabajadores = con.createStatement();
	        ResultSet rsTrabajadores = stTrabajadores.executeQuery(sqlTrabajadores);
	        while (rsTrabajadores.next()) {
	            String dni = rsTrabajadores.getString("Dni");
	            String genero = rsTrabajadores.getString("Genero");
	            String nombre = rsTrabajadores.getString("Nombre");
	            String apellido = rsTrabajadores.getString("Apellido");
	            String email = rsTrabajadores.getString("Email");
	            String direccion = rsTrabajadores.getString("Direccion");
	            LocalDate fNacimiento = Instant.ofEpochMilli(rsTrabajadores.getLong("fNacimiento"))
	                    .atZone(ZoneId.systemDefault()).toLocalDate();
	            String contraseña = rsTrabajadores.getString("Contraseña");
	            String telefono = rsTrabajadores.getString("Telefono");
	            int id = rsTrabajadores.getInt("id");
	            double salario = rsTrabajadores.getDouble("Salario");
	            int horasTrabajadas = rsTrabajadores.getInt("HorasTrabajadas");

	            Trabajador trabajador = new Trabajador(dni, genero, nombre, apellido, email, direccion, fNacimiento,
	                    contraseña, telefono, id, salario, horasTrabajadas);
	            listaPersonas.add(trabajador);
	        }
	        rsTrabajadores.close();
	        stTrabajadores.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaPersonas;
	}

	
	public static Trabajador buscarTrabajador(String user, String password) {
	    String sql = "SELECT * FROM Trabajador WHERE (Dni = ? OR Email = ? OR Telefono = ?) AND Contraseña = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, user); // Buscar por DNI
	        stmt.setString(2, user); // Buscar por Email
	        stmt.setString(3, user); // Buscar por Teléfono
	        stmt.setString(4, password); // Validar contraseña

	        var rs = stmt.executeQuery();

	        if (rs.next()) {
	            // Extraer datos del ResultSet
	            String dni = rs.getString("Dni");
	            String genero = rs.getString("Genero");
	            String nombre = rs.getString("Nombre");
	            String apellido = rs.getString("Apellido");
	            String email = rs.getString("Email");
	            String direccion = rs.getString("Direccion");
	            LocalDate fechaNacimiento = Instant.ofEpochMilli(rs.getLong("fNacimiento"))
	                                    .atZone(ZoneId.systemDefault()).toLocalDate();
	            String telefono = rs.getString("Telefono");
	            int id = rs.getInt("id");
	            double salario = rs.getDouble("Salario");
	            int horasTrabajadas = rs.getInt("HorasTrabajadas");

	            // Crear y devolver un nuevo objeto Trabajador
	            return new Trabajador(dni, genero, nombre, apellido, email, direccion, 
	                                  fechaNacimiento, password, telefono, id, salario, horasTrabajadas);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Manejar errores
	    }
	    return null; // Si no se encuentra ningún trabajador
	}
	
	public static boolean existeTrabajador(String user, String password) {
	    Trabajador trabajador = buscarTrabajador(user, password);

	    return trabajador != null;
	}

	
	public static boolean eliminarCliente(String dni) {
        String sql = "DELETE FROM Cliente WHERE Dni = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            return false;
        }
    }
	public static List<Producto> obtenerListaProductosV(){
		List<Producto> listaProductos = new ArrayList<Producto>(obtenerListaArmarios());
		listaProductos.addAll(obtenerListaMesas());
		listaProductos.addAll(obtenerListaSillas());
		listaProductos.addAll(obtenerListaSofas());
		listaProductos.addAll(obtenerListaBarbacoas());
		listaProductos.addAll(obtenerListaHerramientas());
		listaProductos.addAll(obtenerListaMacetas());
		listaProductos.addAll(obtenerListaPlantas());
		listaProductos.addAll(obtenerListaBides());
		listaProductos.addAll(obtenerListaDuchas());
		listaProductos.addAll(obtenerListaInodoros());
		listaProductos.addAll(obtenerListaLavamanos());
		listaProductos.addAll(obtenerListaEncimeras());
		listaProductos.addAll(obtenerListaFregaderos());
		listaProductos.addAll(obtenerListaNeveras());
		listaProductos.addAll(obtenerListaHornos());
		return listaProductos;
	}
}


