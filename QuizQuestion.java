// Holds ONE quiz question. A quiz is an array of these objects.
public class QuizQuestion {
    public final String question;
    public final String[] options;   // always 4 answers
    public final int correctIndex;   // 0-3: which option is right
    public final String explanation; // shown after the player answers

    public QuizQuestion(String question, String[] options, int correctIndex, String explanation) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanation = explanation;
    }
}