package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Datos;

public class VentanaInicioSesion extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonRegistro, botonAtras, botonInicioSesion;
    protected JPanel panelAbajo, panelArriba, panelEste, panelOeste, panelCentro;
    protected JLabel lblNombreUsuario, lblContrasenia, lblRegistro;
    protected JTextField txtNombreUsuario;
    protected JPasswordField txtContrasenia;	
    
    protected Datos datos;
    
    public VentanaInicioSesion() {
        setTitle("Inicio de Sesión");
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        botonAtras = new JButton("VOLVER");
        botonRegistro = new JButton("REGISTRATE");
        botonInicioSesion = new JButton("INICIO DE SESIÓN");
        
        panelAbajo = new JPanel();
        panelCentro = new JPanel();
        panelArriba = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        
        lblNombreUsuario = new JLabel("USUARIO, EMAIL O TELÉFONO:");
        lblContrasenia = new JLabel("CONTRASEÑA:");
        lblRegistro = new JLabel("<---    ¿No tienes cuenta? Regístrate aquí");
        
		lblNombreUsuario.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblContrasenia.setBorder(new EmptyBorder(0, 0, 10, 20));
		
		txtNombreUsuario = new JTextField(20);
		txtContrasenia = new JPasswordField(20);
		txtNombreUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		txtContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		txtNombreUsuario.setColumns(40);
		txtContrasenia.setColumns(40);
		
		panelOeste.add(lblNombreUsuario);
		panelOeste.add(txtNombreUsuario);
		panelOeste.add(Box.createVerticalStrut(20));
       	panelOeste.add(lblContrasenia);
		panelOeste.add(txtContrasenia);
		panelOeste.add(Box.createVerticalStrut(20));
        
		lblRegistro.setBorder(new EmptyBorder(0, 0, 10, 20));
		
		panelEste.add(lblRegistro);
		panelEste.add(Box.createVerticalStrut(20));
				
		panelAbajo.add(botonAtras);
		panelAbajo.add(botonInicioSesion);
		panelAbajo.add(botonRegistro);
		
		panelOeste.setBorder(new EmptyBorder(50, 50, 50, 50));
        panelEste.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);
		getContentPane().add(panelEste, BorderLayout.EAST);
		getContentPane().add(panelOeste, BorderLayout.WEST);
		
		botonAtras.addActionListener((e)-> {
			dispose();
		});

		botonRegistro.addActionListener((e) -> {
			
		});

		botonInicioSesion.addActionListener((e)-> {

		});
        
        setLocationRelativeTo(null); 
        setVisible(true);
    }
   
//    private void iniciarSesion() {
//		String corrTlfUsu = txtNombreUsuario.getText();
//		char[] contChar = txtContrasenia.getPassword();
//		String contrasenia = new String(contChar);
//		
//		if(corrTlfUsu.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Inserte el teléfono, el mail o el nombre de usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
//		}else if(contrasenia.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Inserte la contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
//		}else {
//			if(!contrasenia.equals()) {
//				JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
//			}else {
//				JOptionPane.showMessageDialog(null, "¡BIENVENID@! "+ "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
//				txtNombreUsuario.setText("");
//				txtContrasenia.setText("");
//			}
//		}
//		
//		}
//	}
    
}




