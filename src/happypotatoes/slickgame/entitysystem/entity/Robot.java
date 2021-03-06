package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIManager;
import happypotatoes.slickgame.entitysystem.component.AIPet;
import happypotatoes.slickgame.entitysystem.component.AIType;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Robot {
	static float speed = 0.0015f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(),"Robot");
		Faction f = new Faction(e, 0, Faction.good);
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .6f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 4);
		AI intelligence = (AI) (new AIPet(e, 0f, walker, movement, speed));
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/robot/", 222, 210, -1.8f, -2.2f);	
		Walk walk = new Walk(e, 0, walker, walkerRender);
		Attack attack = new Attack(e, 0, walker, walkerRender, 1);
		Health health = new Health(e, 0, 100, 1);
		Energy energy = new Energy(e, 0, 100, 5);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.75f, -1.f, 1.5f, 1.5f);
		return e;
	}
	
	public static Entity create(float x, float y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
