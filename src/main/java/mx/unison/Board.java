package mx.unison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/** Dibuja el tablero y recibe las teclas. La física está en BallAnimator. */
public class Board extends JPanel {
    private static final int ANCHO = 640;
    private static final int ALTO = 480;

    private final Paddle raqueta = new Paddle(ANCHO, ALTO);
    private final BallAnimator pelota = new BallAnimator(ANCHO, ALTO, raqueta);
    private final Timer timer;

    public Board() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(ANCHO, ALTO));

        // Un solo Timer reemplaza los dos hilos de la versión anterior.
        timer = new Timer(25, e -> actualizar());
        configurarTeclas();
    }

    private void actualizar() {
        pelota.mover();
        if (pelota.getEstado() == GameState.STOPPED) timer.stop();
        repaint();
    }

    public void iniciar() {
        pelota.iniciar();
        timer.start();
    }

    public void detener() {
        timer.stop();
        pelota.detener();
    }

    private void pausar() {
        pelota.pausar();
        timer.stop();
        repaint();
    }

    private void reanudar() {
        if (pelota.getEstado() == GameState.STOPPED) pelota.reiniciar();
        else pelota.iniciar();
        timer.start();
        repaint();
    }

    private void configurarTeclas() {
        // Key Bindings funcionan aunque el tablero no tenga el foco directo.
        asignarTecla(KeyEvent.VK_LEFT, "izquierda", () -> raqueta.mover(-25));
        asignarTecla(KeyEvent.VK_RIGHT, "derecha", () -> raqueta.mover(25));
        asignarTecla(KeyEvent.VK_SPACE, "pausar", this::pausar);
        asignarTecla(KeyEvent.VK_R, "reanudar", this::reanudar);
        asignarTecla(KeyEvent.VK_P, "alternar", () -> {
            if (pelota.getEstado() == GameState.RUNNING) pausar();
            else reanudar();
        });
        asignarTecla(KeyEvent.VK_A, "rapido", () -> timer.setDelay(Math.max(10, timer.getDelay() - 5)));
        asignarTecla(KeyEvent.VK_S, "lento", () -> timer.setDelay(Math.min(80, timer.getDelay() + 5)));
        asignarTecla(KeyEvent.VK_I, "agrandar", pelota::zoomIn);
        asignarTecla(KeyEvent.VK_O, "achicar", pelota::zoomOut);
    }

    private void asignarTecla(int tecla, String nombre, Runnable accion) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla, 0), nombre);
        getActionMap().put(nombre, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                accion.run();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(pelota.getX(), pelota.getY(),
                pelota.getTamano(), pelota.getTamano()));

        g2.setColor(Color.BLACK);
        g2.fillRect(raqueta.getX(), raqueta.getY(), raqueta.getAncho(), raqueta.getAlto());
        g2.drawString("Flechas: mover | Espacio: pausa | R: seguir | A/S: velocidad | I/O: tamaño", 12, 20);

        if (pelota.getEstado() == GameState.PAUSED) {
            g2.drawString("PAUSA - presiona R", 260, 210);
        } else if (pelota.getEstado() == GameState.STOPPED) {
            g2.drawString("Pelota perdida - presiona R para reiniciar", 190, 210);
        }
    }
}
