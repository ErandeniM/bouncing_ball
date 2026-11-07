# Casos funcionales por comprobar en IntelliJ

No marco estas pruebas como aprobadas porque requieren abrir la ventana y
operar el juego. Anota «OK» o «Falla» después de ejecutarlas.

| ID | Comprobación | Resultado esperado | Resultado obtenido |
| --- | --- | --- | --- |
| CP-01 | Abrir el juego | La pelota se mueve | Pendiente |
| CP-02 | Pulsar espacio | La pelota se detiene | Pendiente |
| CP-03 | Pulsar R | La pelota continúa | Pendiente |
| CP-04 | Cerrar ventana | La aplicación termina | Pendiente |
| CP-05 | Llegar a pared izquierda | Rebota | Pendiente |
| CP-06 | Llegar a pared derecha | Rebota | Pendiente |
| CP-07 | Llegar arriba | Rebota | Pendiente |
| CP-08 | Tocar raqueta | Rebota hacia arriba | Pendiente |
| CP-09 | Mover raqueta a ambos extremos | No sale del tablero | Pendiente |
| CP-10 | Dejar caer pelota | Muestra «Pelota perdida» | Pendiente |
| CP-11 | Pausar y reanudar varias veces | La velocidad no se duplica | Pendiente |
| CP-12 | Tocar extremo de raqueta | Detecta el contacto | Pendiente |

## Pruebas JUnit (6)

Archivo: `src/test/java/mx/unison/BallAnimatorTest.java`.

- Pausa y reanudación.
- Rebotes en paredes laterales.
- Rebote en límite superior.
- Colisión cerca del borde de la raqueta.
- Pelota perdida.
- Límites de la raqueta.

Ejecutar: `./gradlew test`. Registrar el resultado después de ejecutarlo.
