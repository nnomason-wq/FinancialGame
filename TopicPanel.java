import javax.swing.*;
import java.awt.*;

public class TopicPanel extends JPanel {

    public TopicPanel() {
        setBackground(new Color(18, 32, 72));
        setLayout(new GridLayout(4, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel title = new JLabel("Choose a Topic", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        JButton taxesButton = new JButton("Taxes");
        taxesButton.setFont(new Font("Arial", Font.BOLD, 24));
        taxesButton.addActionListener(e -> Main.showScreen("taxes"));

        JButton debtButton = new JButton("Debt");
        debtButton.setFont(new Font("Arial", Font.BOLD, 24));
        debtButton.addActionListener(e -> Main.showScreen("debt"));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        backButton.addActionListener(e -> Main.showScreen("menu"));

        add(title);
        add(taxesButton);
        add(debtButton);
        add(backButton);
    }
}