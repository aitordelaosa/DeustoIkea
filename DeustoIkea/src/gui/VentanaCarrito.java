package gui;

import java.awt.BorderLayout;

import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Cliente;
import domain.Datos;


public class VentanaCarrito extends JFrame{
	private static final long serialVersionUID = 1L;
	private ModeloCarrito modeloCarrito;
	private JTable tablaProductos;
	private JScrollPane scrollTablaProductos;
	protected JButton botonAtras;
	protected JPanel panelPrincipal, panelBotones, panelCentro;
	
	private Cliente cliente;
	
	public VentanaCarrito(Cliente cliente) {
		this.cliente = cliente;
        setTitle("Carrito");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        botonAtras = new JButton("ATRAS");
        
        panelBotones = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        panelCentro= new JPanel();
        panelCentro.setLayout(new BorderLayout());
        
        panelBotones.add(botonAtras);
        
        
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaPrincipal(cliente);
		});
        
        modeloCarrito = new ModeloCarrito(Datos.getProductos());
        tablaProductos = new JTable(modeloCarrito);
        this.tablaProductos.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollTablaProductos = new JScrollPane(tablaProductos);
        
        panelCentro.add(scrollTablaProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(scrollPane);
        add(panelPrincipal);
        
        setVisible(true);
    }
}
