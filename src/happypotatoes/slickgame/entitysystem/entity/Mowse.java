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
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StupidInput;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Mowse {
	static float speed = 0.001f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Mowse");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .6f, .6f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 4);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/mowse/", 278, 237, -2.2f, -2.2f);
		AI intelligence = (AI) (new AIMad(e, 0f, walker, movement, speed));
		Attack attack = new Attack(e, 0, walker, walkerRender, 10);
		Health health = new Health(e, 0, 10, 0);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.7f,-1.2f, 1.4f, 1.8f);
		return e;
	}
	
	public static Entity create(int x, int y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
