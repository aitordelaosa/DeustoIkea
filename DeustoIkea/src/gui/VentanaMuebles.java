package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.Armario;
import domain.Mesa;
import domain.Mueble;
import domain.Silla;
import domain.Sofa;

public class VentanaMuebles extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonCerrar, botonSeleccionar, botonComprar;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelImagen, panelDescripcion;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4;
	protected JTextArea areaTexto;
	protected JTextField texto1, texto2, texto3, texto4;
	protected String[] categoriasMuebles = { "Todos", "Silla", "Mesa", "Armario", "Sofá" };
	protected JComboBox<String> comboDeMuebles;
	protected ArrayList<Mueble> listaMuebles;
	
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
		panelArriba = new JPanel(new BorderLayout());
		panelImagen = new JPanel();
		
		texto1 = new JTextField(20);
		texto2 = new JTextField(20);
		texto3 = new JTextField(20);
		texto4 = new JTextField(20);
		 
        areaTexto = new JTextArea(3, 40);
        areaTexto.setLineWrap(true); // Permitir que el texto se ajuste a la línea
        areaTexto.setWrapStyleWord(true); // Ajustar solo en palabras completas
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de muebles asequibles y modernos.\n" +
                          "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");
		
        JScrollPane scrollTexto = new JScrollPane(areaTexto);
        scrollTexto.setBorder(null);  // Remover borde
        panelArriba.add(scrollTexto, BorderLayout.NORTH); 
        
		comboDeMuebles = new JComboBox<>(categoriasMuebles);
		
		// Lista de muebles temp
        listaMuebles = new ArrayList<>();
        listaMuebles.add(new Silla());
        listaMuebles.add(new Mesa());
        listaMuebles.add(new Armario());
        listaMuebles.add(new Sofa());
		
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonSeleccionar);
		panelAbajo.add(botonComprar);
		
		panelCentro.setLayout(new GridLayout(1, 2));
		panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);
        panelCentroD.setLayout(new GridLayout(1, 2));
        
        panelCentroD.add(panelImagen);
        panelCentroD.add(panelDescripcion);
        panelCentroI.add(comboDeMuebles);
        
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
        
        panelImagen.setLayout(new GridLayout(2, 1));
        panelImagen.add(labelImagen1);
        panelImagen.add(labelImagen2);
        panelImagen.add(labelImagen3);
        panelImagen.add(labelImagen4);
        panelDescripcion.add(labelDescripcion1);
        panelDescripcion.add(labelDescripcion2);
        panelDescripcion.add(labelDescripcion3);
        panelDescripcion.add(labelDescripcion4);
	        
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