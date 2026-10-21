import javax.swing.*;
import java.awt.*;

public class TaxesLessonPanel extends JPanel {

    private String[] titles = {
        "What Is a Tax?",
        "Income Tax",
        "Gross Pay vs. Net Pay",
        "Sales Tax",
        "Why Taxes Matter"
    };

    private String[] texts = {
        "A tax is money that people and businesses must pay to the government. You don't get to choose whether to pay it, and it is usually taken as a percentage.",
        "Income tax is taken out of the money you earn. The more you earn, the more tax you pay. Most people in the U.S. pay federal income tax, and many also pay state income tax.",
        "Gross pay is what you earn BEFORE taxes. Net pay (take-home pay) is what you actually get AFTER taxes. Example: if you earn $1,000 and $150 goes to taxes, your net pay is $850.",
        "Sales tax is added to the price when you buy something. If an item costs $20 and the sales tax is 8%, you pay $20 + $1.60 = $21.60.",
        "Taxes pay for things like roads, public schools, police, and fire departments. Planning for taxes helps you avoid being surprised by how much money you actually keep."
    };

    private int page = 0;

    private JLabel titleLabel;
    private JLabel textLabel;
    private JLabel pageLabel;
    private JButton backButton;
    private JButton nextButton;

    public TaxesLessonPanel() {
        setLayout(new BorderLayout());

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 22));

        pageLabel = new JLabel("", SwingConstants.CENTER);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            page--;
            showPage();
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if (page < titles.length - 1) {
                page++;
                showPage();
            } else {
                page = 0;
                showPage();
                Main.showScreen("menu");
            }
        });

        JPanel bottomBar = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottomBar.add(backButton);
        bottomBar.add(pageLabel);
        bottomBar.add(nextButton);

        add(titleLabel, BorderLayout.NORTH);
        add(textLabel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);

        showPage();
    }

    private void showPage() {
        titleLabel.setText(titles[page]);
        textLabel.setText("<html><div style='width:500px; text-align:center;'>"
                + texts[page] + "</div></html>");
        pageLabel.setText("Page " + (page + 1) + " of " + titles.length);

        backButton.setEnabled(page > 0);

        if (page == titles.length - 1) {
            nextButton.setText("Finish Lesson");
        } else {
            nextButton.setText("Next");
        }
    }
}