import javax.swing.*;
import java.awt.*;

public class Main {

    private static JPanel screens;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Financial Quest");
        frame.setSize(960, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        screens = new JPanel(cardLayout);

        screens.add(new MenuPanel(), "menu");
        screens.add(new TopicPanel(), "topics");
        screens.add(new LessonPanel(LessonContent.TAX_TITLES, LessonContent.TAX_TEXTS, "topics"), "taxes");
        screens.add(new LessonPanel(LessonContent.DEBT_TITLES, LessonContent.DEBT_TEXTS, "topics"), "debt");

        frame.add(screens);
        frame.setVisible(true);
    }

    // Any screen can call Main.showScreen("menu") to switch screens
    public static void showScreen(String name) {
        cardLayout.show(screens, name);
    }
}