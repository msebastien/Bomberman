package Bomberman.EntityManager;

import Bomberman.Animation;
import Bomberman.Direction;
import Bomberman.Main;

import java.awt.*;

public class Projectile extends MovingEntity {

    public final static Projectile SabreLaser=new Projectile(Vitesse.ONLY_PROJECTILE,1,6,EntityType.SABRE_LASER);
    public final static Projectile FireBall=new Projectile(Vitesse.ONLY_PROJECTILE,1,2,EntityType.FIRE_BALL);

    public Projectile(Vitesse vitesse, int Pv, int damage, EntityType entityType) {
        super(vitesse.getDurationMs(), Pv,damage,entityType);
    }

    public Projectile(MovingEntity movingEntity, Direction direction, Point posInArrayMap) {
        super(movingEntity, posInArrayMap);
        this.direction=direction;
        this.directionMovement=direction.getDirection();

        container.init(moveDuration,translateDirectionToAnimation(),entityType);
    }

    @Override
    public void changeDirection() {
        //si on rencontre un obstacle on detruit le projectile
        actionOnDisappearance();

        setDirection(Direction.IDLE);
    }

    @Override
    protected void actionOnCollision(Entity entity) {
        //on frappe toutes les entites quelques quelles soient
        if(entity instanceof Player)
        {
            ((MovingEntity)entity).hurt(damage);
        }
    }

    @Override
    public void actionOnDisappearance() {
        super.actionOnDisappearance();

        Main.game.getMap().addToEntityListOnly(new TemporaryEntity(posInArrayMap,
                Animation.ANIMATION_EXPLOSION,
                Animation.IMPACT,
                EntityType.EXPLOSION));
    }
}
