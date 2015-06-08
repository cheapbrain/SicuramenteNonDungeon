package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIMad;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Target {
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Target");
		HitBox hitBox = new HitBox(e, .05f, .05f, 0);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.05f,-.05f, +.05f, +.05f);
		Walker walker = new Walker(e, 0, 1, 1);
		return e;
	}
	
	public static Entity create(float x, float y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
