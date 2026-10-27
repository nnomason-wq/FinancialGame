
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaxesLessonPanel extends BackgroundPanel {

    private final String[] titles = {
        "What Is a Tax?",
        "Income Tax",
        "Gross Pay vs. Net Pay",
        "Sales Tax",
        "Why Taxes Matter"
    };

    private final String[] texts = {
        "A tax is money that people and businesses must pay to the government. You don't get to choose whether to pay it, and it is usually taken as a percentage.",
        "Income tax is taken out of the money you earn. The more you earn, the more tax you pay. Most people in the U.S. pay federal income tax, and many also pay state income tax.",
        "Gross pay is what you earn BEFORE taxes. Net pay (take-home pay) is what you actually get AFTER taxes. Example: if you earn $1,000 and $150 goes to taxes, your net pay is $850.",
        "Sales tax is added to the price when you buy something. If an item costs $20 and the sales tax is 8%, you pay $20 + $1.60 = $21.60.",
        "Taxes pay for things like roads, public schools, police, and fire departments. Planning for taxes helps you avoid being surprised by how much money you actually keep."
    };

    private int page = 0;

    private JLabel titleLabel;
    private JTextArea textArea;
    private JLabel pageLabel;

    private HotspotButton backButton;
    private HotspotButton nextButton;

    public TaxesLessonPanel() {
        super("lesson.png");
        setLayout(null);

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        // JTextArea automatically wraps long paragraphs.
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));
        textArea.setForeground(Theme.NAVY);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(8, 12, 8, 12));
        add(textArea);

        pageLabel = new JLabel("", SwingConstants.CENTER);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pageLabel.setForeground(Color.WHITE);
        add(pageLabel);

        backButton = new HotspotButton();
        backButton.setText("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 19));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            if (page > 0) {
                page--;
                showPage();
            } else {
                Main.showScreen("topics");
            }
        });
        add(backButton);

        nextButton = new HotspotButton();
        nextButton.setText("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 19));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {
            if (page < titles.length - 1) {
                page++;
                showPage();
            } else {
                page = 0;
                showPage();
                Main.showScreen("taxesQuiz");
            }
        });
        add(nextButton);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayout();
            }
        });

        showPage();
    }

    private void showPage() {
        titleLabel.setText(titles[page]);
        textArea.setText(texts[page]);
        textArea.setCaretPosition(0);

        pageLabel.setText(
            (page + 1) + " / " + titles.length
        );

        nextButton.setText(
            page == titles.length - 1
                ? "Finish Lesson"
                : "Next"
        );

        backButton.setText(
            page == 0 ? "Back" : "Back"
        );

        revalidate();
        repaint();
    }

    private void updateLayout() {
        int w = getWidth();
        int h = getHeight();

        // Title ribbon
        place(titleLabel, 0.24, 0.04, 0.52, 0.12);

        // Text safely inside the cream lesson panel
place(textArea, 0.18, 0.29, 0.58, 0.32);
        // Bottom navigation
        place(backButton, 0.06, 0.80, 0.22, 0.12);
        place(pageLabel, 0.39, 0.80, 0.22, 0.12);
        place(nextButton, 0.72, 0.80, 0.22, 0.12);
    }

    private void place(
        JComponent component,
        double x, double y,
        double width, double height
    ) {
        int w = getWidth();
        int h = getHeight();

        component.setBounds(
            (int) (x * w),
            (int) (y * h),
            (int) (width * w),
            (int) (height * h)
        );
    }

    @Override
    public void doLayout() {
        updateLayout();
    }
}
