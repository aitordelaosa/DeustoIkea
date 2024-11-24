package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import domain.Cliente;

public class VentanaPerfil extends JFrame{
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonModificarDatos;
	private JPanel pCentro, pPrincipal, pContenedor, pSur;
    private JLabel lbNombre, nombre, lbApellidos, apellidos, lbTelefono, telefono, lbCorreo, correo, lbDireccion,
            direccion, lbDNI, DNI, lbContrasenia;
    private JPasswordField contrasenia;
    private JCheckBox mostrarContrasenia;
	private Cliente cliente;
	private int codigo;
	
	public VentanaPerfil(Cliente cliente, int codigo) {
		this.codigo = codigo;
		this.cliente = cliente;
        setTitle("Perfil");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);        
        
        pCentro = new JPanel();
        pCentro.setLayout(new GridLayout(8, 2, 5, 5)); 

        pPrincipal = new JPanel();
        pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 400));

        pContenedor = new JPanel();
        pContenedor.setLayout(new BorderLayout());
        
        pSur = new JPanel();
        
        botonModificarDatos = new JButton("MODIFICAR DATOS");
        botonAtras = new JButton("ATRAS");
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaPrincipal(cliente, codigo);
		});
        
        botonModificarDatos.addActionListener((e) -> {
            new VentanaModificarDatos(cliente, this, codigo);
        });
        
        pSur.add(botonAtras);
        pSur.add(botonModificarDatos);
        
        lbNombre = new JLabel("Nombre");
        lbNombre.setFont(new Font("Tw", Font.BOLD, 14));
        nombre = new JLabel();
        nombre.setText(cliente.getNombre());
        nombre.setFont(new Font("Tw", Font.PLAIN, 14));
        
        lbApellidos = new JLabel("Apellidos:");
        lbApellidos.setFont(new Font("Tw", Font.BOLD, 14));
        apellidos = new JLabel();
        apellidos.setText(cliente.getApellido());
        apellidos.setFont(new Font("Tw", Font.PLAIN, 14));

        lbTelefono = new JLabel("Teléfono:");
        lbTelefono.setFont(new Font("Tw", Font.BOLD, 14));
        telefono = new JLabel();
        telefono.setText(cliente.getTelefono());
        telefono.setFont(new Font("Tw", Font.PLAIN, 14));

        lbCorreo = new JLabel("Email:");
        lbCorreo.setFont(new Font("Tw", Font.BOLD, 14));
        correo = new JLabel();
        correo.setText(cliente.getEmail());
        correo.setFont(new Font("Tw", Font.PLAIN, 14));

        lbDireccion = new JLabel("Dirección:");
        lbDireccion.setFont(new Font("Tw", Font.BOLD, 14));
        direccion = new JLabel();
        direccion.setText(cliente.getDireccion());
        direccion.setFont(new Font("Tw", Font.PLAIN, 14));

        lbDNI = new JLabel("DNI:");
        lbDNI.setFont(new Font("Tw", Font.BOLD, 14));
        DNI = new JLabel();
        DNI.setText(cliente.getDni());
        DNI.setFont(new Font("Tw", Font.PLAIN, 14));

        lbContrasenia = new JLabel("Contraseña:");
        lbContrasenia.setFont(new Font("Tw", Font.BOLD, 14));
        contrasenia = new JPasswordField();
        contrasenia.setText(cliente.getContrasenia());
        contrasenia.setEditable(false); 
        contrasenia.setFont(new Font("Tw", Font.PLAIN, 14));

        mostrarContrasenia = new JCheckBox("Mostrar contraseña");
        mostrarContrasenia.setFont(new Font("Tw", Font.PLAIN, 14));
        
        //Este metodo lo que hace es cuando la checkBox esta 
        //seleccionada muestra el texto de la contraseña, sino salen *
        mostrarContrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrarContrasenia.isSelected()) {
                    contrasenia.setEchoChar((char) 0); 
                } else {
                    contrasenia.setEchoChar('*'); 
                }
            }
        });
        
        pCentro.add(lbNombre);
        pCentro.add(nombre);
        pCentro.add(lbApellidos);
        pCentro.add(apellidos);
        pCentro.add(lbTelefono);
        pCentro.add(telefono);
        pCentro.add(lbCorreo);
        pCentro.add(correo);
        pCentro.add(lbDireccion);
        pCentro.add(direccion);
        pCentro.add(lbDNI);
        pCentro.add(DNI);
        pCentro.add(lbContrasenia);
        pCentro.add(contrasenia);
        pCentro.add(mostrarContrasenia);

        pContenedor.add(pCentro, BorderLayout.CENTER);
        pContenedor.add(pSur, BorderLayout.SOUTH);

        pPrincipal.add(pContenedor, BorderLayout.CENTER);

        getContentPane().add(pPrincipal);
        
        
        setVisible(true);
    }
}
