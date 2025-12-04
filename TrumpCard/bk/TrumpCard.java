package TrumpCard.bk;

public class TrumpCard {
	private static String[][] trumpCard = new String[53][3];
	// 一回だけ処理を実施
	static {
		for (int i = 0; i < 53; i++) {
			int number = cardNum(i); // 1〜13

			String display = cardDisplay(number); // A~K トランプの表示
			String suit = cardSuit(i);// ♠️♥️♣️♦️
			// スートを合わせたカードの表示 例）♠️A,♥️3,♣️J,♦️K
			String trumpCardDisp = suit + display;

			// ID, 数字, 表示, を格納
			trumpCard[i][0] = String.valueOf(i);// ID
			trumpCard[i][1] = String.valueOf(number);// 数字
			trumpCard[i][2] = trumpCardDisp;// 表示
		}
	}

	/**
	 * トランプカードのgetメソッド
	 * 
	 * @return トランプの「ID」「数字」「表示」を格納した配列の返却
	 */
	public static String[][] getTrumpCard() {
		return trumpCard;
	}

	/**
	 * カードの数字
	 * 
	 * @param num ID（0-52）
	 * @return 数字（0、1-13）
	 */
	private static int cardNum(int num) {
		if (num == 0) {
			num = 0;
		} else {
			num = num % 13;
			if (num == 0) {
				num += 13;
			}
		}
		return num;
	}

	/**
	 * カードの表示
	 * 
	 * @param cardNumber 数字（0、1-13）
	 * @return 表示（A,2-10,J,Q,K,JOKER）
	 */
	private static String cardDisplay(int cardNumber) {
		String display = "";
		switch (cardNumber) {
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
				display = String.valueOf(cardNumber);
		}
		;
		return display;
	}

	/**
	 * スート表示
	 * 
	 * @param cardId ID（0~52）
	 * @return ♠️（1-13）♥️（14-26）♣️（27-39）♦️（40-52）
	 */
	private static String cardSuit(int cardId) {
		/*
		 * スート|Id
		 * 🃏 |0
		 * ♠️ |1~13
		 * ♥️ |14~26
		 * ♣️ |27~39
		 * ♦️ |40~52
		 */
		String suit = "";
		if (cardId == 0) {
			suit = "🃏";
		} else {
			int suitNum = (cardId - 1) / 13;
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
