package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIMad;
import happypotatoes.slickgame.entitysystem.component.AIPet;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.StupidInput;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class StupidEntity {
	static float speed = 0.001f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Stupid");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 3);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/player/", 12, 50, -.5f, -1.6f);
		AI intelligence = (AI) (new AIMad(e, 0f, walker, movement, speed));
		Attack attack = new Attack(e, 0, walker, walkerRender, 10);
		Health health = new Health(e, 0, 100, 0);
		return e;
	}
}
