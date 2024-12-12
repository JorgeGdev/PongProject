package PongProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    // Variable estática para el color de fondo
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    // Constructor
    public PongPanel() {
        setBackground(BACKGROUND_COLOR); // Usar la variable estática para el color
    }

    // Implementación de los métodos de KeyListener
    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}

    // Implementación del método de ActionListener
    @Override
    public void actionPerformed(ActionEvent event) {}
}
