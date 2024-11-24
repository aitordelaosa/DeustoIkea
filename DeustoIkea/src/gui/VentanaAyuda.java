package gui;

import java.awt.*;
import javax.swing.*;
import domain.Cliente;

public class VentanaAyuda extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JButton botonAtras;
    protected JPanel panelPrincipal, panelBotones;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    @SuppressWarnings("unused")
	private Cliente cliente;

    public VentanaAyuda(Cliente cliente) {
        this.cliente = cliente;
        setTitle("DeustoIkea - Ayuda");
        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setText("\n\n" + "\n\n"
        		+ "DeustoIkea simula una aplicacion de compra de objetos para casa.\n\n"
                + "Al iniciar el programa nos encontramos con la ventana de carga, en ella nos encontramos el logo de la aplicación y abajo podemos observar 3 botones. Al hacer click sobre el botón 'CERRAR' el programa se cerrará. Si hacemos click en el botón 'ENTRAR' nos llevará a la ventana de inicio de sesión.\n\n"
                + "En la ventana de inicio de sesión podremos iniciar sesión con los datos con los cuales nos hayamos registrado previamente. En la parte inferior nos encontramos con el botón 'TRABAJADOR' que al introducir el código 777 nos llevará hasta la ventana de la que dispondrán los trabajadores (implementaremos más adelante esta funcionalidad). Al hacer click sobre 'VOLVER AL INICIO' nos llevará de nuevo a la ventana principal. Al hacer click en el botón 'REGISTRATE' se nos abrirá una nueva ventana para poder registrarnos con nuestros datos.\n\n"
                + "En cambio si estando en la ventana de carga hacemos click sobre el botón 'Principal' nos dirige a la ventana principal en la que nos encontramos las 4 secciones de las que dispone DeustoIkea. Podemos encontrarnos también una tabla con nuestros productos más comprados y en la parte superior de la ventana disponemos de una serie de botones con diferentes funcionalidades. En el primer botón que nos encontramos podemos encontrarnos los datos de los que dispone cada cliente que ha iniciado sesión. El siguiente botón que observamos nos servirá en un futuro como una ventana de ayuda, por si alguno de nuestros clientes presenta algún tipo de inconveniente. El siguiente botón nos dirige a una ventana en la que se encuentran todos los descuentos que pueden llegar a conseguir nuestros clientes (más adelante implementaremos esta funcionalidad). El último botón que podemos ver sería el del carrito que nos dirigiría a una ventana en la que cada cliente tendrá los productos que haya añadido.\n\n"
                + "Estando en esta ventana, si presionamos las teclas CTRL + W nos llevará a la ventana de carga. Si presionamos sobre alguna de las fotos de las diferentes secciones se nos abrirá una nueva ventana con los diferentes productos que podemos seleccionar de esa categoría. Pasando por encima de los productos nos indica qué producto es cada uno, y cuando queramos ver las características de cada producto en específico debemos de hacer click encima del producto y después pulsar 'SELECCIONAR'. El botón 'COMPRAR' no está implementado ya que posiblemente sufra modificaciones.\n\n"
                + "En caso de que haya alguna duda sobre el funcionamiento de DeustoIkea no dude en ponerse en contacto con nosotros:\n\n"
                + "- Aitor de la Osa: aitordela.osa@opendeusto.es\n"
                + "- Jorge Martínez : jorge.m@opendeusto.es\n"
                + "- Diego Goyoaga : diego.goyoaga@opendeusto.es\n"
                + "- Hugo Arenaza : hugo.arenaza@opendeusto.es");

        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(240, 240, 240));
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        botonAtras = new JButton("ATRAS");
        botonAtras.setFont(new Font("Arial", Font.BOLD, 14));

        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(botonAtras);

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        add(panelPrincipal);

        botonAtras.addActionListener((e) -> {
            dispose();
            new VentanaPrincipal(cliente);
        });

        setVisible(true);
    }
}
