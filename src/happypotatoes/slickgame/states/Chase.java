package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;

public class Chase extends State {
	public float dx, dy, d;
	public float speed;
	
	public Chase(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			if(owner.focus==null)
				return 1;
				if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
					return 2;
				}
				owner.walker.setWalking();
				dx = owner.focus.x-owner.owner.x;
				dy = owner.focus.y-owner.owner.y;
				d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
			
		}
		owner.goTo(dx,dy,d);
		return 0;
	}

}
