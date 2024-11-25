package gui;

import javax.swing.*;
import domain.Cliente;
import domain.Descuento;
import java.awt.*;

public class VentanaDescuentosPersonales extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel etiquetaDescuento;
    private JButton botonCerrar, botonAtras;
    
    private int codigo;

    public VentanaDescuentosPersonales(Cliente cliente, int codigo) {
    	this.codigo = codigo;
        setTitle("Mis Descuentos Personales");
        setTitle("Descuentos Especiales");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon iconoD1 = new ImageIcon(new ImageIcon("src/Imagenes/descuento1.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD2 = new ImageIcon(new ImageIcon("src/Imagenes/descuento2.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));
        ImageIcon iconoD3 = new ImageIcon(new ImageIcon("src/Imagenes/descuento3.jpeg").getImage().getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH));

        JLabel descuento1 = new JLabel(iconoD1);
        descuento1.setText("20% de Descuento en la app");
        descuento1.setHorizontalTextPosition(JLabel.CENTER);
        descuento1.setVerticalTextPosition(JLabel.BOTTOM);

        JLabel descuento2 = new JLabel(iconoD2);
        descuento2.setText("15% de Descuento en la app");
        descuento2.setHorizontalTextPosition(JLabel.CENTER);
        descuento2.setVerticalTextPosition(JLabel.BOTTOM);

        JLabel descuento3 = new JLabel(iconoD3);
        descuento3.setText("10% de Descuento en la app");
        descuento3.setHorizontalTextPosition(JLabel.CENTER);
        descuento3.setVerticalTextPosition(JLabel.BOTTOM);

        JPanel panelDescuento = new JPanel(new BorderLayout());
        JLabel etiquetaImagen = new JLabel();
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);

        String textoDescuento;
        if (cliente.getDescuento() != null) {
            Descuento descuento = cliente.getDescuento();
            switch (descuento) {
                case Descuento_20:
                    etiquetaImagen.setIcon(iconoD1);
                    textoDescuento = "20% de Descuento en la app";
                    break;
                case Descuento_15:
                    etiquetaImagen.setIcon(iconoD2);
                    textoDescuento = "15% de Descuento en la app";
                    break;
                case Descuento_10:
                    etiquetaImagen.setIcon(iconoD3);
                    textoDescuento = "10% de Descuento en la app";
                    break;
                default:
                    textoDescuento = "No tienes descuentos aplicados actualmente.";
            }
        } else {
            textoDescuento = "No tienes descuentos aplicados actualmente.";
        }

        etiquetaDescuento = new JLabel("<html>¡Hola, " + cliente.getNombre() + "!<br>" + textoDescuento + "</html>");
        etiquetaDescuento.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaDescuento.setFont(new Font("Arial", Font.PLAIN, 18));

        panelDescuento.add(etiquetaDescuento, BorderLayout.NORTH);
        panelDescuento.add(etiquetaImagen, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener((e) -> dispose());

        botonAtras = new JButton("Atrás");
        botonAtras.addActionListener((e) -> {
            dispose();
            new VentanaPrincipal(cliente, codigo);
        });

        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrar);

        add(panelDescuento, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}

