package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;


public class ArmourComponent extends ItemComponent{
	
	public ArmourComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority, info);
	}

}
