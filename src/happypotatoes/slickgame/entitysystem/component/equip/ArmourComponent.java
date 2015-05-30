package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;
import happypotatoes.slickgame.items.Armour;
import happypotatoes.slickgame.items.ItemList;


public class ArmourComponent extends ItemComponent{
	
	public ArmourComponent(Entity owner, float priority, Armour armour) {
		super(owner, priority, armour);
	}
	
	public ArmourComponent(Entity owner, float priority, int id) {
		super(owner, priority, ItemList.getItemForId(id));
	}

}
