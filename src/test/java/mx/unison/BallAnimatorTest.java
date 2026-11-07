package mx.unison;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BallAnimatorTest {
    private final Paddle raqueta = new Paddle(640, 480);
    private final BallAnimator pelota = new BallAnimator(640, 480, raqueta);

    @Test
    void pausaYReanuda() {
        double inicio = pelota.getX();
        pelota.mover();
        assertEquals(inicio, pelota.getX());  // Todavía no inició.

        pelota.iniciar();
        pelota.mover();
        assertNotEquals(inicio, pelota.getX());

        pelota.pausar();
        double pausada = pelota.getX();
        pelota.mover();
        assertEquals(pausada, pelota.getX());

        pelota.iniciar();
        pelota.mover();
        assertNotEquals(pausada, pelota.getX());
    }

    @Test
    void rebotaEnParedesLaterales() {
        pelota.iniciar();
        pelota.colocarPelota(1, 100, -4, 4);
        pelota.mover();
        assertEquals(0, pelota.getX());
        assertTrue(pelota.getVelocidadX() > 0);

        pelota.colocarPelota(619, 100, 4, 4);
        pelota.mover();
        assertEquals(620, pelota.getX());
        assertTrue(pelota.getVelocidadX() < 0);
    }

    @Test
    void rebotaEnParteSuperior() {
        pelota.iniciar();
        pelota.colocarPelota(100, 1, 4, -4);
        pelota.mover();
        assertEquals(0, pelota.getY());
        assertTrue(pelota.getVelocidadY() > 0);
    }

    @Test
    void detectaBordeDeRaqueta() {
        pelota.iniciar();
        // Apenas 3 píxeles de la pelota quedan encima de la raqueta.
        pelota.colocarPelota(raqueta.getX() + raqueta.getAncho() - 3,
                raqueta.getY() - pelota.getTamano() - 2, 0, 4);
        pelota.mover();
        assertTrue(pelota.getVelocidadY() < 0);
        assertEquals(GameState.RUNNING, pelota.getEstado());
    }

    @Test
    void seDetieneAlPerderPelota() {
        pelota.iniciar();
        pelota.colocarPelota(0, 457, 0, 4);
        pelota.mover();
        assertEquals(GameState.STOPPED, pelota.getEstado());
    }

    @Test
    void raquetaNoSaleDeTablero() {
        raqueta.mover(-10000);
        assertEquals(0, raqueta.getX());
        raqueta.mover(10000);
        assertEquals(540, raqueta.getX());
    }
}
