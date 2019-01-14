package Bomberman.EntityManager;

import java.util.Arrays;
import java.util.List;

public enum EntityType {
    PLAYER,EXPLOSION,EXIT,ENEMY,BOMB,ITEM_DROPPED,GROUDON,KNIGHT,HURT,DIE,LEVIATHAN,APPARITION,
    DARK_VADOR,HELL_DOG,EVIL,
    SABRE_LASER,FIRE_BALL,
    CHEST,
    ACCUEIL,
    VOLCAN,ICE,DESERT,DUNGEON;

    public static List<EntityType> listBiome= Arrays.asList(VOLCAN,DESERT,ICE,DUNGEON);
}
