package Gambling;

import scannercontrol.GetGamblingSentence;

/**
 * 賭け
 * ●チェック（Check）参加費のまま金額はあげない
 * ●ベット（Bet）賭け金額を上げる
 * ●コール（Call）現状の賭け金額で賭けに参加
 * ●レイズ（Raise）現状の賭け金額を上げる
 * ●フォールド（Fold）賭けから降りる
 * ●オールイン（All-in）全てを賭ける
 */
public class Gambling {

    /** 質問パターン「0」初期、「1」賭け金上昇 */
    private static int gamblingPattern;
    /** 所持金と賭け金の状態管理「1:所持金 < 賭け金」「2:所持金 = 賭け金」「3:所持金 > 賭け金」 */
    private static int gamblMesseBranchCheck;
    /** 賭け金更新のタイミング(賭けからおりた時の金額などの管理や、Maxの賭け金の管理のため) */
    protected int stakesUp;
    /** 賭け金相場 */
    private static int betAmount;
    /** 所持金 */
    private static int amountOnHand;
    /** 賭け可能金額 */
    private static int difference;
    /** 追加賭け金 */
    private static int newStakes;

    /**
     * ゲーム開始時の初期化
     */
    protected void betReset() {
        gamblingPattern = 0;
        stakesUp = 0;
    }

    /**
     * 参加権の確認
     */
    private static int participation(int playerId) {
        return GamblingManagement.playerInformation[playerId][1];
    }

    /**
     * 賭けごとに現状の賭け金とプレイヤー情報の取得
     * 
     * @param id プレイヤーID
     */
    private void setPlayerInfor(int playerId) {
        betAmount = GamblingManagement.stakesManagement[stakesUp];
        amountOnHand = GamblingManagement.playerInformation[playerId][2];
        difference = amountOnHand - betAmount;
        setGamblBranchCheck();
    }

    /**
     * 所持金と賭け金の比較
     */
    private static void setGamblBranchCheck() {
        // 所持金と賭け金
        if (betAmount > amountOnHand) {
            gamblMesseBranchCheck = 1;// 所持金が賭け金より少ない時
        } else if (betAmount == amountOnHand) {
            gamblMesseBranchCheck = 2;// 所持金が賭け金と同じ時
        } else if (betAmount < amountOnHand) {
            gamblMesseBranchCheck = 3;// 所持金が賭け金より多い時
        }
    }

    /**
     * 賭けの質問パターン変更
     */
    private static void nowGamblingPattern(String pattern) {
        if (pattern.equals("reset")) {
            gamblingPattern = 0;
        }
        if (pattern.equals("didBet")) {
            gamblingPattern = 1;
        }
    }

    /**
     * B（Bet）/R（Raise）の場合に実施
     */
    private void setNewStakes(int playerId) {
        stakesUp += 1;
        GamblingManagement.stakesManagement[stakesUp] = newStakes;
        GamblingManagement.playerInformation[playerId][3] = stakesUp;
        if (gamblingPattern == 0) {
            nowGamblingPattern("didBet");
        }
    }

    /**
     * ◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎
     * 継承用メソッド
     * ◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎◼︎
     */
    protected void mainGambling(int playerId) {
        if (participation(playerId) == 0) {
            String input = "x";
            // // test-s
            // gamblingPattern = 0;// 変更
            // betAmount = 100;// 変更
            // amountOnHand = 100;// 変更
            // difference = amountOnHand - betAmount;
            // setGamblBranchCheck();
            // // test-e

            // プレイヤー情報のインプット
            setPlayerInfor(playerId);

            checkAmountAnswer();
            // 賭け前の確認台詞と賭けの選択
            while (true) {
                // 賭け初期メッセージと賭けパターンのゲット
                gamblingMessage();
                input = gamblingCheck();
                // 賭けパターンどうりの文字かのチェック
                switch (input) {
                    case "x":
                        continue;
                    default:
                        break;
                }
                break;
            }
            // 賭け金変更額の指定
            if (bettingResults(input, playerId)) {
                while (true) {
                    // 賭け金変更額の指定の際のメッセージ
                    raiseStart();
                    switch (raise(playerId)) {
                        case "again":
                            continue;
                        default:
                            break;
                    }
                    break;
                }
            }
        }
    }

    /**
     * 賭ける前のメッセージの表示
     * 
     * C（Check）/B（Bet）/
     * C（Call）/R（Raise）/F（Fold）/A（All-in）
     * ◼︎gamblingPattern「0」初期
     * C or Bの選択のみ
     * 
     * ◼︎gamblingPattern「1」賭け金上昇
     * ●Betがあった場合下記の選択に切り替わる
     * C,R,F,Aの選択可能
     * ●賭け金額が手持ちより多い時（サイドポット発生）
     * F,A
     * 
     * @return
     */
    private static void gamblingMessage() {
        if (gamblingPattern == 0) {
            switch (gamblMesseBranchCheck) {
                case 1:
                    System.out.println("金額が足りません。なんで参加できてんの？");
                    break;
                case 2:
                    System.out.println("賭け金と手持ちが同額のため Bet できないため、自動的に「c」(Check)になります。");
                    break;
                case 3:
                    System.out.println("「c」(Check)か「b」(Bet)を入力してください。");
                    break;
            }
        } else if (gamblingPattern == 1) {
            switch (gamblMesseBranchCheck) {
                case 1:
                    System.out.println("「f」(Fold)か「a」(All-in)を入力してください。");
                    break;
                case 2:
                    System.out.println("賭け金と手持ちが同額のため Raise できません。「c」(Call)または「f」(Fold)を入力してください。");
                    break;
                case 3:
                    System.out.println("「c」(Call)「r」(Raise)「f」(Fold)、「a」(All-in)を入力してください。");
                    break;
            }
        }
    }

    /**
     * 賭けで可能な言葉を使用しているかのチェック
     * 
     * @return
     */
    private static String gamblingCheck() {
        GetGamblingSentence scan = new GetGamblingSentence();
        String whileCheck = "x";
        if (gamblingPattern == 0) {
            switch (gamblMesseBranchCheck) {
                case 1:
                    whileCheck = "end";
                    break;
                case 2:
                    whileCheck = "c";
                    break;
                case 3:
                    // ▶︎▶︎▶︎スキャナー実行
                    whileCheck = scan.getGamblingSentence();
                    if (!whileCheck.equals("c") && !whileCheck.equals("b")) {
                        whileCheck = "x";
                    }
                    break;
            }
        } else if (gamblingPattern == 1) {
            switch (gamblMesseBranchCheck) {
                case 1:
                    // ▶︎▶︎▶︎スキャナー実行
                    whileCheck = scan.getGamblingSentence();
                    if (!(whileCheck.equals("f") && !whileCheck.equals("a"))) {
                        whileCheck = "x";
                    }
                    break;
                case 2:
                    // ▶︎▶︎▶︎スキャナー実行
                    whileCheck = scan.getGamblingSentence();
                    if (!whileCheck.equals("c") && !whileCheck.equals("f")) {
                        whileCheck = "x";
                    }
                    break;
                case 3:
                    // ▶︎▶︎▶︎スキャナー実行
                    whileCheck = scan.getGamblingSentence();
                    if (!whileCheck.equals("c") && !whileCheck.equals("r") && !whileCheck.equals("f")
                            && !whileCheck.equals("a")) {
                        whileCheck = "x";
                    }
                    break;
            }
        }
        if (whileCheck.equals("x")) {
            again();
        }
        return whileCheck;
    }

    /**
     * raiseメソッド（賭け金追加）を実施するかの判定メソッド。
     * B（Bet）/R（Raise）の時にraiseメソッドを実施。
     * 
     * @param scanSentence 入力文字の確認
     * @return 「true」追加賭け金を入力して指定する「false」賭け金を入力し指定しない
     */
    private static boolean bettingResults(String scanSentence, int playerId) {
        boolean raiseOrNo = false;
        if (scanSentence.equals("c")) {
            // C(Check/Call)賭け金額はそのまま
            GamblingManagement.playerInformation[playerId][3] = stakesUp;
        } else if (scanSentence.equals("f")) {
            // F(Fold)降りる
            GamblingManagement.playerInformation[playerId][4] = 1;
        } else if (scanSentence.equals("b") || scanSentence.equals("r")) {
            // B(Bet) R(Raise)賭け金額を上げる
            // 賭け金の処理に入る
            raiseOrNo = true;
        } else if (scanSentence.equals("a")) {
            // A(All-in)全て賭ける
            newStakes = amountOnHand;
            setNewStakes(playerId);
        } else if (scanSentence.equals("end")) {
            // 参加権なし
            GamblingManagement.playerInformation[playerId][1] = 1;
        }
        return raiseOrNo;
    }

    private static void raiseStart() {
        System.out.println("賭け金を指定してください");
        /*
         * メモ：追加したい機能
         * 「raise to 20」賭け金額を20にする
         * [raise]賭け金額に20追加する。
         */
    }

    /**
     * 賭け金の追加
     * 
     * @param scanSentence
     * @return 「0」continue「1」break
     */
    private static String raise(int playerId) {
        GetGamblingSentence scan = new GetGamblingSentence();
        String againNum = "";
        // ▶︎▶︎▶︎スキャナー実行
        int scanNum = scan.getReNumber();
        // 金額の確認
        if (scanNum <= 0 || amountOnHand < scanNum) {
            if (scanNum <= 0) {
                // 指定された数字が0以下の場合
                System.out.println("───0より大きい数字を入力してください。");
                checkAmount();
                againNum = "again";
                return againNum;
            } else {
                // 賭けの合計金額が所持金より高い場合
                System.out.println("───そんなに持ってたら良かったのにね。");
                checkAmount();
                againNum = "again";
                return againNum;
            }
        }
        newStakes = scanNum;
        setNewStakes(playerId);
        againNum = "end";
        return againNum;
    }

    /** 「金額の確認」の確認 */
    private static void checkAmount() {
        GetGamblingSentence scan = new GetGamblingSentence();
        System.out.println("─現在の賭け金額と所持金を確認する場合は「Y」を、確認しない場合はそれ以外の文字を入力してください。");
        String input = scan.getGamblingSentence();
        if (input.equals("Y") || input.equals("y")) {
            checkAmountAnswer();
        }
    }

    /** 「金額の確認」の表示 */
    private static void checkAmountAnswer() {
        System.out.println("───現在の賭け金額「" + betAmount + "」");
        System.out.println("───あなたはの所持金「" + amountOnHand + "」");
        if (difference <= 0) {
            System.out.println("所持金が賭け金額より「" + difference + "」少ないため追加できません。");
        } else {
            System.out.println("───賭け金額を最大「" + difference + "」追加できます。");
        }

    }

    /** 「─再度、」の表示 */
    private static void again() {
        System.out.println();
        System.out.print("再度、");
    }
}
