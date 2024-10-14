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

    protected JPanel panelPrincipal, panelSeccionCocina, panelSeccionMuebles, panelSeccionOtro1, panelSeccionOtro2, panelAbajo;
    protected JLabel lblCocina, lblMuebles, lblOtro1, lblOtro2;
    protected JButton botonCerrar, botonAtras;
    
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

        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal, BorderLayout.NORTH);
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
        	new VentanaDeCarga();
        	dispose();
        });
        
        Datos datos = new Datos();
        Mesa mesa = datos.getMesa();
        Silla silla = datos.getSilla();
        Armario armario = datos.getArmario();
        Sofa sofa = datos.getSofa();
        
        modeloTablaMuebles = new ModeloMuebles(null);		
		tablaMuebles = new JTable(modeloTablaMuebles);
		tablaMuebles.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
		tablaMuebles.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaMuebles.setRowHeight(100);
		scrollTablaMuebles = new JScrollPane(tablaMuebles);
		add(scrollTablaMuebles, BorderLayout.CENTER);
        
		modeloTablaMuebles.addProducto(mesa);
		modeloTablaMuebles.addProducto(silla);
		modeloTablaMuebles.addProducto(armario);
		modeloTablaMuebles.addProducto(sofa);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}