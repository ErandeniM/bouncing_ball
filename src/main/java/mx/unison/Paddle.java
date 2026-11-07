package mx.unison;

/** Guarda la posición y tamaño de la raqueta. */
public class Paddle {
    private static final int ANCHO = 100;
    private static final int ALTO = 12;

    private final int anchoTablero;
    private final int y;
    private int x;

    public Paddle(int anchoTablero, int altoTablero) {
        this.anchoTablero = anchoTablero;
        this.x = (anchoTablero - ANCHO) / 2;
        this.y = altoTablero - 45;
    }

    public void mover(int pasos) {
        x = Math.max(0, Math.min(anchoTablero - ANCHO, x + pasos));
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ANCHO; }
    public int getAlto() { return ALTO; }
}
