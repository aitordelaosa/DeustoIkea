package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.sql.SQLException;
import java.time.LocalDate;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.BD;
import domain.Cliente;
import domain.Descuento;
import domain.SistemaUsuarios;

public class VentanaRegistro extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel pSur, pPrincipal, pEste, pOeste;
	private JLabel lblNombre, lblApellido, lblTlf, lblDireccion, lblEmail, lblContrasenia, lblRepetirContrasenia, lblInicioSesion, lblFNac, lblGenero, lblDni;
	private JTextField txtNombre, txtApellido, txtTlf, txtDireccion, txtEmail, txtFNac, txtDni;
	private JPasswordField txtContrasenia, txtRepetirContrasenia;
	private JComboBox<String> comboGenero;
	private JButton btnRegistro, btnCerrar, btnInicioSesion;	
	
	@SuppressWarnings("unused")
	private int codigo;
	
	public VentanaRegistro(int codigo) {
		this.codigo = codigo;
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
	
		lblFNac = new JLabel("FECHA DE NACIMIENTO:");
		lblFNac.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblFNac.setFont(new Font("Tw", Font.BOLD, 14));
		txtFNac = new JTextField(20);
		pOeste.add(lblFNac);
		pOeste.add(txtFNac);
		pOeste.add(Box.createVerticalStrut(30));

		lblGenero = new JLabel("GÉNERO:");
		lblGenero.setBorder(new EmptyBorder(0, 0, 10, 20));
		lblGenero.setFont(new Font("Tw", Font.BOLD, 14));
		String[] generos = { "Masculino", "Femenino", "Otro" };
		comboGenero = new JComboBox<>(generos);
		pOeste.add(lblGenero);
		pOeste.add(comboGenero);
		pOeste.add(Box.createVerticalStrut(30));
		
		lblDni = new JLabel("DNI:");
        lblDni.setBorder(new EmptyBorder(0, 0, 10, 20));
        lblDni.setFont(new Font("Tw", Font.BOLD, 14));
        txtDni = new JTextField(20);
        pOeste.add(lblDni);
        pOeste.add(txtDni);
        pOeste.add(Box.createVerticalStrut(30));

		txtNombre.setColumns(40);
		txtApellido.setColumns(40);
		txtTlf.setColumns(40);
	 	txtDireccion.setColumns(40);
	   	txtEmail.setColumns(40);
	
	   	txtContrasenia.setColumns(40);
		txtRepetirContrasenia.setColumns(40);
		txtFNac.setColumns(40);
		txtDni.setColumns(40);

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
		
		JScrollPane scrollPane = new JScrollPane(pPrincipal);
	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    
	    getContentPane().add(scrollPane);
 	
		btnCerrar.addActionListener((e) -> { 
			System.exit(0);
		});
		
		btnInicioSesion.addActionListener((e) -> {
			dispose();
			new VentanaInicioSesion(null, codigo);
		});

		btnRegistro.addActionListener((e) -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTlf.getText();
            String direccion = txtDireccion.getText();
            String correo = txtEmail.getText();
          
            String contrasenia = new String(txtContrasenia.getPassword());
            String contRep = new String(txtRepetirContrasenia.getPassword());
            String fechaNacimiento = txtFNac.getText();
            String genero = (String) comboGenero.getSelectedItem();
            String dni = txtDni.getText().trim();

            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo DNI no puede estar vacío.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate fechaNac = null;
            try {
                String[] fechaParts = fechaNacimiento.split("/");
                int dia = Integer.parseInt(fechaParts[0]);
                int mes = Integer.parseInt(fechaParts[1]);
                int ano = Integer.parseInt(fechaParts[2]);
                fechaNac = LocalDate.of(ano, mes, dia);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Formato: dd/mm/yyyy", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (contrasenia.equals(contRep)) {
            	int ultimoId = 0;
				
            	if(codigo== 0) {
                    Cliente c = new Cliente(dni, genero, nombre, apellido, correo, direccion, fechaNac, contrasenia, telefono, 77, LocalDate.now(), Descuento.Descuento_15);
                    SistemaUsuarios.getInstancia().agregarCliente(c);
            		
            	} else {
            		try {
    					ultimoId = BD.obtenerUltimoIdCliente();
    				} catch (SQLException e1) {
    					
    					e1.printStackTrace();
    				}
                    int nuevoId = ultimoId + 1;
            		BD.insertarCliente(dni, genero, nombre, apellido, correo, direccion, fechaNac, contrasenia, telefono, nuevoId, LocalDate.now(), Descuento.Descuento_15);
            	}
                JOptionPane.showMessageDialog(null, "¡Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new VentanaInicioSesion(null, codigo);
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
			
		setVisible(true);
	}
	
}


