package Gambling;

public class GamblingManagement {
    /** 賭け金の管理 */
    public static int[] stakesManagement = new int[100];
    static {
        stakesManagement[0] = 100;
    }

    /** プレイヤー情報 */
    public static int[][] playerInformation = new int[6][6];
    static {
        for (int i = 0; i < 6; i++) {
            // ID, 参加有無, 所持金, 賭け金更新のタイミング, フォールドのタイミング, トランプIDを格納
            playerInformation[i][0] = i;// ID
            playerInformation[i][1] = 0;// 参加有無「0:可能」「1:不可」
            playerInformation[i][2] = 10000;// 所持金
            playerInformation[i][3] = 0;// 賭け金更新のタイミング
            playerInformation[i][4] = 0;// フォールド有無「0:降りない」「1:降りる」
            playerInformation[i][5] = 0;// トランプID
        }
    }
    /** プレイヤーのカード */
    public static int[][] playerCardManagement;
}
