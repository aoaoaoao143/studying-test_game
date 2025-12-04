package TrumpCard.bk;

public class TrumpCardForIndianPoker extends TrumpCard {
    private static String[][] trumpCardForIndeeianPoker = new String[53][4];
    static {
        for (int i = 0; i < 53; i++) {
            int Values = cardValues(i);
            // ID, 数字, 表示, 価値を格納
            trumpCardForIndeeianPoker[i][0] = getTrumpCard()[i][0];// ID
            trumpCardForIndeeianPoker[i][1] = getTrumpCard()[i][1];// 数字
            trumpCardForIndeeianPoker[i][2] = getTrumpCard()[i][2];// 表示
            trumpCardForIndeeianPoker[i][3] = String.valueOf(Values);// 価値
        }
    }

    // 呼び出しgetメソッド
    public static String[][] getTrumpCardForIndeianPoker() {
        return trumpCardForIndeeianPoker;
    }

    // カード価値を決める
    private static int cardValues(int cardNumber) {
        int val = 0;
        if (cardNumber == 0) {
            val = 14;
        } else {
            val = (cardNumber + 10) % 13 + 1;
        }
        return val;
    }
}
