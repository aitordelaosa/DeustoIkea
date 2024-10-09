package Gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaMuebles extends JFrame{
	protected JButton botonCerrar, botonSeleccionar, botonComprar;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba;
	protected JTextField campoTexto;
	protected String[] muebles = { "Silla", "Mesa", "Armario", "Sofá" };
	protected JComboBox<String> comboDeMuebles;
	
	public VentanaMuebles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("Muebles");
		
		botonCerrar = new JButton("CERRAR");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");
//		botonCerrar = new JButton("CERRAR");
//		botonCerrar = new JButton("CERRAR");
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		panelCentroD = new JPanel();
		panelCentroI = new JPanel();
		panelArriba = new JPanel();
		
		campoTexto = new JTextField(10);
		comboDeMuebles = new JComboBox<>(muebles);
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonSeleccionar);
		panelAbajo.add(botonComprar);
		
		panelCentro.setLayout(new GridLayout(1, 2));
		panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);
        
        panelCentroD.add(campoTexto); //Donde ira el texto a cerca de nosotros y nuestros muebles
        panelCentroI.add(comboDeMuebles);
	        
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
}