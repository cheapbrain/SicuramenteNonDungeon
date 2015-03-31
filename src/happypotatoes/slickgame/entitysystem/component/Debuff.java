package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Debuff extends Component {

	public Debuff(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {

	}

}
