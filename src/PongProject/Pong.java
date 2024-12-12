package PongProject;


import javax.swing.JFrame;

public class Pong extends JFrame {
    private static final long serialVersionUID = 1L;

    public Pong() {
        setTitle("Pong Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Agregar PongPanel al JFrame
        PongPanel panel = new PongPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Pong();
    }
}

