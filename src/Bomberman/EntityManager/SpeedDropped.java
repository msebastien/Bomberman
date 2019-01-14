package Bomberman.EntityManager;

public class SpeedDropped implements ItemAction{
    @Override
    public void meetPlayer(Player player) {
        player.initSpeed(Vitesse.RAPIDE.getDurationMs());
    }
}
