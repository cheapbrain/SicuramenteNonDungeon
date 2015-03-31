package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Input extends Component {
	PlayerAction pa;
	public Input(Entity owner, float priority, PlayerAction pa) {
		super(owner, priority);
		this.pa = pa;
	}

	@Override
	public void update(World w, long delta) {

	}

}
