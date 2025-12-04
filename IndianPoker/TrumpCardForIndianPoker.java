package IndianPoker;

import java.util.HashMap;
import java.util.Map;

import TrumpCard.TrumpCard;
import TrumpCard.TrumpDeck;

public class TrumpCardForIndianPoker extends TrumpDeck {
    private final Map<Integer, Integer> cardValues = new HashMap<>();

    public TrumpCardForIndianPoker() {
        super();
        for (TrumpCard card : getDeck()) {
            int val = calcCardValue(card.getNumber());
            cardValues.put(card.getId(), val);
        }
    }

    private int calcCardValue(int cardNumber) {
        int val = 0;
        if (cardNumber == 0) {
            val = 14;
        } else {
            val = (cardNumber + 10) % 13 + 1;
        }
        return val;
    }

    public int getCardValue(int getId) {
        return cardValues.get(getId);
    }
}
