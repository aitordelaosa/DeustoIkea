package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaAyuda extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras;
	protected JPanel panelPrincipal, panelBotones;
	
	public VentanaAyuda() {
        setTitle("Ayuda");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        botonAtras = new JButton("ATRAS");
        
        panelBotones = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelBotones.add(botonAtras);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        add(panelPrincipal);
        
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaPrincipal();
		});
        
        
        setVisible(true);
    }
}

