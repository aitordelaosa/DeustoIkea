package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VentanaRegistro extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel pSur, pPrincipal, pEste, pOeste;
	private JLabel lblNombre, lblApellido, lblTlf, lblDireccion, lblEmail, lblNombreUsuario, lblContrasenia, lblRepetirContrasenia, lblInicioSesion;
	private JTextField txtNombre, txtApellido, txtTlf, txtDireccion, txtEmail, txtNombreUsuario;
	private JPasswordField txtContrasenia, txtRepetirContrasenia;
	private JButton btnRegistro, btnCerrar, btnInicioSesion;
	//private JFrame vActual;
	

	
	public VentanaRegistro(/*JFrame va*/) {

		super();
	
		//vActual = this;
		int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		pPrincipal = new JPanel();
		pPrincipal.setLayout(new BorderLayout());

		pOeste = new JPanel();
		pOeste.setLayout(new BoxLayout(pOeste, BoxLayout.Y_AXIS));

		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setBorder(new EmptyBorder(0, 0, 10, 20)); 
		lblNombre.setFont(new Font("Tw", Font.BOLD, 14));
		txtNombre = new JTextField(20);
		
		pOeste.add(lblNombre);
		pOeste.add(txtNombre);
		pOeste.add(Box.createVerticalStrut(30));

		lblApellido = new JLabel("APELLIDOS:");
		lblApellido.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblApellido.setFont(new Font("Tw", Font.BOLD, 14));
		txtApellido = new JTextField(20);

		pOeste.add(lblApellido);
		pOeste.add(txtApellido);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblTlf = new JLabel("TELÉFONO:");
		lblTlf.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblTlf.setFont(new Font("Tw", Font.BOLD, 14));
		txtTlf = new JTextField(20);
	
		pOeste.add(lblTlf);
		pOeste.add(txtTlf);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblDireccion = new JLabel("DIRECCIÓN:");
		lblDireccion.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblDireccion.setFont(new Font("Tw", Font.BOLD, 14));
		txtDireccion = new JTextField(20);
	
		pOeste.add(lblDireccion);
		pOeste.add(txtDireccion);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblEmail = new JLabel("EMAIL:");
		lblEmail.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblEmail.setFont(new Font("Tw", Font.BOLD, 14));
		txtEmail = new JTextField();
	
		pOeste.add(lblEmail);
		pOeste.add(txtEmail);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblNombreUsuario = new JLabel("NOMBRE DE USUARIO:");
		lblNombreUsuario.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblNombreUsuario.setFont(new Font("Tw", Font.BOLD, 14));
		txtNombreUsuario = new JTextField(20);
		pOeste.add(lblNombreUsuario);
		pOeste.add(txtNombreUsuario);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblContrasenia = new JLabel("  CONTRASEÑA: ");
		lblContrasenia.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblContrasenia.setFont(new Font("Tw", Font.BOLD, 14));
		txtContrasenia = new JPasswordField(20);
		
		pOeste.add(lblContrasenia);
		pOeste.add(txtContrasenia);
		pOeste.add(Box.createVerticalStrut(30));
	
		lblRepetirContrasenia = new JLabel("  REPITE LA CONTRASEÑA: ");
		lblRepetirContrasenia.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblRepetirContrasenia.setFont(new Font("Tw", Font.BOLD, 14));
		txtRepetirContrasenia = new JPasswordField(20);

		pOeste.add(lblRepetirContrasenia);
		pOeste.add(txtRepetirContrasenia);
		pOeste.add(Box.createVerticalStrut(30));
	
		txtNombre.setColumns(40);
		txtApellido.setColumns(40);
		txtTlf.setColumns(40);
	 	txtDireccion.setColumns(40);
	   	txtEmail.setColumns(40);
	   	txtNombreUsuario.setColumns(40);
	   	txtContrasenia.setColumns(40);
		txtRepetirContrasenia.setColumns(40);
	
		pEste = new JPanel();
		pEste.setLayout(new BoxLayout(pEste, BoxLayout.Y_AXIS));
		lblInicioSesion = new JLabel("¿Ya tienes cuenta? Inicia sesión aquí");
		lblInicioSesion.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblInicioSesion.setFont(new Font("Tw", Font.BOLD, 14));
		btnInicioSesion = new JButton("                    INICIA SESIÓN                    ");
	
		pEste.add(lblInicioSesion);
		pEste.add(btnInicioSesion);
		pEste.add(Box.createVerticalStrut(30));
	
		pSur = new JPanel();
		pSur.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnCerrar = new JButton("CERRAR");
		pSur.add(btnCerrar);
		
		btnRegistro = new JButton("REGISTRO");
		pSur.add(btnRegistro);
	
		int espacioEntrePaneles1 = 150; 
	    pOeste.setBorder(new EmptyBorder(espacioEntrePaneles1, 300, espacioEntrePaneles1, espacioEntrePaneles1 ));
	    
	    int espacioEntrePaneles2 = 350; 
	    pEste.setBorder(new EmptyBorder(espacioEntrePaneles2, espacioEntrePaneles2, espacioEntrePaneles2, espacioEntrePaneles2 ));
		
		pPrincipal.add(pEste, BorderLayout.EAST);
		pPrincipal.add(pOeste, BorderLayout.WEST);
		pPrincipal.add(pSur, BorderLayout.SOUTH);
		
		getContentPane().add(pPrincipal);
		new JScrollPane();
 	
		btnCerrar.addActionListener((e) -> { 
			System.exit(0);
			/*vActual.setVisible(false);
			vActual.dispose();*/
		});
		
		btnInicioSesion.addActionListener((e) -> {
			/*vActual.dispose();
			vActual.setVisible(false);*/
			dispose();
			new VentanaInicioSesion();
		});

		
		btnRegistro.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null,"Registro realizado correctamente", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);
			/*String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String telefono = txtTlf.getText();
			String direccion = txtDireccion.getText();
			String correo = txtEmail.getText();
			String nombreUsuario = txtNombreUsuario.getText();
			String contrasenia = new String(txtContrasenia.getPassword());
			String contRep = new String(txtRepetirContrasenia.getPassword());
			
			if(contrasenia.equals(contRep)) {
			
				if (Restaurante.registroCliente(nomfichClientes, nombre, apellido, telefono, direccion, correo, Persona.getContador(), 75, nombreUsuario, contrasenia,vActual)
						) {
					JOptionPane.showMessageDialog(vActual, "Registro realizado correctamente", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);
					new VentanaInicioSesion(vActual);
					vActual.dispose();
					

				} else {
					JOptionPane.showMessageDialog(vActual, "Registro NO realizado correctamente", "REGISTRO", JOptionPane.INFORMATION_MESSAGE);

				}
			}else {
				JOptionPane.showMessageDialog(vActual, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
			}*/
		});	
		
		setVisible(true);
	}
}
	
