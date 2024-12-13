package PongProject;

import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
    // Variables privadas
    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity;
    private int width;
    private int height;
    private Color color;

    // Nuevas variables para las posiciones iniciales
    private int initialXPosition;
    private int initialYPosition;

    // Constructor por defecto
    public Sprite() {
        this.xPosition = 0;
        this.yPosition = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.width = 0;
        this.height = 0;
        this.color = Color.WHITE;
        this.initialXPosition = 0;
        this.initialYPosition = 0;
    }

    // Getter y Setter para xPosition
    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    // Sobrecarga de setXPosition para mantenerlo dentro del panel
    public void setXPosition(int xPosition, int panelWidth) {
        if (xPosition < 0) {
            this.xPosition = 0; // Limitar al borde izquierdo
        } else if (xPosition + width > panelWidth) {
            this.xPosition = panelWidth - width; // Limitar al borde derecho
        } else {
            this.xPosition = xPosition; // Dentro de los límites
        }
    }

    // Getter y Setter para yPosition
    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    // Sobrecarga de setYPosition para mantenerlo dentro del panel
    public void setYPosition(int yPosition, int panelHeight) {
        if (yPosition < 0) {
            this.yPosition = 0; // Limitar al borde superior
        } else if (yPosition + height > panelHeight) {
            this.yPosition = panelHeight - height; // Limitar al borde inferior
        } else {
            this.yPosition = yPosition; // Dentro de los límites
        }
    }

    // Getter y Setter para xVelocity
    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Getter y Setter para yVelocity
    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Getter y Setter para width
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // Getter y Setter para height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter y Setter para color
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Método para establecer las posiciones iniciales
    public void setInitialPosition(int x, int y) {
        this.initialXPosition = x;
        this.initialYPosition = y;
    }

    // Método para restablecer las posiciones actuales a las iniciales
    public void resetToInitialPosition() {
        this.xPosition = initialXPosition;
        this.yPosition = initialYPosition;
    }

    // Nuevo método para obtener un rectángulo basado en las propiedades del sprite
    public Rectangle getRectangle() {
        return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
    }
}
