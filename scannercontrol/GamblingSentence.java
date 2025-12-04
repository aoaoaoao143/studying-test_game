package scannercontrol;

public class GamblingSentence {

    protected String getGameStartSentence(String scanSentence) {
        return gameStartSentence(scanSentence);
    }

    protected String getGamblingSentence(String scanSentence) {
        return gamblingSentence(scanSentence);
    }

    protected int getReNumber(String scanSentence) {
        return reNumber(scanSentence);
    }

    /**
     * 文字の統一
     * 小文字大文字関係なく文字が一致した場合「char型」で１文字だけ返す
     * 
     * @param scanSentence 入力文字
     * @return 初期値x、y(yes)、c(賭け金額そのまま)、f(賭けを降りる)、b(賭け金額を上げる)、a(全て賭ける)
     */
    private static String gamblingSentence(String scanSentence) {
        String returnSentence = "x";
        if (scanSentence.equalsIgnoreCase("yes") || scanSentence.equalsIgnoreCase("y")) {
            returnSentence = "y";
        } else if (scanSentence.equalsIgnoreCase("check") || scanSentence.equalsIgnoreCase("call")
                || scanSentence.equalsIgnoreCase("c")) {
            // C(Check/Call) F(Fold)賭け金額はそのまま。
            returnSentence = "c";
        } else if (scanSentence.equalsIgnoreCase("fold") || scanSentence.equalsIgnoreCase("f")) {
            // F(Fold)賭けを降りる。
            returnSentence = "f";
        } else if (scanSentence.equalsIgnoreCase("bet") || scanSentence.equalsIgnoreCase("b")) {
            // B(Bet) R(Raise)賭け金額を上げる。
            returnSentence = "b";
        } else if (scanSentence.equalsIgnoreCase("raise") || scanSentence.equalsIgnoreCase("r")) {
            // B(Bet) R(Raise)賭け金額を上げる。
            returnSentence = "r";
        } else if (scanSentence.equalsIgnoreCase("all-in") || scanSentence.equalsIgnoreCase("all")
                || scanSentence.equalsIgnoreCase("a")) {
            // A(All-in)全て賭ける。
            returnSentence = "a";
        }
        return returnSentence;
    }

    private static String gameStartSentence(String scanSentence) {
        String returnSentence = "x";
        if (scanSentence.equalsIgnoreCase("yes") || scanSentence.equalsIgnoreCase("y")) {
            returnSentence = "y";
        } else if (scanSentence.equalsIgnoreCase("defaults") || scanSentence.equalsIgnoreCase("d")) {
            returnSentence = "d";
        }
        return returnSentence;
    }

    /**
     * 数字に変換
     * 
     * @param scanSentence 入力文字
     * @return 数字変換した入力値。errorの際は「0」を返却。
     */
    private static int reNumber(String scanSentence) {
        int returnNumber;
        try {
            returnNumber = Integer.valueOf(scanSentence);
            return returnNumber;
        } catch (NumberFormatException e) {
            // 入力文字が数字以外の場合
            System.out.println("───数字で入力してください。");
            return 0;
        }
    }
}
