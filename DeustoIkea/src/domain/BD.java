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


