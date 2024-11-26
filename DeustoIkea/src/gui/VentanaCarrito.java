package gui;

import java.awt.BorderLayout;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	protected JButton botonAtras, eliminarProducto, pagar, aplicarDescuento;
	protected JPanel panelPrincipal, panelBotones, panelIzq, panelDrch;
	protected JLabel lblDerecha, lblIzquierda, lblDerecha1, lblIzquierda1;
	
	private Cliente cliente;
	private int codigo;
	
	public VentanaCarrito(Cliente cliente, int codigo) {
		this.codigo = codigo;
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
		
		ImageIcon iconoD = new ImageIcon(new ImageIcon("src/Imagenes/compras1.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoI = new ImageIcon(new ImageIcon("src/Imagenes/compras2.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD1 = new ImageIcon(new ImageIcon("src/Imagenes/compras3.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoI1 = new ImageIcon(new ImageIcon("src/Imagenes/compras4.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        
        lblDerecha = new JLabel(iconoD);
        lblIzquierda = new JLabel(iconoI);
        lblDerecha1 = new JLabel(iconoD1);
        lblIzquierda1 = new JLabel(iconoI1);
        
        botonAtras = new JButton("ATRAS");
        eliminarProducto = new JButton("ELIMINAR PRODUCTO");
        pagar = new JButton("PAGAR");
        aplicarDescuento = new JButton("APLICAR DESCUENTOS");
        
        panelBotones = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        panelIzq = new JPanel();
        panelDrch = new JPanel();

        panelDrch.setLayout(new GridLayout(2, 1));
        panelIzq.setLayout(new GridLayout(2, 1));
        
        panelBotones.add(botonAtras);
        panelBotones.add(pagar);
        panelBotones.add(aplicarDescuento);
        panelBotones.add(eliminarProducto);
           
        botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaPrincipal(cliente, codigo);
		});
        
        modeloCarrito = new ModeloCarrito(Datos.getProductos());
        tablaProductos = new JTable(modeloCarrito);
        this.tablaProductos.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollTablaProductos = new JScrollPane(tablaProductos);
        
        panelDrch.add(lblDerecha);
        panelIzq.add(lblIzquierda);
        panelDrch.add(lblDerecha1);
        panelIzq.add(lblIzquierda1);
        panelPrincipal.add(panelDrch, BorderLayout.EAST);
        panelPrincipal.add(panelIzq, BorderLayout.WEST);
        panelPrincipal.add(scrollTablaProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(scrollPane);
        add(panelPrincipal);
        
        setVisible(true);
    }
}
