package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

    protected JPanel panelPrincipal, panelSeccionCocina, panelSeccionMuebles, panelSeccionOtro1, panelSeccionOtro2, panelAbajo, panelSuperior, panelSuperiorOeste, panelSuperiorEste;
    protected JLabel lblCocina, lblMuebles, lblOtro1, lblOtro2, lblDescripcion;
    protected JButton botonCerrar, botonAtras, botonPerfil, botonCarrito, botonAyuda, botonDescuentos;
    
    protected ModeloMuebles modeloTablaMuebles;
    protected JTable tablaMuebles;
    protected JScrollPane scrollTablaMuebles;
    
    protected Datos datos;

    public VentanaPrincipal() {
        setTitle("Ventana Principal - DeustoIkea");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        panelSeccionOtro1 = new JPanel();
        panelSeccionOtro2 = new JPanel();
        panelAbajo = new JPanel();
        
        ImageIcon cocina = new ImageIcon(new ImageIcon("src/Imagenes/Cocina1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon muebles = new ImageIcon(new ImageIcon("src/Imagenes/Muebles1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon otro1 = new ImageIcon(new ImageIcon("src/Imagenes/app_icon_login_screen.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon otro2 = new ImageIcon(new ImageIcon("src/Imagenes/app_icon_login_screen.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon cocina = new ImageIcon(new ImageIcon("src/Imagenes/cocina.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon muebles = new ImageIcon(new ImageIcon("src/Imagenes/muebles.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon otro1 = new ImageIcon(new ImageIcon("src/Imagenes/otro1.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        ImageIcon otro2 = new ImageIcon(new ImageIcon("src/Imagenes/otro2.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

        lblCocina = new JLabel(cocina);
        lblMuebles = new JLabel(muebles);
        lblOtro1 = new JLabel(otro1);
        lblOtro2 = new JLabel(otro2);
        
        panelSeccionCocina.add(lblCocina);
        panelSeccionMuebles.add(lblMuebles);
        panelSeccionOtro1.add(lblOtro1);
        panelSeccionOtro2.add(lblOtro2);
        
        panelAbajo.add(botonAtras);
        panelAbajo.add(botonCerrar);
        
        panelPrincipal.add(panelSeccionCocina);
        panelPrincipal.add(panelSeccionMuebles);
        panelPrincipal.add(panelSeccionOtro1);
        panelPrincipal.add(panelSeccionOtro2);
        
//        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
//        getContentPane().add(panelPrincipal, BorderLayout.NORTH);
//        getContentPane().add(panelArriba, BorderLayout.NORTH);
        
        panelSeccionMuebles.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VentanaMuebles();
                dispose();
            }
        });
        
        panelSeccionCocina.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VentanaCocina();
                dispose();
            }
        });
//        
//        panelSeccionOtro1.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                new VentanaOtro1();
//            }
//        });
//        
//        panelSeccionOtro2.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                new VentanaOtro2();
//            }
//        });
        
        botonCerrar.addActionListener((e)-> {
			System.exit(0);
		});
        
        botonAtras.addActionListener((e) -> {
        	dispose();
        	new VentanaDeCarga();
        });
        
        botonPerfil.addActionListener((e) -> {
//        	dispose();
//            new VentanaPerfil();
        });

        botonCarrito.addActionListener((e) -> {
//        	dispose();
//            new VentanaCarrito();
        });
        
        botonDescuentos.addActionListener((e) -> {
        	dispose();
          new VentanaDescuentos();
      });
        
        botonAyuda.addActionListener((e) -> {
//        	dispose();
//          new VentanaAyuda();
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
		tablaMuebles.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
		tablaMuebles.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaMuebles.getColumnModel().getColumn(2).setCellRenderer(new TableRenderer());
		tablaMuebles.getColumnModel().getColumn(1).setCellRenderer(new TableRenderer());
		tablaMuebles.setRowHeight(100);
//		scrollTablaMuebles = new JScrollPane(tablaMuebles);
		panelDescripcion.add(tablaMuebles, BorderLayout.CENTER);
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

        setLocationRelativeTo(null);
        setVisible(true);
    }
}