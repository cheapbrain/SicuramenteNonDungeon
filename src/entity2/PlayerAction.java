package entity2;

import happypotatoes.slickgame.world.World;

public class PlayerAction extends Component {
	public State state;
	public static enum State{
		STAND, MOVE, ATTACK
	}
	public PlayerAction(Entity owner, float priority) {
		super(owner, priority);
		state=State.STAND;
	}

	@Override
	public void update(World w, long delta) {
		
	}

}
