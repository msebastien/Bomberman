package Bomberman.EntityManager;

import Bomberman.IssueGame;
import Bomberman.Main;

public class KeyDropped implements ItemAction {
    @Override
    public void meetPlayer(Player player) {
        //player.setAutorizationNextLevel(true);
        Main.game.end(IssueGame.VICTORY);
    }
}
