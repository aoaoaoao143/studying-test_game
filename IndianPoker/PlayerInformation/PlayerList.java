package IndianPoker.PlayerInformation;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private List<playerManagement> playerInfoList = new ArrayList<>();

    public void plList(int playernum, int amountOnHand) {
        for (int i = 0; i < playernum; i++) {
            String playerName;
            if (i == 0) {
                playerName = "   YOU  ";
            } else {
                // int → String に変換してPLAYER名を作成
                playerName = "PLAYER " + String.valueOf(i);
            }
            playerInfoList.add(new playerManagement(i, playerName, 0, amountOnHand, 0, 0, 0));
        }
    }

    public List<playerManagement> getPlayerList() {
        return playerInfoList;
    }

    public List<playerManagement> getActivePlayers() {
        List<playerManagement> active = new ArrayList<>();
        for (playerManagement p : playerInfoList) {
            if (p.getPlGameParticipation() == 0) {
                active.add(p);
            }
        }
        return active;
    }
}