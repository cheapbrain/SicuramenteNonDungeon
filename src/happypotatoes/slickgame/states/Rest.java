package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Health;

public class Rest extends State {

	public Rest(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.walker.setStill();
			if(owner.owner.getComponent(Health.class).getHealth()>
				owner.owner.getComponent(Health.class).getMaxHealth()/10f*8f)
				return 1;
			if(owner.seeAttacked(owner.owner)!=null){
				return 2;
			}
		}
		return 0;
	}

}
