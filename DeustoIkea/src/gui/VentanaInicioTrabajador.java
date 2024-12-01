package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.BD;
import domain.Datos;
import domain.Trabajador;

public class VentanaInicioTrabajador extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonIniciarSesion, botonCerrar, botonAyudaInicio;
	protected JPanel panelPrincipal, panelBotones, panelCentro;
	protected JLabel lblNombreUsuario, lblContrasenia, lblTexto;
	protected JTextField txtNombreUsuario;
	protected JPasswordField txtContrasenia;
	private static Trabajador tbj;
	
	public static Trabajador getTrabajador() {
		return tbj;
	}

	protected Datos datos;
	protected static Trabajador trabajador;
	private int codigo;
	
	public VentanaInicioTrabajador(int codigo) {
		this.codigo = codigo;
		datos = new Datos();
		
        setTitle("Inicio Sesion - Trabajador");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
//        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
		botonAtras = new JButton("ATRÁS");
        botonCerrar = new JButton("CERRAR");
        botonIniciarSesion = new JButton("INICIAR SESIÓN");
        botonAyudaInicio = new JButton("AYUDA_INICIO");

        lblTexto = new JLabel("Bienvenido a la ventana de Inicio de Sesión", JLabel.CENTER);
        lblTexto.setFont(new Font("Arial", Font.BOLD, 16));
        lblTexto.setBorder(new EmptyBorder(20, 0, 20, 0));

        lblNombreUsuario = new JLabel("Usuario, email o teléfono:");
        lblContrasenia = new JLabel("Contraseña:");

        txtNombreUsuario = new JTextField(20);
        txtContrasenia = new JPasswordField(20);
        
        Dimension campoDimension = new Dimension(400, 30);
        txtNombreUsuario.setPreferredSize(campoDimension);
        txtNombreUsuario.setMaximumSize(campoDimension);
        txtContrasenia.setPreferredSize(campoDimension);
        txtContrasenia.setMaximumSize(campoDimension);
		
        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelCentro.add(lblNombreUsuario);
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(txtNombreUsuario);
        panelCentro.add(Box.createVerticalStrut(20));
        panelCentro.add(lblContrasenia);
        panelCentro.add(Box.createVerticalStrut(10));
        panelCentro.add(txtContrasenia);
        panelCentro.add(Box.createVerticalStrut(30));
        panelCentro.add(botonIniciarSesion);
        
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);
        panelBotones.add(botonAyudaInicio);
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(lblTexto, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        getContentPane().add(panelPrincipal);
        
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaInicioSesion(null, codigo);
		});
        
        botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
        
        botonIniciarSesion.addActionListener((e) -> {
        	iniciarSesion();
        });
        
        botonAyudaInicio.addActionListener((e) -> { 
            JOptionPane.showMessageDialog(
                null, 
                "Usuario: 1A\nContraseña: 123", 
                "Ayuda de Inicio de Sesión", 
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        
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
                    txtNombreUsuario.setText("87654321B");
                    txtContrasenia.setText("123993973");
                    JOptionPane.showMessageDialog(null, "Usuario y contraseña de trabajador predeterminado cargados.");
                    iniciarSesion();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }); 
        
        setVisible(true);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
	
	private void iniciarSesion() {
		String user = txtNombreUsuario.getText();
		String contra = new String(txtContrasenia.getPassword());

		if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte un telefono, mail o nombre de usuario valido");
		} else if (contra.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte la contraseña");
		} else {
			Trabajador t = null;

			if (codigo == 0) {
			    t = datos.buscarTrabajador(user); 
			    if (t == null) {
			        JOptionPane.showMessageDialog(null, "Nombre de trabajador, correo electrónico o teléfono no válido", "Error", JOptionPane.ERROR_MESSAGE);
			        return; 
			    }
			} else if (codigo == 1) {
			    t = BD.buscarTrabajador(user, contra); 
			    if (t == null) {
			        JOptionPane.showMessageDialog(null, "DNI, correo electrónico, teléfono o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			        return; 
			    }
			
			
		if (!contra.equals(t.getContrasenia())) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "¡BIENVENID@! " + t.getNombre().toUpperCase(), "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					trabajador = t;
					txtNombreUsuario.setText("");
					txtContrasenia.setText("");
					dispose();
					new VentanaTrabajador(codigo, datos);
				}
			}
		}
	}
	
}
