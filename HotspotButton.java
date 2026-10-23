import javax.swing.*;
import java.awt.*;

// An invisible button that lights up when the mouse is over it
public class HotspotButton extends JButton {

    public HotspotButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Faint white glow on hover, a bit stronger when pressed
        if (getModel().isPressed()) {
            g2.setColor(new Color(255, 255, 255, 90));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(255, 255, 255, 50));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        }
        g2.dispose();
    }
}