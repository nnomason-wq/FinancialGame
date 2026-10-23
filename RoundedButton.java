import javax.swing.*;
import java.awt.*;

// A JButton that we draw ourselves as a colored rounded rectangle
public class RoundedButton extends JButton {

    private Color baseColor;

    public RoundedButton(String text, Color color) {
        super(text);
        baseColor = color;
        setFont(Theme.BUTTON);
        setForeground(Color.WHITE);
        setContentAreaFilled(false); // we paint the background ourselves
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pick the color: grey if disabled, darker while pressed
        Color c = baseColor;
        if (!isEnabled()) {
            c = new Color(90, 100, 130);
        } else if (getModel().isPressed()) {
            c = baseColor.darker();
        }

        g2.setColor(c);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();

        super.paintComponent(g); // draws the button text on top
    }
}