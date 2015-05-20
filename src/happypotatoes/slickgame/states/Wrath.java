package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Walker;

public class Wrath extends State {

	public Wrath(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
			
			if(owner.focus==null)
				return 1;
			
			if(owner.getDistance(owner.focus)>1.5f){
				return 2;
			}
			
			if(owner.focus.isAlive()){
				owner.walker.setAttacking();
			}
			else{
				owner.focus=null;
				return 1;
			}
		}
		return 0;
	}

}
