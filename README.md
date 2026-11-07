# Pong (a partir de bouncing_ball)

Esta versión parte del proyecto **bouncing_ball**, en el que ya tenía controles
para pausar, cambiar la velocidad y modificar el tamaño de la pelota. Lo adapté
para que funcione como un Pong de un jugador y corregí el manejo de hilos.

## Cómo ejecutar

Abre la carpeta en IntelliJ IDEA como proyecto Gradle. Ejecuta la clase
`mx.unison.Main`. Para compilar desde la terminal usa `./gradlew classes`.
El proyecto mantiene la configuración Gradle de mi repositorio original.

## Controles

| Tecla | Acción |
| --- | --- |
| Izquierda / Derecha | Mover la raqueta |
| Espacio | Pausar |
| R | Reanudar (o reiniciar si se perdió la pelota) |
| P | Pausar o reanudar, como en mi proyecto anterior |
| A / S | Aumentar o disminuir velocidad |
| I / O | Aumentar o disminuir el tamaño de la pelota |

## Cambios

1. Reemplazo los dos hilos por un `javax.swing.Timer` en `Board`.
2. `BallAnimator` conserva el movimiento, pero ahora tiene estados
   `RUNNING`, `PAUSED` y `STOPPED` y detecta colisiones.
3. Agregué `Paddle` para mover una raqueta que no sale de la ventana.
4. Reemplazo de `KeyListener` por `Key Bindings`.
5. Seis pruebas JUnit para la lógica, sin abrir Swing.

## Pruebas

Ejecuta `./gradlew test` (requiere poder descargar JUnit si aún no está en caché).
Los 12 casos funcionales para revisar en la ventana están en `docs/PRUEBAS.md`.

**Nota:** es una adaptación de `bouncing_ball`, no la refactorización del código
fuente original `pong_project`, que no estaba disponible.
