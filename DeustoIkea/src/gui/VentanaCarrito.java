package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import domain.Cliente;

import domain.Producto;


public class VentanaCarrito extends JFrame{
	private static final long serialVersionUID = 1L;
	private ModeloCarrito modeloCarrito;
	private JTable tablaProductos;
	private JScrollPane scrollTablaProductos;
	protected JButton botonAtras, pagar, aplicarDescuento;
	//protected JButton eliminarProducto;
	protected JPanel panelPrincipal, panelBotones, panelIzq, panelDrch,panelTotal;
	protected JLabel lblDerecha, lblIzquierda, lblDerecha1, lblIzquierda1,lblTotal;
	
	private Cliente cliente;
	private int codigo;
	
	public VentanaCarrito(Cliente cliente, int codigo, List<Producto> lp) {
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
		
		ImageIcon iconoD = new ImageIcon(new ImageIcon("resources/images/compras1.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoI = new ImageIcon(new ImageIcon("resources/images/compras2.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD1 = new ImageIcon(new ImageIcon("resources/images/compras3.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoI1 = new ImageIcon(new ImageIcon("resources/images/compras4.jpg").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH));
        
        lblDerecha = new JLabel(iconoD);
        lblIzquierda = new JLabel(iconoI);
        lblDerecha1 = new JLabel(iconoD1);
        lblIzquierda1 = new JLabel(iconoI1);
        
        botonAtras = new JButton("ATRAS");
        //eliminarProducto = new JButton("ELIMINAR PRODUCTO");
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
        //panelBotones.add(eliminarProducto);
           
        botonAtras.addActionListener((e) -> {
			dispose();
			//new VentanaPrincipal(cliente, codigo);
			VentanaDeCarga.vp.setVisible(true);
		});
        
        //modeloCarrito = new ModeloCarrito(Datos.getProductos());
        modeloCarrito = new ModeloCarrito(lp);
        tablaProductos = new JTable(modeloCarrito);
        this.tablaProductos.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollTablaProductos = new JScrollPane(tablaProductos);
        
        panelTotal = new JPanel(new BorderLayout());
        lblTotal = new JLabel("Total: " + calcularTotal(lp) + " €");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelTotal.add(lblTotal, BorderLayout.CENTER);
        
        tablaProductos.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                int filaSeleccionada = tablaProductos.getSelectedRow();
	                if (filaSeleccionada != -1) {
	                    Producto productoSeleccionado = modeloCarrito.getProductoAtRow(filaSeleccionada);

	                    int opcion = JOptionPane.showOptionDialog(null,
	                            "Seleccione una acción para el producto seleccionado:",
	                            "Opciones del Producto", JOptionPane.YES_NO_CANCEL_OPTION,
	                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Eliminar", "Modificar Cantidad"}, null);

	                    if (opcion == JOptionPane.YES_OPTION) {
	                        int confirmacion = JOptionPane.showConfirmDialog(null,
	                                "¿Está seguro que desea eliminar el producto de la cesta?",
	                                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

	                        if (confirmacion == JOptionPane.YES_OPTION) {
	                        	modeloCarrito.eliminarProducto(productoSeleccionado);
	                        }
	                    } else if (opcion == JOptionPane.NO_OPTION) {
	                        String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad:");
	                        if (cantidadStr != null && !cantidadStr.isEmpty()) {
	                            int nuevaCantidad = Integer.parseInt(cantidadStr);
	                            productoSeleccionado.setNumeroProductos(nuevaCantidad);
	                            modeloCarrito.actualizarTabla();
	                        }
	                    } 
	                }
	            }
	        }
	    });
        
        panelDrch.add(lblDerecha);
        panelIzq.add(lblIzquierda);
        panelDrch.add(lblDerecha1);
        panelIzq.add(lblIzquierda1);
        panelPrincipal.add(panelDrch, BorderLayout.EAST);
        panelPrincipal.add(panelIzq, BorderLayout.WEST);
        panelPrincipal.add(scrollTablaProductos, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelTotal, BorderLayout.NORTH); 
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(scrollPane);
        add(panelPrincipal);
        
        setVisible(true);
    }
	 private double calcularTotal(List<Producto> productos) {
	        double total = 0;
	        for (Producto p : productos) {
	            total += p.getPrecio() * p.getNumeroProductos();
	        }
	        return total;
	    }
}
