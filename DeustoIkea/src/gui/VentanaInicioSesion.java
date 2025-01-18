package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import db.BD;
import domain.Carrito;
import domain.Cliente;
import domain.Datos;
import domain.SistemaUsuarios;

public class VentanaInicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;

	protected JButton botonRegistro, botonCerrar, botonAtras, botonTrabajador;
//	protected JButton botonInicioSesion;
	protected JPanel panelAbajo, panelArriba, panelEste, panelOeste, panelCentro, panelSuperiorCentro;
	protected JLabel lblNombreUsuario, lblContrasenia, lblRegistro, lblImagen, lblTexto;
	protected JTextField txtNombreUsuario;
	protected JPasswordField txtContrasenia;
	

	protected Datos datos;
	protected  static Cliente cliente;
	private int code;
	static ArrayList<Carrito> carrito;

	@SuppressWarnings("static-access")
	public VentanaInicioSesion(Cliente c, int code) {
		carrito = new ArrayList<Carrito>();
		this.code = code;
		setTitle("Inicio de Sesión");
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getWidth();
		int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
				.getHeight();
		setSize(anchoP, altoP);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		datos = new Datos();
		this.cliente = c;

		botonCerrar = new JButton("CERRAR");
		botonRegistro = new JButton("REGISTRATE");
//		botonInicioSesion = new JButton("INICIO DE SESIÓN");
		botonAtras = new JButton("VOLVER AL INICIO");
		botonTrabajador = new JButton("TRABAJADOR");

		panelAbajo = new JPanel();
		panelCentro = new JPanel();
		panelArriba = new JPanel();
		panelSuperiorCentro = new JPanel();
		panelEste = new JPanel();
		panelOeste = new JPanel();

		lblNombreUsuario = new JLabel("DNI, EMAIL O TELÉFONO:");
		lblContrasenia = new JLabel("CONTRASEÑA:");
		lblRegistro = new JLabel("<---    ¿No tienes cuenta? Regístrate aquí");
		lblTexto = new JLabel("Bienvenido a la ventana de Inicio de sesión");

		ImageIcon img = resizeImageIcon(new ImageIcon("resources/images/app_icon_login_screen.jpeg"), 400, 300);
//        ImageIcon img = new ImageIcon("resources/images/app_icon_login_screen.jpeg");
		lblImagen = new JLabel(img);

		lblNombreUsuario.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblContrasenia.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblRegistro.setBorder(new EmptyBorder(0, 0, 10, 20));

		txtNombreUsuario = new JTextField(20);
		txtContrasenia = new JPasswordField(20);
		txtNombreUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		txtContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		txtNombreUsuario.setColumns(40);
		txtContrasenia.setColumns(40);

		panelOeste.add(lblNombreUsuario);
		panelOeste.add(txtNombreUsuario);
		panelOeste.add(Box.createVerticalStrut(20));
		panelEste.add(lblContrasenia);
		panelEste.add(txtContrasenia);
		panelEste.add(Box.createVerticalStrut(20));
//		panelEste.add(botonInicioSesion);

		panelSuperiorCentro.setLayout(new GridLayout(1, 2));
		panelSuperiorCentro.add(panelOeste);
		panelSuperiorCentro.add(panelEste);

		panelCentro.setLayout(new BorderLayout());
		panelCentro.add(panelSuperiorCentro, BorderLayout.NORTH);
		panelCentro.add(lblImagen, BorderLayout.SOUTH);

		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonTrabajador);
		panelAbajo.add(botonAtras);
		panelAbajo.add(botonRegistro);
		panelAbajo.add(lblRegistro);

		panelCentro.add(lblImagen);

		panelArriba.add(lblTexto);

		panelOeste.setBorder(new EmptyBorder(50, 50, 50, 50));
		panelEste.setBorder(new EmptyBorder(50, 50, 50, 50));

		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);

		botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});

		botonAtras.addActionListener((e) -> {
			new VentanaDeCarga();
			dispose();
		});

		botonTrabajador.addActionListener((e) -> {
			solicitarCodigoTrabajador();
		});

		botonRegistro.addActionListener((e) -> {
			dispose();
			new VentanaRegistro(code);
		});

//		botonInicioSesion.addActionListener((e) -> {
//			iniciarSesion();
//		});
		
		txtContrasenia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });
        
        txtNombreUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_I) {
                    txtNombreUsuario.setText("maria.fernandez@gmail.com");
                    txtContrasenia.setText("mariaPass1238976");
                    JOptionPane.showMessageDialog(null, "Usuario y contraseña predeterminados cargados.");
                    iniciarSesion();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });      
        
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}

	private ImageIcon resizeImageIcon(ImageIcon originalIcon, int width, int height) {
		Image img = originalIcon.getImage();
		Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}


	
	private void iniciarSesion() {
	    String user = txtNombreUsuario.getText();
	    String contra = new String(txtContrasenia.getPassword());

	    if (user.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Inserte un teléfono, mail o DNI válido");
	        return;
	    } else if (contra.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Inserte la contraseña");
	        return;
	    }

	    Cliente c = null;

	    if (code == 0) {
	        c = SistemaUsuarios.getInstancia().buscarCliente(user);

	        if (c == null) {
	            c = datos.buscarCliente(user);
	        }

	        if (c == null || (!user.equals(c.getDni()) && !user.equals(c.getEmail()) && !user.equals(c.getTelefono()))) {
	            JOptionPane.showMessageDialog(null, "DNI, correo electrónico o teléfono no válido", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    } else if (code == 1) {
	        c = BD.buscarCliente(user, contra);

	        if (c == null) {
	            JOptionPane.showMessageDialog(null, "DNI, correo electrónico, teléfono o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	    }

	    if (c != null && !contra.equals(c.getContrasenia())) {
	        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
	    } else if (c != null) {
	        JOptionPane.showMessageDialog(null, "¡BIENVENID@! " + c.getNombre().toUpperCase(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        if (code == 1) {
	            BD.actualizarUltimoLogin(c.getDni());
	            carrito = BD.recuperarCarrito(c.getDni());
	        }
	        cliente = c;
	        txtNombreUsuario.setText("");
	        txtContrasenia.setText("");
	        dispose();
	        //new VentanaPrincipal(c, code);
	        
	        VentanaDeCarga.vp = new VentanaPrincipal(c, code);
	        VentanaDeCarga.vp.setVisible(true);
	    }
	}


	private void solicitarCodigoTrabajador() {
		String codigo = JOptionPane.showInputDialog(this, "Introduce el código de trabajador:");

		if (codigo == null) {
			return;
		}

		if ("777".equals(codigo)) {
			JOptionPane.showMessageDialog(this, "Acceso concedido");
			dispose();
			new VentanaInicioTrabajador(code);
		} else {
			JOptionPane.showMessageDialog(this, "Código incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
