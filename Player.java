// Holds the player's numbers. One Player object is shared by the whole game.
public class Player {
    public int money;
    public int debt;
    public int points;
    public boolean negotiationUnlocked; // used in Section 5

    public Player() {
        reset();
    }

    // Starting values for every new game
    public void reset() {
        money = 1000;
        debt = 500;
        points = 0;
        negotiationUnlocked = false;
    }
}