package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import domain.Datos;
import domain.Mueble;
import domain.Cocina;


public class VentanaDespuesPrincipalPrueba extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonSeleccionar, botonComprar, botonPerfil, botonCarrito;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelSuperior;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4, textoMueble;
	protected JTextArea areaTexto;
	
	private Datos datos;
    private String objetoSeleccionado;
	
	public VentanaDespuesPrincipalPrueba(int code) {
		this.datos = new Datos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (code == 1) {
	        setTitle("Muebles");
	    } else if (code == 2) {
	        setTitle("Cocina");
	    } else {
	        setTitle("Sección Desconocida");
	    }
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		botonAtras = new JButton("ATRAS");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");
		
		ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        botonPerfil = new JButton(iconoPerfil);
        botonCarrito = new JButton(iconoCarrito);
//        ImageIcon iconoMuebles = new ImageIcon(new ImageIcon("src/Imagenes/Seccion_Muebles (1).png").getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH));
//        labelMuebles = new JLabel(iconoMuebles);
		
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
        if (code == 1) {
            areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de muebles asequibles y modernos.");
        } else if (code == 2) {
            areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de electrodomésticos y artículos de cocina.");
        }
//        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de articulos asequibles y modernos.\n" +
//                          "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");
        
        if(code == 1) {
        	textoMueble = new JLabel("Sección de Muebles");
        } else if (code == 2) {
        	textoMueble = new JLabel("Sección de Cocina");
        }
        textoMueble.setFont(new Font("Arial", Font.BOLD, 18));
        textoMueble.setHorizontalAlignment(SwingConstants.CENTER);
        
        Thread colorThread = new Thread(new CambioColorHilo());
        colorThread.start();
        
        JPanel panelBotonesPerfilCarrito = new JPanel(new BorderLayout());

        panelBotonesPerfilCarrito.add(textoMueble, BorderLayout.SOUTH);
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
        
        switch (code) {
        case 1:
            // Mostrar muebles
            ImageIcon sofa = new ImageIcon(new ImageIcon("src/Imagenes/sofa.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon armario = new ImageIcon(new ImageIcon("src/Imagenes/Armario.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon silla = new ImageIcon(new ImageIcon("src/Imagenes/silla.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon mesa = new ImageIcon(new ImageIcon("src/Imagenes/mesa.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        	ImageIcon sofa = new ImageIcon("src/Imagenes/sofa.jpeg");
//            ImageIcon armario = new ImageIcon("src/Imagenes/Armario.jpeg");
//            ImageIcon silla = new ImageIcon("src/Imagenes/silla.jpeg");
//            ImageIcon mesa = new ImageIcon("src/Imagenes/mesa.jpeg");
            
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
            
            break;

        case 2:
            // Mostrar elementos de cocina
            ImageIcon nevera = new ImageIcon(new ImageIcon("src/Imagenes/Nevera.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon horno = new ImageIcon(new ImageIcon("src/Imagenes/horno.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon fregadero = new ImageIcon(new ImageIcon("src/Imagenes/fregadero.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
            ImageIcon encimera = new ImageIcon(new ImageIcon("src/Imagenes/encimera.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        	ImageIcon nevera = new ImageIcon("src/Imagenes/Nevera.jpg");
//            ImageIcon horno = new ImageIcon("src/Imagenes/horno.jpg");
//            ImageIcon encimera = new ImageIcon("src/Imagenes/encimera.jpg");
//            ImageIcon fregadero = new ImageIcon("src/Imagenes/fregadero.jpg");
            
            labelImagen1 = new JLabel(nevera);
            labelImagen2 = new JLabel(horno);
            labelImagen3 = new JLabel(fregadero);
            labelImagen4 = new JLabel(encimera);
        	
//            labelImagen1.setIcon(nevera);
//            labelImagen2.setIcon(horno);
//            labelImagen3.setIcon(fregadero);
//            labelImagen4.setIcon(encimera);
            
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
                    objetoSeleccionado = "Fregadero";
                }
            });
            
            labelImagen4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    objetoSeleccionado = "Encimera";
                }
            });

            break;

        default:
            areaTexto.setText("Sección no reconocida.");
            break;
        }
        
        labelDescripcion1 = new JLabel();
        labelDescripcion2 = new JLabel();
        labelDescripcion3 = new JLabel();
        labelDescripcion4 = new JLabel();
        // Usar el código para filtrar o cambiar el contenido
        switch (code) {
            case 1:
                // Mostrar muebles
            	labelDescripcion1.setText("<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
                labelDescripcion2.setText("<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
                labelDescripcion3.setText("<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica, perfecta para oficina o comedor.</html>");
                labelDescripcion4.setText("<html><b>Mesa</b><br>Precio: $150<br>Peso: 20kg<br>Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.</html>");
                // Otras configuraciones...
                break;
            case 2:
                // Mostrar elementos cocina
//            	labelDescripcion1.setText("<html>Nevera<br>" + 
//                        "Precio: 699.90<br>" + 
//                        "Peso: 81.5kg<br>" + 
//                        "Nevera con dos puertas, ideal para familias.</html>");
//
//                labelDescripcion2.setText("<html>Horno<br>" + 
//                        "Precio: 250.8<br>" + 
//                        "Peso: 67.2kg<br>" + 
//                        "Horno con gran capacidad para tus mejores recetas.</html>");
//
//                labelDescripcion3.setText("<html>Encimera<br>" + 
//                        "Precio: 190.95<br>" + 
//                        "Peso: 34kg<br>" + 
//                        "Elegante encimera para darle un toque moderno a tu cocina.</html>");
//
//                labelDescripcion4.setText("<html>Fregadero<br>" + 
//                        "Precio: 280.87<br>" + 
//                        "Peso: 12.5kg<br>" + 
//                        "Fregadero con dos cubetas y grifo incluido.</html>");
//                break;
            	
            	 labelDescripcion1.setText("<html><b>Nevera</b><br>Precio: $699.90<br>Peso: 81.5kg<br>Descripción: Nevera con dos puertas.</html>");
                 labelDescripcion2.setText("<html><b>Horno</b><br>Precio: $250.8<br>Peso: 67.2kg<br>Descripción: Horno de gran capacidad.</html>");
                 labelDescripcion3.setText("<html><b>Fregadero</b><br>Precio: $280.87<br>Peso: 12.5kg<br>Descripción: Fregadero con dos cubetas.</html>");
                 labelDescripcion4.setText("<html><b>Encimera</b><br>Precio: $500.99<br>Peso: 100.7kg<br>Descripción: Encimera de granito.</html>");
                 break;
            // Futuros casos
            default:
                break;
        }
    
        
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
		 
		botonPerfil.addActionListener((e) -> {
			dispose();
			new VentanaPerfil();
		});
		
		botonCarrito.addActionListener((e) -> {
			dispose();
			new VentanaCarrito();
		});
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
//	@SuppressWarnings("unused")
//	private String mostrarDetalle(String objetoSeleccionado) {
//	    Datos datos = new Datos();
//	    Mueble muebleSeleccionado = null;
//	    Cocina cocinaSeleccionada = null;
//
//	    switch (objetoSeleccionado) {
//	        case "Sofá":
//	            muebleSeleccionado = datos.getSofa();
//	            break;
//	        case "Armario":
//	            muebleSeleccionado = datos.getArmario();
//	            break;
//	        case "Silla":
//	            muebleSeleccionado = datos.getSilla();
//	            break;
//	        case "Mesa":
//	            muebleSeleccionado = datos.getMesa();
//	            break;
//	        case "Nevera":
//	        	cocinaSeleccionada = datos.getNevera();
//	            break;
//	        case "Horno":
//	        	cocinaSeleccionada = datos.getHorno();
//	            break;
//	        case "Fregadero":
//	        	cocinaSeleccionada = datos.getFregadero();
//	            break;
//	        case "Encimera":
//	        	cocinaSeleccionada = datos.getEncimera();
//	            break;
//	        default:
//	            return "No se encontró el objeto seleccionado.";
//	    }
//
//	    if (muebleSeleccionado != null) {
//	        return datos.obtenerDetallesMueble(muebleSeleccionado);
////	    	return muebleSeleccionado.obtenerDetalles(); 
//	    } else if (cocinaSeleccionada != null) {
//	        return datos.obtenerDetallesCocina(cocinaSeleccionada);
////	    	return cocinaSeleccionada.obtenerDetalles();
//	    } else {
//	        return "No se encontraron detalles para el objeto seleccionado.";
//	    }
//	}
	
//	private void mostrarDetalle(String objeto) {
//        if (objeto == null) {
//            JOptionPane.showMessageDialog(null, "No has seleccionado ningún objeto.");
//        } else {
//            String detalles = datos.obtenerDetallesMueble(objeto);
//            JOptionPane.showMessageDialog(null, "Detalles del objeto seleccionado:\n" + detalles);
//        }
//    }
	 
	 private void mostrarInformacionSeleccionada() {
		    if (objetoSeleccionado == null || objetoSeleccionado.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Por favor, selecciona un objeto primero.");
		    } else {
		        Datos datos = new Datos();
		        Mueble muebleSeleccionado = null;
		        Cocina cocinaSeleccionada = null;

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

		        String detalles;
		        if (muebleSeleccionado != null) {
		            detalles = datos.obtenerDetallesMueble(muebleSeleccionado);
		        } else if (cocinaSeleccionada != null) {
		            detalles = datos.obtenerDetallesCocina(cocinaSeleccionada);
		        } else {
		            detalles = "No se encontraron detalles para el objeto seleccionado.";
		        }

		        JOptionPane.showMessageDialog(this, detalles);
		    }
		}

	 
	 private class CambioColorHilo implements Runnable {
	        private final Color[] colores = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
	        private int indiceColor = 0;

	        @Override
	        public void run() {
	            while (true) {
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }

	                textoMueble.setForeground(colores[indiceColor]);
	                indiceColor = (indiceColor + 1) % colores.length;
	            }
	        }
	    }
	
}
