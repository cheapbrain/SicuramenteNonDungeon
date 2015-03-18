package entity2;

import happypotatoes.slickgame.world.World;

public class State extends Component {
	public int state = 0;
	public State(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {

	}

}
