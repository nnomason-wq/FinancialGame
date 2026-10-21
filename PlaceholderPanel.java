import javax.swing.*;
import java.awt.*;

public class PlaceholderPanel extends JPanel {

    public PlaceholderPanel(String topicName) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel(topicName + " lesson coming soon!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> Main.showScreen("menu"));

        add(label, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}