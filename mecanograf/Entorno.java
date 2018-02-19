/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanograf;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

/**
 *
 * @author César Rondón ; Fabio Manrique
 */
public class Entorno extends JPanel {

    private JPanel panel_centro;
    private JPanel panel_inferior;
    private JLabel informacion_lbl;
    private JLabel p_activas_lbl;
    private JLabel p_expiradas_lbl;
    private JLabel p_acertadas_lbl;
    private JLabel vida_lbl;
    private JLabel presentacion;
    private JLabel pausado_lbl;
    private ArrayList<String> lectura;
    private ArrayList<Palabra> vector_palabras;
    private ExecutorService thread_pool;
    private boolean nuevaPalabra, p_aux;
    private int nivel;
    private int vida;
    private Nivel controlNivel;
    private int expiradas;
    private int acertadas;
    private int velocidad_caida;
    private int velocidad_creacion;
    private boolean evento;
    private boolean juego_detenido;
    private Oyente oyente1;

    private final ImageIcon FONDO_IMG = new ImageIcon(getClass().getResource("/fondo/maq.jpg"));

    public Entorno() {
        super();
        oyente1 = new Oyente();
        this.addKeyListener(oyente1);
        juego_detenido = false;
        evento = false;
        nivel = 1;
        vida = 100;
        setLayout(new BorderLayout());

        panel_centro = new JPanel();
        panel_centro.setLayout(null);
        panel_centro.setOpaque(false);

        panel_inferior = new JPanel();
        panel_inferior.setLayout(new GridLayout(1, 5));

        pausado_lbl = new JLabel("EN PAUSA");
        pausado_lbl.setFont(new Font("Arial", Font.BOLD, 32));
        pausado_lbl.setOpaque(true);
        pausado_lbl.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        pausado_lbl.setForeground(Color.white);
        pausado_lbl.setOpaque(true);
        pausado_lbl.setBackground(Color.black);

        informacion_lbl = new JLabel("NIVEL: ");
        p_activas_lbl = new JLabel("En juego: " + 0);
        p_acertadas_lbl = new JLabel("Acertadas: " + acertadas);
        p_expiradas_lbl = new JLabel("Falladas: " + expiradas);
        vida_lbl = new JLabel("Vida: " + vida + "%");

        panel_inferior.add(informacion_lbl);
        panel_inferior.add(p_activas_lbl);
        panel_inferior.add(p_acertadas_lbl);
        panel_inferior.add(p_expiradas_lbl);
        panel_inferior.add(vida_lbl);

        add(panel_inferior, BorderLayout.SOUTH);

        presentacion = new JLabel("Presione espacio para comenzar");
        presentacion.setFont(new Font("Arial", Font.BOLD, 32));
        presentacion.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        presentacion.setForeground(Color.white);
        add(presentacion, BorderLayout.CENTER);

        velocidad_caida = (int) 15 / nivel;

        velocidad_creacion = 1000;
        expiradas = 0;
        acertadas = 0;

        nuevaPalabra = false;
        p_aux = false;
        vector_palabras = new ArrayList<>();
        thread_pool = Executors.newCachedThreadPool();

        lectura = new ArrayList<>();

    }

    /**
     * Metodo que devuelve el foco del evento para que sea tomado en cuenta
     * por el Label
     * @return
     */
    @Override
    public boolean isFocusable() {
        return true;
    }

    /**
     * Metodo para generar palabras mediante hilos
     */
    public void crearPalabra() {
        Palabra palabra1 = new Palabra(this);
        thread_pool.execute(palabra1);
        vector_palabras.add(palabra1);
        validate();
    }

    /**
     * Metodo para colocar el fondo a la app
     * @param grphcs
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        grphcs.drawImage(FONDO_IMG.getImage(), 0, 0, getWidth(), getHeight(), this);

    }

    private class Oyente extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent ke) {
            int aux = ke.getKeyChar();

            switch (aux) {
                case KeyEvent.VK_BACK_SPACE: vaciar();
                    break;
                case KeyEvent.VK_SPACE:
                    espacio();
                    break;
                case KeyEvent.VK_A:
                    verificar_lectura("A");
                    break;
                case KeyEvent.VK_B:
                    verificar_lectura("B");
                    break;
                case KeyEvent.VK_C:
                    verificar_lectura("C");
                    break;
                case KeyEvent.VK_D:
                    verificar_lectura("D");
                    break;
                case KeyEvent.VK_E:
                    verificar_lectura("E");
                    break;
                case KeyEvent.VK_F:
                    verificar_lectura("F");
                    break;
                case KeyEvent.VK_G:
                    verificar_lectura("G");
                    break;
                case KeyEvent.VK_H:
                    verificar_lectura("H");
                    break;
                case KeyEvent.VK_I:
                    verificar_lectura("I");
                    break;
                case KeyEvent.VK_J:
                    verificar_lectura("J");
                    break;
                case KeyEvent.VK_K:
                    verificar_lectura("K");
                    break;
                case KeyEvent.VK_L:
                    verificar_lectura("L");
                    break;
                case KeyEvent.VK_M:
                    verificar_lectura("M");
                    break;
                case KeyEvent.VK_N:
                    verificar_lectura("N");
                    break;
                case KeyEvent.VK_O:
                    verificar_lectura("O");
                    break;
                case KeyEvent.VK_P:
                    verificar_lectura("P");
                    break;
                case KeyEvent.VK_Q:
                    verificar_lectura("Q");
                    break;
                case KeyEvent.VK_R:
                    verificar_lectura("R");
                    break;
                case KeyEvent.VK_S:
                    verificar_lectura("S");
                    break;
                case KeyEvent.VK_T:
                    verificar_lectura("T");
                    break;
                case KeyEvent.VK_U:
                    verificar_lectura("U");
                    break;
                case KeyEvent.VK_V:
                    verificar_lectura("V");
                    break;
                case KeyEvent.VK_W:
                    verificar_lectura("W");
                    break;
                case KeyEvent.VK_X:
                    verificar_lectura("X");
                    break;
                case KeyEvent.VK_Y:
                    verificar_lectura("Y");
                    break;
                case KeyEvent.VK_Z:
                    verificar_lectura("Z");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     *  FUNCION VERIFICAR LECTURA, REALIZA LA COMPARACION DEL LAS LETRAS 
     *  ESCRITAS POR TECLADO Y CADA UNA DE LAS DEL LA CADENA DE PALABRAS 
     * QUE CAEN EN PANTALLA, RECIBE LA LETRA PRESIONADA
     * @param Letra 
     */
    public void verificar_lectura(String Letra) {
        
        lectura.add(Letra);
        boolean secuencia;
        boolean exedido;
        
        if(lectura.size() > vector_palabras.get(0).getCantidad_letras()){
            lectura.clear();
            exedido = true;
        }
        
        for (int i = 0; i < vector_palabras.size(); i++) {
            secuencia = true;            
            for (int j = 0; j < vector_palabras.get(i).getCantidad_letras(); j++) {                
                if (j < lectura.size()) {                    
                    if (vector_palabras.get(i).getVector_letras().get(j).getText().equals(lectura.get(j)) && secuencia) {                        
                        vector_palabras.get(i).getVector_letras().get(j).setOpaque(true);
                        vector_palabras.get(i).getVector_letras().get(j).setBackground(Color.BLUE);
                        validate();
                        if (lectura.size() == vector_palabras.get(i).getVector_letras().size()) {
                            lectura.clear();
                            actualizar_marcador();
                            acertadas++;
                            actualiza_level();
                            j--;
                            vector_palabras.get(i).setDetenido(true);
                            vector_palabras.get(i).limpiar();
                            repaint();
                            validate();                                           
                        }
                    } else {                        
                        secuencia = false;
                        for (int k = 0; k < vector_palabras.get(i).getVector_letras().size(); k++) {
                            vector_palabras.get(i).getVector_letras().get(k).setOpaque(false);
                        }
                    }
                }
            }
        }
    }
    
    /**
     *  Metodo que limpia lo que se ha presionado por teclado
     */
    public void vaciar(){
        lectura.clear();
        for (int i = 0; i < vector_palabras.size(); i++) {
            for (int j = 0; j < vector_palabras.get(i).getCantidad_letras(); j++) {
                vector_palabras.get(i).getVector_letras().get(j).setOpaque(false);
                
            }
            
        }
    }

    /**
     *  Metodo para empezar el juego o para poner en pausa/reanudar la partida
     */
    public void espacio() {
        if (controlNivel == null || controlNivel.isDetenido()) {
            remove(presentacion);
            add(panel_centro, BorderLayout.CENTER);
            if (controlNivel == null) {
                iniciar();
            } else {
                inicializar();
            }
        } else {
            juego_detenido = !juego_detenido;
            if (juego_detenido) {
                remove(panel_centro);
                add(pausado_lbl, BorderLayout.CENTER);
            } else {
                add(panel_centro);
                remove(pausado_lbl);
            }            
            repaint();
            validate();
            for (int i = 0; i < vector_palabras.size(); i++) {
                vector_palabras.get(i).setEnPausa(juego_detenido);
            }
            controlNivel.setEnPausa(juego_detenido);
        }
    }

    /**
     *  Metodo que arranca el proceso multihilos
     */
    public void iniciar() {
        controlNivel = new Nivel(this);
        thread_pool.execute(controlNivel);
    }

    /**
     *  Metodo que limpia todo de la app para permitir iniciar un juego nuevo
     */
    public void reiniciar() {
        this.remove(panel_centro);
        this.add(presentacion);
        repaint();
        addKeyListener(oyente1);

    }

    /**
     *  Metodo que reinicia los contadores
     */
    public void inicializar() {
        acertadas = 0;
        expiradas = 0;
        nivel = 1;
        vida = 100;
        iniciar();
        repaint();

    }
    
    /**
     *  ACCTUALIZA EL MARCADOR EN EL PANEL INFERIOR
     */
    public void actualizar_marcador() {
        if (!controlNivel.isDetenido()) {
            informacion_lbl.setText("NIVEL: " + nivel);
            p_activas_lbl.setText("En juego: " + vector_palabras.size());
            p_acertadas_lbl.setText("Acertadas: " + acertadas);
            p_expiradas_lbl.setText("Falladas: " + expiradas);
            vida_lbl.setText("Vida: " + vida + "%");
        }
    }

    /**
     *  Metodo que controla el nivel del juego de acuerdo a las palabras 
     *  acertadas
     */
    public void actualiza_level() {
        switch (acertadas) {
            case 10: {
                nivel = 2;
                controlNivel.setNivel(nivel);
                controlNivel.actualizar_level();
                for (int i = 0; i < vector_palabras.size(); i++) {
                    vector_palabras.get(i).actualizar_level();
                }

                break;

            }

            case 20: {
                nivel = 3;
                controlNivel.setNivel(nivel);
                controlNivel.actualizar_level();

                for (int i = 0; i < vector_palabras.size(); i++) {
                    vector_palabras.get(i).actualizar_level();
                }

                break;
            }

            case 30: {
                nivel = 4;
                controlNivel.actualizar_level();
                controlNivel.setNivel(nivel);

                for (int i = 0; i < vector_palabras.size(); i++) {
                    vector_palabras.get(i).actualizar_level();
                }

                break;
            }

        }
    }

    //GET && SET
    public boolean isNuevaPalabra() {
        return nuevaPalabra;
    }

    public void setNuevaPalabra(boolean nuevaPalabra) {

        this.nuevaPalabra = nuevaPalabra;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isEvento() {
        return evento;
    }

    public void setEvento(boolean evento) {

        this.evento = evento;
    }

    public int getExpiradas() {
        return expiradas;
    }

    public void setExpiradas(int expiradas) {

        vida = vida - (5 - nivel);

        //AJUSTAR VIDA A CERO FINAL A 0
        if (vida <= 0) {

            vida = 0;
            //HACER QUE EL PANEL DECE DE RECIBIR EVENTOS
            removeKeyListener(oyente1);
            lectura.clear();
            controlNivel.setDetenido(true);
        }

        actualiza_level();

        this.expiradas = expiradas;
    }

    public int getAcertadas() {
        return acertadas;
    }

    public void setAcertadas(int acertadas) {
        this.acertadas = acertadas;
    }

    public ArrayList<Palabra> getVector_palabras() {
        return vector_palabras;
    }

    public void setVector_palabras(ArrayList<Palabra> vector_palabras) {
        this.vector_palabras = vector_palabras;
    }

    public int getVelocidad_caida() {
        return velocidad_caida;
    }

    public void setVelocidad_caida(int velocidad_caida) {
        this.velocidad_caida = velocidad_caida;
    }

    public int getVelocidad_creacion() {
        return velocidad_creacion;
    }

    public void setVelocidad_creacion(int velocidad_creacion) {
        this.velocidad_creacion = velocidad_creacion;
    }

    public JPanel getPanel_centro() {
        return panel_centro;
    }

    public void setPanel_centro(JPanel panel_centro) {
        this.panel_centro = panel_centro;
    }

    public boolean isJuego_detenido() {
        return juego_detenido;
    }

    public void setJuego_detenido(boolean juego_detenido) {
        this.juego_detenido = juego_detenido;
    }

}
