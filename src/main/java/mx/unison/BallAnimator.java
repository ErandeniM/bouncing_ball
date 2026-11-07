package mx.unison;

/**
 * Contiene el movimiento de la pelota y las colisiones.
 * Antes era un Runnable; ahora Board llama a mover() mediante un Timer.
 */
public class BallAnimator {
    private static final double VELOCIDAD_INICIAL = 4;
    private static final double TAMANO_INICIAL = 20;

    private final int anchoTablero;
    private final int altoTablero;
    private final Paddle raqueta;
    private double x, y;
    private double velocidadX, velocidadY;
    private double tamano = TAMANO_INICIAL;
    private GameState estado = GameState.PAUSED;

    public BallAnimator(int anchoTablero, int altoTablero, Paddle raqueta) {
        this.anchoTablero = anchoTablero;
        this.altoTablero = altoTablero;
        this.raqueta = raqueta;
        reiniciar();
        estado = GameState.PAUSED;
    }

    public void iniciar() {
        if (estado == GameState.PAUSED) estado = GameState.RUNNING;
    }

    public void pausar() {
        if (estado == GameState.RUNNING) estado = GameState.PAUSED;
    }

    public void detener() { estado = GameState.STOPPED; }

    public void reiniciar() {
        x = (anchoTablero - tamano) / 2;
        y = altoTablero / 2.0;
        velocidadX = VELOCIDAD_INICIAL;
        velocidadY = VELOCIDAD_INICIAL;
        estado = GameState.RUNNING;
    }

    /** Avanza un paso; no necesita hilos porque el Timer funciona en el EDT. */
    public void mover() {
        if (estado != GameState.RUNNING) return;

        double siguienteX = x + velocidadX;
        double siguienteY = y + velocidadY;

        // Limites izquierdo y derecho (considera el tamaño de la pelota).
        if (siguienteX < 0) {
            siguienteX = 0;
            velocidadX = Math.abs(velocidadX);
        } else if (siguienteX + tamano > anchoTablero) {
            siguienteX = anchoTablero - tamano;
            velocidadX = -Math.abs(velocidadX);
        }

        if (siguienteY < 0) {
            siguienteY = 0;
            velocidadY = Math.abs(velocidadY);
        }

        // La pelota rebota si cruza la parte superior de la raqueta bajando.
        boolean cruzaRaqueta = velocidadY > 0
                && y + tamano <= raqueta.getY()
                && siguienteY + tamano >= raqueta.getY();
        boolean coincideEnHorizontal = siguienteX + tamano > raqueta.getX()
                && siguienteX < raqueta.getX() + raqueta.getAncho();

        if (cruzaRaqueta && coincideEnHorizontal) {
            siguienteY = raqueta.getY() - tamano;
            velocidadY = -Math.abs(velocidadY);
        }

        x = siguienteX;
        y = siguienteY;

        // Pelota perdida: llegó abajo sin ser atrapada.
        if (y + tamano >= altoTablero) {
            y = altoTablero - tamano;
            detener();
        }
    }

    /** Ajustes de tamaño que ya existían en el proyecto anterior. */
    public void zoomIn() {
        tamano = Math.min(100, tamano * 1.1);
        ajustarLimites();
    }

    public void zoomOut() {
        tamano = Math.max(15, tamano * 0.9);
        ajustarLimites();
    }

    private void ajustarLimites() {
        x = Math.min(x, anchoTablero - tamano);
        y = Math.min(y, altoTablero - tamano);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getTamano() { return tamano; }
    public double getVelocidadX() { return velocidadX; }
    public double getVelocidadY() { return velocidadY; }
    public GameState getEstado() { return estado; }

    // Permite preparar posiciones concretas al probar las colisiones.
    void colocarPelota(double x, double y, double velocidadX, double velocidadY) {
        this.x = x;
        this.y = y;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
    }
}
