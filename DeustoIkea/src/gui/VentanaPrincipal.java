package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import domain.Armario;
import domain.Datos;
import domain.Mesa;
import domain.Silla;
import domain.Sofa;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JPanel panelPrincipal, panelSeccionCocina, panelSeccionMuebles, panelSeccionBaño, panelSeccionJardineria, panelAbajo, panelSuperior, panelSuperiorOeste, panelSuperiorEste;
    protected JLabel lblCocina, lblMuebles, lblBaño, lblJardineria, lblDescripcion;
    protected JButton botonCerrar, botonAtras, botonPerfil, botonCarrito, botonAyuda, botonDescuentos;
    
    protected ModeloMuebles modeloTablaMuebles;
    protected JTable tablaMuebles;
    protected JScrollPane scrollTablaMuebles;
    
    protected Datos datos;

    public VentanaPrincipal() {
        setTitle("Ventana Principal - DeustoIkea");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        botonCerrar = new JButton("CERRAR");
        botonAtras = new JButton("ATRAS");
        
        
        
        ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoAyuda = new ImageIcon(new ImageIcon("src/Imagenes/ayuda1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoDescuentos = new ImageIcon(new ImageIcon("src/Imagenes/cupon.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        
        botonPerfil = new JButton(iconoPerfil);
        botonCarrito = new JButton(iconoCarrito);
        botonAyuda = new JButton(iconoAyuda);
        botonDescuentos = new JButton(iconoDescuentos);

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperiorEste = new JPanel();
        panelSuperiorOeste = new JPanel();
        
        JPanel panelBotonesPerfilCarrito = new JPanel(new BorderLayout());
        panelSuperiorEste.add(botonCarrito);
        panelSuperiorOeste.add(botonPerfil);
        panelSuperiorOeste.add(botonAyuda);
        panelSuperiorOeste.add(botonDescuentos);
        panelBotonesPerfilCarrito.add(panelSuperiorEste, BorderLayout.EAST);
        panelBotonesPerfilCarrito.add(panelSuperiorOeste, BorderLayout.WEST);
        panelSuperior.add(panelBotonesPerfilCarrito, BorderLayout.NORTH);

        panelPrincipal = new JPanel(new GridLayout(2, 2, 20, 20)); // 2x2 grid con espacio de 20px entre secciones
        
        panelSeccionCocina = new JPanel();
        panelSeccionMuebles = new JPanel();
        panelSeccionBaño = new JPanel();
        panelSeccionJardineria = new JPanel();
        panelAbajo = new JPanel();
        
        ImageIcon cocina = new ImageIcon(new ImageIcon("src/Imagenes/Cocina1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon muebles = new ImageIcon(new ImageIcon("src/Imagenes/Muebles1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon baño = new ImageIcon(new ImageIcon("src/Imagenes/baño.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon jardineria = new ImageIcon(new ImageIcon("src/Imagenes/Jardineria.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon cocina = new ImageIcon(new ImageIcon("src/Imagenes/cocina.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon muebles = new ImageIcon(new ImageIcon("src/Imagenes/muebles.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon otro1 = new ImageIcon(new ImageIcon("src/Imagenes/otro1.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon otro2 = new ImageIcon(new ImageIcon("src/Imagenes/otro2.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

        lblCocina = new JLabel(cocina);
        lblMuebles = new JLabel(muebles);
        lblBaño = new JLabel(baño);
        lblJardineria = new JLabel(jardineria);
        
        panelSeccionCocina.add(lblCocina);
        panelSeccionMuebles.add(lblMuebles);
        panelSeccionBaño.add(lblBaño);
        panelSeccionJardineria.add(lblJardineria);
        
        JLabel lblTextoCocina = new JLabel("Cocina");
        JLabel lblTextoMuebles = new JLabel("Muebles");
        JLabel lblTextoBaño = new JLabel("Baño");
        JLabel lblTextoJardineria = new JLabel("Jardinería");

        
        lblTextoCocina.setForeground(Color.BLACK);
        lblTextoCocina.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoCocina.setBounds(50, 80, 150, 30);

        lblTextoMuebles.setForeground(Color.BLACK);
        lblTextoMuebles.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoMuebles.setBounds(50, 80, 150, 30);

        lblTextoBaño.setForeground(Color.BLACK);
        lblTextoBaño.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoBaño.setBounds(50, 80, 150, 30);

        lblTextoJardineria.setForeground(Color.BLACK);
        lblTextoJardineria.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoJardineria.setBounds(50, 80, 150, 30);

       
        
        panelSeccionCocina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelSeccionCocina.add(lblTextoCocina);
                panelSeccionCocina.repaint();  
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelSeccionCocina.remove(lblTextoCocina);
                panelSeccionCocina.repaint();  
            }
            public void mouseClicked(MouseEvent e) {
//            	new VentanaDespuesPrincipalPrueba(2);
            	new VentanaPostPrincipal(2);
//                new VentanaCocina();
                dispose();
            }
        });

        panelSeccionMuebles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelSeccionMuebles.add(lblTextoMuebles);
                panelSeccionMuebles.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelSeccionMuebles.remove(lblTextoMuebles);
                panelSeccionMuebles.repaint();
            }
            public void mouseClicked(MouseEvent e) {
//              new VentanaMuebles();
//          	new VentanaDespuesPrincipalPrueba(1);
          	new VentanaPostPrincipal(1);
              dispose();
          }
        });

        panelSeccionBaño.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelSeccionBaño.add(lblTextoBaño);
                panelSeccionBaño.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelSeccionBaño.remove(lblTextoBaño);
                panelSeccionBaño.repaint();
            }
            public void mouseClicked(MouseEvent e) {
//                new VentanaDespuesPrincipalPrueba(3);
                new VentanaPostPrincipal(3);
            }
        });

        panelSeccionJardineria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelSeccionJardineria.add(lblTextoJardineria);
                panelSeccionJardineria.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelSeccionJardineria.remove(lblTextoJardineria);
                panelSeccionJardineria.repaint();
            }
            public void mouseClicked(MouseEvent e) {
//            	new VentanaDespuesPrincipalPrueba(4);
            	new VentanaPostPrincipal(4);
            }
        });
        
        panelAbajo.add(botonAtras);
        panelAbajo.add(botonCerrar);
        
        panelPrincipal.add(panelSeccionCocina);
        panelPrincipal.add(panelSeccionMuebles);
        panelPrincipal.add(panelSeccionBaño);
        panelPrincipal.add(panelSeccionJardineria);
        
//        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
//        getContentPane().add(panelPrincipal, BorderLayout.NORTH);
//        getContentPane().add(panelArriba, BorderLayout.NORTH);
        
//        
        
        
        botonCerrar.addActionListener((e)-> {
			System.exit(0);
		});
        
        botonAtras.addActionListener((e) -> {
        	dispose();
        	new VentanaDeCarga();
        });
        
        botonPerfil.addActionListener((e) -> {
        	dispose();
            new VentanaPerfil();
        });

        botonCarrito.addActionListener((e) -> {
        	dispose();
            new VentanaCarrito();
        });
        
        botonDescuentos.addActionListener((e) -> {
        	dispose();
        	new VentanaDescuentos();
      });
        
        botonAyuda.addActionListener((e) -> {
        	dispose();
        	new VentanaAyuda();
      });
        
        Datos datos = new Datos();
        Mesa mesa = datos.getMesa();
        Silla silla = datos.getSilla();
        Armario armario = datos.getArmario();
        Sofa sofa = datos.getSofa();
        
        lblDescripcion = new JLabel();
        lblDescripcion.setText("<html><b>Algunos de nuestros muebles más destacados: </b></html>");
        lblDescripcion.setHorizontalAlignment(JLabel.CENTER);
//        add(lblDescripcion, BorderLayout.CENTER);
        JPanel panelDescripcion = new JPanel(new BorderLayout());
        panelDescripcion.add(lblDescripcion, BorderLayout.NORTH);
        
        modeloTablaMuebles = new ModeloMuebles(null);		
		tablaMuebles = new JTable(modeloTablaMuebles);
		tablaMuebles.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
		tablaMuebles.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaMuebles.getColumnModel().getColumn(2).setCellRenderer(new TableRenderer());
		tablaMuebles.getColumnModel().getColumn(1).setCellRenderer(new TableRenderer());
		tablaMuebles.setRowHeight(100);
		scrollTablaMuebles = new JScrollPane(tablaMuebles);
		panelDescripcion.add(scrollTablaMuebles, BorderLayout.CENTER);
//		panelDescripcion.add(tablaMuebles, BorderLayout.CENTER);
//		add(scrollTablaMuebles, BorderLayout.CENTER);
//		add(panelDescripcion, BorderLayout.CENTER);
		
		//Panel contenedor para todo
        JPanel panelContenedor = new JPanel(new BorderLayout());
        panelSuperior.add(panelPrincipal, BorderLayout.CENTER);
        panelContenedor.add(panelSuperior, BorderLayout.NORTH);
        panelContenedor.add(panelAbajo, BorderLayout.SOUTH);
	    panelContenedor.add(panelDescripcion, BorderLayout.CENTER);
	    JScrollPane scrollPane = new JScrollPane(panelContenedor);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
        
		modeloTablaMuebles.addProducto(mesa);
		modeloTablaMuebles.addProducto(silla);
		modeloTablaMuebles.addProducto(armario);
		modeloTablaMuebles.addProducto(sofa);
		
		panelContenedor.setFocusable(true);
        panelContenedor.requestFocusInWindow();
        panelContenedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
                    dispose();
                    new VentanaDeCarga();
                }
            }
        });
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
}