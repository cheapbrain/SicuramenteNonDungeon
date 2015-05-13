package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Health;

public class Fight extends State {

	public Fight(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if(owner.focus==null)
			return 1;
		
		if(owner.getDistance(owner.focus)>1.5f){
			return 2;
		}
		
		Health thisHealth = owner.owner.getComponent(Health.class);
		if(thisHealth!=null){
			if(thisHealth.getHealth()<thisHealth.getMaxHealth()/10f)
				return 3;
		}
		
		
		//componenti del focus
		Attack atk = null;
		Health enemyHealth = null;
		if(owner.focus!=null){
			atk = owner.focus.getComponent(Attack.class);
			enemyHealth = owner.focus.getComponent(Health.class);
		}
		if(enemyHealth!=null&&enemyHealth.getHealth()>0){
			owner.walker.state=2;
		}
		else{
			return 1;
		}
		if(atk!=null){
			if(atk.animationTime>0)
				if(owner.walker.state!=5) owner.walker.state=4;
		}
		return 0;
		
	}

}
