package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;

import java.util.Properties;

public class RangedWeaponComponent extends WeaponComponent{
	private float distance;
	public RangedWeaponComponent(Entity owner, float priority, String path, Properties prop) {
		super(owner, priority, path, prop);
		
	}

}
