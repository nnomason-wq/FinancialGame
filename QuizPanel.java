import javax.swing.*;
import java.awt.*;

// One reusable quiz screen. The questions and the next screen are passed in.
public class QuizPanel extends BackgroundPanel {

    private QuizQuestion[] questions;
    private String nextScreen;
    private int current = 0;          // which question we're on (0 = first)
    private int score = 0;            // number of correct answers
    private boolean answered = false; // stops the player answering twice
            for (int i = 0; i < 4; i++) {
            answerButtons[i].setTint(null);
        }

    private JLabel headerLabel;
    private JLabel questionLabel;
    private HotspotButton[] answerButtons = new HotspotButton[4];
    private HotspotButton continueButton;

    public QuizPanel(QuizQuestion[] questions, String nextScreen) {
        super("quiz.png");
        this.questions = questions;
        this.nextScreen = nextScreen;
        setLayout(null);

        headerLabel = new JLabel("", SwingConstants.CENTER);
        headerLabel.setFont(Theme.HEADING);
        headerLabel.setForeground(Color.WHITE);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(Theme.BODY);
        questionLabel.setForeground(Theme.NAVY);

        add(headerLabel);
        add(questionLabel);

        // A loop builds all four answer buttons so we don't repeat code 4 times
        for (int i = 0; i < 4; i++) {
            final int choice = i; // the lambda below needs its own unchanging copy of i
            answerButtons[i] = new HotspotButton();
            answerButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            answerButtons[i].setForeground(Color.WHITE);
               // Called when the player clicks one of the four answers
    private void checkAnswer(int choice) {
        if (answered) return; // ignore extra clicks
        answered = true;

        QuizQuestion q = questions[current];
        if (choice == q.correctIndex) {
            score++;
            answerButtons[choice].setTint(Theme.GREEN);      // right = green
            setQuestionText("Correct! " + q.explanation);
        } else {
            answerButtons[choice].setTint(Theme.RED);        // wrong = red
            answerButtons[q.correctIndex].setTint(Theme.GREEN); // show the right one in green
            setQuestionText("Not quite. The answer was: " + q.options[q.correctIndex]
                    + ". " + q.explanation);
        }
        continueButton.setText(current == questions.length - 1 ? "See Score" : "Continue");
    }
        }

        continueButton = new HotspotButton();
        continueButton.setFont(Theme.BUTTON);
        continueButton.setForeground(Color.WHITE);
        continueButton.addActionListener(e -> goNext());
        add(continueButton);

        showQuestion();
    }

    // Shows the current question and resets the buttons
    private void showQuestion() {
        QuizQuestion q = questions[current];
        answered = false;
        headerLabel.setText("Question " + (current + 1) + " of " + questions.length);
        setQuestionText(q.question);
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText("<html><div style='text-align:center; width:280px;'>"
                    + q.options[i] + "</div></html>");
        }
        continueButton.setText("");
    }

    // Called when the player clicks one of the four answers
    private void checkAnswer(int choice) {
        if (answered) return; // ignore extra clicks
        answered = true;

        QuizQuestion q = questions[current];
        if (choice == q.correctIndex) {
            score++;
            setQuestionText("Correct! " + q.explanation);
        } else {
            setQuestionText("Not quite. The answer was: " + q.options[q.correctIndex]
                    + ". " + q.explanation);
        }
        continueButton.setText(current == questions.length - 1 ? "See Score" : "Continue");
    }

    // Called when the player clicks the green button
    private void goNext() {
        if (!answered) return; // must answer first
        if (current < questions.length - 1) {
            current++;
            showQuestion();
        } else {
            JOptionPane.showMessageDialog(this,
                    "You got " + score + " out of " + questions.length + " correct!",
                    "Quiz Complete", JOptionPane.INFORMATION_MESSAGE);
            current = 0;   // reset so the quiz restarts next time
            score = 0;
            showQuestion();
            Main.showScreen(nextScreen);
        }
    }

    private void setQuestionText(String text) {
        questionLabel.setText("<html><div style='text-align:center; width:560px;'>"
                + text + "</div></html>");
    }

    // Positions are fractions of the window: left edge, top edge, width, height
       @Override
    public void doLayout() {
        place(headerLabel,      0.24, 0.03,  0.52, 0.14);
        place(questionLabel,    0.14, 0.23,  0.72, 0.20);
        place(answerButtons[0], 0.13, 0.455, 0.36, 0.123);
        place(answerButtons[1], 0.51, 0.455, 0.36, 0.123);
        place(answerButtons[2], 0.13, 0.587, 0.36, 0.126);
        place(answerButtons[3], 0.51, 0.587, 0.36, 0.126);
        place(continueButton,   0.345, 0.73, 0.31, 0.114);
    }

    private void place(JComponent c, double x, double y, double bw, double bh) {
        int w = getWidth();
        int h = getHeight();
        c.setBounds((int) (x * w), (int) (y * h), (int) (bw * w), (int) (bh * h));
    }
}