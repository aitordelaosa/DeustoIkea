package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Armario;
import domain.Mesa;
import domain.Mueble;
import domain.Silla;
import domain.Sofa;

public class VentanaMuebles extends JFrame{
	protected JButton botonCerrar, botonSeleccionar, botonComprar;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelImagen;
	protected JLabel labelImagen, labelDescripcion;
	protected JTextField campoTexto;
	protected String[] muebles = { "Silla", "Mesa", "Armario", "Sofá" };
	protected JComboBox<String> comboDeMuebles;
	protected Mueble[] listaMuebles;
	
	public VentanaMuebles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("Muebles");
		
		botonCerrar = new JButton("CERRAR");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		panelCentroD = new JPanel();
		panelCentroI = new JPanel();
		panelArriba = new JPanel();
		panelImagen = new JPanel();
		 
		campoTexto = new JTextField(20);
		comboDeMuebles = new JComboBox<>(muebles);
		
		// Inicialización temporal de los muebles
        listaMuebles = new Mueble[] {
            new Silla(),
            new Mesa(),
            new Armario(),
            new Sofa()
        };
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonSeleccionar);
		panelAbajo.add(botonComprar);
		
		panelCentro.setLayout(new GridLayout(1, 2));
		panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);
        
        panelCentroD.add(campoTexto); //Donde ira el texto a cerca de nosotros y nuestros muebles
        panelCentroI.add(comboDeMuebles);
        
        labelImagen = new JLabel();
        labelDescripcion = new JLabel();
        panelImagen.setLayout(new GridLayout(2, 1));
        panelImagen.add(labelImagen);
        panelImagen.add(labelDescripcion);
	        
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);
		
		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
		
		campoTexto.setText("Bienvenido a DeustoIkea, tu tienda de muebles asequibles y modernos. Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");
		
		botonSeleccionar.addActionListener((e) -> {
            int indice = comboDeMuebles.getSelectedIndex();
            Mueble muebleSeleccionado = listaMuebles[indice];

            labelImagen.setIcon(muebleSeleccionado.getImagen());
            labelDescripcion.setText(muebleSeleccionado.getDescripcion());
            campoTexto.setText(muebleSeleccionado.getIdProducto());
        });
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
}