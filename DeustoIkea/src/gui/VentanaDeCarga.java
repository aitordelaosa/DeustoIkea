package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class VentanaDeCarga extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JButton botonCerrar, botonEntrar;
    protected JPanel panelAbajo, panelCentro, panelArriba;
    protected JProgressBar progressBar;

    public VentanaDeCarga() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("DeustoIkea");

        botonCerrar = new JButton("CERRAR");
        botonEntrar = new JButton("ENTRAR");

        panelAbajo = new JPanel();
        panelCentro = new JPanel();
        panelArriba = new JPanel();

        panelAbajo.add(botonCerrar);
        panelAbajo.add(botonEntrar);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        
//        ImageIcon iconoCarga = new ImageIcon("/DeustoIkea/Imagenes/FondoP3.webp");
        ImageIcon iconoCarga = new ImageIcon(getClass().getResource("/Imagenes/FondoP3.webp"));
        JLabel labelImagen = new JLabel(iconoCarga);
        
        panelCentro.add(labelImagen);
        panelArriba.add(progressBar);

        getContentPane().add(panelAbajo, BorderLayout.SOUTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        getContentPane().add(panelArriba, BorderLayout.NORTH);

        botonCerrar.addActionListener((e) -> {
            System.exit(0);
        });

        botonEntrar.addActionListener((e) -> {
            botonEntrar.setEnabled(false); // Desactivar el botón mientras se carga
            progressBar.setValue(0);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(40);
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
                        	new VentanaMuebles();
                            // Para cargar los datos de los ficheros, bd y datos de prueba
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