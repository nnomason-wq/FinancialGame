import javax.swing.*;
import java.awt.*;

public class Main {

    // These are shared by the whole class so every method can use them
    static JPanel screens;
    static CardLayout cardLayout;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Money Smart");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // The "deck of cards" that holds all our screens
        cardLayout = new CardLayout();
        screens = new JPanel(cardLayout);

        // Add each screen to the deck and give it a name
        screens.add(createMenuPanel(), "menu");
        screens.add(createPlaceholderPanel("Taxes"), "taxes");
        screens.add(createPlaceholderPanel("Debt"), "debt");

        frame.add(screens);
        frame.setVisible(true);
    }

    // Builds the main menu screen
    static JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("Money Smart", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));

        JButton taxesButton = new JButton("Taxes");
        taxesButton.setFont(new Font("Arial", Font.BOLD, 24));
        taxesButton.addActionListener(e -> cardLayout.show(screens, "taxes"));

        JButton debtButton = new JButton("Debt");
        debtButton.setFont(new Font("Arial", Font.BOLD, 24));
        debtButton.addActionListener(e -> cardLayout.show(screens, "debt"));

        panel.add(title);
        panel.add(taxesButton);
        panel.add(debtButton);
        return panel;
    }

    // Builds a temporary screen with a topic name and a Back button
    static JPanel createPlaceholderPanel(String topicName) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(topicName + " lesson coming soon!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(screens, "menu"));

        panel.add(label, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }
}