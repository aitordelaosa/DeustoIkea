package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import domain.Baño;
import domain.Cliente;
import domain.Cocina;
import domain.Datos;
import domain.Jardineria;
import domain.Mueble;
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

        raiz.add(clientes);
        raiz.add(trabajadores);
        raiz.add(eliminarClientes);
        raiz.add(buscarTrabajador);
        raiz.add(muebles);
        raiz.add(cocina);
        raiz.add(jardin);
        raiz.add(baño);
        raiz.add(stock);

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

        iniciarHiloAnimacion();

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
            	Modelo modelo = new Modelo(new ArrayList<>(), Arrays.asList("TI1","Tit2"), Tipo.Baño);
            	JTable tabla = new JTable(modelo);
                mostrarBañoDisponibles();
                break;
            case "Mostrar elementos de Jardineria Disponibles":
                mostrarJardineriaDisponibles();
                break;
            case "Rellenar Stock":
                rellenarStock();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Función no implementada");
        }
    }

    private void verClientes() {
//        JOptionPane.showMessageDialog(this, "Mostrando clientes...");
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
//        JOptionPane.showMessageDialog(this, "Mostrando trabajadores...");
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
}

