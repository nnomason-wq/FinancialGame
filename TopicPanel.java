import javax.swing.*;

// Topic selection screen: ChatGPT artwork with painted buttons,
// and invisible HotspotButtons placed on top of them
public class TopicPanel extends BackgroundPanel {

    private HotspotButton taxesButton;
    private HotspotButton debtButton;
    private HotspotButton backButton;

    public TopicPanel() {
        super("topics.png");
        setLayout(null); // we position the buttons ourselves in doLayout()

        taxesButton = new HotspotButton();
        taxesButton.addActionListener(e -> Main.showScreen("taxes"));

        debtButton = new HotspotButton();
        debtButton.addActionListener(e -> Main.showScreen("debt"));

        backButton = new HotspotButton();
        backButton.addActionListener(e -> Main.showScreen("menu"));

        add(taxesButton);
        add(debtButton);
        add(backButton);
    }

    // Positions are fractions of the window: left edge, top edge, width, height
    @Override
    public void doLayout() {
        place(taxesButton, 0.307, 0.38, 0.387, 0.13);
        place(debtButton,  0.307, 0.54, 0.387, 0.13);
        place(backButton,  0.307, 0.68, 0.387, 0.13);
    }

    private void place(JComponent c, double x, double y, double bw, double bh) {
        int w = getWidth();
        int h = getHeight();
        c.setBounds((int) (x * w), (int) (y * h), (int) (bw * w), (int) (bh * h));
    }
}