import javax.swing.*;

public class MenuPanel extends BackgroundPanel {

    private HotspotButton startButton;
    private HotspotButton howButton;
    private HotspotButton exitButton;

    public MenuPanel() {
        super("start.png");
        setLayout(null); // we position the buttons ourselves in doLayout()

        startButton = new HotspotButton();
        startButton.addActionListener(e -> Main.showScreen("topics"));

        howButton = new HotspotButton();
        howButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "1. Learn about a topic.\n"
                + "2. Take a quick quiz.\n"
                + "3. Make money decisions in real-life scenarios.\n"
                + "4. See the consequences and earn points!",
                "How to Play", JOptionPane.INFORMATION_MESSAGE));

        exitButton = new HotspotButton();
        exitButton.addActionListener(e -> System.exit(0));

        add(startButton);
        add(howButton);
        add(exitButton);
    }

    // Swing calls this whenever the panel needs to arrange its children
    @Override
    public void doLayout() {
       place(startButton, 0.307, 0.481, 0.387, 0.111);
       place(howButton,   0.307, 0.603, 0.387, 0.105);
       place(exitButton,  0.307, 0.716, 0.387, 0.111);
    }

    // x, y, bw, bh are fractions of the panel's width/height (0.0 to 1.0)
    private void place(JComponent c, double x, double y, double bw, double bh) {
        int w = getWidth();
        int h = getHeight();
        c.setBounds((int) (x * w), (int) (y * h), (int) (bw * w), (int) (bh * h));
    }
}