package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public abstract class Interact extends Component{

	public Interact(Entity owner, float priority) {
		super(owner, priority);
		
	}

	@Override
	public void update(World w, long delta) {
		
	}
	public abstract void interact(Entity e);
}
