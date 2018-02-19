/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanograf;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author César Rondón ; Fabio Manrique
 */
public class Ventana extends JFrame {

    public Ventana() {
        super();

        setSize(640, 426);
        setTitle("Mecano UC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contenedor = getContentPane();
        contenedor.add(new Entorno(), BorderLayout.CENTER);

    }

}
