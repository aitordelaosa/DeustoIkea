package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Producto;

import java.awt.*;

import java.util.List;

public class VentanaPagar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelIzquierda, panelDerecha, panelInferior, panelCentro;
    private JTextArea resumenCompra;
    private JTextField campoTarjeta;
    private JButton botonFinalizar;
    private JLabel etiquetaTotal;

    public VentanaPagar(List<Producto> productos) {
        setTitle("Pagar");

        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel Izquierda
        panelIzquierda = new JPanel(new BorderLayout());
        resumenCompra = new JTextArea(15, 30);
        resumenCompra.setEditable(false);
        resumenCompra.setLineWrap(true);
        resumenCompra.setWrapStyleWord(true);
        resumenCompra.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(resumenCompra);
        panelIzquierda.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelIzquierda.add(new JLabel("Resumen de compra", SwingConstants.CENTER), BorderLayout.NORTH);

        panelIzquierda.add(scrollPane, BorderLayout.CENTER);

        double total = calcularResumen(productos);

        etiquetaTotal = new JLabel("Total: " + String.format("%.2f €", total));
        etiquetaTotal.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaTotal.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelIzquierda.add(etiquetaTotal, BorderLayout.SOUTH);

        // Panel Derecha con BoxLayout
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
        panelDerecha.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        // Número de tarjeta
        JLabel tarjeta = new JLabel("Número de tarjeta:");
        tarjeta.setFont(new Font("Arial", Font.BOLD, 16));
        tarjeta.setBorder(new EmptyBorder(0, 0, 10, 20));

        campoTarjeta = new JTextField(10);  
        campoTarjeta.setFont(new Font("Arial", Font.PLAIN, 12));
        campoTarjeta.setPreferredSize(new Dimension(10, 20)); 

        panelDerecha.add(tarjeta);
        panelDerecha.add(campoTarjeta);
        panelDerecha.add(Box.createVerticalStrut(20));

        // Botón finalizar compra
        botonFinalizar = new JButton("Finalizar Compra");
        botonFinalizar.setFont(new Font("Arial", Font.BOLD, 16));
        botonFinalizar.setBackground(new Color(34, 139, 34));
        botonFinalizar.setForeground(Color.WHITE);
        botonFinalizar.setFocusPainted(false);

        // Añadir un poco de espacio antes del botón
        panelDerecha.add(Box.createVerticalStrut(30));
        panelDerecha.add(botonFinalizar);
        panelDerecha.add(Box.createVerticalStrut(20));
        

        // Panel Centro
        panelCentro = new JPanel(new GridLayout(1, 2, 1, 1));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentro.add(panelIzquierda);
        panelCentro.add(panelDerecha);

        // Panel Inferior
        panelInferior = new JPanel();
        panelInferior.setBackground(new Color(245, 245, 245));

        botonFinalizar.addActionListener(e -> {
            String tar = campoTarjeta.getText();
            if (tar.isEmpty()) {
                JOptionPane.showMessageDialog(VentanaPagar.this, "Por favor, introduce un número de tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!tar.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(VentanaPagar.this, "El número de tarjeta debe tener 16 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(VentanaPagar.this, "Compra finalizada correctamente.\nGracias por tu compra!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        // Layout Principal
        setLayout(new BorderLayout(20, 20));
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    private double calcularResumen(List<Producto> productos) {
        StringBuilder resumen = new StringBuilder();
        double total = 0.0;

        for (Producto producto : productos) {
            String nombre = producto.getClass().getSimpleName();
            double precio = producto.getPrecio() * producto.getNumeroProductos();

            resumen.append("                                             ").append(nombre)
            .append(" ---------------------------------------------------------------").append(String.format("%.2f €", precio))
            .append("\n");
      
            total += precio;
        }

        resumenCompra.setText(resumen.toString());
        return total;
    }
}