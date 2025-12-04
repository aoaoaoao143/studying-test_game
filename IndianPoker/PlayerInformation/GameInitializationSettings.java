package IndianPoker.PlayerInformation;

import scannercontrol.GetGamblingSentence;

public class GameInitializationSettings {

    // ①ゲーム参加者数を決める。(2~6人)
    public static int participants;
    // ②全員の一律の手持ち金額を決める。（100~10000で百単位）
    public static int monneyOnHand;
    // ③毎ゲームの参加費を決める。（手持ち金額の1割以下）
    public static int participantionFee;

    /**
     * 
     * @param gameTitle ・IndianPoker
     */
    public static void GameSettings(String gameTitle) {
        Defaultvalues(gameTitle);
        GetGamblingSentence scan = new GetGamblingSentence();
        String sentence;

        System.out.println("──────" + gameTitle + "──────");
        System.out.println("デフォルト値:");
        System.out.println();
        System.out.println("プレイヤー：" + participants + "人（NPC:" + (participants - 1) + "人）");
        System.out.println("手持ち金額：" + monneyOnHand);
        System.out.println("賭け参加費：" + participantionFee);
        System.out.println();
        System.out.println("デフォルト値でゲームするの場合は「d」を、オリジナルの値でゲームする場合は「o」を入力ください。");
        System.out.print("▶︎ ");
        sentence = scan.getGameStartSentence();
        if (sentence.equals("d")) {
        } else {
            int num;
            System.out.println("ゲーム参加者数を決める。(2~6人)");
            while (true) {
                System.out.print("▶︎ ");
                num = scan.getReNumber();
                if (num >= 2 && num <= 6) {
                    participants = num;
                    break;
                }
                System.out.println("2~6 で入力してください");
            }

            System.out.println("全員の一律の手持ち金額を決める。（100~10000で百単位）");
            while (true) {
                System.out.print("▶︎ ");
                num = scan.getReNumber();
                if (num >= 100 && num <= 10000 && 0 == num % 100) {
                    // if (0 == num % 100) {
                    monneyOnHand = num;
                    break;
                    // }

                }
                System.out.print("で入力してください。また、");
                System.out.println("100~10000 また 百単位 で入力してください。");
            }

            int tenPercent = monneyOnHand * 10 / 100;
            System.out.println("毎ゲームの参加費を決めてください。上限は、手持ち金額の1割（" + tenPercent + "）以下で入力してください。参加費無しはできません");
            while (true) {
                System.out.print("▶︎ ");
                num = scan.getReNumber();
                if (num <= tenPercent && num > 0) {
                    participantionFee = num;
                    break;
                }
                System.out.println("手持ち金額の1割（" + monneyOnHand * 10 / 100 + "）以下で入力してください。参加費無しはできません。");
            }

        }
        System.out.println("プレイヤー：" + participants + "人（NPC:" + (participants - 1) + "人）");
        System.out.println("手持ち金額：" + monneyOnHand);
        System.out.println("賭け参加費：" + participantionFee);

        // プレイヤー情報の作成
        // PlayerList pL = new PlayerList();
        // pL.plList(participants, monneyOnHand);
    }

    private static void Defaultvalues(String gameTitle) {
        if (gameTitle.equals("IndianPoker")) {
            participants = 4;
            monneyOnHand = 1000;
            participantionFee = 10;
        } else {
            participants = 1;
            monneyOnHand = 0;
            participantionFee = 0;
        }
    }

}
