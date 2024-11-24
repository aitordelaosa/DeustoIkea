package gui;

import java.awt.*;
import javax.swing.*;
import domain.Cliente;

public class VentanaModificarDatos extends JFrame {
    private static final long serialVersionUID = 1L;
    private int codigo;

    public VentanaModificarDatos(Cliente cliente, JFrame ventanaAnterior, int codigo) {
    	this.codigo = codigo;
        setTitle("Modificar Datos");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel lbNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(cliente.getNombre());

        JLabel lbTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField(cliente.getTelefono());

        JLabel lbDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(cliente.getDireccion());

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((e) -> {
            cliente.setNombre(txtNombre.getText());
            cliente.setTelefono(txtTelefono.getText());
            cliente.setDireccion(txtDireccion.getText());
            ventanaAnterior.dispose();
            new VentanaPerfil(cliente, codigo);
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((e) -> dispose());

        panel.add(lbNombre);
        panel.add(txtNombre);
        panel.add(lbTelefono);
        panel.add(txtTelefono);
        panel.add(lbDireccion);
        panel.add(txtDireccion);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }
}
