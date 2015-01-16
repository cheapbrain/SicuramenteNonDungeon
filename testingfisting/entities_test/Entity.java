package entities_test;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.world.World;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Entity { //copiata
    public enum EntityType {
        GenericEntity, PlayerEntity, EnemyEntity, ProjectileEntity
    }

    private static int nextId = 0;
    protected static Random random = new Random();

    protected int id;
    protected float x, y, z;

    protected Vector3f acceleration = new Vector3f(0, 0, 0);
    protected Vector3f velocity = new Vector3f(0, 0, 0);

    public static Image shadowImage;
    protected static final Color shadowColorMult = new Color(0, 0, 0, 0.5f);
    private boolean removed;

    protected EntityType entityType = EntityType.GenericEntity;

    public Entity(World world) { //copiata
        this.id = ++nextId;
    }

    public Entity(World world, float x, float z) { //copiata
    	this(world);
        this.x = x;
        this.z = z;
    }

    public EntityType getType() {
        return entityType;
    }
    
    public abstract boolean update(GameContainer slickContainer, int deltaMS); //copiata

    public abstract void render(GameContainer slickContainer, Graphics g, Camera camera); //copiata, forse da cambiare

}
