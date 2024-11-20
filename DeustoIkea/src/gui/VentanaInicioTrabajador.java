package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Cliente;

public class VentanaInicioTrabajador extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonIniciarSesion, botonCerrar;
	protected JPanel panelPrincipal, panelBotones;
	
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
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);
        panelBotones.add(botonIniciarSesion);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
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
        	
        });
        
        private void iniciarSesion() {
    		String user = txtNombreUsuario.getText();
//        	char[] contraa = txtContrasenia.getPassword();
//        	String contra = new String(contraa);
    		String contra = new String(txtContrasenia.getPassword());

    		if (user.isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Inserte un telefono, mail o nombre de usuario valido");
    		} else if (contra.isEmpty()) {
    			JOptionPane.showMessageDialog(null, "Inserte la contraseña");
    		} else {
    			Cliente c = datos.buscarCliente(user);
    			if (c == null
    					|| (!user.equals(c.getDni()) && !user.equals(c.getEmail()) && !user.equals(c.getTelefono()))) {
    				JOptionPane.showMessageDialog(null, "Nombre de usuario, correo electrónico o teléfono no válido",
    						"Error", JOptionPane.ERROR_MESSAGE);
    			} else {
    				if (!contra.equals(c.getContrasenia())) {
    					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
    				} else {
    					JOptionPane.showMessageDialog(null, "¡BIENVENID@! " + c.getNombre().toUpperCase(), "Éxito",
    							JOptionPane.INFORMATION_MESSAGE);
    					cliente = c;
    					txtNombreUsuario.setText("");
    					txtContrasenia.setText("");
    					dispose();
    					new VentanaPrincipal();
    				}
    			}
    		}
    	}
        
        setVisible(true);
    }
}
