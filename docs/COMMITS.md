# Guía para los commits

El ZIP incluye el historial Git original de `bouncing_ball` y los cambios
**sin registrar** para que puedas revisarlos y hacer tus propios commits.
Abre una terminal en la raíz del proyecto.

## 1. Juego Pong y corrección del movimiento

```bash
git add src/main/java/mx/unison/Main.java src/main/java/mx/unison/Board.java src/main/java/mx/unison/BallAnimator.java src/main/java/mx/unison/Paddle.java src/main/java/mx/unison/GameState.java
git commit -m "Adaptar bouncing ball a Pong con Timer y raqueta"
```

## 2. Pruebas unitarias

```bash
git add src/test/java/mx/unison/BallAnimatorTest.java
git commit -m "Agregar pruebas de movimiento y colisiones"
```

## 3. Documentación

```bash
git add README.md docs/
git commit -m "Documentar diagnóstico, pruebas y cambios del juego"
```

Después: `git log --oneline -4` para ver tu commit anterior y los tres nuevos.
Si tienes permisos sobre el repositorio y tu rama está sincronizada con el
remoto, podrás usar `git push origin master`.

No incluyas `.gradle`, `build` o carpetas de IntelliJ en los commits.
