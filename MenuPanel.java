import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new GridLayout(3, 1, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("Money Smart", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));

        JButton taxesButton = new JButton("Taxes");
        taxesButton.setFont(new Font("Arial", Font.BOLD, 24));
        taxesButton.addActionListener(e -> Main.showScreen("taxes"));

        JButton debtButton = new JButton("Debt");
        debtButton.setFont(new Font("Arial", Font.BOLD, 24));
        debtButton.addActionListener(e -> Main.showScreen("debt"));

        add(title);
        add(taxesButton);
        add(debtButton);
    }
}