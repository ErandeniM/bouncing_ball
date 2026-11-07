package mx.unison;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Pong - Bouncing Ball");
            Board tablero = new Board();
            ventana.setContentPane(tablero);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    tablero.detener();
                }
            });
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            tablero.iniciar();
        });
    }
}
