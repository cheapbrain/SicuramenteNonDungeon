package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Walker;

public class Fight extends State {

	public Fight(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			if(owner.focus==null)
				return 1;
				
			
			Health thisHealth = owner.owner.getComponent(Health.class);
			if(thisHealth!=null){
				if(thisHealth.getHealth()<thisHealth.getMaxHealth()/100f*20f)
					if(owner.isAttacked(owner.owner) != null)
						return 3;
			}	
			
			
			if(owner.getDistance(owner.focus)>1.5f){
				return 2;
			}
			
			Defend thisDefend = owner.owner.getComponent(Defend.class);
			
			
			//componenti del focus
			Attack enemyAttack = null;
			Health enemyHealth = null;
			if(owner.focus!=null){
				enemyAttack = owner.focus.getComponent(Attack.class);
				enemyHealth = owner.focus.getComponent(Health.class);
			}
			if(owner.focus.isAlive()){
				owner.walker.setAttacking();
			}
			else{
				owner.focus=null;
				return 1;
			}
			if(thisDefend!=null){
				if(enemyAttack!=null)
					if(enemyAttack.isAttacking())
						owner.walker.setDefending();
			}
		}
		return 0;
		
	}

}
