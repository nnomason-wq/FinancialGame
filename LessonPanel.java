
import javax.swing.*;
import java.awt.*;

public class LessonPanel extends BackgroundPanel {

    private String[] titles;
    private String[] texts;
    private String nextScreen;
    private int page = 0;

    private JLabel titleLabel;
    private JTextArea textArea;
    private JLabel pageLabel;
    private HotspotButton backButton;
    private HotspotButton nextButton;

    public LessonPanel(String[] titles, String[] texts, String nextScreen) {
        super("lesson.png");

        this.titles = titles;
        this.texts = texts;
        this.nextScreen = nextScreen;

        setLayout(null);

        // TITLE
        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);

        // FIXED LESSON TEXT
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 17));
        textArea.setForeground(Theme.NAVY);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);

        // THESE TWO LINES FIX THE TEXT OVERFLOW
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.setMargin(new Insets(10, 15, 10, 15));

        // PAGE COUNTER
        pageLabel = new JLabel("", SwingConstants.CENTER);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pageLabel.setForeground(Color.WHITE);

        // BACK BUTTON
        backButton = new HotspotButton();
        backButton.addActionListener(e -> {
            if (page > 0) {
                page--;
                showPage();
            } else {
                Main.showScreen("topics");
            }
        });

        // NEXT BUTTON
        nextButton = new HotspotButton();
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextButton.setForeground(Color.WHITE);

        nextButton.addActionListener(e -> {
            if (page < titles.length - 1) {
                page++;
                showPage();
            } else {
                page = 0;
                showPage();
                Main.showScreen(nextScreen);
            }
        });

        add(titleLabel);
        add(textArea);
        add(pageLabel);
        add(backButton);
        add(nextButton);

        showPage();
    }

    private void showPage() {
        titleLabel.setText(titles[page]);

        // Plain text automatically wraps within the text area
        textArea.setText(texts[page]);
        textArea.setCaretPosition(0);

        pageLabel.setText(
            (page + 1) + " / " + titles.length
        );

        if (page == titles.length - 1) {
            nextButton.setText("Finish Lesson");
        } else {
            nextButton.setText("Next");
        }

        repaint();
    }

    @Override
    public void doLayout() {

        // TITLE RIBBON
        place(titleLabel, 0.24, 0.03, 0.52, 0.16);

        // TEXT INSIDE CREAM PANEL
        // Narrower width keeps text away from edges
        place(textArea, 0.18, 0.33, 0.64, 0.27);

        // PAGE NUMBER
        place(pageLabel, 0.43, 0.74, 0.16, 0.08);

        // BUTTONS
        place(backButton, 0.18, 0.72, 0.24, 0.11);
        place(nextButton, 0.60, 0.72, 0.25, 0.11);
    }

    private void place(
        JComponent c,
        double x,
        double y,
        double bw,
        double bh
    ) {
        int w = getWidth();
        int h = getHeight();

        c.setBounds(
            (int) (x * w),
            (int) (y * h),
            (int) (bw * w),
            (int) (bh * h)
        );
    }
}
