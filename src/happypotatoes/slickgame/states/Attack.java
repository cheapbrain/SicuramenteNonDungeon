package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Attack extends State {

	public Attack(AI owner, Integer...state) {
		super(owner, state);
}

	@Override
	public int update(long delta) {
		return 0;
	}

}
