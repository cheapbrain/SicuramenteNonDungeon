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
		if (owner.time<=0) {
			if(owner.focus==null) //se perde focus 
				return 1;
			
			if(owner.getDistance(owner.focus)>1.5f){
				return 2;
			}
			
			Health thisHealth = owner.owner.getComponent(Health.class);
			if(thisHealth!=null){
				if(thisHealth.getHealth()<thisHealth.getMaxHealth()/10f)
					return 3;
			}
			Defend thisDefend = owner.owner.getComponent(Defend.class);
			
			
			//componenti del focus
			Attack atk = null;
			Health enemyHealth = null;
			if(owner.focus!=null){
				atk = owner.focus.getComponent(Attack.class);
				enemyHealth = owner.focus.getComponent(Health.class);
			}
			if(enemyHealth!=null&&enemyHealth.getHealth()>0){
				owner.walker.setAttacking();
			}
			else{
				return 1;
			}
			if(thisDefend!=null){
				if(atk!=null)
					if(atk.isAttacking())
						owner.walker.setDefending();
			}
		}
		return 0;
	}
}