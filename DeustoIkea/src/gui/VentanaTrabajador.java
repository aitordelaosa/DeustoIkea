package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import domain.BD;
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
        "src/Imagenes/mesa.jpeg",//Regular
        "src/Imagenes/silla.jpeg",//Regular
        "src/Imagenes/Armario.jpeg",//Regular
        "src/Imagenes/sofa.jpeg",//Regular
        "src/Imagenes/lavamanos.jpg",
        "src/Imagenes/ducha.jpg",
        "src/Imagenes/bide.jpeg",//Regular
        "src/Imagenes/inodoro.jpg",
        "src/Imagenes/Maceta.jpg",
        "src/Imagenes/adelfas-colores.jpg",//Regular
        "src/Imagenes/Nevera.jpg",
        "src/Imagenes/horno.jpg",
        "src/Imagenes/encimera.jpg",
        "src/Imagenes/fregadero.jpg"
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
 	     
 	     
 	 

 	     panelDerecho.setLayout(new BorderLayout());
 	     panelDerecho.removeAll();
 	     panelDerecho.add(scrollPane, BorderLayout.CENTER);
 	     panelDerecho.revalidate();
 	     panelDerecho.repaint();
 	  		        
 	  		  }
	   
   
}

