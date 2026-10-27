import javax.swing.*;
import java.awt.*;

public class Main {

    private static JPanel screens;
    private static CardLayout cardLayout;
    public static Player player = new Player(); // shared by the whole game

    public static void main(String[] args) {
        JFrame frame = new JFrame("Financial Quest");
        frame.setSize(960, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        screens = new JPanel(cardLayout);

        screens.add(new MenuPanel(), "menu");
        screens.add(new TopicPanel(), "topics");
        screens.add(new LessonPanel(LessonContent.TAX_TITLES, LessonContent.TAX_TEXTS, "taxQuiz"), "taxes");
        screens.add(new LessonPanel(LessonContent.DEBT_TITLES, LessonContent.DEBT_TEXTS, "debtQuiz"), "debt");
          screens.add(new QuizPanel(QuizContent.TAX_QUIZ, "taxGame"), "taxQuiz");
   screens.add(new QuizPanel(QuizContent.DEBT_QUIZ, "debtGame"), "debtQuiz");
   screens.add(new GamePanel(ScenarioContent.TAX_SCENARIOS, "topics"), "taxGame");
   screens.add(new GamePanel(ScenarioContent.DEBT_SCENARIOS, "topics"), "debtGame");

        frame.add(screens);
        frame.setVisible(true);
    }

    // Any screen can call Main.showScreen("menu") to switch screens
    public static void showScreen(String name) {
        cardLayout.show(screens, name);
    }
}