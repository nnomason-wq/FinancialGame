import javax.swing.*;
import java.awt.*;

// An invisible button that glows on hover and can be tinted a solid color
public class HotspotButton extends JButton {

    private Color tint = null; // null = no tint

    public HotspotButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Used by the quiz to turn a button green (right) or red (wrong)
    public void setTint(Color c) {
        tint = c;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Solid color over the painted button (slightly inset so the art's border still shows)
        if (tint != null) {
            g2.setColor(tint);
            g2.fillRoundRect(4, 4, getWidth() - 8, getHeight() - 8, 30, 30);
        }

        // 2. Faint white glow on hover, a bit stronger when pressed
        if (getModel().isPressed()) {
            g2.setColor(new Color(255, 255, 255, 90));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(255, 255, 255, 50));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        }
        g2.dispose();

        super.paintComponent(g); // 3. draws the button's text on top
    }
}