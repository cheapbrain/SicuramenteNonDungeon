package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;

import java.util.Properties;

public class ArmourComponent extends ItemComponent{
	
	public ArmourComponent(Entity owner, float priority, String path, Properties prop) {
		super(owner, priority, path, prop);
	}

}
