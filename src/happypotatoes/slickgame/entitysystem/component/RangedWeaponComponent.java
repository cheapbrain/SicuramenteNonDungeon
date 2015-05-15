package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;


public class RangedWeaponComponent extends WeaponComponent{
	public RangedWeaponComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority, info);
		
	}

}
