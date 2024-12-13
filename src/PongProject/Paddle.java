package PongProject;

import java.awt.Color;

public class Paddle extends Sprite {
    // Constantes para las características del paddle
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final Color PADDLE_COLOR = Color.WHITE;
    private static final int DISTANCE_FROM_EDGE = 40;

    // Constructor
    public Paddle(Player player, int panelWidth, int panelHeight) {
        // Configurar las dimensiones y el color del paddle
        setWidth(PADDLE_WIDTH);
        setHeight(PADDLE_HEIGHT);
        setColor(PADDLE_COLOR);

        // Establecer la posición inicial basada en el jugador
        if (player == Player.One) {
            // Paddle del jugador 1 (cerca del borde izquierdo)
            setInitialPosition(DISTANCE_FROM_EDGE, panelHeight / 2 - (getHeight() / 2));
        } else if (player == Player.Two) {
            // Paddle del jugador 2 (cerca del borde derecho)
            setInitialPosition(panelWidth - DISTANCE_FROM_EDGE - getWidth(), panelHeight / 2 - (getHeight() / 2));
        }

        // Restablecer a la posición inicial
        resetToInitialPosition();
    }
}
