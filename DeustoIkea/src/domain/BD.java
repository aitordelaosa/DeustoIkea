package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
}
