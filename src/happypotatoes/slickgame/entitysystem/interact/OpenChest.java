package happypotatoes.slickgame.entitysystem.interact;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Interact;

public class OpenChest extends Interact {

	public OpenChest(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void interact(Entity e) {
		
	}

}
