package Bomberman.EntityManager;

import Bomberman.Main;
import Bomberman.Tile;

import java.awt.*;

public class Explosion extends TemporaryEntity {

    public Explosion(Point posInArrayMap, int countDown) {
        super(posInArrayMap, countDown);
    }



    @Override
    public void action() {
        super.action();
        Tile tile= Main.game.getMap().getTile(posInArrayMap);
        if(tile.hasEntity()&&tile.getEntity() instanceof Enemy)
        {
            Entity entity=tile.getEntity();
            //tile.getEntity().destroy();
            //tile.setEntity(null);

            //do this after put the tile.entity to null because we save the item in this tile
            Main.game.getMap().deleteFromMap(entity);
            ((Enemy)entity).actionOnDisappearance();
        }
    }

    @Override
    public void actionOnDisappearance() {

        //bomb is not in the map so we jsut have to delete on the listentities
        Main.game.getMap().getEntitiesList().remove(this);
    }
}