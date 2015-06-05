package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StaticRender;
import happypotatoes.slickgame.entitysystem.interact.Pickup;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.items.ItemList;

public class ItemEntity {
	
	public static Entity create(int id, float x, float y) {
		Item i = ItemList.getItemForId(id);
		Entity item = new Entity(EntitySystem.getInstance().getFreeID(), i.getName());
		item.x = x;
		item.y = y;
		Pickup itemEntity = new Pickup(item, 0, id);
		StaticRender render = new StaticRender(item, i.getPath(),-0.5f, -0.5f, true);	
		SelectComponent selectComponent = new SelectComponent(item, 0, -.5f,-.5f, 1, 1);
		return item;
	}
	
}
