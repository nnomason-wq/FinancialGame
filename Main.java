import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create the window
        JFrame frame = new JFrame("Money Smart");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create the welcome text
        JLabel welcomeLabel = new JLabel("Welcome to Money Smart!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));

        // Put the text in the window, then show the window
        frame.add(welcomeLabel);
        frame.setVisible(true);
    }
}