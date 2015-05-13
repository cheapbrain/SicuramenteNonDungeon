package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Follow extends State {

	public Follow(AI owner, Integer...states) {
		super(owner, states);
	}

	@Override
	public int update(long delta) {
		return 0;
	}

}
