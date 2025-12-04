package Game;

// import TrumpCard.TrumpCardForIndianPoker;
// import TrumpCard.TrumpCard;
import scannercontrol.GetGamblingSentence;

import java.util.List;

import Gambling.Gambling;
import Gambling.GamblingManagement;
import Gambling.SetGambling;
import IndianPoker.TrumpCardForIndianPoker;
import IndianPoker.PlayerInformation.GameInitializationSettings;
import IndianPoker.PlayerInformation.PlayerList;
import IndianPoker.PlayerInformation.playerManagement;
import IndianPoker.Judge;
import TrumpCard.DealingTheCards;
import TrumpCard.TrumpCard;
import TrumpCard.TrumpDeck;

public class test {
    public static void main(String[] args) {

        // トランプ確認出力 s
        // TrumpDeck trumpD = new TrumpDeck();
        // List<TrumpCard> trump = trumpD.getDeck();
        // //TrumpCard.toString（デバック確認用）での確認
        // for (TrumpCard i : trump) {
        // System.out.println(i);
        // }
        // //id毎の確認
        // TrumpCard card;
        // for (int i = 0; i < 53; i++) {
        // card = trump.get(i);
        // System.out.print(card.getId() + ",");
        // System.out.print(card.getSuit() + card.getDisplay() + ",");
        // System.out.print(card.getNumber());
        // System.out.println();
        // if (card.getNumber() == 13) {
        // System.out.println();
        // }
        // }
        // // トランプ確認出力 e

        // ----------------------------------------------------------------------------
        // // インディアンポーカートランプ確認 s
        // TrumpDeck trumpD = new TrumpDeck();
        // List<TrumpCard> trump = trumpD.getDeck();

        // TrumpCardForIndianPoker TrumpIndi = new TrumpCardForIndianPoker();

        // TrumpCard card;
        // for (int i = 0; i < 53; i++) {
        // card = trump.get(i);
        // System.out.print(card.getId() + ",");
        // System.out.print(card.getSuit() + card.getDisplay() + ",");
        // System.out.print(card.getNumber() + ",");
        // System.out.print(TrumpIndi.getCardValue(i));
        // System.out.println();
        // if (card.getNumber() == 13) {
        // System.out.println();
        // }
        // }
        // // インディアンポーカートランプ確認 e
        // ----------------------------------------------------------------------------
        // // ギャンブル s
        // int playerId = 0;
        // // プレイヤーの参加有無, 賭け金更新, フォールド有無
        // System.out.println("参加有無" +
        // GamblingManagement.playerInformation[playerId][1]);
        // System.out.println("賭け金更新" +
        // GamblingManagement.playerInformation[playerId][3]);
        // System.out.println("フォールド有無" +
        // GamblingManagement.playerInformation[playerId][4]);

        // // ゲーム開始時に初期化
        // SetGambling.betReset();
        // // 賭けメインの呼び出し
        // SetGambling.mainGambling(playerId);

        // System.out.println("現状賭け金" +
        // GamblingManagement.stakesManagement[Gambling.stakesUp]);

        // // プレイヤーの参加有無, 賭け金更新, フォールド有無
        // System.out.println("参加有無" +
        // GamblingManagement.playerInformation[playerId][1]);
        // System.out.println("賭け金更新" +
        // GamblingManagement.playerInformation[playerId][3]);
        // System.out.println("フォールド有無" +
        // GamblingManagement.playerInformation[playerId][4]);
        // // // ギャンブル e

        // // // ▶︎スキャナークローズ
        // GetGamblingSentence.scanContorol(true);
        // ----------------------------------------------------------------------------
        // // ジャッジ s
        // Judge judging = new Judge();
        // judging.cardJudge(1, 2, 7, 5);
        // System.out.println(judging.maxNum);
        // // ジャッジ e
        // ----------------------------------------------------------------------------
        // // ランダム配布確認 s
        // DealingTheCards card = new DealingTheCards();
        // int participants = 6;
        // int reset = 53 - 53 % NumberOfParticipants;
        // for (int i = 1; i <= reset; i++) {
        // System.out.println(i + ":" + card.dealing(NumberOfParticipants));
        // }
        // // ランダム配布確認 e
        // ----------------------------------------------------------------------------
        // プレイヤー情報 s
        // DealingTheCards card = new DealingTheCards();
        // int participants = 6;
        // PlayerList pl1 = new PlayerList();
        // pl1.plList(participants, 10000);
        // List<playerManagement> PlList = pl1.getPlayerList();
        // playerManagement setPlayerInfo;

        // setPlayerInfo = PlList.get(1);
        // setPlayerInfo.setFoldStakes(3);
        // setPlayerInfo.addAmount(9950, 100);
        // setPlayerInfo = PlList.get(2);
        // setPlayerInfo.setFoldStakes(3);
        // setPlayerInfo.addAmount(-9950, 100);

        // List<playerManagement> activePlayers = pl1.getActivePlayers();

        // for (playerManagement aP : activePlayers) {
        // aP.setPlCardId(card.dealing(participants));
        // }
        // System.out.println("ID:名前:参加0不参加1:所持金:賭け金更新タイミング:賭け参加有無:所持カードID");
        // for (playerManagement i : PlList) {
        // System.out.println(i);
        // }
        // for (int a = 0; a <= 8; a++) {
        // for (int i = 0; i < participants; i++) {
        // setPlayerInfo = PlList.get(i);
        // setPlayerInfo.setPlCardId(card.dealing(participants));
        // }
        // for (playerManagement i : PlList) {
        // System.out.println(i);
        // }
        // }

        // System.out.println(pl1.getActivePlayers());
        // プレイヤー情報 e
        // ----------------------------------------------------------------------------
        // 初期設定 s
        GameInitializationSettings.GameSettings("IndianPoker");
        // 初期設定 e
        // ----------------------------------------------------------------------------
    }
}