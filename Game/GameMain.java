package Game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import IndianPoker.Judge;
import IndianPoker.TrumpCardForIndianPoker;
import IndianPoker.PlayerInformation.GameInitializationSettings;
import IndianPoker.PlayerInformation.PlayerList;
import IndianPoker.PlayerInformation.playerManagement;
import TrumpCard.DealingTheCards;
import TrumpCard.TrumpCard;
import TrumpCard.TrumpDeck;
import scannercontrol.GetGamblingSentence;

public class GameMain {
    /**
     * プレイヤー数の設定
     * スコアの設定
     * 
     * ゲームのルール
     * ①ゲーム参加者数を決める。(2~6人)
     * ②全員の一律の手持ち金額を決める。（100~10000で百単位）
     * ③毎ゲームの参加費を決める。（手持ち金額の1割以下）
     * ❶ゲーム参加者には各ゲームごとにカードがそれぞれ１枚ずつ配られる。
     * ❷配られたカードの自分の数字は見ることができないが相手のカードを見ることはできる。
     * （昇順：3,4,5...J,Q,K,A,2,JOKER（ただしJOKERは3に負ける））
     * ❸（賭けるかどうかの駆け引きをする。）
     * ❹勝負するか掛け金を上げるか降りるかを決める。
     * ❺ポッド金額を配当。
     * ❻続けるか終了かを選択。
     * 続ける場合は❶から再度ゲームをする。
     * 終了の場合は全員の所持金から順位の発表。
     */

    /**
     * ◼︎ 賭けフレーズ ◼︎
     * チェック（Check）
     * ベット（Bet）
     * コール（Call）
     * レイズ（Raise）
     * フォールド（Fold）
     * オールイン（All-in）
     */
    public static void main(String[] args) {
        // トランプの基本情報リスト化
        TrumpDeck trumpD = new TrumpDeck();
        List<TrumpCard> trump = trumpD.getDeck();
        // トランプの価値
        TrumpCardForIndianPoker TrumpIndi = new TrumpCardForIndianPoker();
        // カードシャッフル
        DealingTheCards card = new DealingTheCards();

        GameInitializationSettings.GameSettings();
        PlayerList pL = new PlayerList();

        for (int game = 1; game <= 2; game++) {
            List<playerManagement> activePlayers = pL.getActivePlayers();
            // ❶ゲーム参加者には各ゲームごとにカードがそれぞれ１枚ずつ配られる。
            // シャッフルされたカードIDをセット
            for (playerManagement aP : activePlayers) {
                aP.setPlCardId(card.dealing(4));
            }

            // ❷配られたカードの自分の数字は見ることができないが相手のカードを見ることはできる。

            // ❸（賭けるかどうかの駆け引きをする。）

            // ❹勝負するか掛け金を上げるか降りるかを決める。

            // （昇順：3,4,5...J,Q,K,A,2,JOKER（ただしJOKERは3に負ける））
            Judge judging = new Judge();
            judging.cardJudge(1, 2, 7, 5);

            // ❺ポッド金額を配当。
            // 勝者に複数いた場合配当金額を割り、余が出た場合は、余りをスートの一番強いものに配当する。

            // 勝者に複数いた場合、スートの一番強いものに配当する。

            // ❻続けるか終了かを選択。

            // 続ける場合は❶から再度ゲームをする。

            // 全員の情報の確認。
            List<playerManagement> PlList = pL.getPlayerList();
            System.out.println("ID:名前:参加有無:所持金:賭け金更新タイミング:賭け参加有無:所持カードID");
            for (playerManagement i : PlList) {
                System.out.println(i);
            }
        }
        // 終了の場合は全員の所持金から順位の発表。

    }
}
