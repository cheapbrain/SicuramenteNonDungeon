package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StaticRender;
import happypotatoes.slickgame.entitysystem.interact.OpenDoor;

public class Door {

	public static Entity create(float x, float y) {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(),"Door");
		e.x = x;
		e.y = y;
		
		Faction f = new Faction(e, 0, Faction.neutral);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.5f,-1.2f, 1f, 1.2f);
		StaticRender render = new StaticRender(e, "res/door.png", -1.5f, -2.7f, false );
		OpenDoor door = new OpenDoor(e, 0);
		
		return e;
	}
}
