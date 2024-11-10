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
import domain.Jardineria;
import domain.Mueble;
import domain.Baño;
import domain.Cocina;

public class VentanaDespuesPrincipalPrueba extends JFrame {
	private static final long serialVersionUID = 1L;

	protected JButton botonAtras, botonSeleccionar, botonComprar, botonPerfil, botonCarrito;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelSuperior;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2,
			labelDescripcion3, labelDescripcion4, textoMueble;
	protected JTextArea areaTexto;
	@SuppressWarnings("unused")
	private Datos datos;
	private String objetoSeleccionado;

	public VentanaDespuesPrincipalPrueba(int code) {
		this.datos = new Datos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (code == 1) {
			setTitle("Muebles");
		} else if (code == 2) {
			setTitle("Cocina");
		} else if (code == 3) {
			setTitle("Baño");
		} else if (code == 4){
			setTitle("Jardineria");
		} else {
			setTitle("SECCION DESCONOCIDA");
		}
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		

		botonAtras = new JButton("ATRAS");
		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("COMPRAR");

		ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40,
				40, java.awt.Image.SCALE_SMOOTH));
		ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage()
				.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
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
		} else if (code == 3) {
			areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de baños");
		} else if (code == 4) {
			areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de Jardineria y herramientas.");
		}
			
//        areaTexto.setText("Bienvenido a DeustoIkea, tu tienda de articulos asequibles y modernos.\n" +
//                          "Ofrecemos una amplia gama de muebles que se adaptan a todos los estilos y presupuestos.");

		if (code == 1) {
			textoMueble = new JLabel("Sección de Muebles");
		} else if (code == 2) {
			textoMueble = new JLabel("Sección de Cocina");
		} else if (code == 3) {
			textoMueble = new JLabel("Seccion de Baño");
		} else if (code == 4) {
			textoMueble = new JLabel("Seccion de Jardineria");
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
			ImageIcon sofa = new ImageIcon(new ImageIcon("src/Imagenes/sofa.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon armario = new ImageIcon(new ImageIcon("src/Imagenes/Armario.jpeg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon silla = new ImageIcon(new ImageIcon("src/Imagenes/silla.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon mesa = new ImageIcon(new ImageIcon("src/Imagenes/mesa.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
//        	ImageIcon sofa = new ImageIcon("src/Imagenes/sofa.jpeg");
//            ImageIcon armario = new ImageIcon("src/Imagenes/Armario.jpeg");
//            ImageIcon silla = new ImageIcon("src/Imagenes/silla.jpeg");
//            ImageIcon mesa = new ImageIcon("src/Imagenes/mesa.jpeg");

			labelImagen1 = new JLabel(sofa);
			labelImagen2 = new JLabel(armario);
			labelImagen3 = new JLabel(silla);
			labelImagen4 = new JLabel(mesa);
			
			labelImagen1.setToolTipText("Sofá - Perfecto para la sala de estar.");
	        labelImagen2.setToolTipText("Armario - Gran capacidad de almacenamiento.");
	        labelImagen3.setToolTipText("Silla - Ergonómica y cómoda.");
	        labelImagen4.setToolTipText("Mesa - Ideal para el comedor.");

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
			ImageIcon nevera = new ImageIcon(new ImageIcon("src/Imagenes/Nevera.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon horno = new ImageIcon(new ImageIcon("src/Imagenes/horno.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon fregadero = new ImageIcon(new ImageIcon("src/Imagenes/fregadero.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon encimera = new ImageIcon(new ImageIcon("src/Imagenes/encimera.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        	ImageIcon nevera = new ImageIcon("src/Imagenes/Nevera.jpg");
//            ImageIcon horno = new ImageIcon("src/Imagenes/horno.jpg");
//            ImageIcon encimera = new ImageIcon("src/Imagenes/encimera.jpg");
//            ImageIcon fregadero = new ImageIcon("src/Imagenes/fregadero.jpg");

			labelImagen1 = new JLabel(nevera);
			labelImagen2 = new JLabel(horno);
			labelImagen3 = new JLabel(fregadero);
			labelImagen4 = new JLabel(encimera);
			
			labelImagen1.setToolTipText("Nevera - Perfecto para tu cocina");
	        labelImagen2.setToolTipText("Horno - Ideal para tus mejores recetas.");
	        labelImagen3.setToolTipText("Fregadero - Deja tus platos relucientes.");
	        labelImagen4.setToolTipText("Encimera - Ideal para cocinas modernas.");
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

		case 3:
			// Mostrar elemntos Baño
			ImageIcon bide = new ImageIcon(new ImageIcon("src/Imagenes/bide.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon ducha = new ImageIcon(new ImageIcon("src/Imagenes/ducha.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon inodoro = new ImageIcon(new ImageIcon("src/Imagenes/inodoro.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon lavamanos = new ImageIcon(new ImageIcon("src/Imagenes/lavamanos.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//         	ImageIcon nevera = new ImageIcon("src/Imagenes/Nevera.jpg");
//             ImageIcon horno = new ImageIcon("src/Imagenes/horno.jpg");
//             ImageIcon encimera = new ImageIcon("src/Imagenes/encimera.jpg");
//             ImageIcon fregadero = new ImageIcon("src/Imagenes/fregadero.jpg");

			labelImagen1 = new JLabel(bide);
			labelImagen2 = new JLabel(ducha);
			labelImagen3 = new JLabel(inodoro);
			labelImagen4 = new JLabel(lavamanos);
			
			labelImagen1.setToolTipText("Bide - Perfecto para cualquier baño.");
	        labelImagen2.setToolTipText("Ducha - Amplia ducha para mass comodidad.");
	        labelImagen3.setToolTipText("Inodoro - Con la tecnologia mas actual.");
	        labelImagen4.setToolTipText("Lavamanos - Ideal para cualquier altura.");

			labelImagen1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Bide";
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Ducha";
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Inodoro";
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Lavamanos";
				}
			});

			break;
			
		case 4:
			// Mostrar elementos de Jardineria
			ImageIcon barbacoa = new ImageIcon(new ImageIcon("src/Imagenes/barbacoa-40-cm.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon planta = new ImageIcon(new ImageIcon("src/Imagenes/adelfas-colores.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon maceta= new ImageIcon(new ImageIcon("src/Imagenes/Maceta.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon pala = new ImageIcon(new ImageIcon("src/Imagenes/pala2.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
//        	ImageIcon barbacoa = new ImageIcon("src/Imagenes/Barbacoa.jpg");
//            ImageIcon adelfas = new ImageIcon("src/Imagenes/adelfas-colores.jpg");
//            ImageIcon maceta = new ImageIcon("src/Imagenes/maceta.jpg");
//            ImageIcon pala = new ImageIcon("src/Imagenes/Pala.png");

			labelImagen1 = new JLabel(barbacoa);
			labelImagen2 = new JLabel(planta);
			labelImagen3 = new JLabel(maceta);
			labelImagen4 = new JLabel(pala);
			
			labelImagen1.setToolTipText("Barbacoa - Ideal para tus comidas con amigos.");
	        labelImagen2.setToolTipText("Planta - Elegancia.");
	        labelImagen3.setToolTipText("Maceta - Perfecta para tus plantas.");
	        labelImagen4.setToolTipText("Pala - Ideal para poder plantar tus plantas.");

//            labelImagen1.setIcon(barbacoa);
//            labelImagen2.setIcon(adelfas);
//            labelImagen3.setIcon(maceta);
//            labelImagen4.setIcon(pala);

			labelImagen1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Barbacoa";
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Adelfas";
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Maceta";
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					objetoSeleccionado = "Pala";
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
			labelDescripcion1.setText(
					"<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
			labelDescripcion2.setText(
					"<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
			labelDescripcion3.setText(
					"<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica, perfecta para oficina o comedor.</html>");
			labelDescripcion4.setText(
					"<html><b>Mesa</b><br>Precio: $150<br>Peso: 20kg<br>Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.</html>");
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

			labelDescripcion1.setText(
					"<html><b>Nevera</b><br>Precio: $699.90<br>Peso: 81.5kg<br>Descripción: Nevera con dos puertas.</html>");
			labelDescripcion2.setText(
					"<html><b>Horno</b><br>Precio: $250.8<br>Peso: 67.2kg<br>Descripción: Horno de gran capacidad.</html>");
			labelDescripcion3.setText(
					"<html><b>Fregadero</b><br>Precio: $280.87<br>Peso: 12.5kg<br>Descripción: Fregadero con dos cubetas.</html>");
			labelDescripcion4.setText(
					"<html><b>Encimera</b><br>Precio: $500.99<br>Peso: 100.7kg<br>Descripción: Encimera de granito.</html>");
			break;
		case 3:
			labelDescripcion1.setText(
					"<html><b>Bidé</b><br>Precio: $200.60<br>Peso: 20kg<br>Descripción: Bidé compacto de fácil instalación.</html>");
			labelDescripcion2.setText(
					"<html><b>Ducha</b><br>Precio: $320.75<br>Peso: 30kg<br>Descripción: Ducha con sistema de hidromasaje y puerta de vidrio templado.</html>");
			labelDescripcion3.setText(
					"<html><b>Inodoro</b><br>Precio: $180.50<br>Peso: 25kg<br>Descripción: Inodoro de porcelana con sistema de bajo consumo de agua.</html>");
			labelDescripcion4.setText(
					"<html><b>Lavamanos</b><br>Precio: $150.45<br>Peso: 15kg<br>Descripción: Lavamanos de cerámica con grifo monomando.</html>");
			break;
		case 4: 
			labelDescripcion1.setText(
					"<html><b>Barbacoa</b><br>Precio: $50.99<br>Peso: 150kg<br>Descripción: resistente y duradera, ideal para exteriores. Incluye parrilla ajustable y espacio de almacenamiento,perfecta para disfrutar asados en el jardín.</html>");
			labelDescripcion2.setText(
					"<html><b>Planta</b><br>Precio: $21.99<br>Peso: 20kg<br>Descripción: Es una planta de exterior de tamaño medio, ideal para jardines y patios. Con follaje perenne y flores en tonos rosa, blanco o rojo.</html>");
			labelDescripcion3.setText(
					"<html><b>Maceta</b><br>Precio: $10.75<br>Peso: 3.2kg<br>Descripción: Maceta para plantas tamaño mediano.</html>");
			labelDescripcion4.setText(
					"<html><b>Pala</b><br>Precio: $7.5<br>Peso: 1kg<br>Descripción: herramienta resistente con cabeza de acero para cavar y mover tierra, y mango de madera ergonómico para un agarre cómodo.</html>");
			
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
			Baño bañoSeleccionado = null;
			Jardineria jardineriaSeleccionado = null;

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
			case "Bide":
				bañoSeleccionado = datos.getBide();
				break;
			case "Ducha":
				bañoSeleccionado = datos.getDucha();
				break;
			case "Inodoro":
				bañoSeleccionado = datos.getInodoro();
				break;
			case "Lavamanos":
				bañoSeleccionado = datos.getLavamanos();
				break;
			case "Barbacoa":
				jardineriaSeleccionado = datos.getBarbacoa();
				break;
			case "Planta":
				jardineriaSeleccionado = datos.getPlanta();
				break;
			case "Maceta":
				jardineriaSeleccionado = datos.getMaceta();
				break;
			case "Pala":
				jardineriaSeleccionado = datos.getHerramienta();
				break;
				

			}

			String detalles;
			if (muebleSeleccionado != null) {
				detalles = datos.obtenerDetallesMueble(muebleSeleccionado);
			} else if (cocinaSeleccionada != null) {
				detalles = datos.obtenerDetallesCocina(cocinaSeleccionada);
			} else if(bañoSeleccionado != null){
				detalles = datos.obtenerDetallesBaño(bañoSeleccionado);
				
			}else if(jardineriaSeleccionado !=null){
				detalles = datos.obtenerDetallesJardineria(jardineriaSeleccionado);
			}else {
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
