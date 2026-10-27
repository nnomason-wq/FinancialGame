
import javax.swing.*;
import java.awt.*;

public class QuizPanel extends BackgroundPanel {

    private QuizQuestion[] questions;
    private String nextScreen;
    private int current = 0;
    private int score = 0;
    private boolean answered = false;

    private JLabel headerLabel;
    private JTextArea questionText;
    private HotspotButton[] answerButtons = new HotspotButton[4];
    private HotspotButton continueButton;

    public QuizPanel(QuizQuestion[] questions, String nextScreen) {
        super("quiz.png");

        this.questions = questions;
        this.nextScreen = nextScreen;
        setLayout(null);

        // Question number in the title ribbon
        headerLabel = new JLabel("", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel);

        // Question and feedback text
        questionText = new JTextArea();
        questionText.setFont(new Font("Arial", Font.PLAIN, 16));
        questionText.setForeground(Theme.NAVY);
        questionText.setOpaque(false);
        questionText.setEditable(false);
        questionText.setFocusable(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setMargin(new Insets(5, 8, 5, 8));
        add(questionText);

        // Four answer buttons
        for (int i = 0; i < 4; i++) {
            final int choice = i;

            answerButtons[i] = new HotspotButton();
            answerButtons[i].setFont(
                new Font("Arial", Font.BOLD, 15)
            );
            answerButtons[i].setForeground(Color.WHITE);
            answerButtons[i].setHorizontalAlignment(
                SwingConstants.CENTER
            );

            answerButtons[i].addActionListener(
                e -> checkAnswer(choice)
            );

            add(answerButtons[i]);
        }

        // Continue button
        continueButton = new HotspotButton();
        continueButton.setFont(
            new Font("Arial", Font.BOLD, 18)
        );
        continueButton.setForeground(Color.WHITE);
        continueButton.addActionListener(e -> goNext());
        add(continueButton);

        showQuestion();
    }

    private void showQuestion() {
        QuizQuestion q = questions[current];
        answered = false;

        headerLabel.setText(
            "Question " + (current + 1)
            + " of " + questions.length
        );

        setQuestionText(q.question);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setTint(null);

            // Removed fixed-width HTML
            answerButtons[i].setText(q.options[i]);
        }

        continueButton.setText("");
    }

    private void checkAnswer(int choice) {
        if (answered) return;

        answered = true;

        QuizQuestion q = questions[current];

        if (choice == q.correctIndex) {
            score++;

            answerButtons[choice].setTint(Theme.GREEN);

            setQuestionText(
                "Correct! " + q.explanation
            );

        } else {
            answerButtons[choice].setTint(Theme.RED);

            answerButtons[q.correctIndex].setTint(
                Theme.GREEN
            );

            setQuestionText(
                "Not quite. The answer was: "
                + q.options[q.correctIndex]
                + ". " + q.explanation
            );
        }

        continueButton.setText(
            current == questions.length - 1
                ? "See Score"
                : "Continue"
        );
    }

    private void goNext() {
        if (!answered) return;

        if (current < questions.length - 1) {
            current++;
            showQuestion();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "You got " + score + " out of "
                + questions.length + " correct!",
                "Quiz Complete",
                JOptionPane.INFORMATION_MESSAGE
            );

            current = 0;
            score = 0;
            showQuestion();

            Main.showScreen(nextScreen);
        }
    }

    private void setQuestionText(String text) {
        questionText.setText(text);
        questionText.setCaretPosition(0);
    }

    @Override
    public void doLayout() {

        // Top title ribbon
        place(headerLabel,
            0.24, 0.03, 0.52, 0.14);

        // Cream question panel
        // Narrower area prevents text overflow
        place(questionText,
            0.18, 0.265, 0.64, 0.14);

        // Four colorful answer buttons
        place(answerButtons[0],
            0.13, 0.455, 0.36, 0.123);

        place(answerButtons[1],
            0.51, 0.455, 0.36, 0.123);

        place(answerButtons[2],
            0.13, 0.587, 0.36, 0.126);

        place(answerButtons[3],
            0.51, 0.587, 0.36, 0.126);

        // Green Continue button
        place(continueButton,
            0.345, 0.73, 0.31, 0.114);
    }

    private void place(
        JComponent c,
        double x,
        double y,
        double bw,
        double bh
    ) {
        int w = getWidth();
        int h = getHeight();

        c.setBounds(
            (int) (x * w),
            (int) (y * h),
            (int) (bw * w),
            (int) (bh * h)
        );
    }
}
