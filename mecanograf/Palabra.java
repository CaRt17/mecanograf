/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanograf;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author César Rondón ; Fabio Manrique
 */
public class Palabra extends JPanel implements Runnable {

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int estado;

    private Entorno entorno;

    private ArrayList<JLabel> vector_letras;
    private int cantidad_letras;
    private int ancho_aux, alto_aux;
    private double relac;
    private double relac_y;

    private long time_sleep;
    private boolean enPausa, detenido;

    public Palabra(Entorno entorno) {
        super();
        this.entorno = entorno;
        //ESTADO 0 PERDIDA, ESTADO 1 EN JUEGO, ESTADO 2 ESCRITA
        estado = 1;

        alto_aux = entorno.getHeight();
        ancho_aux = this.entorno.getWidth();

        cantidad_letras = entorno.getNivel() + 2;
        setLayout(new GridLayout(1, cantidad_letras));
        x = x_ale();
        y = 0;
        ancho = 20 * cantidad_letras;
        alto = 20;

        vector_letras = new ArrayList<>();

        generar_palabra();

        actualizar_level();

        enPausa = false;
        detenido = false;
        setOpaque(false);

        entorno.getPanel_centro().add(this);
    }

    @Override
    public void run() {

        while (!detenido) {

            try {

                if (!enPausa) {

                    verificar_x();

                    verificar_y();

                    mover();

                }

                Thread.sleep(time_sleep);

            } catch (Exception e) {

            }

        }

     //   limpiar();

        vector_letras.clear();
        entorno.getVector_palabras().remove(this);
        entorno.getPanel_centro().remove(this);
        
        if (entorno.getVector_palabras().isEmpty()) {
            entorno.reiniciar();
        }

    }

    public void limpiar() {

        detenido = true;
        vector_letras.clear();
        entorno.getVector_palabras().remove(this);
        entorno.getPanel_centro().remove(this);

    }

    public void verificar_x() {

        if (ancho_aux != entorno.getWidth()) {

            ancho_aux = entorno.getWidth();

            x = ajustar(ancho_aux);

        }

    }

    public void verificar_y() {

        if (alto_aux != entorno.getHeight()) {

            relac_y = (double) y / alto_aux;

            alto_aux = entorno.getHeight();

            y = (int) (relac_y * alto_aux);

            setBounds(x, y, ancho, alto);
        }

    }

    public int x_ale() {

        relac = Math.random();

        return ajustar(ancho_aux);
    }

    public int ajustar(int ancho) {

        return (20 + (int) (relac * (ancho - 40 - 20 * cantidad_letras)));
    }

    public void mover() {
        if (y < entorno.getHeight()) {
            y++;

        } else {

            entorno.setExpiradas(entorno.getExpiradas() + 1);
            detenido = true;
            validate();
            entorno.actualizar_marcador();

        }

        setBounds(x, y, ancho, alto);

    }

    public static char generar_letra() {
        int a = 65;
        int b = 90;
        int nr = 0;
        nr = (int) ((Math.random() * (b - a)) + a);
        return (char) nr;
    }

    public void generar_palabra() {

        for (int i = 0; i < cantidad_letras; i++) {

            JLabel L = new JLabel();
            L.setOpaque(false);
            L.setFont(new Font("Arial", Font.BOLD, 20));
            L.setForeground(Color.WHITE);
            L.setText(String.valueOf(generar_letra()));
            L.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            vector_letras.add(L);
            validate();
        }

        for (int i = 0; i < vector_letras.size(); i++) {

            this.add(vector_letras.get(i));

        }

    }

    public void actualizar_level() {

        time_sleep = (long) 30 - 3 * entorno.getNivel();
    }

    @Override
    public String toString() {
        String resp = "";

        resp = "\n VELOCIDAD DE CAIDA: " + time_sleep;

        return resp;
    }

    public ArrayList<JLabel> getVector_letras() {
        return vector_letras;
    }

    public void setVector_letras(ArrayList<JLabel> vector_letras) {
        this.vector_letras = vector_letras;
    }

    public boolean isDetenido() {
        return detenido;
    }

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public boolean isEnPausa() {
        return enPausa;
    }

    public void setEnPausa(boolean enPausa) {
        this.enPausa = enPausa;
    }

    public int getCantidad_letras() {
        return cantidad_letras;
    }

    public void setCantidad_letras(int cantidad_letras) {
        this.cantidad_letras = cantidad_letras;
    }

}
