
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends BackgroundPanel {

    private Scenario[] scenarios;
    private String nextScreen;
    private int current = 0;

    private JLabel moneyLabel, debtLabel, pointsLabel;
    private JLabel numberLabel, scenarioLabel;

    private HotspotButton[] choiceButtons = new HotspotButton[3];
    private HotspotButton lockedButton;
    private ResultCard resultCard;

    public GamePanel(Scenario[] scenarios, String nextScreen) {
        super("game.png");

        this.scenarios = scenarios;
        this.nextScreen = nextScreen;

        setLayout(null);

        // Smaller dashboard text
        moneyLabel = makeLabel(
            new Font("Arial", Font.BOLD, 20), Theme.GREEN);

        debtLabel = makeLabel(
            new Font("Arial", Font.BOLD, 20),
            new Color(255, 120, 110));

        pointsLabel = makeLabel(
            new Font("Arial", Font.BOLD, 20),
            new Color(255, 215, 0));

        numberLabel = makeLabel(
            new Font("Arial", Font.BOLD, 17), Theme.NAVY);

        // Scenario description
        scenarioLabel = new JLabel("", SwingConstants.CENTER);
        scenarioLabel.setFont(
            new Font("Arial", Font.PLAIN, 18));
        scenarioLabel.setForeground(Theme.NAVY);
        add(scenarioLabel);

        // Three financial decision buttons
        for (int i = 0; i < 3; i++) {
            final int choice = i;

            choiceButtons[i] = new HotspotButton();
            choiceButtons[i].setFont(
                new Font("Arial", Font.BOLD, 17));
            choiceButtons[i].setForeground(Color.WHITE);
            choiceButtons[i].setHorizontalAlignment(
                SwingConstants.CENTER);

            choiceButtons[i].addActionListener(
                e -> chooseOption(choice));

            add(choiceButtons[i]);
        }

        // Purple negotiation button
        lockedButton = new HotspotButton();
        lockedButton.setFont(
            new Font("Arial", Font.BOLD, 17));
        lockedButton.setForeground(Color.WHITE);
        lockedButton.setHorizontalAlignment(
            SwingConstants.CENTER);
        lockedButton.setText("Negotiate (Locked)");
        lockedButton.setTint(
            new Color(40, 30, 60, 170));

        add(lockedButton);

        // Feedback card
        resultCard = new ResultCard();
        resultCard.setVisible(false);
        add(resultCard);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                startGame();
            }
        });

        startGame();
    }

    private JLabel makeLabel(Font font, Color color) {
        JLabel label = new JLabel(
            "", SwingConstants.CENTER);

        label.setFont(font);
        label.setForeground(color);
        add(label);

        return label;
    }

    private void startGame() {
        Main.player.reset();
        current = 0;
        showScenario();
    }

    private void showScenario() {
        Scenario s = scenarios[current];

        numberLabel.setText(
            "Scenario " + (current + 1)
            + " of " + scenarios.length);

        scenarioLabel.setText(
            "<html><div style='text-align:center;"
            + " width:500px;'>"
            + s.text
            + "</div></html>");

        for (int i = 0; i < 3; i++) {
            choiceButtons[i].setText(
                "<html><div style='text-align:center;"
                + " width:440px;'>"
                + s.choices[i].label
                + "</div></html>");

            choiceButtons[i].setVisible(true);
        }

        lockedButton.setText("Negotiate (Locked)");
        lockedButton.setVisible(true);

        resultCard.setVisible(false);
        updateDashboard();
    }

    private void chooseOption(int index) {
        Scenario.Choice c =
            scenarios[current].choices[index];

        Player p = Main.player;

        int oldMoney = p.money;
        int oldDebt = p.debt;

        // Apply the financial consequences
        p.money += c.moneyChange;
        p.debt += c.debtChange;
        p.points += c.pointsChange;

        String heading;
        String color;

        if (c.rating == 1) {
            heading = "Good Choice!";
            color = "#27ae60";
        } else if (c.rating == 0) {
            heading = "It Depends...";
            color = "#e67e22";
        } else {
            heading = "Costly Choice";
            color = "#e74c3c";
        }

        String feedback =
            "<html><div style='text-align:center;"
            + " width:490px;'>"
            + "<b style='color:" + color + ";'>"
            + heading + "</b><br>"
            + "Money: " + money(oldMoney)
            + " → " + money(p.money) + "<br>"
            + "Debt: " + money(oldDebt)
            + " → " + money(p.debt) + "<br>"
            + "Points: "
            + (c.pointsChange >= 0 ? "+" : "")
            + c.pointsChange + "<br><br>"
            + c.explanation
            + "</div></html>";

        for (HotspotButton button : choiceButtons) {
            button.setVisible(false);
        }

        lockedButton.setVisible(false);

        resultCard.show(
            feedback,
            current == scenarios.length - 1
                ? "Finish" : "Continue");

        updateDashboard();
    }

    private void goNext() {
        if (current < scenarios.length - 1) {
            current++;
            showScenario();
        } else {
            Player p = Main.player;

            JOptionPane.showMessageDialog(
                this,
                "Final money: " + money(p.money)
                + "\nRemaining debt: " + money(p.debt)
                + "\nPoints: " + p.points,
                "Game Complete",
                JOptionPane.INFORMATION_MESSAGE
            );

            Main.showScreen(nextScreen);
        }
    }

    private void updateDashboard() {
        moneyLabel.setText(
            "Money: " + money(Main.player.money));

        debtLabel.setText(
            "Debt: " + money(Main.player.debt));

        pointsLabel.setText(
            "Points: " + Main.player.points);
    }

    private String money(int n) {
        return (n < 0 ? "-" : "")
            + "$"
            + String.format("%,d", Math.abs((long) n));
    }

    // Proportional positioning
    @Override
    public void doLayout() {
        place(moneyLabel,
            0.06, 0.035, 0.28, 0.095);

        place(debtLabel,
            0.36, 0.035, 0.28, 0.095);

        place(pointsLabel,
            0.66, 0.035, 0.28, 0.095);

        place(numberLabel,
            0.36, 0.152, 0.28, 0.065);

        place(scenarioLabel,
            0.12, 0.25, 0.76, 0.21);

        place(choiceButtons[0],
            0.15, 0.49, 0.70, 0.09);

        place(choiceButtons[1],
            0.15, 0.60, 0.70, 0.09);

        place(choiceButtons[2],
            0.15, 0.71, 0.70, 0.09);

        place(lockedButton,
            0.15, 0.82, 0.70, 0.09);

        place(resultCard,
            0.10, 0.49, 0.80, 0.43);
    }

    private void place(
        JComponent component,
        double x, double y,
        double width, double height) {

        int w = getWidth();
        int h = getHeight();

        component.setBounds(
            (int) (x * w),
            (int) (y * h),
            (int) (width * w),
            (int) (height * h)
        );
    }

    // Financial consequence feedback card
    private class ResultCard extends JPanel {

        private JLabel text = new JLabel(
            "", SwingConstants.CENTER);

        private RoundedButton continueButton =
            new RoundedButton("Continue", Theme.GREEN);

        ResultCard() {
            setOpaque(false);
            setLayout(new BorderLayout(0, 8));

            setBorder(
                BorderFactory.createEmptyBorder(
                    10, 20, 15, 20));

            text.setFont(
                new Font("Arial", Font.PLAIN, 14));

            text.setForeground(Theme.NAVY);

            continueButton.setFont(
                new Font("Arial", Font.BOLD, 17));

            continueButton.setPreferredSize(
                new Dimension(200, 44));

            continueButton.addActionListener(
                e -> goNext());

            add(text, BorderLayout.CENTER);
            add(continueButton, BorderLayout.SOUTH);
        }

        void show(String html, String buttonText) {
            text.setText(html);
            continueButton.setText(buttonText);
            setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 =
                (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(
                new Color(255, 248, 235));

            g2.fillRoundRect(
                0, 0,
                getWidth(), getHeight(),
                40, 40);

            g2.setColor(Theme.NAVY);
            g2.setStroke(new BasicStroke(4));

            g2.drawRoundRect(
                2, 2,
                getWidth() - 5,
                getHeight() - 5,
                40, 40);

            g2.dispose();
        }
    }
}
