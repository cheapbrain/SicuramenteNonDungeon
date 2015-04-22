package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;

import java.util.Properties;

public class WeaponComponent extends ItemComponent{
	private int damage, weight;
	public WeaponComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority, info);
		damage = Integer.parseInt(info.get("Damage"));
		weight = Integer.parseInt(info.get("Weight"));
	}

}
