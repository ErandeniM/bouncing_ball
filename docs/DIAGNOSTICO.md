# Diagnóstico del proyecto original

| Problema | Dónde estaba | Consecuencia | Qué modifiqué |
| --- | --- | --- | --- |
| Dos hilos para mover y repintar | `Board` | Acceso simultáneo a la pelota | Un solo `javax.swing.Timer` |
| Pausa revisada en cada vuelta del ciclo | `BallAnimator.run()` | Aunque esté pausado, sigue despertando | Parar el Timer mientras esté en pausa |
| El hilo de repintado tenía `while(true)` | `Board.run()` | No terminaba al cerrar | Quitar ese hilo |
| Rebotes con comprobaciones que podían pasarse del borde | `BallAnimator.run()` | Pelota fuera de la ventana | Calcular siguiente posición y corregir el límite |
| `KeyListener` y foco de ventana | `Board` y `Main` | Algunas teclas podían no responder | `Key Bindings` |
| No existía una raqueta | Proyecto original | No era todavía un juego Pong | Nueva clase `Paddle` y colisión |

El cambio más importante fue usar `Timer`: en lugar de que varios hilos modifiquen
el dibujo, Swing actualiza la pelota desde su hilo de eventos (EDT).
