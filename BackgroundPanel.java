import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

// A panel that draws a picture stretched to fill the whole panel
public class BackgroundPanel extends JPanel {

    private Image background;

    public BackgroundPanel(String path) {
        setBackground(new Color(18, 32, 72)); // fallback color if the image is missing
        try {
            background = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("Could not load image: " + path);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // fills in the fallback color first
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}