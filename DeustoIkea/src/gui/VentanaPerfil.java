package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPerfil extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras;
	protected JPanel panelPrincipal, panelBotones;
	
	public VentanaPerfil() {
        setTitle("Perfil");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
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
