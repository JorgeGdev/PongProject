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

    private GameState gameState; // Estado del juego
    private Ball ball;
    private Paddle paddle1; // Paddle del jugador 1
    private Paddle paddle2; // Paddle del jugador 2

    public PongPanel() {
        setBackground(BACKGROUND_COLOUR);
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();

        // Inicializar el estado inicial del juego
        gameState = GameState.INITIALISING;
    }

    // Crear los objetos del juego
    public void createObjects() {
        ball = new Ball(getWidth(), getHeight());
        ball.setXVelocity(2);
        ball.setYVelocity(2);

        // Crear los paddles
        paddle1 = new Paddle(Player.One, getWidth(), getHeight());
        paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
    }

    private void update() {
        switch (gameState) {
            case INITIALISING: {
                createObjects();
                gameState = GameState.PLAYING;
                break;
            }
            case PLAYING: {
                // Lógica del juego
                ball.move(getWidth(), getHeight());
                break;
            }
            case GAMEOVER: {
                // Lógica para cuando el juego termina
                break;
            }
        }
    }

    private void paintDottedLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.setPaint(Color.WHITE);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g2d.dispose();
    }

    private void paintSprite(Graphics g, Sprite sprite) {
        g.setColor(sprite.getColor());
        g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintDottedLine(g);

        // Dibujar los objetos solo si no estamos inicializando
        if (gameState != GameState.INITIALISING) {
            paintSprite(g, ball);
            paintSprite(g, paddle1); // Dibujar paddle 1
            paintSprite(g, paddle2); // Dibujar paddle 2
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
        repaint();
    }
}
