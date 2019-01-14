package Bomberman.EntityManager;


public class BombDropped implements ItemAction {

    @Override
    public void meetPlayer(Player player) {
        player.addBombToReserve();
    }
}
