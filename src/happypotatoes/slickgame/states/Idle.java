package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Idle extends State {
	
	public Idle(AI owner, Integer...state){
		super(owner, state);
	}

	@Override
	public int update(long delta) {
		if(owner.time()){
			owner.walker.setStill();
			if(owner.focus!=null)
				return 1;
		}
		return 0;
	}

}
