package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import domain.Datos;
import domain.Jardineria;
import domain.Mueble;
import domain.Producto;
import domain.Baño;
import domain.Cliente;
import domain.Cocina;

public class VentanaPostPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	protected JButton botonAtras, botonSeleccionar, botonComprar, botonPerfil, botonCarrito;
	protected JPanel panelAbajo, panelCentro, panelCentroD, panelCentroI, panelArriba, panelSuperior;
	protected JLabel labelImagen1, labelImagen2, labelImagen3, labelImagen4, labelDescripcion1, labelDescripcion2, labelDescripcion3, labelDescripcion4, textoMueble;
	protected JTextArea areaTexto;
	
	private JLabel ultimaImagenSeleccionada = null;
	
	@SuppressWarnings("unused")
	
	private Datos datos;
	private String objetoSeleccionado;
	private Cliente cliente;
	private int codigo;
	private VentanaCarrito ventanaCarrito;
	
	public VentanaPostPrincipal(int code, Cliente cliente, int codigo) {
		this.codigo = codigo;
		this.datos = new Datos();
		this.cliente = cliente;
//		ventanaCarrito = new VentanaCarrito(cliente, codigo);
		
		Border borderResaltado = BorderFactory.createLineBorder(Color.WHITE, 2);

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
		
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
		
		botonAtras = new JButton("ATRAS");
//		botonSeleccionar = new JButton("SELECCIONAR");
		botonComprar = new JButton("AÑADIR AL CARRITO");

		ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		botonPerfil = new JButton(iconoPerfil);
		botonCarrito = new JButton(iconoCarrito);
		
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
		panelArriba.add(panelSuperior, BorderLayout.NORTH);

		panelAbajo.add(botonAtras);
//		panelAbajo.add(botonSeleccionar);
		panelAbajo.add(botonComprar);

		panelCentro.setLayout(new GridLayout(1, 2));
		panelCentro.add(panelCentroI);
		panelCentro.add(panelCentroD);

		switch (code) {
		case 1:
			// Mostrar elementos de muebles
			ImageIcon sofa = new ImageIcon(new ImageIcon("src/Imagenes/sofa.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon armario = new ImageIcon(new ImageIcon("src/Imagenes/Armario.jpeg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon silla = new ImageIcon(new ImageIcon("src/Imagenes/silla.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon mesa = new ImageIcon(new ImageIcon("src/Imagenes/mesa.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));

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
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Sofá";
					labelImagen1.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Sofá");
					ultimaImagenSeleccionada = labelImagen1;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Armario";
					labelImagen2.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Armario");
					ultimaImagenSeleccionada = labelImagen2;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Silla";
					labelImagen3.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Silla");
					ultimaImagenSeleccionada = labelImagen3;
					mostrarInformacionSeleccionada();
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Mesa";
					labelImagen4.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Mesa");
					ultimaImagenSeleccionada = labelImagen4;
			        mostrarInformacionSeleccionada();
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

			labelImagen1 = new JLabel(nevera);
			labelImagen2 = new JLabel(horno);
			labelImagen3 = new JLabel(fregadero);
			labelImagen4 = new JLabel(encimera);
			
			labelImagen1.setToolTipText("Nevera - Perfecto para tu cocina");
	        labelImagen2.setToolTipText("Horno - Ideal para tus mejores recetas.");
	        labelImagen3.setToolTipText("Fregadero - Deja tus platos relucientes.");
	        labelImagen4.setToolTipText("Encimera - Ideal para cocinas modernas.");

			labelImagen1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Nevera";
					labelImagen1.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Nevera");
					ultimaImagenSeleccionada = labelImagen1;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Horno";
					labelImagen2.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Horno");
					ultimaImagenSeleccionada = labelImagen2;
					mostrarInformacionSeleccionada();
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Fregadero";
					labelImagen3.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Fregadero");
					ultimaImagenSeleccionada = labelImagen3;
					mostrarInformacionSeleccionada();
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Encimera";
					labelImagen4.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Encimera");
					ultimaImagenSeleccionada = labelImagen4;
					mostrarInformacionSeleccionada();
				}
			});

			break;

		case 3:
			// Mostrar elementos de baño
			ImageIcon bide = new ImageIcon(new ImageIcon("src/Imagenes/bide.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon ducha = new ImageIcon(new ImageIcon("src/Imagenes/ducha.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon inodoro = new ImageIcon(new ImageIcon("src/Imagenes/inodoro.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon lavamanos = new ImageIcon(new ImageIcon("src/Imagenes/lavamanos.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

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
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Bide";
					labelImagen1.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Bide");
					ultimaImagenSeleccionada = labelImagen1;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Ducha";
					labelImagen2.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Ducha");
					ultimaImagenSeleccionada = labelImagen2;
					mostrarInformacionSeleccionada();
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Inodoro";
					labelImagen3.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Inodoro");
					ultimaImagenSeleccionada = labelImagen3;
					mostrarInformacionSeleccionada();
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Lavamanos";
					labelImagen4.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado el Lavamanos");
					ultimaImagenSeleccionada = labelImagen4;
					mostrarInformacionSeleccionada();
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

			labelImagen1 = new JLabel(barbacoa);
			labelImagen2 = new JLabel(planta);
			labelImagen3 = new JLabel(maceta);
			labelImagen4 = new JLabel(pala);
			
			labelImagen1.setToolTipText("Barbacoa - Ideal para tus comidas con amigos.");
	        labelImagen2.setToolTipText("Planta - Elegancia.");
	        labelImagen3.setToolTipText("Maceta - Perfecta para tus plantas.");
	        labelImagen4.setToolTipText("Pala - Ideal para poder plantar tus plantas.");

			labelImagen1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Barbacoa";
					labelImagen1.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Barbacoa");
					ultimaImagenSeleccionada = labelImagen1;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Planta";
					labelImagen2.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Planta");
					ultimaImagenSeleccionada = labelImagen2;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Maceta";
					labelImagen3.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Maceta");
					ultimaImagenSeleccionada = labelImagen3;
			        mostrarInformacionSeleccionada();
				}
			});

			labelImagen4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (ultimaImagenSeleccionada != null) {
			            ultimaImagenSeleccionada.setBorder(null);
			        }
					
					objetoSeleccionado = "Pala";
					labelImagen4.setBorder(borderResaltado);
//			        JOptionPane.showMessageDialog(null, "Has seleccionado la Pala");
					ultimaImagenSeleccionada = labelImagen4;
					mostrarInformacionSeleccionada();
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
		
		switch (code) {
		
		case 1:
			labelDescripcion1.setText(
					"<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
			labelDescripcion2.setText(
					"<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
			labelDescripcion3.setText(
					"<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica, perfecta para oficina o comedor.</html>");
			labelDescripcion4.setText(
					"<html><b>Mesa</b><br>Precio: $150<br>Peso: 20kg<br>Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.</html>");
			break;
			
		case 2:
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
			
		default:
			break;
		}
		
		panelCentroI.setLayout(new GridLayout(2, 2));
		panelCentroD.setLayout(new GridLayout(2, 2));

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
			new VentanaPrincipal(cliente, codigo);
		});

//		botonSeleccionar.addActionListener((e) -> {
//			mostrarInformacionSeleccionada();
//		});

		botonPerfil.addActionListener((e) -> {
			dispose();
			new VentanaPerfil(cliente, codigo);
		});

		botonCarrito.addActionListener((e) -> {
			dispose();
			new VentanaCarrito(cliente, codigo, VentanaPrincipal.lp);
		});
		
		botonComprar.addActionListener((e) -> {
		    String c = JOptionPane.showInputDialog(
		        null, 
		        "Ingrese la cantidad que desea añadir al carrito:", 
		        "Seleccionar cantidad", 
		        JOptionPane.PLAIN_MESSAGE
		    );

		    if (c != null) {
		        try {
		            int cantidad = Integer.parseInt(c);

		            if ((cantidad > 0) && (cantidad < 4)) {
		                System.out.println("Cantidad añadida al carrito: " + cantidad);
		                // actualizar el carrito
		                VentanaPrincipal.productoSeleccionado.setNumeroProductos(cantidad);
		                VentanaPrincipal.lp.add(VentanaPrincipal.productoSeleccionado);
		            } else if (cantidad > 4){
		                JOptionPane.showMessageDialog(
		                    null, 
		                    "Por favor, ingrese una cantidad válida menor a 4.", 
		                    "Cantidad no válida", 
		                    JOptionPane.WARNING_MESSAGE
		                );
		            } else {
		                JOptionPane.showMessageDialog(
			                    null, 
			                    "Por favor, ingrese una cantidad válida mayor a 0.", 
			                    "Cantidad no válida", 
			                    JOptionPane.WARNING_MESSAGE
			                );
			            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(
		                null, 
		                "Por favor, ingrese un número válido.", 
		                "Entrada no válida", 
		                JOptionPane.ERROR_MESSAGE
		            );
		        }
		    }
		});
		
		
		// Evento de teclado 
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_J) {
                    int respuesta = JOptionPane.showOptionDialog(
                        VentanaPostPrincipal.this,
                        "Obtener descuento gratuito",
                        "Descuento",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Sí", "No"},
                        "Sí"
                    );

                    if (respuesta == JOptionPane.YES_OPTION || respuesta == JOptionPane.NO_OPTION) {
                        //Mensaje
                        System.out.println("Se seleccionó: " + (respuesta == JOptionPane.YES_OPTION ? "Sí" : "No"));
                        // Descuento en el futuro
                    }
                }
            }
        });

		setVisible(true);
		setLocationRelativeTo(null);
	}    

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
				VentanaPrincipal.productoSeleccionado = muebleSeleccionado;
				break;
			case "Armario":
				muebleSeleccionado = datos.getArmario();
				VentanaPrincipal.productoSeleccionado = muebleSeleccionado;
				break;
			case "Silla":
				muebleSeleccionado = datos.getSilla();
				VentanaPrincipal.productoSeleccionado = muebleSeleccionado;
				break;
			case "Mesa":
				muebleSeleccionado = datos.getMesa();
				VentanaPrincipal.productoSeleccionado = muebleSeleccionado;
				break;
				
			case "Nevera":
				cocinaSeleccionada = datos.getNevera();
				VentanaPrincipal.productoSeleccionado = cocinaSeleccionada;
				break;
			case "Horno":
				cocinaSeleccionada = datos.getHorno();
				VentanaPrincipal.productoSeleccionado = cocinaSeleccionada;
				break;
			case "Encimera":
				cocinaSeleccionada = datos.getEncimera();
				VentanaPrincipal.productoSeleccionado = cocinaSeleccionada;
				break;
			case "Fregadero":
				cocinaSeleccionada = datos.getFregadero();
				VentanaPrincipal.productoSeleccionado = cocinaSeleccionada;
				break;
				
			case "Bide":
				bañoSeleccionado = datos.getBide();
				VentanaPrincipal.productoSeleccionado = bañoSeleccionado;
				break;
			case "Ducha":
				bañoSeleccionado = datos.getDucha();
				VentanaPrincipal.productoSeleccionado = bañoSeleccionado;
				break;
			case "Inodoro":
				bañoSeleccionado = datos.getInodoro();
				VentanaPrincipal.productoSeleccionado = bañoSeleccionado;
				break;
			case "Lavamanos":
				bañoSeleccionado = datos.getLavamanos();
				VentanaPrincipal.productoSeleccionado = bañoSeleccionado;
				break;
				
			case "Barbacoa":
				jardineriaSeleccionado = datos.getBarbacoa();
				VentanaPrincipal.productoSeleccionado = jardineriaSeleccionado;
				break;
			case "Planta":
				jardineriaSeleccionado = datos.getPlanta();
				VentanaPrincipal.productoSeleccionado = jardineriaSeleccionado;
				break;
			case "Maceta":
				jardineriaSeleccionado = datos.getMaceta();
				VentanaPrincipal.productoSeleccionado = jardineriaSeleccionado;
				break;
			case "Pala":
				jardineriaSeleccionado = datos.getHerramienta();
				VentanaPrincipal.productoSeleccionado = jardineriaSeleccionado;
				break;
				
			}

			String detalles;
			
			if (muebleSeleccionado != null) {
				detalles = datos.obtenerDetallesMueble(muebleSeleccionado);
			} else if (cocinaSeleccionada != null) {
				detalles = datos.obtenerDetallesCocina(cocinaSeleccionada);
			} else if(bañoSeleccionado != null){
				detalles = datos.obtenerDetallesBaño(bañoSeleccionado);
				
			} else if(jardineriaSeleccionado !=null){
				detalles = datos.obtenerDetallesJardineria(jardineriaSeleccionado);
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