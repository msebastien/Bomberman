package Bomberman.EntityManager;

import Bomberman.Animation;

public class RangeDropped implements ItemAction {

    @Override
    public void meetPlayer(Player player) {
        player.addRange();
    }
}
