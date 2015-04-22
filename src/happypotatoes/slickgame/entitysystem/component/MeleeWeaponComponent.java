package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;

import java.util.Properties;

public class MeleeWeaponComponent extends WeaponComponent{

	public MeleeWeaponComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority, info);
		
	}

}
