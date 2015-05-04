package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIFighter;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class FighterEntity {
	static float speed = 0.001f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Stupid");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 5);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/fighterTest/", 170, 189, -1.3f, -2.4f);
		AI intelligence = (AI) (new AIFighter(e, 0f, walker, movement, speed));
		Attack attack = new Attack(e, 0, walker, walkerRender, 5);
		Health health = new Health(e, 0, 200, 0);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.4f,-1.8f, .8f, 2);
		return e;
	}
	
	public static Entity create(int x, int y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
