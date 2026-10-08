import javax.swing.*;
import java.awt.*;

public class Main {

    // These are shared by the whole class so every method can use them
    static JPanel screens;
    static CardLayout cardLayout;

        // ----- Taxes lesson data -----
    static String[] taxTitles = {
        "What Is a Tax?",
        "Income Tax",
        "Gross Pay vs. Net Pay",
        "Sales Tax",
        "Why Taxes Matter"
    };

    static String[] taxText = {
        "A tax is money that people and businesses must pay to the government. You don't get to choose whether to pay it, and it is usually taken as a percentage.",
        "Income tax is taken out of the money you earn. The more you earn, the more tax you pay. Most people in the U.S. pay federal income tax, and many also pay state income tax.",
        "Gross pay is what you earn BEFORE taxes. Net pay (take-home pay) is what you actually get AFTER taxes. Example: if you earn $1,000 and $150 goes to taxes, your net pay is $850.",
        "Sales tax is added to the price when you buy something. If an item costs $20 and the sales tax is 8%, you pay $20 + $1.60 = $21.60.",
        "Taxes pay for things like roads, public schools, police, and fire departments. Planning for taxes helps you avoid being surprised by how much money you actually keep."
    };

    // Remembers which page the player is on (0 = first page)
    static int taxPage = 0;

    // The parts of the screen that change when the page changes
    static JLabel taxTitleLabel;
    static JLabel taxTextLabel;
    static JLabel taxPageLabel;
    static JButton taxBackButton;
    static JButton taxNextButton;

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
        screens.add(createTaxesLessonPanel(), "taxes");
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
    // Builds the Taxes lesson screen
    static JPanel createTaxesLessonPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        taxTitleLabel = new JLabel("", SwingConstants.CENTER);
        taxTitleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        taxTitleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        taxTextLabel = new JLabel("", SwingConstants.CENTER);
        taxTextLabel.setFont(new Font("Arial", Font.PLAIN, 22));

        taxPageLabel = new JLabel("", SwingConstants.CENTER);

        taxBackButton = new JButton("Back");
        taxBackButton.addActionListener(e -> {
            taxPage--;
            showTaxPage();
        });

        taxNextButton = new JButton("Next");
        taxNextButton.addActionListener(e -> {
            if (taxPage < taxTitles.length - 1) {
                taxPage++;
                showTaxPage();
            } else {
                // Last page: reset the lesson and return to the menu
                taxPage = 0;
                showTaxPage();
                cardLayout.show(screens, "menu");
            }
        });

        // A bottom bar that holds Back, the page number, and Next
        JPanel bottomBar = new JPanel(new GridLayout(1, 3, 10, 10));
        bottomBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        bottomBar.add(taxBackButton);
        bottomBar.add(taxPageLabel);
        bottomBar.add(taxNextButton);

        panel.add(taxTitleLabel, BorderLayout.NORTH);
        panel.add(taxTextLabel, BorderLayout.CENTER);
        panel.add(bottomBar, BorderLayout.SOUTH);

        showTaxPage(); // fill in page 1
        return panel;
    }

    // Updates the screen to match the current page number
    static void showTaxPage() {
        taxTitleLabel.setText(taxTitles[taxPage]);
        taxTextLabel.setText("<html><div style='width:500px; text-align:center;'>"
                + taxText[taxPage] + "</div></html>");
        taxPageLabel.setText("Page " + (taxPage + 1) + " of " + taxTitles.length);

        taxBackButton.setEnabled(taxPage > 0); // can't go back from page 1

        if (taxPage == taxTitles.length - 1) {
            taxNextButton.setText("Finish Lesson");
        } else {
            taxNextButton.setText("Next");
        }
    }}