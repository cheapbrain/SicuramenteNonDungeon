package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Walk;

public class Chase extends State {
	public float speed;
	
	public Chase(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
			if(owner.focus==null)
				return 1;
				if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
					return 2;
				}
				owner.walker.setWalking();
				Walk thisWalk = owner.owner.getComponent(Walk.class); 
				thisWalk.dx = owner.focus.x-owner.owner.x;
				thisWalk.dy = owner.focus.y-owner.owner.y;
				thisWalk.d = (float)Math.sqrt(thisWalk.dx*thisWalk.dx+thisWalk.dy*thisWalk.dy)+0.000001f;
		}
		return 0;
	}

}
