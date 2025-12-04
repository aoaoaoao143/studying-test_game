package TrumpCard;

public class CardUtil {
    public static int cardNum(int id) {
        if (id == 0) {
            id = 0;
        } else {
            id = id % 13;
            if (id == 0) {
                id += 13;
            }
        }
        return id;
    }

    public static String cardDisplay(int num) {
        String display = "";
        switch (num) {
            case 1:
                display = "A";
                break;
            case 11:
                display = "J";
                break;
            case 12:
                display = "Q";
                break;
            case 13:
                display = "K";
                break;
            case 0:
                display = "JOKER";
                break;
            default:
                display = String.valueOf(num);
        }
        ;
        return display;
    }

    public static String cardSuit(int id) {
        /*
         * スート|Id
         * 🃏 |0
         * ♠️ |1~13
         * ♥️ |14~26
         * ♣️ |27~39
         * ♦️ |40~52
         */
        String suit = "";
        if (id == 0) {
            suit = "🃏";
        } else {
            int suitNum = (id - 1) / 13;
            switch (suitNum) {
                case 0:
                    suit = "♠️";
                    break;
                case 1:
                    suit = "♥️";
                    break;
                case 2:
                    suit = "♣️";
                    break;
                case 3:
                    suit = "♦️";
                    break;
                default:
                    suit = "";
            }
            ;
        }
        return suit;
    }
}
