package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Datos;

public class VentanaInicioSesion extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonRegistro, botonAtras, botonInicioSesion;
    protected JPanel panelAbajo, panelArriba, panelEste, panelOeste, panelCentro;
    protected JLabel lblNombreUsuario, lblContrasenia, lblRegistro, lblFoto;
    protected JTextField txtNombreUsuario;
    protected JPasswordField txtContrasenia;	
    
    protected Datos datos;
    
    public VentanaInicioSesion() {
        setTitle("Inicio de Sesión");
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        botonAtras = new JButton("VOLVER");
        botonRegistro = new JButton("                    REGISTRATE                   ");
        botonInicioSesion = new JButton("INICIO DE SESIÓN");
        
        panelAbajo = new JPanel();
        panelCentro = new JPanel();
        panelArriba = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        
        lblNombreUsuario = new JLabel();
        lblContrasenia = new JLabel();
        lblRegistro = new JLabel();
        lblFoto = new JLabel();  
        
        JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BorderLayout());
		
		lblNombreUsuario = new JLabel("USUARIO, EMAIL O TELÉFONO:");
		lblNombreUsuario.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblNombreUsuario.setFont(new Font("Tw", Font.BOLD, 14));
		txtNombreUsuario = new JTextField(20);
		panelOeste.add(lblNombreUsuario);
		panelOeste.add(txtNombreUsuario);
		panelOeste.add(Box.createVerticalStrut(30));
        
		lblContrasenia = new JLabel("  CONTRASEÑA: ");
		lblContrasenia.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblContrasenia.setFont(new Font("Tw", Font.BOLD, 14));
		txtContrasenia = new JPasswordField(20);
		
		panelOeste.add(lblContrasenia);
		panelOeste.add(txtContrasenia);
		panelOeste.add(Box.createVerticalStrut(30));
		
		txtNombreUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		txtContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		
		txtNombreUsuario.setColumns(40);
		txtContrasenia.setColumns(40);
        
		panelEste = new JPanel();
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
		lblRegistro = new JLabel("¿No tienes cuenta? Regístrate aquí");
		lblRegistro.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblRegistro.setFont(new Font("Tw", Font.BOLD, 14));
		
		
		panelEste.add(lblRegistro);
		panelEste.add(botonRegistro);
		panelEste.add(Box.createVerticalStrut(30));
		
		
		panelAbajo = new JPanel();
		panelAbajo.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		panelAbajo.add(botonAtras);
		
		
		panelAbajo.add(botonInicioSesion);
		panelOeste.setBorder(new EmptyBorder(200, 200, 200, 200 ));
		panelEste.setBorder(new EmptyBorder(200, 200, 200, 200 ));
		
		botonAtras.addActionListener((e)-> {

		});

		botonRegistro.addActionListener((e) -> {
			
		});

		botonInicioSesion.addActionListener((e)-> {

		});
        
        setLocationRelativeTo(null); 
        setVisible(true);
    }
   
    private void iniciarSesion() {
		String corrTlfUsu = txtNombreUsuario.getText();
		char[] contChar = txtContrasenia.getPassword();
		String contrasenia = new String(contChar);
		
		if(corrTlfUsu.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte el teléfono, el mail o el nombre de usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else if(contrasenia.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte la contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else {
			Cliente cliente = Restaurante.buscarUsuario(corrTlfUsu);
//			Connection con = BD.initBD(Main.nombreBD);
//			Cliente cliente = BD.buscarCliente(con, corrTlfUsu, corrTlfUsu, corrTlfUsu);
			if(cliente==null || (!corrTlfUsu.equals(cliente.getCorreo()) && !corrTlfUsu.equals(cliente.getTelefono()) && !corrTlfUsu.equals(cliente.getNombreUsuario()))) {
				JOptionPane.showMessageDialog(null, "Nombre de usuario, correo electrónico o teléfono no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				if(!contrasenia.equals(cliente.getContrasenia())) {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "¡BIENVENID@! "+ cliente.getNombreUsuario().toUpperCase(), "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
					cli=cliente;
					txtNombreUsuario.setText("");
					txtContrasenia.setText("");
					new VentanaCliente(vActual);
			}
		}
		
		}
	}
    
}




