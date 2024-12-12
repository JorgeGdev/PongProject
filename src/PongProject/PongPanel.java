package PongProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final static Color BACKGROUND_COLOUR = Color.BLACK;
    private final static int TIMER_DELAY = 5;

    // Coordenadas de la pelota
    private int ballX = 100;
    private int ballY = 100;
    private int ballDeltaX = 2;
    private int ballDeltaY = 2;

    public PongPanel() {
        setBackground(BACKGROUND_COLOUR);
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    // Método personalizado para actualizar la lógica del juego
    private void update() {
        // Actualizar coordenadas de la pelota
        ballX += ballDeltaX;
        ballY += ballDeltaY;

        // Detectar colisiones con los bordes del panel
        if (ballX <= 0 || ballX >= getWidth() - 20) { // 20 es el tamaño de la pelota
            ballDeltaX *= -1; // Cambiar dirección
        }
        if (ballY <= 0 || ballY >= getHeight() - 20) {
            ballDeltaY *= -1; // Cambiar dirección
        }
    }

    // Método para dibujar la línea punteada
    private void paintDottedLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpiar la pantalla
        paintDottedLine(g); // Dibujar la línea punteada

        // Dibujar la pelota
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update(); // Actualizar la lógica del juego
        repaint(); // Redibujar el panel
    }
}

