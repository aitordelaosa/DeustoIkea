package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import db.BD;
import domain.Baño;
import domain.Cliente;
import domain.Cocina;
import domain.Datos;
import domain.Jardineria;
import domain.Mueble;
import domain.Producto;
import domain.Tipo;
import domain.Trabajador;

public class VentanaTrabajador extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonAtras, botonCerrar;
    protected JPanel panelPrincipal, panelBotones, panelDerecho;
    private JTree arbolFunciones;
    private String[] imagenes = {
        "resources/images/mesa.jpeg",//Regular
        "resources/images/silla.jpeg",//Regular
        "resources/images/Armario.jpeg",//Regular
        "resources/images/sofa.jpeg",//Regular
        "resources/images/lavamanos.jpg",
        "resources/images/ducha.jpg",
        "resources/images/bide.jpeg",//Regular
        "resources/images/inodoro.jpg",
        "resources/images/Maceta.jpg",
        "resources/images/adelfas-colores.jpg",//Regular
        "resources/images/Nevera.jpg",
        "resources/images/horno.jpg",
        "resources/images/encimera.jpg",
        "resources/images/fregadero.jpg"
    };
    private int indiceImagen = 0;
    private int codigo;
    private Datos datos;
    private JTable tabla;
    private JScrollPane scrollpane;
    private Modelo modelo;

    public VentanaTrabajador(int codigo, Datos datos) {
    	this.codigo = codigo;
    	this.datos = datos;
        setTitle("Ventana-Trabajador");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        botonAtras = new JButton("ATRÁS");
        botonCerrar = new JButton("CERRAR");

        panelBotones = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);
        
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(600, altoP));
        panelDerecho.setBackground(Color.WHITE);

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Funciones");
        DefaultMutableTreeNode clientes = new DefaultMutableTreeNode("Ver Clientes");
        DefaultMutableTreeNode trabajadores = new DefaultMutableTreeNode("Ver Trabajadores");
        DefaultMutableTreeNode eliminarClientes = new DefaultMutableTreeNode("Eliminar Clientes");
        DefaultMutableTreeNode buscarTrabajador = new DefaultMutableTreeNode("Buscar Trabajador");
        DefaultMutableTreeNode muebles = new DefaultMutableTreeNode("Mostrar Muebles Disponibles");
        DefaultMutableTreeNode cocina = new DefaultMutableTreeNode("Mostrar elementos de Cocina Disponibles");
        DefaultMutableTreeNode jardin = new DefaultMutableTreeNode("Mostrar elementos de Jardineria Disponibles");
        DefaultMutableTreeNode baño = new DefaultMutableTreeNode("Mostrar elementos de Baño Disponibles");
        DefaultMutableTreeNode stock = new DefaultMutableTreeNode("Rellenar Stock");
        DefaultMutableTreeNode productos = new DefaultMutableTreeNode("Ver Productos");
        

        raiz.add(clientes);
        raiz.add(trabajadores);
        if (codigo ==0) {
        	raiz.add(eliminarClientes);
            raiz.add(buscarTrabajador);
            raiz.add(muebles);
            raiz.add(cocina);
            raiz.add(jardin);
            raiz.add(baño);
            raiz.add(stock);
        	
        } else {
        	raiz.add(productos);
        }
        

        arbolFunciones = new JTree(raiz);
        arbolFunciones.setRootVisible(true);
        arbolFunciones.setPreferredSize(new Dimension(250, 300));

        arbolFunciones.addTreeSelectionListener(e -> {
            TreePath path = arbolFunciones.getSelectionPath();
            if (path != null) {
                String seleccion = path.getLastPathComponent().toString();
                ejecutarFuncion(seleccion);
            }
        });

        panelPrincipal.add(arbolFunciones, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal);

        botonAtras.addActionListener((e) -> {
            dispose();
            new VentanaInicioTrabajador(codigo);
        });

        botonCerrar.addActionListener((e) -> {
            System.exit(0);
        });

        if (codigo==0) {
        	iniciarHiloAnimacion();
        }

        setVisible(true);
    }

   
    	private void ejecutarFuncion(String seleccion) {
    	
    			
    
        switch (seleccion) {
            case "Ver Clientes":
                verClientes();
                break;
            case "Ver Trabajadores":
                verTrabajadores();
                break;
            case "Eliminar Clientes":
                eliminarClientes();
                break;
            case "Buscar Trabajador":
            	buscarTrabajador();
                break;
            case "Mostrar Muebles Disponibles":
                mostrarMueblesDisponibles();
                break;
            case "Mostrar elementos de Cocina Disponibles":
                mostrarCocinaDisponibles();
                break;
            case "Mostrar elementos de Baño Disponibles":
                mostrarBañoDisponibles();
                break;
            case "Mostrar elementos de Jardineria Disponibles":
                mostrarJardineriaDisponibles();
                break;
            case "Rellenar Stock":
                rellenarStock();
                break;
            case "Ver Productos":
            	verProductos();
            	break;
            default:
                JOptionPane.showMessageDialog(this, "Función no implementada");
        }
        
    
    }

    private void verClientes() {
//      JOptionPane.showMessageDialog(this, "Mostrando clientes...");
  	if(codigo ==0) {
  		Object[] options = {"Mostrar"};
          
          JOptionPane.showOptionDialog(this, 
              "Mostrando clientes...",
              "Información",
              JOptionPane.DEFAULT_OPTION, 
              JOptionPane.INFORMATION_MESSAGE, 
              null,
              options,
              options[0]
          );
         Timer timer = new Timer(500, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 cargarClientes();
             }
         });
         timer.setRepeats(false);
         timer.start();
     } else {
  	List<Object[]> datos = new ArrayList<>();
		List<String> titulos = new ArrayList<>();

	        
   	  List<Cliente> clientes = BD.ObtenerListaCliente(); 
      titulos = Arrays.asList("DNI", "Género", "Nombre", "Apellido", "Email", 
                               "Dirección", "Fecha Nacimiento", "Contraseña", 
                               "Teléfono", "ID", "Ultimo Login", "Descuento");

      for (Cliente c : clientes) {
          datos.add(new Object[]{
              c.getDni(), c.getGenero(), c.getNombre(), c.getApellido(),
              c.getEmail(), c.getDireccion(), c.getfNacimiento(),
              c.getContrasenia(), c.getTelefono(), c.getContador(), c.getUltimoLogin(),
              c.getDescuento()
          });
      }

      modelo = new Modelo(datos, titulos);
      tabla = new JTable(modelo);
      
      
      scrollpane = new JScrollPane(tabla);
      System.out.println("Datos iniciales en el modelo:");
      for (int i = 0; i < modelo.getRowCount(); i++) {
          for (int j = 0; j < modelo.getColumnCount(); j++) {
              System.out.print(modelo.getValueAt(i, j) + "\t");
          }
          System.out.println();
      }
     
      tabla.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
              if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                  try {
                      System.out.println("Tecla BACK_SPACE presionada.");

                      int selectedRow = tabla.getSelectedRow();
                      System.out.println("Fila seleccionada: " + selectedRow);
                      if (selectedRow < 0 || selectedRow >= modelo.getRowCount()) {
                          JOptionPane.showMessageDialog(
                              VentanaTrabajador.this,
                              "No se ha seleccionado una fila válida para eliminar.",
                              "Aviso",
                              JOptionPane.INFORMATION_MESSAGE
                          );
                          return;
                      }

                      String dniCliente = (String) modelo.getValueAt(selectedRow, 0);
                      System.out.println("DNI del cliente seleccionado: " + dniCliente);

                      int response = JOptionPane.showConfirmDialog(
                          VentanaTrabajador.this,
                          "¿Seguro que quieres borrar al cliente con DNI: " + dniCliente + "?",
                          "Confirmación",
                          JOptionPane.YES_NO_OPTION,
                          JOptionPane.WARNING_MESSAGE
                      );

                      if (response == JOptionPane.YES_OPTION) {
                          modelo.removeRow(selectedRow);
                          System.out.println("Fila eliminada correctamente: " + selectedRow);
                          BD.eliminarCliente(dniCliente);
                      }
                  } catch (Exception ex) {
                      System.err.println("Error al manejar el evento de teclado:");
                      ex.printStackTrace();
                  }
              }
          }
      });
      
      panelDerecho.setLayout(new BorderLayout());
      panelDerecho.removeAll();
      panelDerecho.add(scrollpane, BorderLayout.CENTER);
      panelDerecho.revalidate();
      panelDerecho.repaint();
   		        
   		  }
  	   
     }
//    private void cargarClientes() {
//        List<Cliente> clientes = new Datos().lCliente;
//        
//        StringBuilder mensaje = new StringBuilder("Clientes:\n");
//        for (Cliente cliente : clientes) {
//            mensaje.append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n").append(cliente.getDni()).append("\n").append(cliente.getTelefono()).append("\n").append(cliente.getEmail()).append("\n");
//        }
//
//        JOptionPane.showMessageDialog(this, mensaje.toString());
//    }
    
    private void cargarClientes() {
        List<Cliente> clientes = datos.verClientes();
        
        StringBuilder mensaje = new StringBuilder("Clientes:\n");
        for (Cliente cliente : clientes) {
            mensaje.append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n")
                   .append(cliente.getDni()).append("\n")
                   .append(cliente.getTelefono()).append("\n")
                   .append(cliente.getEmail()).append("\n\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }

    private void verTrabajadores() {
//      JOptionPane.showMessageDialog(this, "Mostrando trabajadores...");
  	if(codigo ==0) {
  		Object[] options = {"Mostrar"};
  		JOptionPane.showOptionDialog(this, 
  	            "Mostrando trabajadores...",
  	            "Información",
  	            JOptionPane.DEFAULT_OPTION, 
  	            JOptionPane.INFORMATION_MESSAGE, 
  	            null,
  	            options,
  	            options[0]
  	        );
  	       Timer timer = new Timer(500, new ActionListener() {
  	           @Override
  	           public void actionPerformed(ActionEvent e) {
  	               cargarTrabajadores();
  	           }
  	       });
  	       timer.setRepeats(false);
  	       timer.start();
  	} else {
  		List<Object[]> datos = new ArrayList<>();
  		List<String> titulos = new ArrayList<>();

  		        
  	  List<Trabajador> trabajadores = BD.ObtenerListaTrabajador(); 
     titulos = Arrays.asList("DNI", "Género", "Nombre", "Apellido", "Email", 
                              "Dirección", "Fecha Nacimiento", "Contraseña", 
                              "Teléfono", "ID", "Salario", "Horas Trabajadas");

     for (Trabajador t : trabajadores) {
         datos.add(new Object[]{
             t.getDni(), t.getGenero(), t.getNombre(), t.getApellido(),
             t.getEmail(), t.getDireccion(), t.getfNacimiento(),
             t.getContrasenia(), t.getTelefono(), t.getContador(), t.getSalario(),
             t.getHorasTrabajadas()
         });
     }

     Modelo modelo = new Modelo(datos, titulos);
     JTable tabla = new JTable(modelo);
     
     JScrollPane scrollPane = new JScrollPane(tabla);
     
     
 

     panelDerecho.setLayout(new BorderLayout());
     panelDerecho.removeAll();
     panelDerecho.add(scrollPane, BorderLayout.CENTER);
     panelDerecho.revalidate();
     panelDerecho.repaint();
  		        
  		  }
  		
  	}
        
        
    

    private void cargarTrabajadores() {
        List<Trabajador> trabajadores = new Datos().lTrabajador;
        
        StringBuilder mensaje = new StringBuilder("Trabajadores:\n");
        for (Trabajador trabajador : trabajadores) {
            mensaje.append(trabajador.getNombre()).append(" ").append(trabajador.getApellido()).append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    private void eliminarClientes() {
        String user = JOptionPane.showInputDialog(this, "Introduce el Dni, telefono o email del cliente a eliminar:", "Eliminar Cliente", JOptionPane.QUESTION_MESSAGE);

        if (user != null && !user.trim().isEmpty()) {
            Object[] options = {"Eliminar"};

            JOptionPane.showOptionDialog(this, 
                "Eliminando cliente: " + user,
                "Información",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]
            );

            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarEliminacion(user);
                }
            });

            timer.setRepeats(false);
            timer.start();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha introducido un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void realizarEliminacion(String user) {
    	Datos datos = new Datos();
		datos.eliminarCliente(user);
    }
    
    private void buscarTrabajador() {
        String criterio = JOptionPane.showInputDialog(this, "Buscar trabajador por:\n1. DNI\n2. Teléfono\n3. Email");
        
        if (criterio == null) {
            return;
        }
        
        String dato = JOptionPane.showInputDialog(this, "Ingrese el " + criterio + " del trabajador:");
        if (dato == null || dato.isEmpty()) {
            return;
        }
        
        Trabajador trabajador = null;
        switch (criterio) {
            case "1":
                trabajador = datos.buscarTrabajadorPorDni(dato); // Usamos el método de Datos
                break;
            case "2":
                trabajador = datos.buscarTrabajadorPorTelefono(dato); // Usamos el método de Datos
                break;
            case "3":
                trabajador = datos.buscarTrabajadorPorEmail(dato); // Usamos el método de Datos
                break;
            default:
                JOptionPane.showMessageDialog(this, "Criterio inválido.");
                return;
        }

        if (trabajador != null) {
            String mensaje = "Trabajador encontrado:\n";
            mensaje += "Nombre: " + trabajador.getNombre() + " " + trabajador.getApellido() + "\n";
            mensaje += "DNI: " + trabajador.getDni() + "\n";
            mensaje += "Teléfono: " + trabajador.getTelefono() + "\n";
            mensaje += "Email: " + trabajador.getEmail() + "\n";
            mensaje += "Salario: " + trabajador.getSalario() + "\n";
            mensaje += "Horas trabajadas: " + trabajador.getHorasTrabajadas() + "\n";
            JOptionPane.showMessageDialog(this, mensaje);
        } else {
            JOptionPane.showMessageDialog(this, "Trabajador no encontrado.");
        }
    }

    private void mostrarMueblesDisponibles() {
        List<Mueble> mueblesDisponibles = datos.obtenerMueblesDisponibles();

        if (mueblesDisponibles == null || mueblesDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay muebles disponibles.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Muebles disponibles:\n");

        for (Mueble mueble : mueblesDisponibles) {
            mensaje.append("ID: ").append(mueble.getIdProducto()).append("\n");
            mensaje.append("Precio: ").append(mueble.getPrecio()).append("\n");
            mensaje.append("Peso: ").append(mueble.getPeso()).append("\n");
            mensaje.append("Material: ").append(mueble.getMaterial()).append("\n");
            mensaje.append("Descripción: ").append(mueble.getDescripcion()).append("\n");
            mensaje.append("------------------------------\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    private void mostrarCocinaDisponibles() {
        List<Cocina> cocinaDisponibles = datos.obtenerElementosCocinaDisponibles();

        if (cocinaDisponibles == null || cocinaDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay elementos de cocina disponibles.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Elementos de cocina disponibles:\n");

        for (Cocina cocina : cocinaDisponibles) {
            mensaje.append("ID: ").append(cocina.getIdProducto()).append("\n");
            mensaje.append("Precio: ").append(cocina.getPrecio()).append("\n");
            mensaje.append("Peso: ").append(cocina.getPeso()).append("\n");
            mensaje.append("Material: ").append(cocina.getMaterialC()).append("\n");
            mensaje.append("Descripción: ").append(cocina.getDescripcionC()).append("\n");
            mensaje.append("------------------------------\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    private void mostrarBañoDisponibles() {
        List<Baño> bañoDisponibles = datos.obtenerElementosBañoDisponibles();

        if (bañoDisponibles == null || bañoDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay elementos de baño disponibles.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Elementos de baño disponibles:\n");

        for (Baño baño : bañoDisponibles) {
            mensaje.append("ID: ").append(baño.getIdProducto()).append("\n");
            mensaje.append("Precio: ").append(baño.getPrecio()).append("\n");
            mensaje.append("Peso: ").append(baño.getPeso()).append("\n");
            mensaje.append("Material: ").append(baño.getMaterialB()).append("\n");
            mensaje.append("Descripción: ").append(baño.getDescripcionB()).append("\n");
            mensaje.append("------------------------------\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
    
    private void mostrarJardineriaDisponibles() {
        List<Jardineria> jardinDisponibles = datos.obtenerElementosJardineriaDisponibles();

        if (jardinDisponibles == null || jardinDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay elementos de jardinería disponibles.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("Elementos de jardinería disponibles:\n");

        for (Jardineria jardin : jardinDisponibles) {
            mensaje.append("ID: ").append(jardin.getIdProducto()).append("\n");
            mensaje.append("Precio: ").append(jardin.getPrecio()).append("\n");
            mensaje.append("Peso: ").append(jardin.getPeso()).append("\n");
            mensaje.append("Material: ").append(jardin.getMaterial()).append("\n");
            mensaje.append("Descripción: ").append(jardin.getDescripcion()).append("\n");
            mensaje.append("------------------------------\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString());
    }



    private void rellenarStock() {
        JOptionPane.showMessageDialog(this, "Rellenando stock...");
    }

    private void iniciarHiloAnimacion() {
        Thread hiloAnimacion = new Thread(() -> {
            while (true) {
                try {
                    ImageIcon imagenActual = new ImageIcon(imagenes[indiceImagen]);
                    
                    java.awt.Image imagenRedimensionada = imagenActual.getImage().getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
                    
                    panelDerecho.removeAll();
                    panelDerecho.add(new javax.swing.JLabel(imagenRedimensionadaIcon));
                    panelDerecho.revalidate();
                    panelDerecho.repaint();

                    indiceImagen = (indiceImagen + 1) % imagenes.length;

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloAnimacion.start();
    }
   private void verProductos() {
	   List<Object[]> datos = new ArrayList<>();
 		List<String> titulos = new ArrayList<>();
 		List<Producto> Productos = BD.obtenerListaProductosV(); 
 	     titulos = Arrays.asList("Nombre", "ID", "Numero", "Peso", "Precio");
 	     
 	    for (Producto p : Productos ) {
 	         datos.add(new Object[]{
 	        		 p.getClass().getSimpleName(), p.getIdProducto(), p.getNumeroProductos(), p.getPeso(), p.getPrecio()
 	            
 	         });
 	     }
 	   Modelo modelo = new Modelo(datos, titulos);
 	     JTable tabla = new JTable(modelo);
 	     
 	     JScrollPane scrollPane = new JScrollPane(tabla);
 	     
 	    /*tabla.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
             if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                 try {
                     System.out.println("Tecla BACK_SPACE presionada.");

                     int selectedRow = tabla.getSelectedRow();
                     System.out.println("Fila seleccionada: " + selectedRow);
                     if (selectedRow < 0 || selectedRow >= modelo.getRowCount()) {
                         JOptionPane.showMessageDialog(
                             VentanaTrabajador.this,
                             "No se ha seleccionado una fila válida para eliminar.",
                             "Aviso",
                             JOptionPane.INFORMATION_MESSAGE
                         );
                         return;
                     }

                     int id = (int) modelo.getValueAt(selectedRow, 1);
                     System.out.println("ID" + id);

                     int response = JOptionPane.showConfirmDialog(
                         VentanaTrabajador.this,
                         "¿Seguro que quieres borrar el producto con ID: " + id + "?",
                         "Confirmación",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE
                     );

                     if (response == JOptionPane.YES_OPTION) {
                         modelo.removeRow(selectedRow);
                         System.out.println("Fila eliminada correctamente: " + selectedRow);
                         BD.eliminarProducto(id);
                     }
                 } catch (Exception ex) {
                     System.err.println("Error al manejar el evento de teclado:");
                     ex.printStackTrace();
                 }
             }
         }
     });*/

     
     
 

     panelDerecho.setLayout(new BorderLayout());
     panelDerecho.removeAll();
     panelDerecho.add(scrollPane, BorderLayout.CENTER);
     panelDerecho.revalidate();
     panelDerecho.repaint();
    panelDerecho.setFocusable(true);

// Solicitar el foco para capturar eventos de teclado
    panelDerecho.requestFocusInWindow();
    // FALTA POR TERMINAR EN BD Y AQUI añadir productos y arreglar cosas.
   if (arbolFunciones.getLastSelectedPathComponent().toString().equals("Ver Productos")) {
	    panelDerecho.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_P) {
	                try {
	                    System.out.println("Tecla P presionada.");
	                    String[] opciones = {"Armario", "Barbacoa", "Bide", "Ducha", "Encimera", "Fregadero", "Herramienta", "Horno", "Inodoro", "Maceta", "Mesa", "Nevera", "Planta", "Silla", "Sofa"};

	                    // Crear un JComboBox con las opciones
	                    JComboBox<String> comboBox = new JComboBox<>(opciones);

	                    // Mostrar el JComboBox dentro de un JOptionPane
	                    int seleccion = JOptionPane.showConfirmDialog(
	                        null,
	                        comboBox,
	                        "Seleccione una opción",
	                        JOptionPane.OK_CANCEL_OPTION
	                    );

	                    if (seleccion == JOptionPane.OK_OPTION) {
	                        String opcionSeleccionada = (String) comboBox.getSelectedItem();

	                        // Crear campos de texto para ingresar datos
	                        JTextField numProductosF = new JTextField();
	                        JTextField pesoF = new JTextField();
	                        JTextField precioF = new JTextField();

	                        // Crear un panel para contener los campos
	                        JPanel Panel = new JPanel(new GridLayout(3, 2, 5, 5));
	                        Panel.add(new JLabel("Número de Productos:"));
	                        Panel.add(numProductosF);
	                        Panel.add(new JLabel("Peso:"));
	                        Panel.add(pesoF);
	                        Panel.add(new JLabel("Precio:"));
	                        Panel.add(precioF);

	                        // Mostrar el segundo JOptionPane
	                        int resultado = JOptionPane.showConfirmDialog(null,Panel,"Ingrese los detalles del producto",JOptionPane.OK_CANCEL_OPTION
	                        );

	                        if (resultado == JOptionPane.OK_OPTION) {
	                        	
	                            // Obtener los valores ingresados
	                            int id = BD.obtenerSiguienteIdProducto();
	                            int numProductos = Integer.parseInt(numProductosF.getText());
	                            double peso = Double.parseDouble(pesoF.getText());
	                            double precio = Double.parseDouble(precioF.getText());

	                         
	                                // Verificar si es un tipo que hereda de Mueble
	                         if (opcionSeleccionada.equals("Armario") || opcionSeleccionada.equals("Silla") ||
	                                    opcionSeleccionada.equals("Sofá") || opcionSeleccionada.equals("Mesa")) {

	                                    // Solicitar datos genéricos de Mueble
	                                    JTextField materialF = new JTextField();
	                                    JTextField colorF = new JTextField();
	                                    JTextField descripcionF = new JTextField();
	                                    JTextField rutaImagenF = new JTextField();

	                                    JPanel mueblePanel = new JPanel(new GridLayout(4, 2, 5, 5));
	                                    mueblePanel.add(new JLabel("Material:"));
	                                    mueblePanel.add(materialF);
	                                    mueblePanel.add(new JLabel("Color:"));
	                                    mueblePanel.add(colorF);
	                                    mueblePanel.add(new JLabel("Descripción:"));
	                                    mueblePanel.add(descripcionF);
	                                    mueblePanel.add(new JLabel("Ruta de Imagen:"));
	                                    mueblePanel.add(rutaImagenF);

	                                    int muebleResult = JOptionPane.showConfirmDialog(null, mueblePanel,
	                                            "Ingrese los datos generales del Mueble", JOptionPane.OK_CANCEL_OPTION);

	                                    if (muebleResult != JOptionPane.OK_OPTION) {
	                                        return; // Si cancela, salir del flujo
	                                    }

	                                    String material = materialF.getText();
	                                    String color = colorF.getText();
	                                    String descripcion = descripcionF.getText();
	                                    String rutaImagen = rutaImagenF.getText();
	                                    switch (opcionSeleccionada) {
	      	                          		case "Armario" -> {
	      	                          			boolean validInput = false;
		      	                             while (!validInput) {
		      	                                 // Crear los campos para los datos específicos
		      	                                 JTextField numeroDePuertasF = new JTextField();
		      	                                 JTextField alturaF = new JTextField();
		      	                                 JTextField anchuraF = new JTextField();
		      	                                 JTextField profundidadF = new JTextField();
	
		      	                                 // Crear un panel con GridLayout para contener los campos
		      	                                 JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
		      	                                 panel.add(new JLabel("Número de Puertas:"));
		      	                                 panel.add(numeroDePuertasF);
		      	                                 panel.add(new JLabel("Altura:"));
		      	                                 panel.add(alturaF);
		      	                                 panel.add(new JLabel("Anchura:"));
		      	                                 panel.add(anchuraF);
		      	                                 panel.add(new JLabel("Profundidad:"));
		      	                                 panel.add(profundidadF);
	
		      	                                 int result = JOptionPane.showConfirmDialog(null, panel,
		      	                                         "Ingrese los datos para Armario", JOptionPane.OK_CANCEL_OPTION);
		      	                                 if (result == JOptionPane.OK_OPTION) {
		      	                                     try {
		      	                                         int numeroDePuertas = Integer.parseInt(numeroDePuertasF.getText());
		      	                                         double altura = Double.parseDouble(alturaF.getText());
		      	                                         double anchura = Double.parseDouble(anchuraF.getText());
		      	                                         double profundidad = Double.parseDouble(profundidadF.getText());
		      	                                         validInput = true; // Los datos son válidos, salimos del bucle
		      	                                         BD.insertarArmario(id, numProductos, peso, precio, material, color, descripcion,
		      	                                                 rutaImagen, numeroDePuertas, altura, anchura, profundidad);
	
		      	                                         JOptionPane.showMessageDialog(null, "Armario añadido correctamente.");
		      	                                     } catch (NumberFormatException a) {
		      	                                         JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
		      	                                                 "Error", JOptionPane.ERROR_MESSAGE);
		      	                                     }
		      	                                 } else {
		      	                                     
		      	                                     break;
	      	                                 }
	      	                             }
	      	                         }
	      	                          case "Silla" -> {
	      	  	                        JTextField alturaF = new JTextField();
	      	  	                        JTextField anchuraF = new JTextField();
	      	  	                        JTextField capacidadDeCargaF = new JTextField();

	      	  	                        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	      	  	                        panel.add(new JLabel("Altura:"));
	      	  	                        panel.add(alturaF);
	      	  	                        panel.add(new JLabel("Anchura:"));
	      	  	                        panel.add(anchuraF);
	      	  	                        panel.add(new JLabel("Capacidad de Carga:"));
	      	  	                        panel.add(capacidadDeCargaF);

	      	  	                        int result = JOptionPane.showConfirmDialog(null, panel,
	      	  	                                "Ingrese los datos para Silla", JOptionPane.OK_CANCEL_OPTION);

	      	  	                        if (result == JOptionPane.OK_OPTION) {
	      	  	                            try {
	      	  	                                double altura = Double.parseDouble(alturaF.getText());
	      	  	                                double anchura = Double.parseDouble(anchuraF.getText());
	      	  	                                double capacidadDeCarga = Double.parseDouble(capacidadDeCargaF.getText());
	      	  	                                System.out.println("Silla creada con altura: " + altura + ", anchura: " + anchura +
	      	  	                                        ", capacidad de carga: " + capacidadDeCarga);
	      	  	                                BD.insertarSilla(id, numProductos, peso, precio, material, color, descripcion, rutaImagen, altura, anchura, capacidadDeCarga);
	      	  	                            } catch (NumberFormatException a) {
	      	  	                                JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	      	  	                                        "Error", JOptionPane.ERROR_MESSAGE);
	      	  	                            }
	      	  	                        }
	      	  	                    }
	      	  	                    case "Sofa" -> {
	      	  	                       JTextField capacidadDeAsientosF = new JTextField();

	      	  	                       JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
	      	  	                       panel.add(new JLabel("Capacidad de Asientos:"));
	      	  	                       panel.add(capacidadDeAsientosF);

	      	  	                       int result = JOptionPane.showConfirmDialog(null, panel,
	      	  	                               "Ingrese los datos para Sofá", JOptionPane.OK_CANCEL_OPTION);

	      	  	                       if (result == JOptionPane.OK_OPTION) {
	      	  	                           try {
	      	  	                               int capacidadDeAsientos = Integer.parseInt(capacidadDeAsientosF.getText());
	      	  	                               System.out.println("Sofá creado con capacidad de asientos: " + capacidadDeAsientos);
	      	  	                               BD.insertarSofa(id, numProductos, peso, precio, material, color, descripcion, rutaImagen, capacidadDeAsientos);
	      	  	                           } catch (NumberFormatException a) {
	      	  	                               JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	      	  	                                       "Error", JOptionPane.ERROR_MESSAGE);
	      	  	                           }
	      	  	                       }
	      	  	                   }
	      	  	                  case "Mesa" -> {
	      	                            JTextField alturaF = new JTextField();
	      	                            JTextField capacidadF = new JTextField();

	      	                            JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
	      	                            panel.add(new JLabel("Altura:"));
	      	                            panel.add(alturaF);
	      	                            panel.add(new JLabel("Capacidad:"));
	      	                            panel.add(capacidadF);

	      	                            int result = JOptionPane.showConfirmDialog(null, panel,
	      	                                    "Ingrese los datos para Mesa", JOptionPane.OK_CANCEL_OPTION);

	      	                            if (result == JOptionPane.OK_OPTION) {
	      	                                try {
	      	                                    double altura = Double.parseDouble(alturaF.getText());
	      	                                    int capacidad = Integer.parseInt(capacidadF.getText());
	      	                                    System.out.println("Mesa creada con altura: " + altura + ", capacidad: " + capacidad);
	      	                                    BD.insertarMesa(id, numProductos, peso, precio, material, color, descripcion, rutaImagen, altura, capacidad);
	      	                                } catch (NumberFormatException a) {
	      	                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	      	                                            "Error", JOptionPane.ERROR_MESSAGE);
	      	                                }
	      	                            }
	      	                        }
	                                    }
	                         } else if (opcionSeleccionada.equals("Heramienta") || opcionSeleccionada.equals("Barbacoa") ||
	                                    opcionSeleccionada.equals("Planta") || opcionSeleccionada.equals("Maceta")) {

	                                    // Solicitar datos genéricos de Mueble
	                                    JTextField materialF = new JTextField();
	                                    JTextField esExteriorF = new JTextField();
	                                    JTextField descripcionF = new JTextField();
	                                    JTextField rutaImagenF = new JTextField();

	                                    JPanel mueblePanel = new JPanel(new GridLayout(4, 2, 5, 5));
	                                    mueblePanel.add(new JLabel("Material:"));
	                                    mueblePanel.add(materialF);
	                                    mueblePanel.add(new JLabel("Es Exterior(si/no):"));
	                                    mueblePanel.add(esExteriorF);
	                                    mueblePanel.add(new JLabel("Descripción:"));
	                                    mueblePanel.add(descripcionF);
	                                    mueblePanel.add(new JLabel("Ruta de Imagen:"));
	                                    mueblePanel.add(rutaImagenF);

	                                    int muebleResult = JOptionPane.showConfirmDialog(null, mueblePanel,
	                                            "Ingrese los datos generales del Jardineria", JOptionPane.OK_CANCEL_OPTION);

	                                    if (muebleResult != JOptionPane.OK_OPTION) {
	                                        return; // Si cancela, salir del flujo
	                                    }

	                                    String material = materialF.getText();
	                                    boolean esExterior = parseBoolean(esExteriorF.getText());;
	                                    String descripcion = descripcionF.getText();
	                                    String rutaImagen = rutaImagenF.getText();
	                                    
	                                    switch (opcionSeleccionada) {
	       	                         case "Barbacoa" -> {
	       	                            boolean validInput = false;
	       	                            while (!validInput) {
	       	                                JTextField tipoCombustibleF = new JTextField();
	       	                                JTextField superficieCoccionF = new JTextField();
	       	                                JTextField tieneTapaF = new JTextField();

	       	                                JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	       	                                panel.add(new JLabel("Tipo de Combustible:"));
	       	                                panel.add(tipoCombustibleF);
	       	                                panel.add(new JLabel("Superficie de Cocción:"));
	       	                                panel.add(superficieCoccionF);
	       	                                panel.add(new JLabel("Tiene Tapa (Sí/No):"));
	       	                                panel.add(tieneTapaF);

	       	                                int result = JOptionPane.showConfirmDialog(null, panel,
	       	                                        "Ingrese los datos para Barbacoa", JOptionPane.OK_CANCEL_OPTION);
	       	                                if (result == JOptionPane.OK_OPTION) {
	       	                                    try {
	       	                                        String tipoCombustible = tipoCombustibleF.getText();
	       	                                        double superficieCoccion = Double.parseDouble(superficieCoccionF.getText());
	       	                                        boolean tieneTapa = parseBoolean(tieneTapaF.getText());
	       	                                        System.out.println("Barbacoa creada con combustible: " + tipoCombustible +
	       	                                                ", superficie: " + superficieCoccion + ", tiene tapa: " + tieneTapa);
	       	                                        validInput = true; // Los datos son válidos, salimos del bucle
	       	                                        BD.insertarBarbacoa(id, numProductos, peso, precio, opcionSeleccionada, material, descripcion, rutaImagen, tipoCombustible, superficieCoccion, tipoCombustible);
	       	                                    } catch (NumberFormatException a) {
	       	                                        JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	       	                                                "Error", JOptionPane.ERROR_MESSAGE);
	       	                                    }
	       	                                } else {
	       	                                    // Si el usuario cancela, salimos del bucle sin procesar más
	       	                                    break;
	       	                                }
	       	                            }
	       	                        }
	       	                         case "Herramienta" -> {
	       		                         JTextField tipoF = new JTextField();

	       		                         JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
	       		                         panel.add(new JLabel("Tipo:"));
	       		                         panel.add(tipoF);

	       		                         int result = JOptionPane.showConfirmDialog(null, panel,
	       		                                 "Ingrese los datos para Herramienta", JOptionPane.OK_CANCEL_OPTION);

	       		                         if (result == JOptionPane.OK_OPTION) {
	       		                             try {
	       		                                 String tipo = tipoF.getText();
	       		                                 System.out.println("Herramienta creada con tipo: " + tipo);
	       		                                 BD.insertarHerramienta(id, numProductos, peso, precio, opcionSeleccionada, material, descripcion, rutaImagen, tipo);
	       		                             } catch (Exception a) {
	       		                                 JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	       		                                         "Error", JOptionPane.ERROR_MESSAGE);
	       		                             }
	       		                         }
	       		                     }
	       	                         case "Maceta" -> {
	       	                        	 JTextField diametroF = new JTextField();

	       	                            JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
	       	                            panel.add(new JLabel("Diámetro:"));
	       	                            panel.add(diametroF);

	       	                            int result = JOptionPane.showConfirmDialog(null, panel,
	       	                                    "Ingrese los datos para Maceta", JOptionPane.OK_CANCEL_OPTION);

	       	                            if (result == JOptionPane.OK_OPTION) {
	       	                                try {
	       	                                    double diametro = Double.parseDouble(diametroF.getText());
	       	                                    System.out.println("Maceta creada con diámetro: " + diametro);
	       	                                    BD.insertarMaceta(id, numProductos, peso, precio, opcionSeleccionada, material, descripcion, rutaImagen, diametro);
	       	                                } catch (NumberFormatException a) {
	       	                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	       	                                            "Error", JOptionPane.ERROR_MESSAGE);
	       	                                }
	       	                            }
	       	                        }
	       	                         case "Planta" -> {
	       		                         JTextField alturaF = new JTextField();
	       		                         JTextField tipoDePlantaF = new JTextField();
	       		                         JTextField diametroF = new JTextField();

	       		                         JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	       		                         panel.add(new JLabel("Altura:"));
	       		                         panel.add(alturaF);
	       		                         panel.add(new JLabel("Tipo de Planta:"));
	       		                         panel.add(tipoDePlantaF);
	       		                         panel.add(new JLabel("Diámetro:"));
	       		                         panel.add(diametroF);

	       		                         int result = JOptionPane.showConfirmDialog(null, panel,
	       		                                 "Ingrese los datos para Planta", JOptionPane.OK_CANCEL_OPTION);

	       		                         if (result == JOptionPane.OK_OPTION) {
	       		                             try {
	       		                                 double altura = Double.parseDouble(alturaF.getText());
	       		                                 String tipoDePlanta = tipoDePlantaF.getText();
	       		                                 double diametro = Double.parseDouble(diametroF.getText());
	       		                                 System.out.println("Planta creada con altura: " + altura + ", tipo: " + tipoDePlanta +
	       		                                         ", diámetro: " + diametro);
	       		                                 BD.insertarPlanta(id, numProductos, peso, precio, opcionSeleccionada, material, descripcion, rutaImagen, altura, tipoDePlanta, diametro);
	       		                             } catch (NumberFormatException a) {
	       		                                 JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	       		                                         "Error", JOptionPane.ERROR_MESSAGE);
	       		                             }
	       		                         }
	       		                     }
	                                 }
	                         }  else if (opcionSeleccionada.equals("Bide") || opcionSeleccionada.equals("Lavamanos") ||
	                                    opcionSeleccionada.equals("Ducha") || opcionSeleccionada.equals("Inodoro")) {

	                                    // Solicitar datos genéricos de Mueble
	                                    JTextField materialF = new JTextField();
	                                    JTextField esExteriorF = new JTextField();
	                                    JTextField descripcionF = new JTextField();
	                                    JTextField rutaImagenF = new JTextField();

	                                    JPanel mueblePanel = new JPanel(new GridLayout(3, 2, 5, 5));
	                                    mueblePanel.add(new JLabel("Material:"));
	                                    mueblePanel.add(materialF);
	                                    mueblePanel.add(new JLabel("Descripción:"));
	                                    mueblePanel.add(descripcionF);
	                                    mueblePanel.add(new JLabel("Ruta de Imagen:"));
	                                    mueblePanel.add(rutaImagenF);

	                                    int muebleResult = JOptionPane.showConfirmDialog(null, mueblePanel,
	                                            "Ingrese los datos generales del Baño", JOptionPane.OK_CANCEL_OPTION);

	                                    if (muebleResult != JOptionPane.OK_OPTION) {
	                                        return; // Si cancela, salir del flujo
	                                    }

	                                    String material = materialF.getText();
	                                    String descripcion = descripcionF.getText();
	                                    String rutaImagen = rutaImagenF.getText();
	                                    
	                                    switch (opcionSeleccionada) {
	                                    case "Bidé" -> {
	         	                           boolean validInput = false;
	         	                           while (!validInput) {
	         	                               JTextField tieneCalefaccionF = new JTextField();
	         	                               JTextField esElectricoF = new JTextField();

	         	                               JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
	         	                               panel.add(new JLabel("Tiene Calefacción (Sí/No):"));
	         	                               panel.add(tieneCalefaccionF);
	         	                               panel.add(new JLabel("Es Eléctrico (Sí/No):"));
	         	                               panel.add(esElectricoF);

	         	                               int result = JOptionPane.showConfirmDialog(null, panel,
	         	                                       "Ingrese los datos para Bidé", JOptionPane.OK_CANCEL_OPTION);
	         	                               if (result == JOptionPane.OK_OPTION) {
	         	                                   try {
	         	                                       boolean tieneCalefaccion = parseBoolean(tieneCalefaccionF.getText());
	         	                                       boolean esElectrico = parseBoolean(esElectricoF.getText());
	         	                                       validInput = true;
	         	                                       BD.insertarBide(id, numProductos, peso, precio, material, descripcion, rutaImagen, descripcion, rutaImagen);
	         	                                   } catch (IllegalArgumentException a) {
	         	                                       JOptionPane.showMessageDialog(null, "Por favor, ingrese 'Sí' o 'No'.",
	         	                                               "Error", JOptionPane.ERROR_MESSAGE);
	         	                                   }
	         	                               } else {
	         	                                   break;
	         	                               }
	         	                           }
	         	                       }
	         	                          
	         	                         case "Ducha" -> {
	         	                            boolean validInput = false;
	         	                            while (!validInput) {
	         	                                JTextField tipoRociadorF = new JTextField();
	         	                                JTextField tieneMamparaF = new JTextField();

	         	                                JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
	         	                                panel.add(new JLabel("Tipo de Rociador:"));
	         	                                panel.add(tipoRociadorF);
	         	                                panel.add(new JLabel("Tiene Mampara (Sí/No):"));
	         	                                panel.add(tieneMamparaF);

	         	                                int result = JOptionPane.showConfirmDialog(null, panel,
	         	                                        "Ingrese los datos para Ducha", JOptionPane.OK_CANCEL_OPTION);
	         	                                if (result == JOptionPane.OK_OPTION) {
	         	                                    try {
	         	                                        String tipoRociador = tipoRociadorF.getText();
	         	                                        boolean tieneMampara = parseBoolean(tieneMamparaF.getText());
	         	                                        validInput = true;
	         	                                        BD.insertarDucha(id, numProductos, peso, precio, material, descripcion, rutaImagen, tipoRociador, tipoRociador);
	         	                                    } catch (IllegalArgumentException a) {
	         	                                        JOptionPane.showMessageDialog(null, "Por favor, ingrese 'Sí' o 'No' para Mampara.",
	         	                                                "Error", JOptionPane.ERROR_MESSAGE);
	         	                                    }
	         	                                } else {
	         	                                    break;
	         	                                }
	         	                            }
	         	                        }
	         	                         case "Inodoro" -> {
	         		                            boolean validInput = false;
	         		                            while (!validInput) {
	         		                                JTextField tipoDescargaF = new JTextField();
	         		                                JTextField tieneAsientoCalefaccionadoF = new JTextField();

	         		                                JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
	         		                                panel.add(new JLabel("Tipo de Descarga:"));
	         		                                panel.add(tipoDescargaF);
	         		                                panel.add(new JLabel("Tiene Asiento Calefaccionado (Sí/No):"));
	         		                                panel.add(tieneAsientoCalefaccionadoF);

	         		                                int result = JOptionPane.showConfirmDialog(null, panel,
	         		                                        "Ingrese los datos para Inodoro", JOptionPane.OK_CANCEL_OPTION);

	         		                                if (result == JOptionPane.OK_OPTION) {
	         		                                    try {
	         		                                        String tipoDescarga = tipoDescargaF.getText();
	         		                                        boolean tieneAsientoCalefaccionado = parseBoolean(tieneAsientoCalefaccionadoF.getText());
	         		                                        System.out.println("Inodoro creado con tipo de descarga: " + tipoDescarga +
	         		                                                ", asiento calefaccionado: " + tieneAsientoCalefaccionado);
	         		                                        validInput = true;
	         		                                        BD.insertarInodoro(id, numProductos, peso, precio, material, descripcion, rutaImagen, tipoDescarga, tipoDescarga);
	         		                                    } catch (IllegalArgumentException a) {
	         		                                        JOptionPane.showMessageDialog(null, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	         		                                    }
	         		                                } else {
	         		                                    validInput = true; // Cerrar ventana
	         		                                }
	         		                            }
	         	                         }
	                                    }
	         	                          
	                         } else if (opcionSeleccionada.equals("Nevera") || opcionSeleccionada.equals("Horno") ||
	                                    opcionSeleccionada.equals("Fregadero") || opcionSeleccionada.equals("Encimera")) {

	                                    // Solicitar datos genéricos de Mueble
	                                    JTextField materialF = new JTextField();
	                                    JTextField esExteriorF = new JTextField();
	                                    JTextField descripcionF = new JTextField();
	                                    JTextField rutaImagenF = new JTextField();

	                                    JPanel mueblePanel = new JPanel(new GridLayout(4, 2, 5, 5));
	                                    mueblePanel.add(new JLabel("Material:"));
	                                    mueblePanel.add(materialF);
	                                    mueblePanel.add(new JLabel("Color:"));
	                                    mueblePanel.add(esExteriorF);
	                                    mueblePanel.add(new JLabel("Descripción:"));
	                                    mueblePanel.add(descripcionF);
	                                    mueblePanel.add(new JLabel("Ruta de Imagen:"));
	                                    mueblePanel.add(rutaImagenF);

	                                    int muebleResult = JOptionPane.showConfirmDialog(null, mueblePanel,
	                                            "Ingrese los datos generales del Cocina", JOptionPane.OK_CANCEL_OPTION);

	                                    if (muebleResult != JOptionPane.OK_OPTION) {
	                                        return; // Si cancela, salir del flujo
	                                    }

	                                    String material = materialF.getText();
	                                    String esExterior = esExteriorF.getText();
	                                    String descripcion = descripcionF.getText();
	                                    String rutaImagen = rutaImagenF.getText();
	                         
	                            
	                          
	                              
	                        switch (opcionSeleccionada) {
	                        
	                        case "Encimera" -> {
	                           boolean validInput = false;
	                           while (!validInput) {
	                               JTextField resistenciaCalorF = new JTextField();
	                               JTextField grosorF = new JTextField();
	                               JTextField colorF = new JTextField();

	                               JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	                               panel.add(new JLabel("Resistencia al Calor (Sí/No):"));
	                               panel.add(resistenciaCalorF);
	                               panel.add(new JLabel("Grosor:"));
	                               panel.add(grosorF);
	                               panel.add(new JLabel("Color:"));
	                               panel.add(colorF);

	                               int result = JOptionPane.showConfirmDialog(null, panel,
	                                       "Ingrese los datos para Encimera", JOptionPane.OK_CANCEL_OPTION);
	                               if (result == JOptionPane.OK_OPTION) {
	                                   try {
	                                       boolean resistenciaCalor = parseBoolean(resistenciaCalorF.getText());
	                                       String grosor = grosorF.getText();
	                                       String color = colorF.getText();
	                                       System.out.println("Encimera creada con resistencia al calor: " + resistenciaCalor +
	                                               ", grosor: " + grosor + ", color: " + color);
	                                       validInput = true;
	                                       BD.insertarEncimera(id, numProductos, peso, precio, material, descripcion, rutaImagen, color, grosor, color);
	                                   } catch (IllegalArgumentException a) {
	                                       JOptionPane.showMessageDialog(null, "Por favor, ingrese 'Sí' o 'No' para Resistencia al Calor.",
	                                               "Error", JOptionPane.ERROR_MESSAGE);
	                                   }
	                               } else {
	                                   break;
	                               }
	                           }
	                       }
	                       case "Fregadero" -> {
	                          boolean validInput = false;
	                          while (!validInput) {
	                              JTextField numCubetasF = new JTextField();
	                              JTextField profundidadF = new JTextField();
	                              JTextField grifoF = new JTextField();

	                              JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	                              panel.add(new JLabel("Número de Cubetas:"));
	                              panel.add(numCubetasF);
	                              panel.add(new JLabel("Profundidad:"));
	                              panel.add(profundidadF);
	                              panel.add(new JLabel("Grifo (Si/No):"));
	                              panel.add(grifoF);

	                              int result = JOptionPane.showConfirmDialog(null, panel,
	                                      "Ingrese los datos para Fregadero", JOptionPane.OK_CANCEL_OPTION);
	                              if (result == JOptionPane.OK_OPTION) {
	                                  try {
	                                      int numCubetas = Integer.parseInt(numCubetasF.getText());
	                                      double profundidad = Double.parseDouble(profundidadF.getText());
	                                      Boolean grifo = parseBoolean(grifoF.getText());
	                                      System.out.println("Fregadero creado con " + numCubetas +
	                                              " cubetas, profundidad: " + profundidad + ", grifo: " + grifo);
	                                      validInput = true;
	                                      BD.insertarFregadero(id, numProductos, peso, precio, material, descripcion, rutaImagen, numCubetas, profundidad, rutaImagen);
	                                  } catch (NumberFormatException a) {
	                                      JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos para Cubetas o Profundidad.",
	                                              "Error", JOptionPane.ERROR_MESSAGE);
	                                  }
	                              } else {
	                                  break;
	                              }
	                          }
	                      }
	                      
	                         case "Horno" -> {
	                            JTextField alturaF = new JTextField();
	                            JTextField anchuraF = new JTextField();
	                            JTextField profundidadF = new JTextField();
	                            JTextField potenciaF = new JTextField();
	                            JTextField numeroBandejasF = new JTextField();

	                            JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
	                            panel.add(new JLabel("Altura:"));
	                            panel.add(alturaF);
	                            panel.add(new JLabel("Anchura:"));
	                            panel.add(anchuraF);
	                            panel.add(new JLabel("Profundidad:"));
	                            panel.add(profundidadF);
	                            panel.add(new JLabel("Potencia:"));
	                            panel.add(potenciaF);
	                            panel.add(new JLabel("Número de Bandejas:"));
	                            panel.add(numeroBandejasF);

	                            int result = JOptionPane.showConfirmDialog(null, panel,
	                                    "Ingrese los datos para Horno", JOptionPane.OK_CANCEL_OPTION);

	                            if (result == JOptionPane.OK_OPTION) {
	                                try {
	                                    double altura = Double.parseDouble(alturaF.getText());
	                                    double anchura = Double.parseDouble(anchuraF.getText());
	                                    double profundidad = Double.parseDouble(profundidadF.getText());
	                                    int potencia = Integer.parseInt(potenciaF.getText());
	                                    int numeroBandejas = Integer.parseInt(numeroBandejasF.getText());
	                                    System.out.println("Horno creado con altura: " + altura + ", anchura: " + anchura +
	                                            ", profundidad: " + profundidad + ", potencia: " + potencia +
	                                            ", número de bandejas: " + numeroBandejas);
	                                    BD.insertarHorno(id, numProductos, peso, precio, material, descripcion, rutaImagen, altura, anchura, profundidad, potencia, numeroBandejas);
	                                } catch (NumberFormatException a) {
	                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	                                            "Error", JOptionPane.ERROR_MESSAGE);
	                                }
	                            }
	                        }
	                          
	                         
	                          
	                       case "Nevera" -> {
	                          JTextField alturaF = new JTextField();
	                          JTextField anchuraF = new JTextField();
	                          JTextField profundidadF = new JTextField();
	                          JTextField capacidadF = new JTextField();
	                          JTextField tipoNeveraF = new JTextField();

	                          JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
	                          panel.add(new JLabel("Altura:"));
	                          panel.add(alturaF);
	                          panel.add(new JLabel("Anchura:"));
	                          panel.add(anchuraF);
	                          panel.add(new JLabel("Profundidad:"));
	                          panel.add(profundidadF);
	                          panel.add(new JLabel("Capacidad:"));
	                          panel.add(capacidadF);
	                          panel.add(new JLabel("Tipo de Nevera:"));
	                          panel.add(tipoNeveraF);

	                          int result = JOptionPane.showConfirmDialog(null, panel,
	                                  "Ingrese los datos para Nevera", JOptionPane.OK_CANCEL_OPTION);

	                          if (result == JOptionPane.OK_OPTION) {
	                              try {
	                                  double altura = Double.parseDouble(alturaF.getText());
	                                  double anchura = Double.parseDouble(anchuraF.getText());
	                                  double profundidad = Double.parseDouble(profundidadF.getText());
	                                  double capacidad = Double.parseDouble(capacidadF.getText());
	                                  String tipoNevera = tipoNeveraF.getText();
	                                  System.out.println("Nevera creada con altura: " + altura + ", anchura: " + anchura +
	                                          ", profundidad: " + profundidad + ", capacidad: " + capacidad +
	                                          ", tipo: " + tipoNevera);
	                                  BD.insertarNevera(id, numProductos, peso, precio, material, descripcion, rutaImagen, altura, anchura, profundidad, capacidad, tipoNevera);
	                              } catch (NumberFormatException a) {
	                                  JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.",
	                                          "Error", JOptionPane.ERROR_MESSAGE);
	                              }
	                          }
	                      }
	                      		                          
	                        // Código para opciones no esperadas
	                          }
	                      }
	                            	
	                        } else {
	                            JOptionPane.showMessageDialog(null, "Cancelaste el ingreso de datos.");
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Cancelaste la selección.");
	                    }
	                } catch (Exception ex) {
	                    System.err.println("Error al manejar el evento de teclado:");
	                    ex.printStackTrace();
	                }
	            }
	        }
	    });
	    panelDerecho.repaint();
	}
  		        
  		  }
private boolean parseBoolean(String input) throws IllegalArgumentException {
   if (input.equalsIgnoreCase("Sí")) {
       return true; // "Sí" se convierte a true
   } else if (input.equalsIgnoreCase("No")) {
       return false; // "No" se convierte a false
   } else {
       throw new IllegalArgumentException("Entrada inválida para boolean."); // Manejo de error si no es válido
   }
}
   
}

