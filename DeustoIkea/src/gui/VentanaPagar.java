package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Producto;

import java.awt.*;

import java.util.List;

public class VentanaPagar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelIzquierda, panelDerecha, panelInferior, panelCentro, panelderecho, panelizquierdo, panelmain;;
    private JTextArea resumenCompra;
    private JTextField campoTarjeta;
    private JButton botonFinalizar;
    private JLabel etiquetaTotal, etiquetaResumenCompraIzq, etiquetaResumenCompraDer;

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

        // Configuración del panel izquierdo
        panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));

        etiquetaResumenCompraIzq = new JLabel("Resumen Compra");
        etiquetaResumenCompraIzq.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaResumenCompraIzq.setAlignmentX(Component.CENTER_ALIGNMENT);

        resumenCompra = new JTextArea(60, 70);
        resumenCompra.setEditable(false);
        resumenCompra.setLineWrap(true);
        resumenCompra.setWrapStyleWord(true);
        resumenCompra.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(resumenCompra);
        
        double total = calcularResumen(productos);

        etiquetaTotal = new JLabel("Total: " + String.format("%.2f €", total));
        etiquetaTotal.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaTotal.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelIzquierda.add(etiquetaTotal, BorderLayout.SOUTH);


        panelIzquierda.add(etiquetaResumenCompraIzq);
        panelIzquierda.add(Box.createVerticalStrut(10)); // Espaciado
        panelIzquierda.add(scroll);
        panelIzquierda.add(Box.createVerticalStrut(10)); // Espaciado
        panelIzquierda.add(etiquetaTotal);

        // Configuración del panel derecho
        panelDerecha = new JPanel();
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));

        etiquetaResumenCompraDer = new JLabel("Numero de Tarjeta");
        etiquetaResumenCompraDer.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaResumenCompraDer.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoTarjeta = new JTextField(20);
        campoTarjeta.setMaximumSize(new Dimension(300, 30));

        botonFinalizar = new JButton("Finalizar Compra");
        botonFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDerecha.add(etiquetaResumenCompraDer);
        panelDerecha.add(Box.createVerticalStrut(10)); // Espaciado
        panelDerecha.add(campoTarjeta);
        panelDerecha.add(Box.createVerticalStrut(10)); // Espaciado
        panelDerecha.add(botonFinalizar);

        // Configuración del panel central
        panelCentro = new JPanel(new GridLayout(1, 2, 20, 0)); // Dos columnas, espacio horizontal entre paneles
        panelCentro.add(panelIzquierda);
        panelCentro.add(panelDerecha);

        // Añadir panel central al JFrame
        panelderecho = new JPanel();
        panelizquierdo = new JPanel();
        panelmain = new JPanel();
        
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
        
        panelmain.add(panelCentro, BorderLayout.CENTER);
        panelmain.add(panelderecho, BorderLayout.EAST);
        panelmain.add(panelizquierdo, BorderLayout.WEST); 
        
        add(panelmain, BorderLayout.CENTER);

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