package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

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

    public VentanaTrabajador() {
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
        
        panelDerecho = new JPanel();
        panelDerecho.setPreferredSize(new Dimension(600, altoP));
        panelDerecho.setBackground(Color.WHITE);

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Funciones");
        DefaultMutableTreeNode clientes = new DefaultMutableTreeNode("Ver Clientes");
        DefaultMutableTreeNode trabajadores = new DefaultMutableTreeNode("Ver Trabajadores");
        DefaultMutableTreeNode eliminarClientes = new DefaultMutableTreeNode("Eliminar Clientes");
        DefaultMutableTreeNode actividad = new DefaultMutableTreeNode("Ver Actividad");
        DefaultMutableTreeNode muebles = new DefaultMutableTreeNode("Mostrar Muebles Disponibles");
        DefaultMutableTreeNode stock = new DefaultMutableTreeNode("Rellenar Stock");

        raiz.add(clientes);
        raiz.add(trabajadores);
        raiz.add(eliminarClientes);
        raiz.add(actividad);
        raiz.add(muebles);
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
            new VentanaInicioTrabajador();
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
            case "Ver Actividad":
                verActividad();
                break;
            case "Mostrar Muebles Disponibles":
                mostrarMueblesDisponibles();
                break;
            case "Rellenar Stock":
                rellenarStock();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Función no implementada");
        }
    }

    private void verClientes() {
        JOptionPane.showMessageDialog(this, "Mostrando clientes...");
    }

    private void verTrabajadores() {
        JOptionPane.showMessageDialog(this, "Mostrando trabajadores...");
    }

    private void eliminarClientes() {
        JOptionPane.showMessageDialog(this, "Eliminando clientes...");
    }

    private void verActividad() {
        JOptionPane.showMessageDialog(this, "Mostrando actividad...");
    }

    private void mostrarMueblesDisponibles() {
        JOptionPane.showMessageDialog(this, "Mostrando muebles disponibles...");
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
