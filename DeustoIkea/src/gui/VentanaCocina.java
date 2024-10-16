package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.Cocina;
import domain.Datos;


public class VentanaCocina extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonCerrar, botonSeleccionar,botonComprar;
	protected JPanel panelAbajo,  panelArriba, panelCentro, panelCentroD, panelCentroI;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4;
	protected JTextArea areaTexto;
	
	private Datos datos;
    private String objetoSeleccionado;
	
	public VentanaCocina() {
		this.datos = new Datos();
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cocina");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

       
        botonCerrar = new JButton("Atras");
        botonSeleccionar = new JButton("Seleccionar");
        botonComprar = new JButton("Comprar");

        
        panelAbajo = new JPanel();  
        panelArriba = new JPanel();  
        panelCentro = new JPanel();  
        panelCentroD = new JPanel(); 
        panelCentroI = new JPanel();

        
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
        
        panelCentro.setLayout(new GridLayout(1, 2));
        panelCentro.add(panelCentroI);
        panelCentro.add(panelCentroD);
        
        panelCentroI.setLayout(new GridLayout(2, 2));
        panelCentroD.setLayout(new GridLayout(2, 2));

        panelAbajo.add(botonCerrar);
        panelAbajo.add(botonSeleccionar);
        panelAbajo.add(botonComprar);
 
        labelDescripcion1 = new JLabel();
        labelDescripcion2 = new JLabel();
        labelDescripcion3 = new JLabel();
        labelDescripcion4 = new JLabel();
        
        
        labelDescripcion1.setText("<html>Nevera<br>" + 
                "Precio: 699.90<br>" + 
                "Peso: 81.5kg<br>" + 
                "Nevera con dos puertas, ideal para familias.</html>");

        labelDescripcion2.setText("<html>Horno<br>" + 
                "Precio: 250.8<br>" + 
                "Peso: 67.2kg<br>" + 
                "Horno con gran capacidad para tus mejores recetas.</html>");

        labelDescripcion3.setText("<html>Encimera<br>" + 
                "Precio: 190.95<br>" + 
                "Peso: 34kg<br>" + 
                "Elegante encimera para darle un toque moderno a tu cocina.</html>");

        labelDescripcion4.setText("<html>Fregadero<br>" + 
                "Precio: 280.87<br>" + 
                "Peso: 12.5kg<br>" + 
                "Fregadero con dos cubetas y grifo incluido.</html>");

        
        panelCentroI.add(labelImagen1);
        panelCentroI.add(labelDescripcion1);
        panelCentroI.add(labelImagen2);
        panelCentroI.add(labelDescripcion2);
        
        panelCentroD.add(labelImagen3);
        panelCentroD.add(labelDescripcion3);
        panelCentroD.add(labelImagen4);
        panelCentroD.add(labelDescripcion4);
        getContentPane().add(panelArriba, BorderLayout.NORTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        
       
        
        
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
        
        botonCerrar.addActionListener((e) -> {
            dispose();
            new VentanaPrincipal();
        });
        botonSeleccionar.addActionListener((e) -> {
            mostrarInformacionSeleccionada();
        });
        
        setVisible(true);
        setLocationRelativeTo(null); 
    }
	
	 private void mostrarInformacionSeleccionada() {
		    if (objetoSeleccionado.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Por favor, selecciona un objeto primero.");
		    } else {
		        Cocina cocinaSeleccionada = null;

		        switch (objetoSeleccionado) {
		            case "Nevera":
		                cocinaSeleccionada = datos.getNevera();
		                break;
		            case "Horno":
		            	cocinaSeleccionada = datos.getHorno();
		                break;
		            case "Encimera":
		            	cocinaSeleccionada = datos.getEncimera();
		                break;
		            case "Fregadero":
		            	cocinaSeleccionada = datos.getFregadero();
		                break;
		        }

		        if (cocinaSeleccionada != null) {
		            String detalles = datos.obtenerDetallesCocina(cocinaSeleccionada);
		            JOptionPane.showMessageDialog(this, detalles);
		        }
		    }
		}
	

	
}
