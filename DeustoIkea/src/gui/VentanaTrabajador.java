package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class VentanaTrabajador extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonCerrar;
	protected JPanel panelPrincipal, panelBotones;
	
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
        
        botonAtras = new JButton("ATRAS");
        botonCerrar = new JButton("CERRAR");
        
        panelBotones = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        add(panelPrincipal);
        
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaInicioTrabajador();
		});
        
        botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
        
        
        setVisible(true);
    }
}