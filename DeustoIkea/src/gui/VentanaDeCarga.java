package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import domain.Datos;

public class VentanaDeCarga extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonCerrar, botonEntrar, botonAyudaInicio;
    protected JPanel panelAbajo, panelFoto, panelCentro;
    protected JLabel labelImagen;
    protected JProgressBar progressBar;
    
    protected Datos datos;

    public VentanaDeCarga() {
        setTitle("DeustoIkea");
        setSize(800, 500);
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        ImageIcon i = new ImageIcon("src/Imagenes/DeustoIkea_app_icon.png");
        setIconImage(i.getImage());

        botonCerrar = new JButton("CERRAR");
        botonEntrar = new JButton("ENTRAR");
        botonAyudaInicio = new JButton("AYUDA_INICIO");

        panelAbajo = new JPanel();
        panelCentro = new JPanel();
        panelFoto = new JPanel();
        
		ImageIcon imIkea = new ImageIcon("src/Imagenes/DeustoIkea_app_icon.png");
		Image imagen = imIkea.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(325, 350, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcon = new ImageIcon(imagenRedimensionada);
		labelImagen = new JLabel(imagenRedimensionadaIcon);
        panelFoto.add(labelImagen);

        panelAbajo.add(botonCerrar);
        panelAbajo.add(botonEntrar);
        panelAbajo.add(botonAyudaInicio);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        panelCentro.add(progressBar);

        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        getContentPane().add(panelFoto, BorderLayout.NORTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);

        botonCerrar.addActionListener((e) -> {
            System.exit(0);
        });
        
        botonAyudaInicio.addActionListener((e) -> { 
            JOptionPane.showMessageDialog(
                null, 
                "Usuario: 1A\nContraseña: 123", 
                "Ayuda de Inicio de Sesión", 
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        botonEntrar.addActionListener((e) -> {
            botonEntrar.setEnabled(false); // Desactivar el botón mientras se carga
            progressBar.setValue(0);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(2); //Poner entre 15 y 30
                        progressBar.setValue(i);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    botonEntrar.setEnabled(true); // Habilitar el botón nuevamente
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new VentanaInicioSesion(null);
                        }
                    });
                    dispose();
                }
            };

            worker.execute();
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }

}
