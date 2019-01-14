package Bomberman.EntityManager;

public class PvDropped implements ItemAction {
    @Override
    public void meetPlayer(Player player) {
        player.addLife();
    }
}
