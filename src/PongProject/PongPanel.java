package PongProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private static final Color BACKGROUND_COLOUR = Color.BLACK;
    private static final int TIMER_DELAY = 5;
    private static final int BALL_MOVEMENT_SPEED = 2;
    private static final int POINTS_TO_WIN = 3;

    // Constantes para el puntaje
    private static final int SCORE_X_PADDING = 100;
    private static final int SCORE_Y_PADDING = 100;
    private static final int SCORE_FONT_SIZE = 50;
    private static final Font SCORE_FONT = new Font("Serif", Font.BOLD, SCORE_FONT_SIZE);

    private GameState gameState;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    private int player1Score = 0, player2Score = 0;
    private Player gameWinner;

    public PongPanel() {
        setBackground(BACKGROUND_COLOUR);
        Timer timer = new Timer(TIMER_DELAY, this);
        timer.start();

        gameState = GameState.INITIALISING;

        addKeyListener(this);
        setFocusable(true);
    }

    public void createObjects() {
        ball = new Ball(getWidth(), getHeight());
        paddle1 = new Paddle(Player.One, getWidth(), getHeight());
        paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
    }

    private void moveObject(Sprite object) {
        object.setXPosition(object.getXPosition() + object.getXVelocity(), getWidth());
        object.setYPosition(object.getYPosition() + object.getYVelocity(), getHeight());
    }

    private void checkWallBounce() {
        if (ball.getXPosition() <= 0) {
            addScore(Player.Two);
            resetBall();
        } else if (ball.getXPosition() + ball.getWidth() >= getWidth()) {
            addScore(Player.One);
            resetBall();
        }

        if (ball.getYPosition() <= 0 || ball.getYPosition() + ball.getHeight() >= getHeight()) {
            ball.setYVelocity(-ball.getYVelocity());
        }
    }

    private void checkPaddleBounce() {
        if (ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
            ball.setXVelocity(BALL_MOVEMENT_SPEED);
        }
        if (ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
            ball.setXVelocity(-BALL_MOVEMENT_SPEED);
        }
    }

    private void checkWin() {
        if (player1Score >= POINTS_TO_WIN) {
            gameWinner = Player.One;
            gameState = GameState.GAMEOVER;
        } else if (player2Score >= POINTS_TO_WIN) {
            gameWinner = Player.Two;
            gameState = GameState.GAMEOVER;
        }
    }

    private void addScore(Player player) {
        if (player == Player.One) {
            player1Score++;
        } else if (player == Player.Two) {
            player2Score++;
        }
    }

    private void resetBall() {
        ball.resetToInitialPosition();
        ball.setXVelocity(BALL_MOVEMENT_SPEED);
        ball.setYVelocity(BALL_MOVEMENT_SPEED);
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

    private void paintScores(Graphics g) {
        g.setFont(SCORE_FONT);

        String leftScore = Integer.toString(player1Score);
        String rightScore = Integer.toString(player2Score);

        g.drawString(leftScore, SCORE_X_PADDING, SCORE_Y_PADDING);
        g.drawString(rightScore, getWidth() - SCORE_X_PADDING, SCORE_Y_PADDING);
    }

    private void paintWinner(Graphics g) {
        if (gameState == GameState.GAMEOVER && gameWinner != null) {
            final int FONT_SIZE = 50;
            Font winFont = new Font("Serif", Font.BOLD, FONT_SIZE);
            g.setFont(winFont);
            g.setColor(Color.WHITE);

            int xPosition = (gameWinner == Player.One) ? SCORE_X_PADDING : getWidth() - SCORE_X_PADDING - 50;
            int yPosition = SCORE_Y_PADDING + 50;

            g.drawString("Win!", xPosition, yPosition);
        }
    }

    private void gameUpdate() { // Renombrado de update a gameUpdate
        switch (gameState) {
            case INITIALISING: {
                createObjects();
                ball.setXVelocity(BALL_MOVEMENT_SPEED);
                ball.setYVelocity(BALL_MOVEMENT_SPEED);
                gameState = GameState.PLAYING;
                break;
            }
            case PLAYING: {
                moveObject(paddle1);
                moveObject(paddle2);
                moveObject(ball);
                checkWallBounce();
                checkPaddleBounce();
                checkWin();
                break;
            }
            case GAMEOVER: {
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintDottedLine(g);
        if (gameState != GameState.INITIALISING) {
            paintSprite(g, ball);
            paintSprite(g, paddle1);
            paintSprite(g, paddle2);
            paintScores(g);
            paintWinner(g); // Dibujar "Win!" si alguien gana
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameUpdate(); // Llamar al método renombrado
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            paddle2.setYVelocity(-1);
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setYVelocity(1);
        }
        if (event.getKeyCode() == KeyEvent.VK_W) {
            paddle1.setYVelocity(-1);
        } else if (event.getKeyCode() == KeyEvent.VK_S) {
            paddle1.setYVelocity(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
            paddle2.setYVelocity(0);
        }
        if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
            paddle1.setYVelocity(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // No se utiliza
    }
}
