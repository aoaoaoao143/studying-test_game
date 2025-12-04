package TrumpCard;

import java.util.ArrayList;
import java.util.List;

public class TrumpDeck {
    private final List<TrumpCard> deck = new ArrayList<>();

    public TrumpDeck() {
        for (int i = 0; i < 53; i++) {
            int number = CardUtil.cardNum(i);
            String display = CardUtil.cardDisplay(number);
            String suit = CardUtil.cardSuit(i);
            deck.add(new TrumpCard(i, suit, display, number));
        }
    }

    public List<TrumpCard> getDeck() {
        return deck;
    }
}
