package TrumpCard;

public class TrumpCard {
    private final int id;
    private final String suit;
    private final String display;
    private final int number;

    public TrumpCard(int id, String suit, String display, int number) {
        this.id = id;
        this.suit = suit;
        this.display = display;
        this.number = number;
    }

    // getter類
    public int getId() {
        return id;
    }

    public String getSuit() {
        return suit;
    }

    public String getDisplay() {
        return display;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return id + ":" + suit + display + "(" + number + ")";
    }
}
