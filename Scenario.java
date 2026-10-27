// One financial situation with three decisions
public class Scenario {
    public final String text;
    public final Choice[] choices;

    public Scenario(String text, Choice[] choices) {
        this.text = text;
        this.choices = choices;
    }

    // One decision and what it does to the player's numbers
    public static class Choice {
        public final String label;
        public final int moneyChange;   // + adds money, - removes money
        public final int debtChange;    // + adds debt, - pays debt off
        public final int pointsChange;
        public final int rating;        // 1 = good, 0 = mixed, -1 = costly
        public final String explanation;

        public Choice(String label, int moneyChange, int debtChange,
                      int pointsChange, int rating, String explanation) {
            this.label = label;
            this.moneyChange = moneyChange;
            this.debtChange = debtChange;
            this.pointsChange = pointsChange;
            this.rating = rating;
            this.explanation = explanation;
        }
    }
}