package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StaticRender;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.items.ItemList;

public class Energy_Potion {
		public static Entity create(){
			Entity item = new Entity(EntitySystem.getInstance().getFreeID(), ItemList.sword.getName());
			Item i = ItemList.energy_potion;
			Pickup itemEntity = new Pickup(item, 0, i.getId());
			StaticRender render = new StaticRender(item, "res/MyMod/Sprites/Items/energy_potion.png",-0.5f, -0.3f, true);	
			SelectComponent selectComponent = new SelectComponent(item, 0, -.5f,-.5f, 1, 1);
			return item;
		}
}
