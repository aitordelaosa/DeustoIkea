package main;

import java.awt.GraphicsConfiguration;


import javax.swing.SwingUtilities;

import domain.BD;
import domain.Datos;
import gui.VentanaDeCarga;

public class Main {

	public static void main(String[] args) {
		BD.initBD("src/data/DeustoIkea.db");
		BD.crearTablas();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//            	VentanaDeCarga vc= new VentanaDeCarga();
//            	new gui.VentanaMuebles();
            	new VentanaDeCarga();
            }
        });
    }
}
