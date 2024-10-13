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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Datos;
import domain.Nevera;

public class VentanaCocina extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonCerrar, botonSeleccionar,botonComprar;
	protected JPanel panelAbajo,  panelArriba, panelCentro, panelCentroD, panelCentroI;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4;
	protected JTextArea areaTexto;
	
	private Datos datos;
    private String objetoSeleccionado;
	
	public VentanaCocina() {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cocina");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

       
        botonCerrar = new JButton("Cerrar");
        botonSeleccionar = new JButton("Seleccionar");
        botonComprar = new JButton("Comprar");

        
        panelAbajo = new JPanel();  
        panelArriba = new JPanel(new BorderLayout());  
        panelCentro = new JPanel(new GridLayout(1, 2));  
        panelCentroD = new JPanel(new GridLayout(2, 2)); 
        panelCentroI = new JPanel(new GridLayout(2, 2));

        
        areaTexto = new JTextArea(3, 40);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de muebles asequibles y modernos.\n" +
                "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");


        JScrollPane scrollTexto = new JScrollPane(areaTexto); 
        scrollTexto.setBorder(null); 

        panelArriba.add(scrollTexto, BorderLayout.NORTH);

        
        ImageIcon nevera = new ImageIcon("src/Imagenes/Nevera.jpg");
        ImageIcon horno = new ImageIcon("src/Imagenes/horno.jpg");
        ImageIcon encimera = new ImageIcon("src/Imagenes/encimera.jpg");
        ImageIcon fregadero = new ImageIcon("src/Imagenes/fregadero.jpg");
        
        labelImagen1 = new JLabel(nevera);
        labelImagen2 = new JLabel(horno);
        labelImagen3 = new JLabel(encimera);
        labelImagen4 = new JLabel(fregadero);
      

        panelCentroD.add(labelImagen1);
        panelCentroD.add(labelImagen2);
        panelCentroD.add(labelImagen3);
        panelCentroD.add(labelImagen4);
        
        panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);

        panelAbajo.add(botonCerrar);
        panelAbajo.add(botonSeleccionar);
        panelAbajo.add(botonComprar);

        
        getContentPane().add(panelArriba, BorderLayout.NORTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panelAbajo, BorderLayout.SOUTH);

        
        botonCerrar.addActionListener((e) -> {
            System.exit(0);
        });
        
        
        labelImagen1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Nevera";
            }
        });
        
        labelImagen2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Horno";
            }
        });
        
        labelImagen3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Encimera";
            }
        });
        
        labelImagen4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Fregadero";
            }
        });
        
        labelDescripcion1 = new JLabel();
        labelDescripcion2 = new JLabel();
        labelDescripcion3 = new JLabel();
        labelDescripcion4 = new JLabel();
        labelDescripcion1.setText("Nevera\n" + "Precio: 699.90 \n" + "Peso: 81.5kg \n" + "Descripción: Nevera con dos puertas, ideal para familias.");
        labelDescripcion2.setText("Horno\n" + "Precio: 250.8 \n" + "Peso: 67.2kg \n" + "Horno con gran capacidad para tus mejores recetas");
        labelDescripcion3.setText("Encimera\n" + "Precio: 190.95 \n" + "Peso: 34kg \n" + "Elegante encimera para darle un toque moderno a tu cocina");
        labelDescripcion4.setText("Fregadero\n" + "Precio: 280.87 \n" + "Peso: 12.5kg \n" + "Fregadero con dos cubetas y grifo incluido");

        
        setVisible(true);
        setLocationRelativeTo(null); 
    }

	
}
