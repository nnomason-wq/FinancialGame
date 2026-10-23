import javax.swing.*;
import java.awt.*;

public class PlaceholderPanel extends JPanel {

    public PlaceholderPanel(String topicName) {
        setBackground(Theme.NAVY);
        setLayout(new BorderLayout());

        JLabel label = new JLabel(topicName + " lesson coming soon!", SwingConstants.CENTER);
        label.setFont(Theme.HEADING);
        label.setForeground(Color.WHITE);

        RoundedButton backButton = new RoundedButton("Back to Topics", Theme.BLUE);
        backButton.addActionListener(e -> Main.showScreen("topics"));

        // Wrap the button so it gets some breathing room
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottom.add(backButton, BorderLayout.CENTER);

        add(label, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}