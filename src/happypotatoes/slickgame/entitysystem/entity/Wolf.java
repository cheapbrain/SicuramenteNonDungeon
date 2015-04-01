package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import entity2.Entity;
import entity2.HitBox;
import entity2.Movement;
import entity2.PlayerInput;
import entity2.TerrainCollision;
import entity2.Walker;

public class Wolf {
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getFreeID());
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 1);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/wolf/", 12, 50, -.5f, -1.6f);
		return e;
	}
}
