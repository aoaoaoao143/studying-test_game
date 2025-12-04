package scannercontrol;

import java.util.Scanner;

public class GetGamblingSentence extends GamblingSentence {

    private static String scan;

    public static void scanContorol(boolean change) {
        Scanner input = new Scanner(System.in);
        if (change) {
            input.close();
        } else {
            scan = input.next();
        }
    }

    /**
     * ゲーム初期値の文字の統一
     * 
     * @param scanSentence 入力文字
     * @return 初期値x、y(yes)、c(賭け金額そのまま)、f(賭けを降りる)、b(賭け金額を上げる)、a(全て賭ける)
     */
    public String getGameStartSentence() {
        scanContorol(false);
        return super.getGameStartSentence(scan);
    }

    /**
     * ギャンブル時の文字の統一
     * 
     * @param scanSentence 入力文字
     * @return 初期値x、y(yes)、c(賭け金額そのまま)、f(賭けを降りる)、b(賭け金額を上げる)、a(全て賭ける)
     */
    public String getGamblingSentence() {
        scanContorol(false);
        return super.getGamblingSentence(scan);
    }

    /**
     * 数字に変換
     * 
     * @param scanSentence 入力文字
     * @return 数字変換した入力値。errorの際は「0」を返却。
     */
    public int getReNumber() {
        scanContorol(false);
        return super.getReNumber(scan);
    }
}
