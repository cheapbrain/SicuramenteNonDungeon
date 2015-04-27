package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIManager;
import happypotatoes.slickgame.entitysystem.component.AIPet;
import happypotatoes.slickgame.entitysystem.component.AIType;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Wolf {
	static float speed = 0.01f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(),"");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 2);
		//AI intelligence = (new AIManager(e, 0f, walker, movement, speed, AIType.Pet)).get();
		AI intelligence = (AI) (new AIPet(e, 0f, walker, movement, speed));
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/wolf/", 10, 50, -.75f, -0.8f);	
		return e;
	}
	
	public static Entity create(int x, int y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
