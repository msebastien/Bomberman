package Bomberman.EntityManager;

import Bomberman.*;

import java.awt.*;

public class Boss extends Enemy{

    private static final Boss[] ListBoss=new Boss[]{
            new Boss(Vitesse.TRES_RAPIDE,EntityType.HELL_DOG,8,2,Projectile.FireBall),
            new Boss(Vitesse.LENT,EntityType.GROUDON,7,10,Projectile.FireBall),
            new Boss(Vitesse.MOYEN,EntityType.DARK_VADOR,15,12,Projectile.SabreLaser)
    };

    private Projectile projectile;

    public Boss(Boss enemyCopy) {
        super(enemyCopy);
        projectile=enemyCopy.projectile;
    }

    protected Boss(Vitesse vitesse, EntityType entityType, int damage, int nbrPv,Projectile projectile) {
        super(vitesse, entityType, damage, nbrPv);
        this.projectile=projectile;
    }

    @Override
    //here we will search if we see the player and send him a projectile
    public boolean isMoveBegin() {
        boolean res=super.isMoveBegin();

        //si on est sur un debut de mouvement
        if(res)
        {
            throwProjectile();
            //alors on cherche le joueur pour lancer un projectile
        }
        return res;
    }

    @Override
    public void actionOnDisappearance() {
        //comme ça sur le hud on voit la cle a ramasser
        super.actionOnDisappearance();
    }

    public void throwProjectile()
    {
        Point pointSearch=new Point();
        Map map=Main.game.getMap();
        for(int i=2;i<10;i++)
        {

            pointSearch.setLocation(directionMovement.x*i,directionMovement.y*i);
            pointSearch.translate(posInArrayMap.x,posInArrayMap.y);

            //si le point est plus dans la map ou que c'est un mur ou qu'il y a une entite
            if(!map.isInsideMap(pointSearch)
                    ||!map.getTile(pointSearch).isWay())
            {
                //alors on quitte le calcul
                return;
            }

            if(map.getTile(pointSearch).hasEntity()&&map.getTile(pointSearch).getEntity() instanceof Player)
            {

                pointSearch.setLocation(directionMovement);
                pointSearch.translate(posInArrayMap.x,posInArrayMap.y);

                if(!map.getTile(pointSearch).hasEntity())
                {
                    map.addToMap(new Projectile(projectile,direction,pointSearch));
                    changeDirection();
                }
                return;
            }
        }
    }

    @Override
    protected ItemDropped itemToDrop() {

        ItemDropped itemDrop=new ItemDropped(ItemDropped.KeyEscape,posInArrayMap);

        //on fait pointer le conteneur du boss vers la cle comme ça on peut afficher la cle sur le hud
        container=itemDrop.getContainer();

        return itemDrop;
    }

    public static Boss getBossFrom(int level)
    {
        int indexTab=level/ Game.FRQCY_BOSS-1;
        if(indexTab>=ListBoss.length)
        {
            indexTab=ListBoss.length-1;
        }

        return ListBoss[indexTab];
    }
}
