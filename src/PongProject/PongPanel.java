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

    private boolean gameInitialised = false; // Nuevo flag para inicialización
    private Ball ball; // Variable para la bola

    public PongPanel() {
        setBackground(BACKGROUND_COLOUR);
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    // Método para inicializar los objetos del juego
    public void createObjects() {
        ball = new Ball(getWidth(), getHeight()); // Crear la bola con las dimensiones del panel
        ball.setXVelocity(2); // Velocidad inicial en X
        ball.setYVelocity(2); // Velocidad inicial en Y
    }

    // Método para actualizar la lógica del juego
    private void update() {
        if (!gameInitialised) {
            createObjects(); // Inicializar los objetos si no está inicializado
            gameInitialised = true;
        }
        ball.move(getWidth(), getHeight()); // Actualizar la posición de la bola
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

    // Método genérico para dibujar sprites
    private void paintSprite(Graphics g, Sprite sprite) {
        g.setColor(sprite.getColor());
        g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintDottedLine(g);
        if (gameInitialised) {
            paintSprite(g, ball); // Dibujar la bola
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
        repaint();
    }
}
