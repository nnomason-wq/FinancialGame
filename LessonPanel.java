import javax.swing.*;
import java.awt.*;

// One reusable lesson screen. It works for ANY topic because the
// content (titles, texts) and the next screen are passed in.
public class LessonPanel extends BackgroundPanel {

    private String[] titles;
    private String[] texts;
    private String nextScreen;   // where "Finish Lesson" goes
    private int page = 0;        // which page we're on (0 = first)

    private JLabel titleLabel;
    private JLabel textLabel;
    private JLabel pageLabel;
    private HotspotButton backButton;
    private HotspotButton nextButton;

    public LessonPanel(String[] titles, String[] texts, String nextScreen) {
        super("lesson.png");
        this.titles = titles;
        this.texts = texts;
        this.nextScreen = nextScreen;
        setLayout(null);

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(Theme.HEADING);
        titleLabel.setForeground(Color.WHITE);

        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(Theme.BODY);
        textLabel.setForeground(Theme.NAVY);

        pageLabel = new JLabel("", SwingConstants.CENTER);
        pageLabel.setFont(Theme.BUTTON);
        pageLabel.setForeground(Color.WHITE);

        // Back is painted in the artwork, so this button has no text of its own
        backButton = new HotspotButton();
        backButton.addActionListener(e -> {
            if (page > 0) {
                page--;
                showPage();
            } else {
                Main.showScreen("topics"); // Back on page 1 leaves the lesson
            }
        });

        // The green button is blank in the artwork, so we draw its text
        nextButton = new HotspotButton();
        nextButton.setFont(Theme.BUTTON);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {
            if (page < titles.length - 1) {
                page++;
                showPage();
            } else {
                page = 0;       // reset so the lesson restarts next time
                showPage();
                Main.showScreen(nextScreen);
            }
        });

        add(titleLabel);
        add(textLabel);
        add(pageLabel);
        add(backButton);
        add(nextButton);
        showPage();
    }

    // Refreshes the screen to match the current page number
    private void showPage() {
        titleLabel.setText(titles[page]);
        textLabel.setText("<html><div style='width:600px; text-align:center;'>"
                + texts[page] + "</div></html>");
        pageLabel.setText((page + 1) + " / " + titles.length);

        if (page == titles.length - 1) {
            nextButton.setText("Finish Lesson");
        } else {
            nextButton.setText("Next");
        }
    }

    // Positions are fractions of the window: left edge, top edge, width, height
    @Override
    public void doLayout() {
        place(titleLabel, 0.24, 0.03, 0.52, 0.16);  // inside the navy ribbon
        place(textLabel,  0.14, 0.26, 0.72, 0.40);  // inside the cream panel
        place(pageLabel,  0.43, 0.74, 0.16, 0.08);  // between the two buttons
        place(backButton, 0.18, 0.72, 0.24, 0.11);  // over the painted Back button
        place(nextButton, 0.60, 0.72, 0.25, 0.11);  // over the painted green button
    }

    private void place(JComponent c, double x, double y, double bw, double bh) {
        int w = getWidth();
        int h = getHeight();
        c.setBounds((int) (x * w), (int) (y * h), (int) (bw * w), (int) (bh * h));
    }
}