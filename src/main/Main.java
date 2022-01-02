package main;

import javax.swing.*;

public class Main {
    public static void main(String[] arguments) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setVisible(true);

        gamePanel.setup();
        gamePanel.startGameThread();
    }
}
