package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Health;

public class DefendPlayer extends State {

	public DefendPlayer(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
			if(owner.focus==null) //se perde focus 
				return 1;
			
			if(owner.getDistance(owner.focus)>1.5f){
				return 2;
			}
			
			Defend thisDefend = owner.owner.getComponent(Defend.class);
						
			//componenti del focus
			Attack enemyAttack = null;
			if(owner.focus!=null){
				enemyAttack = owner.focus.getComponent(Attack.class);
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
