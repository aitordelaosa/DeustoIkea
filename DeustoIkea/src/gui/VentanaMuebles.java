package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class VentanaMuebles extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonCerrar, botonSeleccionar, botonComprar;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4;
	protected JTextArea areaTexto;
//	protected ArrayList<Mueble> listaMuebles;
	
	public VentanaMuebles() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Muebles");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		botonCerrar = new JButton("CERRAR");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		panelCentroD = new JPanel();
		panelCentroI = new JPanel();
		panelArriba = new JPanel(new BorderLayout());
		
        areaTexto = new JTextArea(3, 40);
        areaTexto.setLineWrap(true); // Permitir que el texto se ajuste a la línea
        areaTexto.setWrapStyleWord(true); // Ajustar solo en palabras completas
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de muebles asequibles y modernos.\n" +
                          "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");
        
		
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        scrollTexto.setBorder(null);  // Remover borde
        panelArriba.add(scrollTexto, BorderLayout.NORTH); 
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonSeleccionar);
		panelAbajo.add(botonComprar);
		
		panelCentro.setLayout(new GridLayout(1, 2));
		panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);
        
        panelCentroI.setLayout(new GridLayout(2, 2));
        panelCentroD.setLayout(new GridLayout(2, 2));
        
        ImageIcon sofa = new ImageIcon("src/Imagenes/sofa.jpeg");
        ImageIcon armario = new ImageIcon("src/Imagenes/Armario.jpeg");
        ImageIcon silla = new ImageIcon("src/Imagenes/silla.jpeg");
        ImageIcon mesa = new ImageIcon("src/Imagenes/mesa.jpeg");
        labelImagen1 = new JLabel(sofa);
        labelImagen2 = new JLabel(armario);
        labelImagen3 = new JLabel(silla);
        labelImagen4 = new JLabel(mesa);
        
        
        labelDescripcion1 = new JLabel();
        labelDescripcion2 = new JLabel();
        labelDescripcion3 = new JLabel();
        labelDescripcion4 = new JLabel();
        labelDescripcion1.setText("<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
        labelDescripcion2.setText("<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
        labelDescripcion3.setText("<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica con respaldo ajustable, perfecta para oficina o comedor.</html>");
        labelDescripcion4.setText("<html><b>Mesa</b><br>Precio: $150<br>Peso: 20kg<br>Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.</html>");
//        labelDescripcion4.setText("Mesa \n" + "Precio: $150 \n" + "Peso: 20kg \n" + "Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.");
        
        panelCentroI.add(labelImagen1);
        panelCentroI.add(labelDescripcion1);
        panelCentroI.add(labelImagen2);
        panelCentroI.add(labelDescripcion2);
        
        panelCentroD.add(labelImagen3);
        panelCentroD.add(labelDescripcion3);
        panelCentroD.add(labelImagen4);
        panelCentroD.add(labelDescripcion4);
	        
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);
          

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
				
//		botonSeleccionar.addActionListener((e) -> {
//            String categoriaSeleccionada = (String) comboDeMuebles.getSelectedItem();
//            mostrarMueblesSegunCategoria(categoriaSeleccionada);
//        });
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
//	private void mostrarMueblesSegunCategoria(String categoria) {
//        for (Mueble mueble : listaMuebles) {
//            if (categoria.equals("Todos") || mueble.getClass().getSimpleName().equals(categoria)) {
//                labelImagen.setIcon(mueble.getImagen());
//                labelDescripcion.setText(mueble.getDescripcion());
//                break;
//            }
//        }
//    }
}