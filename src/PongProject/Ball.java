package PongProject;

import java.awt.Color;

public class Ball extends Sprite {
    private static final Color BALL_COLOR = Color.WHITE;
    private static final int BALL_WIDTH = 25;
    private static final int BALL_HEIGHT = 25;

    public Ball(int panelWidth, int panelHeight) {
        // Establece el color, ancho y alto de la bola
        setColor(BALL_COLOR);
        setWidth(BALL_WIDTH);
        setHeight(BALL_HEIGHT);

        // Establece la posición inicial de la bola en el centro del panel
        setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));

        // Coloca la bola en su posición inicial
        resetToInitialPosition();
    }

    // Actualiza la posición de la bola y detecta colisiones
    public void move(int panelWidth, int panelHeight) {
        setXPosition(getXPosition() + getXVelocity(), panelWidth);
        setYPosition(getYPosition() + getYVelocity(), panelHeight);

        // Detectar colisiones con los bordes del panel
        if (getXPosition() <= 0 || getXPosition() + getWidth() >= panelWidth) {
            setXVelocity(-getXVelocity());
        }
        if (getYPosition() <= 0 || getYPosition() + getHeight() >= panelHeight) {
            setYVelocity(-getYVelocity());
        }
    }
}
