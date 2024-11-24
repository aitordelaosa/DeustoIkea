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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import domain.Armario;
import domain.Cliente;
import domain.Datos;
import domain.Descuento;
import domain.Mesa;
import domain.Silla;
import domain.Sofa;

public class VentanaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JPanel panelPrincipal, panelSeccionCocina, panelSeccionMuebles, panelSeccionBaño, panelSeccionJardineria, panelAbajo, panelSuperior, panelSuperiorOeste, panelSuperiorEste;
    protected JLabel lblCocina, lblMuebles, lblBaño, lblJardineria, lblDescripcion;
    protected JButton botonCerrar, botonAtras, botonPerfil, botonCarrito, botonAyuda, botonDescuentos;
    
    protected ModeloMuebles modeloTablaMuebles;
    protected JTable tablaMuebles;
    protected JScrollPane scrollTablaMuebles;
    
    protected Datos datos;
    private Cliente cliente;
    private int codigo;

    public VentanaPrincipal(Cliente cliente, int codigo) {
    	this.codigo = codigo;
    	this.cliente = cliente;
        setTitle("Ventana Principal - DeustoIkea");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        botonCerrar = new JButton("CERRAR");
        botonAtras = new JButton("ATRAS");
        
        ImageIcon iconoPerfil = new ImageIcon(new ImageIcon("src/Imagenes/perfil1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoCarrito = new ImageIcon(new ImageIcon("src/Imagenes/carrito1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoAyuda = new ImageIcon(new ImageIcon("src/Imagenes/ayuda1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoDescuentos = new ImageIcon(new ImageIcon("src/Imagenes/cupon.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        
        botonPerfil = new JButton(iconoPerfil);
        botonCarrito = new JButton(iconoCarrito);
        botonAyuda = new JButton(iconoAyuda);
        botonDescuentos = new JButton(iconoDescuentos);

        panelSuperior = new JPanel(new BorderLayout());
        panelSuperiorEste = new JPanel();
        panelSuperiorOeste = new JPanel();
        
        JPanel panelBotonesPerfilCarrito = new JPanel(new BorderLayout());
        panelSuperiorEste.add(botonCarrito);
        panelSuperiorOeste.add(botonPerfil);
        panelSuperiorOeste.add(botonAyuda);
        panelSuperiorOeste.add(botonDescuentos);
        panelBotonesPerfilCarrito.add(panelSuperiorEste, BorderLayout.EAST);
        panelBotonesPerfilCarrito.add(panelSuperiorOeste, BorderLayout.WEST);
        panelSuperior.add(panelBotonesPerfilCarrito, BorderLayout.NORTH);

        panelPrincipal = new JPanel(new GridLayout(2, 2, 20, 20)); // 2x2 grid con espacio de 20px entre secciones
        
        panelSeccionCocina = new JPanel();
        panelSeccionMuebles = new JPanel();
        panelSeccionBaño = new JPanel();
        panelSeccionJardineria = new JPanel();
        panelAbajo = new JPanel();
        
        ImageIcon cocina = new ImageIcon(new ImageIcon("src/Imagenes/Cocina1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon muebles = new ImageIcon(new ImageIcon("src/Imagenes/Muebles1.jpeg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon baño = new ImageIcon(new ImageIcon("src/Imagenes/baño.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        ImageIcon jardineria = new ImageIcon(new ImageIcon("src/Imagenes/Jardineria.jpg").getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));

        lblCocina = new JLabel(cocina);
        lblMuebles = new JLabel(muebles);
        lblBaño = new JLabel(baño);
        lblJardineria = new JLabel(jardineria);
        
        panelSeccionCocina.add(lblCocina);
        panelSeccionMuebles.add(lblMuebles);
        panelSeccionBaño.add(lblBaño);
        panelSeccionJardineria.add(lblJardineria);
        
        JLabel lblTextoCocina = new JLabel("Cocina");
        JLabel lblTextoMuebles = new JLabel("Muebles");
        JLabel lblTextoBaño = new JLabel("Baño");
        JLabel lblTextoJardineria = new JLabel("Jardinería");
     
        lblTextoCocina.setForeground(Color.BLACK);
        lblTextoCocina.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoCocina.setBounds(50, 80, 150, 30);

        lblTextoMuebles.setForeground(Color.BLACK);
        lblTextoMuebles.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoMuebles.setBounds(50, 80, 150, 30);

        lblTextoBaño.setForeground(Color.BLACK);
        lblTextoBaño.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoBaño.setBounds(50, 80, 150, 30);

        lblTextoJardineria.setForeground(Color.BLACK);
        lblTextoJardineria.setFont(new Font("Arial", Font.BOLD, 16));
        lblTextoJardineria.setBounds(50, 80, 150, 30);
        
        panelSeccionCocina.add(lblTextoCocina);
        panelSeccionMuebles.add(lblTextoMuebles);
        panelSeccionBaño.add(lblTextoBaño);
        panelSeccionJardineria.add(lblTextoJardineria);
        
        lblTextoBaño.setVisible(false);
        lblTextoMuebles.setVisible(false);
        lblTextoCocina.setVisible(false);
        lblTextoJardineria.setVisible(false);
        
        panelSeccionCocina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                panelSeccionCocina.add(lblTextoCocina);
//                panelSeccionCocina.repaint(); 
            	lblTextoCocina.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                panelSeccionCocina.remove(lblTextoCocina);
//                panelSeccionCocina.repaint();  
                lblTextoCocina.setVisible(false);
            }
            public void mouseClicked(MouseEvent e) {
            	new VentanaPostPrincipal(2, cliente, codigo);
                dispose();
            }
        });

        panelSeccionMuebles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                panelSeccionMuebles.add(lblTextoMuebles);
//                panelSeccionMuebles.repaint();
                lblTextoMuebles.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                panelSeccionMuebles.remove(lblTextoMuebles);
//                panelSeccionMuebles.repaint();
            	lblTextoMuebles.setVisible(false);
            }
            public void mouseClicked(MouseEvent e) {
          	new VentanaPostPrincipal(1, cliente, codigo);
              dispose();
          }
        });

        panelSeccionBaño.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                panelSeccionBaño.add(lblTextoBaño);
//                panelSeccionBaño.repaint();
            	lblTextoBaño.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                panelSeccionBaño.remove(lblTextoBaño);
//                panelSeccionBaño.repaint();
            	lblTextoBaño.setVisible(false);
            }
            public void mouseClicked(MouseEvent e) {
                new VentanaPostPrincipal(3, cliente, codigo);
            }
        });

        panelSeccionJardineria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                panelSeccionJardineria.add(lblTextoJardineria);
//                panelSeccionJardineria.repaint();
            	lblTextoJardineria.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                panelSeccionJardineria.remove(lblTextoJardineria);
//                panelSeccionJardineria.repaint();
                lblTextoJardineria.setVisible(false);
            }
            public void mouseClicked(MouseEvent e) {
            	new VentanaPostPrincipal(4, cliente, codigo);
            }
        });
        
        panelAbajo.add(botonAtras);
        panelAbajo.add(botonCerrar);
        
        panelPrincipal.add(panelSeccionCocina);
        panelPrincipal.add(panelSeccionMuebles);
        panelPrincipal.add(panelSeccionBaño);
        panelPrincipal.add(panelSeccionJardineria);   
        
        
        botonCerrar.addActionListener((e)-> {
			System.exit(0);
		});
        
        botonAtras.addActionListener((e) -> {
        	dispose();
        	new VentanaDeCarga();
        });
        
        botonPerfil.addActionListener((e) -> {
        	dispose();
            new VentanaPerfil(cliente, codigo);
        });

        botonCarrito.addActionListener((e) -> {
        	dispose();
            new VentanaCarrito(cliente, codigo);
        });
        
        botonDescuentos.addActionListener((e) -> {
            if (cliente != null) {
                new VentanaDescuentos(cliente, codigo);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error: No se pudo cargar la información del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        botonAyuda.addActionListener((e) -> {
        	dispose();
        	new VentanaAyuda(cliente);
        });
        
        Datos datos = new Datos();
        Mesa mesa = datos.getMesa();
        Silla silla = datos.getSilla();
        Armario armario = datos.getArmario();
        Sofa sofa = datos.getSofa();
        
        lblDescripcion = new JLabel();
        lblDescripcion.setText("<html><b>Algunos de nuestros muebles más destacados: </b></html>");
        lblDescripcion.setHorizontalAlignment(JLabel.CENTER);
        JPanel panelDescripcion = new JPanel(new BorderLayout());
        panelDescripcion.add(lblDescripcion, BorderLayout.NORTH);
        
        modeloTablaMuebles = new ModeloMuebles(null);		
		tablaMuebles = new JTable(modeloTablaMuebles);
		tablaMuebles.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
		tablaMuebles.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaMuebles.getColumnModel().getColumn(2).setCellRenderer(new TableRenderer());
		tablaMuebles.getColumnModel().getColumn(1).setCellRenderer(new TableRenderer());
		tablaMuebles.setRowHeight(100);
		scrollTablaMuebles = new JScrollPane(tablaMuebles);
		panelDescripcion.add(scrollTablaMuebles, BorderLayout.CENTER);
		
		//Panel contenedor para todo
        JPanel panelContenedor = new JPanel(new BorderLayout());
        panelSuperior.add(panelPrincipal, BorderLayout.CENTER);
        panelContenedor.add(panelSuperior, BorderLayout.NORTH);
        panelContenedor.add(panelAbajo, BorderLayout.SOUTH);
	    panelContenedor.add(panelDescripcion, BorderLayout.CENTER);
	    JScrollPane scrollPane = new JScrollPane(panelContenedor);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
        
		modeloTablaMuebles.addProducto(mesa);
		modeloTablaMuebles.addProducto(silla);
		modeloTablaMuebles.addProducto(armario);
		modeloTablaMuebles.addProducto(sofa);
		
		panelContenedor.setFocusable(true);
        panelContenedor.requestFocusInWindow();
        
        //Funcion control+W para volver a la ventana de carga
        panelContenedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
                    dispose();
                    new VentanaDeCarga();
                }
            }
        });
        
      //Funcion control+D para conseguir descuento
        panelContenedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_D) {
                    String codigo = JOptionPane.showInputDialog(VentanaPrincipal.this, "Introduce el código de descuento:");

                    if (codigo != null) {
                        Descuento descuento = null;
                        switch (codigo.trim().toUpperCase()) {
                            case "10":
                                descuento = Descuento.Descuento_10;
                                break;
                            case "15":
                                descuento = Descuento.Descuento_15;
                                break;
                            case "20":
                                descuento = Descuento.Descuento_20;
                                break;
                        }

                        if (descuento != null) {
                            cliente.setDescuento(descuento);
                            JOptionPane.showMessageDialog(VentanaPrincipal.this, "Descuento del " + descuento.name() + " aplicado.");
                        } else {
                            JOptionPane.showMessageDialog(VentanaPrincipal.this, "Código de descuento no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
}