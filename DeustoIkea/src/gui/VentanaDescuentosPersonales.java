package gui;

import javax.swing.*;
import domain.Cliente;
import java.awt.*;

public class VentanaDescuentosPersonales extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel etiquetaDescuento;
    private JButton botonCerrar;
    
    public VentanaDescuentosPersonales(Cliente cliente) {
        setTitle("Mis Descuentos Personales");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String descuentoActual = cliente.getDescuento() != null 
            ? cliente.getDescuento().name().replace("_", " ").toLowerCase() + "%"
            : "No tienes descuentos aplicados actualmente.";

        etiquetaDescuento = new JLabel("<html>¡Hola, " + cliente.getNombre() + "!<br>" + descuentoActual + "</html>");
        etiquetaDescuento.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaDescuento.setFont(new Font("Arial", Font.PLAIN, 18));

        botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener((e) -> dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(botonCerrar);

        add(etiquetaDescuento, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
