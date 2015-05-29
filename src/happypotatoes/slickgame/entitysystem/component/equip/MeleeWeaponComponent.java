package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;
import happypotatoes.slickgame.items.Weapon;


public class MeleeWeaponComponent extends WeaponComponent{

	public MeleeWeaponComponent(Entity owner, float priority, Weapon weapon) {
		super(owner, priority, weapon);
		
	}

}
