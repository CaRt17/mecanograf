/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanograf;

import javax.swing.JOptionPane;

/**
 *
 * @author César Rondón ; Fabio Manrique
 */
public class Nivel implements Runnable {

    private boolean enPausa, detenido;
    private long velocidad;
    private Entorno entorno;
    private int nivel;
    private int aux_alto;
    private int aux_ancho;

    public Nivel(Entorno entorno) {

        this.nivel = entorno.getNivel();
        velocidad = (long) 2000 - 100 * this.nivel;
        this.entorno = entorno;

        aux_alto = entorno.getHeight();
        aux_ancho = entorno.getWidth();
    }

    @Override
    public void run() {

        while (!detenido) {

            try {

                if (!enPausa) {

                    
                    if (!redimencionado()) {

                        entorno.crearPalabra();
                        entorno.actualizar_marcador();

                    }
                    
                    
                }

                Thread.sleep(velocidad);

            } catch (Exception e) {

            }

        }

        JOptionPane.showMessageDialog(null, "Fin del juego");

    }

    public boolean redimencionado() {

        boolean resp;

        resp = aux_alto != entorno.getHeight() || aux_ancho != entorno.getWidth();

        aux_alto = entorno.getHeight();
        aux_ancho = entorno.getWidth();
        return resp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void actualizar_level() {

        velocidad = (long) (1200 - 100 * nivel);

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

    @Override
    public String toString() {

        return "\n VELOCIDAD DE CREACION: " + velocidad;

    }

}
