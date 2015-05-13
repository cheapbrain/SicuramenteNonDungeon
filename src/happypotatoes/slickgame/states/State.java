package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public abstract class State {
	public Integer[] pointers;
	public AI owner;
	
	public State(AI owner, Integer...pointers){
		this.owner=owner;
		this.pointers=pointers;
	}
	
	public abstract int update(long delta);

}
