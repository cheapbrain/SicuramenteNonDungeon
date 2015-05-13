package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Escape extends State {

	public Escape(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		
		return 0;
	}

}
