package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIManager;
import happypotatoes.slickgame.entitysystem.component.AIPet;
import happypotatoes.slickgame.entitysystem.component.AIType;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Wolf {
	static float speed = 0.002f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(),"");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 4);
		//AI intelligence = (new AIManager(e, 0f, walker, movement, speed, AIType.Pet)).get();
		AI intelligence = (AI) (new AIPet(e, 0f, walker, movement, speed));
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/wolf/", 96, 54, -.75f, -0.8f);	
		Attack attack = new Attack(e, 0, walker, walkerRender, 10);
		Health health = new Health(e, 0, 100, 0);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.4f,-.8f, 1, 1);
		return e;
	}
	
	public static Entity create(int x, int y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
