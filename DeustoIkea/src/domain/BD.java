package domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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
			sql = "CREATE TABLE IF NOT EXISTS Armario(idProducto int, NumProductos int, Peso double, Precio double, Material String, Color String, Descripcion String, RutaImagen String, NumeroDePuertas int, Altura double, Anchura double, Profundidad double)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Mesa(idProducto int, NumProductos int, Peso double, Precio double, Material String, Color String, Descripcion String, RutaImagen String, Altura double, Capacidad int)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Silla(idProducto int, NumProductos int, Peso double, Precio double, Material String, Color String, Descripcion String, RutaImagen String, Altura double, Anchura double, CapacidadDeCarga double)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Barbacoa(idProducto int, NumProductos int, Peso double, Precio double, esExterior String, Material String, Descripcion String, RutaImagem String, tipoCombustible String, superficieCoccion double, tieneTapa String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Planta(idProducto int, NumProductos int, Peso double, Precio double, esExterior String, Material String, Descripcion String, RutaImagem String, Altura double, esFrutal String, TipoDePlanta String, Diametro double)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Maceta(idProducto int, NumProductos int, Peso double, Precio double, esExterior String, Material String, Descripcion String, RutaImagem String, Diametro double)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Herramienta(idProducto int, NumProductos int, Peso double, Precio double, esExterior String, Material String, Descripcion String, RutaImagem String, tipo String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Inodoro(idProducto int, NumProductos int, Peso double, Precio double, MaterialB String, Descripcion String, rutaImagen String, tipoDescarga String, tieneAsientoCalefaccionado String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Lavamanos(idProducto int, NumProductos int, Peso double, Precio double, MaterialB String, Descripcion String, rutaImagen String, tipoGrifo String,  tieneAlmacenamiento String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Ducha(idProducto int, NumProductos int, Peso double, Precio double, MaterialB String, Descripcion String, rutaImagen String, tipoRociador String, tieneMampara String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Bide(idProducto int, NumProductos int, Peso double, Precio double, MaterialB String, Descripcion String, rutaImagen String, tieneCalefaccion String, esElectrico String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Encimera(idProducto int, NumProductos int, Peso double, Precio double, MaterialC String, DescripcionC String, rutaImagen String, resistenciaCalor String, Grosor String, Color String )";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Fregadero(idProducto int, NumProductos int, Peso double, Precio double, MaterialC String, DescripcionC String, rutaImagen String, numCubetas int, Profundidad double, Grifo String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Horno(idProducto int, NumProductos int, Peso double, Precio double, MaterialC String, DescripcionC String, rutaImagen String, Altura double, Anchura double, Profundida double, Potencia int, NumeroBandejas int)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Nevera(idProducto int, NumProductos int, Peso double, Precio double, MaterialC String, DescripcionC String, rutaImagen String, Altura double, Anchura double, Profundida double, Capacidad double, tipoNevera String)";
			stmt.executeUpdate(sql);
			stmt.close();
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
}


