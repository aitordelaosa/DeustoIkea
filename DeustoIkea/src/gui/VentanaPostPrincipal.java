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
import java.awt.event.MouseListener;
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

import db.BD;
import domain.Datos;
import domain.Ducha;
import domain.Encimera;
import domain.Fregadero;
import domain.Herramienta;
import domain.Horno;
import domain.Inodoro;
import domain.Jardineria;
import domain.Lavamanos;
import domain.Maceta;
import domain.Mesa;
import domain.Mueble;
import domain.Nevera;
import domain.Planta;
import domain.Producto;
import domain.Silla;
import domain.Sofa;
import domain.Armario;
import domain.Barbacoa;
import domain.Baño;
import domain.Bide;
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
	private List<Producto> lista;
	
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

		ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("resources/images/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("resources/images/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
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
			
			if(codigo==1) {
				lista = BD.obtenerListaMuebles();
			}
			
		} else if (code == 2) {
			textoMueble = new JLabel("Sección de Cocina");
			
			if (codigo ==1) {
				lista = BD.obtenerListaCocinas();
			}
			
		} else if (code == 3) {
			textoMueble = new JLabel("Seccion de Baño");
			
			if (codigo ==1) {
				lista = BD.obtenerListaBaños();
			}
			
		} else if (code == 4) {
			textoMueble = new JLabel("Seccion de Jardineria");
			
			if (codigo ==1) {
				lista = BD.obtenerListaMJardinerias();
			}
			
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

		//panelCentro.setLayout(new GridLayout(1, 2));
		//panelCentro.add(panelCentroI);
		//panelCentro.add(panelCentroD);
		panelCentro.setLayout(new GridLayout(0, 2));
		switch (code) {
		case 1:
			if (codigo == 1) {
				//JLabel labels[] = new JLabel[lista.size()];
				//int i=0;
				for(Producto p: lista) {
					if (p instanceof Mueble) {
					Mueble m = (Mueble)p;
					ImageIcon im = new ImageIcon(m.getImagen().getImage().getScaledInstance(200,
							200, java.awt.Image.SCALE_SMOOTH));
					labelImagen1 = new JLabel(im);
					panelCentro.add(labelImagen1);
					labelImagen1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(m instanceof Sofa) {
								String nombre = "Sofá";
								Sofa s = (Sofa)m;
								
								double precio = s.getPrecio();
								String color = s.getColor();
								double peso = s.getPeso();
								String descripcion = s.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+color+"\n"+peso+" kg"+"\n"+descripcion);	
								
							}else if(m instanceof Silla) {
								String nombre = "Silla";
								Silla si = (Silla)m;
								
								double precio = si.getPrecio();
								String color = si.getColor();
								double peso = si.getPeso();
								String descripcion = si.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+color+"\n"+peso+" kg"+"\n"+descripcion);
																
							}else if(m instanceof Mesa) {
								String nombre = "Mesa";
								Mesa me = (Mesa)m;
								
								double precio = me.getPrecio();
								String color = me.getColor();
								double peso = me.getPeso();
								String descripcion = me.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+color+"\n"+peso+" kg"+"\n"+descripcion);
																
							}else if(m instanceof Armario) {
								String nombre = "Armario";
								Armario a = (Armario)m;
								
								double precio = a.getPrecio();
								String color = a.getColor();
								double peso = a.getPeso();
								String descripcion = a.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+color+"\n"+peso+" kg"+"\n"+descripcion);	
								
								}
						}
						
					
					});
					
					if(m instanceof Sofa) {
						String nombre = "Sofá";
						Sofa s = (Sofa)m;
						double precio = s.getPrecio();
						String descripcion = s.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(m instanceof Silla) {
						String nombre = "Silla";
						Silla si = (Silla)m;
						double precio = si.getPrecio();
						String descripcion = si.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(m instanceof Mesa) {
						String nombre = "Mesa";
						Mesa me = (Mesa)m;
						double precio = me.getPrecio();
						String descripcion = me.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(m instanceof Armario) {
						String nombre = "Armario";
						Armario a = (Armario)m;
						double precio = a.getPrecio();
						String descripcion = a.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}
					
					
				}
				}
			}else if(codigo ==0) {
				// Mostrar elementos de muebles
				
				ImageIcon sofa = new ImageIcon(new ImageIcon("resources/images/sofa.jpeg").getImage().getScaledInstance(200,
						200, java.awt.Image.SCALE_SMOOTH));
				ImageIcon armario = new ImageIcon(new ImageIcon("resources/images/Armario.jpeg").getImage()
						.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
				ImageIcon silla = new ImageIcon(new ImageIcon("resources/images/silla.jpeg").getImage().getScaledInstance(200,
						200, java.awt.Image.SCALE_SMOOTH));
				ImageIcon mesa = new ImageIcon(new ImageIcon("resources/images/mesa.jpeg").getImage().getScaledInstance(200,
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
//				        JOptionPane.showMessageDialog(null, "Has seleccionado el Sofá");
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
//				        JOptionPane.showMessageDialog(null, "Has seleccionado el Armario");
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
//				        JOptionPane.showMessageDialog(null, "Has seleccionado la Silla");
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
//				        JOptionPane.showMessageDialog(null, "Has seleccionado la Mesa");
						ultimaImagenSeleccionada = labelImagen4;
				        mostrarInformacionSeleccionada();
					}
				});

				break;

			}
			
			
			
		case 2:
			if (codigo == 1) {
			    for (Producto p : lista) {
			        if (p instanceof Cocina) { 
			            Cocina c = (Cocina) p;
			            ImageIcon im = new ImageIcon(c.getImagenC().getImage().getScaledInstance(200,
			                    200, java.awt.Image.SCALE_SMOOTH));
			            labelImagen2 = new JLabel(im);
			            panelCentro.add(labelImagen2);
			            labelImagen2.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								
								if(c instanceof Horno) {
									String nombre = "Horno";
									Horno h = (Horno)c;
									
									double precio = h.getPrecio();
									String material = h.getMaterialC();
									double peso = h.getPeso();
									String descripcion = h.getDescripcionC();
									
									JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);	
																		
								}else if(c instanceof Nevera) {
									String nombre = "Nevera";
									Nevera n = (Nevera)c;
									
									double precio = n.getPrecio();
									String material = n.getMaterialC();
									double peso = n.getPeso();
									String descripcion = n.getDescripcionC();
									
									JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
									
								}else if(c instanceof Encimera) {
									String nombre = "Encimera";
									Encimera en = (Encimera)c;
									
									double precio = en.getPrecio();
									String material = en.getMaterialC();
									double peso = en.getPeso();
									String descripcion = en.getDescripcionC();
									
									JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
									
									
								}else if(c instanceof Fregadero) {
									String nombre = "Fregadero";
									Fregadero f = (Fregadero)c;
									
									double precio = f.getPrecio();
									String material = f.getMaterialC();
									double peso = f.getPeso();
									String descripcion = f.getDescripcionC();
									
									JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
									
									
									}
							}
						
						});
						
						if(c instanceof Horno) {
							String nombre = "Horno";
							Horno h = (Horno)c;
							double precio = h.getPrecio();
							String descripcion = h.getDescripcionC();
							
							JLabel l1 = new JLabel(nombre);
							JLabel l2 = new JLabel(String.valueOf(precio)+" €");
							JLabel l3 = new JLabel(descripcion);
							
							l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
							l2.setFont(new Font("Arial", Font.BOLD, 14));
							l3.setFont(new Font("Arial", Font.ITALIC, 14));
							
							JPanel pa = new JPanel(new GridLayout(3, 1));
							pa.add(l1);
							pa.add(l2);
							pa.add(l3);
						
							panelCentro.add(pa);
							
						}else if(c instanceof Nevera) {
							String nombre = "Nevera";
							Nevera n = (Nevera)c;
							double precio = n.getPrecio();
							String descripcion = n.getDescripcionC();
							
							JLabel l1 = new JLabel(nombre);
							JLabel l2 = new JLabel(String.valueOf(precio)+" €");
							JLabel l3 = new JLabel(descripcion);
							
							l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
							l2.setFont(new Font("Arial", Font.BOLD, 14));
							l3.setFont(new Font("Arial", Font.ITALIC, 14));
							
							JPanel pa = new JPanel(new GridLayout(3, 1));
							pa.add(l1);
							pa.add(l2);
							pa.add(l3);
						
							panelCentro.add(pa);
							
						}else if(c instanceof Encimera) {
							String nombre = "Encimera";
							Encimera en = (Encimera)c;
							double precio = en.getPrecio();
							String descripcion = en.getDescripcionC();
							
							JLabel l1 = new JLabel(nombre);
							JLabel l2 = new JLabel(String.valueOf(precio)+" €");
							JLabel l3 = new JLabel(descripcion);
							
							l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
							l2.setFont(new Font("Arial", Font.BOLD, 14));
							l3.setFont(new Font("Arial", Font.ITALIC, 14));
							
							JPanel pa = new JPanel(new GridLayout(3, 1));
							pa.add(l1);
							pa.add(l2);
							pa.add(l3);
						
							panelCentro.add(pa);
							
						}else if(c instanceof Fregadero) {
							String nombre = "Fregadero";
							Fregadero f = (Fregadero)c;
							double precio = f.getPrecio();
							String descripcion = f.getDescripcionC();
							
							JLabel l1 = new JLabel(nombre);
							JLabel l2 = new JLabel(String.valueOf(precio)+" €");
							JLabel l3 = new JLabel(descripcion);
							
							l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
							l2.setFont(new Font("Arial", Font.BOLD, 14));
							l3.setFont(new Font("Arial", Font.ITALIC, 14));
							
							JPanel pa = new JPanel(new GridLayout(3, 1));
							pa.add(l1);
							pa.add(l2);
							pa.add(l3);
						
							panelCentro.add(pa);
							
						}
			            
			        }
			    }
			}
			else if(codigo==0) {
				
			
			// Mostrar elementos de cocina
			ImageIcon nevera = new ImageIcon(new ImageIcon("resources/images/Nevera.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon horno = new ImageIcon(new ImageIcon("resources/images/horno.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon fregadero = new ImageIcon(new ImageIcon("resources/images/fregadero.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon encimera = new ImageIcon(new ImageIcon("resources/images/encimera.jpg").getImage()
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
			}
			break;

		case 3:
			if (codigo==1) {
				for(Producto p: lista) {
					if (p instanceof Baño) {
					Baño b= (Baño)p;
					ImageIcon im = new ImageIcon(b.getImagenB().getImage().getScaledInstance(200,
							200, java.awt.Image.SCALE_SMOOTH));
					labelImagen3 = new JLabel(im);
					panelCentro.add(labelImagen3);
					labelImagen3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							if(b instanceof Bide) {
								String nombre = "Bide";
								Bide bi = (Bide)b;
								
								double precio = bi.getPrecio();
								String material = bi.getMaterialB();
								double peso = bi.getPeso();
								String descripcion = bi.getDescripcionB();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);	
																	
							}else if(b instanceof Ducha) {
								String nombre = "Ducha";
								Ducha d = (Ducha)b;
								
								double precio = d.getPrecio();
								String material = d.getMaterialB();
								double peso = d.getPeso();
								String descripcion = d.getDescripcionB();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
							}else if(b instanceof Inodoro) {
								String nombre = "Inodoro";
								Inodoro i = (Inodoro)b;
								
								double precio = i.getPrecio();
								String material = i.getMaterialB();
								double peso = i.getPeso();
								String descripcion = i.getDescripcionB();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
								
							}else if(b instanceof Lavamanos) {
								String nombre = "Lavamanos";
								Lavamanos la = (Lavamanos)b;
								
								double precio = la.getPrecio();
								String material = la.getMaterialB();
								double peso = la.getPeso();
								String descripcion = la.getDescripcionB();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
								
								}
						}
					
					});
					
					if(b instanceof Bide) {
						String nombre = "Bide";
						Bide bi = (Bide)b;
						double precio = bi.getPrecio();
						String descripcion = bi.getDescripcionB();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(b instanceof Ducha) {
						String nombre = "Ducha";
						Ducha d = (Ducha)b;
						double precio = d.getPrecio();
						String descripcion = d.getDescripcionB();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(b instanceof Inodoro) {
						String nombre = "Inodoro";
						Inodoro i = (Inodoro)b;
						double precio = i.getPrecio();
						String descripcion = i.getDescripcionB();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(b instanceof Lavamanos) {
						String nombre = "Lavamanos";
						Lavamanos la = (Lavamanos)b;
						double precio = la.getPrecio();
						String descripcion = la.getDescripcionB();
						
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}
				}
				}
			}else if(codigo==0) {
				
			// Mostrar elementos de baño
			ImageIcon bide = new ImageIcon(new ImageIcon("resources/images/bide.jpeg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon ducha = new ImageIcon(new ImageIcon("resources/images/ducha.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon inodoro = new ImageIcon(new ImageIcon("resources/images/inodoro.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon lavamanos = new ImageIcon(new ImageIcon("resources/images/lavamanos.jpg").getImage()
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
			}
			break;
			
		case 4:
			if (codigo==1) {
				for(Producto p: lista) {
					if (p instanceof Jardineria) {
					Jardineria j= (Jardineria)p;
					ImageIcon im = new ImageIcon(j.getImagen().getImage().getScaledInstance(200,
							200, java.awt.Image.SCALE_SMOOTH));
					labelImagen4 = new JLabel(im);
					panelCentro.add(labelImagen4);
					labelImagen4.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							if(j instanceof Barbacoa) {
								String nombre = "Barbacoa";
								Barbacoa bar = (Barbacoa)j;
								
								double precio = bar.getPrecio();
								String material = bar.getMaterial();
								double peso = bar.getPeso();
								String descripcion = bar.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);	
																	
							}else if(j instanceof Planta) {
								String nombre = "Planta";
								Planta pl = (Planta)j;
								
								double precio = pl.getPrecio();
								String material = pl.getMaterial();
								double peso = pl.getPeso();
								String descripcion = pl.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
							}else if(j instanceof Maceta) {
								String nombre = "Maceta";
								Maceta ma = (Maceta)j;
								
								double precio = ma.getPrecio();
								String material = ma.getMaterial();
								double peso = ma.getPeso();
								String descripcion = ma.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
								
							}else if(j instanceof Herramienta) {
								String nombre = "Herramienta";
								Herramienta he = (Herramienta)j;
								
								double precio = he.getPrecio();
								String material = he.getMaterial();
								double peso = he.getPeso();
								String descripcion = he.getDescripcion();
								
								JOptionPane.showMessageDialog(null, nombre+"\n"+precio+" €"+"\n"+material+"\n"+peso+" kg"+"\n"+descripcion);
								
								}
						}
					
					});
					
					if(j instanceof Barbacoa) {
						String nombre = "Barbacoa";
						Barbacoa bar = (Barbacoa)j;
						double precio = bar.getPrecio();
						String descripcion = bar.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(j instanceof Planta) {
						String nombre = "Planta";
						Planta pl = (Planta)j;
						double precio = pl.getPrecio();
						String descripcion = pl.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(j instanceof Maceta) {
						String nombre = "Barbacoa";
						Maceta mac = (Maceta)j;
						double precio = mac.getPrecio();
						String descripcion = mac.getDescripcion();
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}else if(j instanceof Herramienta) {
						String nombre = "Herramienta";
						Herramienta he = (Herramienta)j;
						double precio = he.getPrecio();
						String descripcion = he.getDescripcion();
						
						
						JLabel l1 = new JLabel(nombre);
						JLabel l2 = new JLabel(String.valueOf(precio)+" €");
						JLabel l3 = new JLabel(descripcion);
						
						l1.setFont(new Font("Arial", Font.PLAIN, 14)); 
						l2.setFont(new Font("Arial", Font.BOLD, 14));
						l3.setFont(new Font("Arial", Font.ITALIC, 14));
						
						JPanel pa = new JPanel(new GridLayout(3, 1));
						pa.add(l1);
						pa.add(l2);
						pa.add(l3);
					
						panelCentro.add(pa);
						
					}
				}
				}
			}else if(codigo==0) {
			// Mostrar elementos de Jardineria
			ImageIcon barbacoa = new ImageIcon(new ImageIcon("resources/images/barbacoa-40-cm.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon planta = new ImageIcon(new ImageIcon("resources/images/adelfas-colores.jpg").getImage().getScaledInstance(200,
					200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon maceta= new ImageIcon(new ImageIcon("resources/images/Maceta.jpg").getImage()
					.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
			ImageIcon pala = new ImageIcon(new ImageIcon("resources/images/pala2.jpg").getImage()
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
			}
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
			if (codigo==0) {
				labelDescripcion1.setText(
						"<html><b>Sofá</b><br>Precio: $350<br>Peso: 40kg<br>Descripción: Sofá de tres plazas, cómodo y moderno, ideal para cualquier sala de estar.</html>");
				labelDescripcion2.setText(
						"<html><b>Armario</b><br>Precio: $200<br>Peso: 50kg<br>Descripción: Armario espacioso de dos puertas, con estantes internos para optimizar el almacenamiento.</html>");
				labelDescripcion3.setText(
						"<html><b>Silla</b><br>Precio: $75<br>Peso: 5kg<br>Descripción: Silla ergonómica, perfecta para oficina o comedor.</html>");
				labelDescripcion4.setText(
						"<html><b>Mesa</b><br>Precio: $150<br>Peso: 20kg<br>Descripción: Mesa de comedor para seis personas, hecha de madera de alta calidad.</html>");
				panelCentro.add(labelImagen1);
				panelCentro.add(labelDescripcion1);
				panelCentro.add(labelImagen2);
				panelCentro.add(labelDescripcion2);
				panelCentro.add(labelImagen3);
				panelCentro.add(labelDescripcion3);
				panelCentro.add(labelImagen4);
				panelCentro.add(labelDescripcion4);
			}
				break;
			
		case 2:
			if (codigo == 0) {
				
			
			labelDescripcion1.setText(
					"<html><b>Nevera</b><br>Precio: $699.90<br>Peso: 81.5kg<br>Descripción: Nevera con dos puertas.</html>");
			labelDescripcion2.setText(
					"<html><b>Horno</b><br>Precio: $250.8<br>Peso: 67.2kg<br>Descripción: Horno de gran capacidad.</html>");
			labelDescripcion3.setText(
					"<html><b>Fregadero</b><br>Precio: $280.87<br>Peso: 12.5kg<br>Descripción: Fregadero con dos cubetas.</html>");
			labelDescripcion4.setText(
					"<html><b>Encimera</b><br>Precio: $500.99<br>Peso: 100.7kg<br>Descripción: Encimera de granito.</html>");
			panelCentro.add(labelImagen1);
			panelCentro.add(labelDescripcion1);
			panelCentro.add(labelImagen2);
			panelCentro.add(labelDescripcion2);
			panelCentro.add(labelImagen3);
			panelCentro.add(labelDescripcion3);
			panelCentro.add(labelImagen4);
			panelCentro.add(labelDescripcion4);
			}
			break;
			
		
			
		case 3:
			if (codigo ==0) {
				
			labelDescripcion1.setText(
					"<html><b>Bidé</b><br>Precio: $200.60<br>Peso: 20kg<br>Descripción: Bidé compacto de fácil instalación.</html>");
			labelDescripcion2.setText(
					"<html><b>Ducha</b><br>Precio: $320.75<br>Peso: 30kg<br>Descripción: Ducha con sistema de hidromasaje y puerta de vidrio templado.</html>");
			labelDescripcion3.setText(
					"<html><b>Inodoro</b><br>Precio: $180.50<br>Peso: 25kg<br>Descripción: Inodoro de porcelana con sistema de bajo consumo de agua.</html>");
			labelDescripcion4.setText(
					"<html><b>Lavamanos</b><br>Precio: $150.45<br>Peso: 15kg<br>Descripción: Lavamanos de cerámica con grifo monomando.</html>");
			panelCentro.add(labelImagen1);
			panelCentro.add(labelDescripcion1);
			panelCentro.add(labelImagen2);
			panelCentro.add(labelDescripcion2);
			panelCentro.add(labelImagen3);
			panelCentro.add(labelDescripcion3);
			panelCentro.add(labelImagen4);
			panelCentro.add(labelDescripcion4);
			}
			break;
			
		case 4: 
			if (codigo ==0) {
				
			labelDescripcion1.setText(
					"<html><b>Barbacoa</b><br>Precio: $50.99<br>Peso: 150kg<br>Descripción: resistente y duradera, ideal para exteriores. Incluye parrilla ajustable y espacio de almacenamiento,perfecta para disfrutar asados en el jardín.</html>");
			labelDescripcion2.setText(
					"<html><b>Planta</b><br>Precio: $21.99<br>Peso: 20kg<br>Descripción: Es una planta de exterior de tamaño medio, ideal para jardines y patios. Con follaje perenne y flores en tonos rosa, blanco o rojo.</html>");
			labelDescripcion3.setText(
					"<html><b>Maceta</b><br>Precio: $10.75<br>Peso: 3.2kg<br>Descripción: Maceta para plantas tamaño mediano.</html>");
			labelDescripcion4.setText(
					"<html><b>Pala</b><br>Precio: $7.5<br>Peso: 1kg<br>Descripción: herramienta resistente con cabeza de acero para cavar y mover tierra, y mango de madera ergonómico para un agarre cómodo.</html>");
			panelCentro.add(labelImagen1);
			panelCentro.add(labelDescripcion1);
			panelCentro.add(labelImagen2);
			panelCentro.add(labelDescripcion2);
			panelCentro.add(labelImagen3);
			panelCentro.add(labelDescripcion3);
			panelCentro.add(labelImagen4);
			panelCentro.add(labelDescripcion4);
			}
			
		default:
			break;
		}
		
		//panelCentroI.setLayout(new GridLayout(2, 2));
		//panelCentroD.setLayout(new GridLayout(2, 2));

		
		/*panelCentro.add(labelImagen1);
		panelCentro.add(labelDescripcion1);
		panelCentro.add(labelImagen2);
		panelCentro.add(labelDescripcion2);

		panelCentro.add(labelImagen3);
		panelCentro.add(labelDescripcion3);
		panelCentro.add(labelImagen4);
		panelCentro.add(labelDescripcion4);*/
		 	
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);

		botonAtras.addActionListener((e) -> {
			dispose();
			//new VentanaPrincipal(cliente, codigo);
			VentanaDeCarga.vp.setVisible(true);
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
		            	JOptionPane.showMessageDialog(null, "Cantidad añadida al carrito: " + cantidad, "Información", JOptionPane.INFORMATION_MESSAGE);
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