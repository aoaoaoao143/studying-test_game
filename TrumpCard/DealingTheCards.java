package TrumpCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealingTheCards {
    private List<Integer> cards;
    private int currentIndex;

    public DealingTheCards() {
        resetCards(); // 初期化処理を共通化
    }

    public int dealing(int NumberOfParticipants) {
        // トランプ枚数を参加人数で割って余った数字分を引いた最大配布可能枚数
        int reset = 53 - 53 % NumberOfParticipants;
        // 最大配布可能枚数以上配っている時リセットします。
        if (currentIndex >= reset) {
            System.out.println("リセットします。");
            resetCards();
        }
        return cards.get(currentIndex++);
    }

    private void resetCards() {
        cards = new ArrayList<>();
        for (int i = 0; i < 53; i++) {
            cards.add(i);
        }
        Collections.shuffle(cards);
        currentIndex = 0;
    }
}