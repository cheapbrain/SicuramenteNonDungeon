package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Idle extends State {
	
	public Idle(AI owner, Integer...state){
		super(owner, state);
	}

	@Override
	public int update(long delta) {
		owner.walker.state=0;
		if(owner.focus!=null)
			return 1;
		return 0;
	}

}
