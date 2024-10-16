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

import domain.Datos;
import domain.Mueble;


public class VentanaMuebles extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonSeleccionar, botonComprar, botonPerfil, botonCarrito;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelSuperior;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4, labelMuebles;
	protected JTextArea areaTexto;
	
	private Datos datos;
    private String objetoSeleccionado;
	
	public VentanaMuebles() {
		this.datos = new Datos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Muebles");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		botonAtras = new JButton("ATRAS");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");
		
		ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        botonPerfil = new JButton(iconoPerfil);
        botonCarrito = new JButton(iconoCarrito);
        ImageIcon iconoMuebles = new ImageIcon(new ImageIcon("src/Imagenes/seccion_muebles.png").getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH));
        labelMuebles = new JLabel(iconoMuebles);
		
		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		panelCentroD = new JPanel();
		panelCentroI = new JPanel();
		panelArriba = new JPanel(new BorderLayout());
		panelSuperior = new JPanel(new BorderLayout());
		
        areaTexto = new JTextArea(3, 40);
        areaTexto.setLineWrap(true); // Permitir que el texto se ajuste a la línea
        areaTexto.setWrapStyleWord(true); // Ajustar solo en palabras completas
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de articulos asequibles y modernos.\n" +
                          "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");
        
        JPanel panelBotonesPerfilCarrito = new JPanel(new BorderLayout());

        panelBotonesPerfilCarrito.add(labelMuebles, BorderLayout.NORTH);
        panelBotonesPerfilCarrito.add(botonPerfil, BorderLayout.WEST);
        panelBotonesPerfilCarrito.add(botonCarrito, BorderLayout.EAST);
        panelBotonesPerfilCarrito.add(areaTexto, BorderLayout.CENTER);
        panelSuperior.add(panelBotonesPerfilCarrito, BorderLayout.NORTH);
        
//        JScrollPane scrollTexto = new JScrollPane(areaTexto);
//        scrollTexto.setBorder(null);  // Remover borde
//        panelArriba.add(scrollTexto, BorderLayout.NORTH); 
        panelArriba.add(panelSuperior, BorderLayout.NORTH); 
//        panelArriba.add(botonPerfil, BorderLayout.NORTH);
//        panelArriba.add(botonCarrito, BorderLayout.NORTH);
		
		panelAbajo.add(botonAtras);
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
        
        labelImagen1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Sofá";
            }
        });
        
        labelImagen2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Armario";
            }
        });
        
        labelImagen3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Silla";
            }
        });
        
        labelImagen4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                objetoSeleccionado = "Mesa";
            }
        });
        
        labelDescripcion1 = new JLabel();
        labelDescripcion2 = new JLabel();
        labelDescripcion3 = new JLabel();
        labelDescripcion4 = new JLabel();
        labelDescripcion1.setText("<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
        labelDescripcion2.setText("<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
        labelDescripcion3.setText("<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica, perfecta para oficina o comedor.</html>");
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
          
		
		botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaPrincipal();
		});
				
		 botonSeleccionar.addActionListener((e) -> {
            mostrarInformacionSeleccionada();
        });
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unused")
	private String mostrarDetalle(String objetoSeleccionado) {
	    Datos datos = new Datos();
	    Mueble muebleSeleccionado = null;

	    switch (objetoSeleccionado) {
	        case "Sofá":
	            muebleSeleccionado = datos.getSofa();
	            break;
	        case "Armario":
	            muebleSeleccionado = datos.getArmario();
	            break;
	        case "Silla":
	            muebleSeleccionado = datos.getSilla();
	            break;
	        case "Mesa":
	            muebleSeleccionado = datos.getMesa();
	            break;
	    }

	    if (muebleSeleccionado != null) {
	        return datos.obtenerDetallesMueble(muebleSeleccionado);
	    } else {
	        return "No se encontraron detalles para el objeto seleccionado.";
	    }
	}
	
	 private void mostrarInformacionSeleccionada() {
		    if (objetoSeleccionado.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Por favor, selecciona un objeto primero.");
		    } else {
		        Mueble muebleSeleccionado = null;

		        switch (objetoSeleccionado) {
		            case "Sofá":
		                muebleSeleccionado = datos.getSofa();
		                break;
		            case "Armario":
		                muebleSeleccionado = datos.getArmario();
		                break;
		            case "Silla":
		                muebleSeleccionado = datos.getSilla();
		                break;
		            case "Mesa":
		                muebleSeleccionado = datos.getMesa();
		                break;
		        }

		        if (muebleSeleccionado != null) {
		            String detalles = datos.obtenerDetallesMueble(muebleSeleccionado);
		            JOptionPane.showMessageDialog(this, detalles);
		        }
		    }
		}
	
}