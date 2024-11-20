package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.Cliente;
import domain.Datos;
import domain.Trabajador;

public class VentanaInicioTrabajador extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonIniciarSesion, botonCerrar;
	protected JPanel panelPrincipal, panelBotones, panelOeste, panelEste;
	protected JLabel lblNombreUsuario, lblContrasenia, lblRegistro, lblImagen, lblTexto;
	protected JTextField txtNombreUsuario;
	protected JPasswordField txtContrasenia;
	private static Trabajador tbj;
	
	public static Trabajador getTrabajador() {
		return tbj;
	}

	protected Datos datos;
	protected static Trabajador trabajador;
	
	public VentanaInicioTrabajador() {
        setTitle("Inicio Sesion-Trabajador");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        botonAtras = new JButton("ATRAS");
        botonCerrar = new JButton("CERRAR");
        botonIniciarSesion = new JButton("INICIAR SESION");
        
        panelBotones = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        
        lblNombreUsuario = new JLabel("USUARIO, EMAIL O TELÉFONO:");
		lblContrasenia = new JLabel("CONTRASEÑA:");
		lblRegistro = new JLabel("<---    ¿No tienes cuenta? Regístrate aquí");
		lblTexto = new JLabel("Bienvenido a la ventana de Inicio de sesión");
		
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
		panelEste.add(botonIniciarSesion);
        
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);
        panelBotones.add(botonIniciarSesion);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelOeste, BorderLayout.WEST);
        panelPrincipal.add(panelEste, BorderLayout.EAST);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        add(panelPrincipal);
        
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaInicioSesion();
		});
        
        botonCerrar.addActionListener((e) -> {
			System.exit(0);
		});
        
        botonIniciarSesion.addActionListener((e) -> {
        	iniciarSesion();
        });
        
        setVisible(true);
    }
	
	private void iniciarSesion() {
		String user = txtNombreUsuario.getText();
//    	char[] contraa = txtContrasenia.getPassword();
//    	String contra = new String(contraa);
		String contra = new String(txtContrasenia.getPassword());

		if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte un telefono, mail o nombre de usuario valido");
		} else if (contra.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte la contraseña");
		} else {
			Trabajador t = datos.buscarTrabajador(user);
			if (t == null
					|| (!user.equals(t.getDni()) && !user.equals(t.getEmail()) && !user.equals(t.getTelefono()))) {
				JOptionPane.showMessageDialog(null, "Nombre de trabajador, correo electrónico o teléfono no válido",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!contra.equals(t.getContrasenia())) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "¡BIENVENID@! " + t.getNombre().toUpperCase(), "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					trabajador = t;
					txtNombreUsuario.setText("");
					txtContrasenia.setText("");
					dispose();
					new VentanaTrabajador();
				}
			}
		}
	}
}
