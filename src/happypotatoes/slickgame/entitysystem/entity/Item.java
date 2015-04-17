package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;

public class Item {
	public static Entity create(int id){
		Entity item = new Entity(EntitySystem.getFreeID(), "Item");
		
		return item;
	}
}
