package main;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//            	VentanaDeCarga vc= new VentanaDeCarga();
            	new gui.VentanaMuebles();
            }
        });
    }
}
