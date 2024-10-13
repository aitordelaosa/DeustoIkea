package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Datos;

public class VentanaInicioSesion extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonRegistro, botonCerrar, botonInicioSesion;
    protected JPanel panelAbajo, panelArriba, panelEste, panelOeste, panelCentro, panelSuperiorCentro;
    protected JLabel lblNombreUsuario, lblContrasenia, lblRegistro, lblImagen, lblTexto;
    protected JTextField txtNombreUsuario;
    protected JPasswordField txtContrasenia;	
    
    protected Datos datos;
    
    public VentanaInicioSesion() {
        setTitle("Inicio de Sesión");
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        botonCerrar = new JButton("CERRAR");
        botonRegistro = new JButton("REGISTRATE");
        botonInicioSesion = new JButton("INICIO DE SESIÓN");
        
        panelAbajo = new JPanel();
        panelCentro = new JPanel();
        panelArriba = new JPanel();
        panelSuperiorCentro = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        
        lblNombreUsuario = new JLabel("USUARIO, EMAIL O TELÉFONO:");
        lblContrasenia = new JLabel("CONTRASEÑA:");
        lblRegistro = new JLabel("<---    ¿No tienes cuenta? Regístrate aquí");
        lblTexto = new JLabel("Bienvenido a la ventana de Inicio de sesión");
        
        ImageIcon img = resizeImageIcon(new ImageIcon("src/Imagenes/app_icon_login_screen.jpeg"), 600, 400);
//        ImageIcon img = new ImageIcon("src/Imagenes/app_icon_login_screen.jpeg");
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
		
		panelSuperiorCentro.setLayout(new GridLayout(1, 2));
        panelSuperiorCentro.add(panelOeste);
        panelSuperiorCentro.add(panelEste);
        
        panelCentro.setLayout(new BorderLayout());
        panelCentro.add(panelSuperiorCentro, BorderLayout.NORTH);
        panelCentro.add(lblImagen, BorderLayout.SOUTH);
				
		panelAbajo.add(botonCerrar);
		panelAbajo.add(botonInicioSesion);
		panelAbajo.add(botonRegistro);
		panelAbajo.add(lblRegistro);
		
		panelCentro.add(lblImagen);
		
		panelArriba.add(lblTexto);
		
		panelOeste.setBorder(new EmptyBorder(50, 50, 50, 50));
        panelEste.setBorder(new EmptyBorder(50, 50, 50, 50));
		
		getContentPane().add(panelAbajo, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelArriba, BorderLayout.NORTH);
		
		botonCerrar.addActionListener((e)-> {
			dispose();
		});

		botonRegistro.addActionListener((e) -> {
			
		});

		botonInicioSesion.addActionListener((e)-> {

		});
        
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    private ImageIcon resizeImageIcon(ImageIcon originalIcon, int width, int height) {
        Image img = originalIcon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
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




