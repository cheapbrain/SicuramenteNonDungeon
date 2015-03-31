package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Buff extends Component {

	public Buff(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {

	}

}
