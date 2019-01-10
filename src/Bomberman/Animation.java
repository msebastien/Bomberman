package Bomberman;

public enum Animation {

    //PLAYER_IDLE, PLAYER_MOVE_FORWARD, PLAYER_MOVE_BACKWARD, PLAYER_MOVE_LEFT, PLAYER_MOVE_RIGHT, PLAYER_DIE,

    //ENEMY_MOVE_FORWARD, ENEMY_MOVE_BACKWARD, ENEMY_MOVE_LEFT, ENEMY_MOVE_RIGHT, ENEMY_DIE,ENEMY_IDLE,

    //ENEMY_1_MOVE_FORWARD, ENEMY_1_MOVE_BACKWARD, ENEMY_1_MOVE_LEFT, ENEMY_1_MOVE_RIGHT, ENEMY_1_DIE,ENEMY_1_IDLE,

    MOVE_EAST,MOVE_WEST,MOVE_NORTH,MOVE_SOUTH,IDLE,DIE,
    EXPLOSION, EXIT,BOMB,ITEM_BOMB,HURT,
    OBSTACLE,
    GRASS;

    public final static int DURATION_ANIMATION_ITEM=2000;
    public final static int DURATION_ANIMATION_EXIT =1500;
    public final static int COUNT_DOWN_EXPLOSION =700;
    public final static int DURATION_ANIMATION_HURT=700;
}
