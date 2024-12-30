package gui;

import javax.swing.*;

import domain.Cliente;

import java.awt.*;

public class VentanaDescuentos extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonAtras, botonMisDescuentos;
	protected JPanel panelPrincipal, panelImagenes, panelBotones;
	protected JLabel descuento1, descuento2, descuento3;
	
	private int codigo;
	
	public VentanaDescuentos(Cliente cliente, int codigo) {
		this.codigo = codigo;
        setTitle("Descuentos Especiales");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        botonAtras = new JButton("ATRAS");
        botonMisDescuentos = new JButton("MIS DESCUENTOS");
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelImagenes = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotones = new JPanel();
        
        ImageIcon iconoD1 = new ImageIcon(new ImageIcon("resources/images/descuento1.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD2 = new ImageIcon(new ImageIcon("resources/images/descuento2.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD3 = new ImageIcon(new ImageIcon("resources/images/descuento3.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));
        
        descuento1 = new JLabel(iconoD1);
        descuento2 = new JLabel(iconoD2);
        descuento3 = new JLabel(iconoD3);
        
        descuento1.setText("20% de Descuento en la app");
        descuento1.setHorizontalTextPosition(JLabel.CENTER);
        descuento1.setVerticalTextPosition(JLabel.BOTTOM);
        
        descuento2.setText("15% de Descuento en la app");
        descuento2.setHorizontalTextPosition(JLabel.CENTER);
        descuento2.setVerticalTextPosition(JLabel.BOTTOM);
        
        descuento3.setText("10% de Descuento en la app");
        descuento3.setHorizontalTextPosition(JLabel.CENTER);
        descuento3.setVerticalTextPosition(JLabel.BOTTOM);

        panelImagenes.add(descuento1);
        panelImagenes.add(descuento2);
        panelImagenes.add(descuento3);
        panelBotones.add(botonAtras);
        panelBotones.add(botonMisDescuentos);

        panelPrincipal.add(panelImagenes, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
//        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        add(panelPrincipal);
        
        botonAtras.addActionListener((e) -> {
			dispose();
			//new VentanaPrincipal(cliente, codigo);
			VentanaDeCarga.vp.setVisible(true);
		});
        
        botonMisDescuentos.addActionListener((e) -> {
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Error: No se encontró información del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new VentanaDescuentosPersonales(cliente, codigo);
            }
        });

        setVisible(true);
    }

}
