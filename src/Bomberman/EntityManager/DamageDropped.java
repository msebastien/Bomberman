package Bomberman.EntityManager;

import Bomberman.Animation;

public class DamageDropped implements ItemAction {

    @Override
    public void meetPlayer(Player player) {
        player.addDamage();
    }
}
