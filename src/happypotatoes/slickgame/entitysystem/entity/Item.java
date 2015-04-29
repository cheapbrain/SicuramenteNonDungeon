package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.ItemEntity;
import happypotatoes.slickgame.entitysystem.component.StaticRender;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Item {
	public static Entity create(String id){
		Entity item = new Entity(EntitySystem.getInstance().getFreeID(), "Item");
		ItemInfo info = ItemSystem.get(id);
		ItemEntity itemEntity = new ItemEntity(item, 0, id);
		StaticRender render = new StaticRender(item, "res/MyMod/Sprites/Items/Spada.png",-0.5f, -0.3f, true);	
		return item;
	}
}
