package IndianPoker.PlayerInformation;

import java.util.List;

import TrumpCard.TrumpCard;
import TrumpCard.TrumpDeck;

public class playerManagement {
    /** プレイヤーID */
    private int playerId;
    /** プレイヤー名 */
    private String playerName;
    /** 参加有無（0=参加中, 1=脱落） */
    private int plGameParticipation;
    /** 所持金 */
    private int amountOnHand;
    /** 賭け金更新のタイミング */
    private int foldStakes;
    /** 賭け参加有無（0=参加中, 1=脱落） */
    private int plBetParticipation;
    /** 所持カードID */
    private int plCardId;

    public playerManagement(int playerId, String playerName, int plGameParticipation, int amountOnHand, int foldStakes,
            int plBetParticipation, int plCardId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.plGameParticipation = plGameParticipation;
        this.amountOnHand = amountOnHand;
        this.foldStakes = foldStakes;
        this.plBetParticipation = plBetParticipation;
        this.plCardId = plCardId;
    }

    // ===== Getter =====
    /** プレイヤー */
    public int getPlayerId() {
        return playerId;
    }

    /** プレイヤー */
    public String getPlayerName() {
        return playerName;
    }

    /** 参加有無 */
    public int getPlGameParticipation() {
        return plGameParticipation;
    }

    /** 所持金 */
    public int getAmountOnHand() {
        return amountOnHand;
    }

    /** 賭け金更新のタイミング */
    public int getFoldStakes() {
        return foldStakes;
    }

    /** 賭け参加有無 */
    public int getPlBetParticipation() {
        return plBetParticipation;
    }

    /** 所持カードID */
    public int getPlCardId() {
        return plCardId;
    }

    // ===== Setter =====
    public void setPlGameParticipation(int plGameParticipation) {
        this.plGameParticipation = plGameParticipation;
    }

    public void setFoldStakes(int foldStakes) {
        this.foldStakes = foldStakes;
    }

    public void setPlBetParticipation(int plBetParticipation) {
        this.plBetParticipation = plBetParticipation;
    }

    public void setPlCardId(int plCardId) {
        this.plCardId = plCardId;
    }

    // ===== 所持金操作 =====
    /** 所持金を増減させる（マイナスなら自動で脱落） */
    public void addAmount(int value, int participationFee) {
        if (this.plGameParticipation == 1)
            return; // 脱落者は金額操作不可
        this.amountOnHand += value;
        checkBankrupt(participationFee);
    }

    /** 賭け金をリセット */
    public void resetFoldStakes() {
        this.foldStakes = 0;
    }

    /** 所持金が0未満なら強制的に参加権を失う */
    private void checkBankrupt(int participationFee) {
        if (this.amountOnHand < participationFee) {
            this.amountOnHand = Math.max(this.amountOnHand, 0);
            this.plGameParticipation = 1; // 1 = 参加権なし（脱落）
        }
    }

    @Override
    public String toString() {
        String plGameParticipat;
        switch (plGameParticipation) {
            default:
                plGameParticipat = "脱落";
                break;
            case 0:
                plGameParticipat = "参加中";
                break;
        }
        String plBetParticipat;
        switch (plBetParticipation) {
            default:
                plBetParticipat = "不参加";
                break;
            case 0:
                plBetParticipat = "参加";
                break;
        }
        TrumpDeck trumpD = new TrumpDeck();
        List<TrumpCard> trump = trumpD.getDeck();
        TrumpCard card;
        card = trump.get(plCardId);
        String display = card.getSuit() + card.getDisplay();
        // "ID:名前:参加0不参加1:所持金:賭け金更新タイミング:賭け参加有無:所持カードID"
        return playerId + ":" + playerName + ":" + plGameParticipat + ":" + amountOnHand + ":" + foldStakes + ":"
                + plBetParticipat + ":" + display;
    }
}