package mx.unison;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BallAnimator implements Runnable {
    public static final int MAX_X = 640;
    public static final int MAX_Y = 480;
    public static final int SIGN = -1;

    public static final int DX = 10;
    public static final int DY = 10;
    private Ellipse2D.Double ball;

    private int ballX;
    private int ballY;

    private volatile int esperaMs = 150;
    private volatile boolean pausado = false;

    public BallAnimator(Shape shape) {
        ball = ( Ellipse2D.Double) shape;
        ballX = 320;
        ballY = 240;
        ball.x = ballX ;
        ball.y = ballY;
    }

    @Override
    public void run() {
        int sY = 1;
        int sX = 1;

        while (!Thread.currentThread().isInterrupted()) {

            // Solo mover la pelota si no está pausada
            if (!pausado) {

                if (ballY < 0) {
                    sY = sY * SIGN;
                }

                if (ballX < 0) {
                    sX = sX * SIGN;
                }

                if (ballY > (MAX_Y - ball.height)) {
                    sY = sY * SIGN;
                }

                if (ballX > (MAX_X - ball.width)) {
                    sX = sX * SIGN;
                }

                ballX = ballX + (DX * sX);
                ballY = ballY + (DY * sY);

                ball.x = ballX;
                ball.y = ballY;
            }

            try {
                Thread.sleep(pausado ? 30 : esperaMs);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void aumentarVelocidad() {
        esperaMs = Math.max(30, esperaMs - 20);
    }

    public void disminuirVelocidad() {
        esperaMs = Math.min(500, esperaMs + 20);
    }

    public void alternarPausa() {
        pausado = !pausado;
    }
}
