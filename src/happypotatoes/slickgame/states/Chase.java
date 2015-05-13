package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Attack;

public class Chase extends State {
	public long delay = 100;
	public float dx, dy, d;
	public float speed;
	
	public Chase(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		owner.walker.state=1;
		
		if(owner.focus==null)
			return 1;
		
		if (owner.time<=0) {
			if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
				return 2;
			}
			owner.time = delay;
			dx = owner.focus.x-owner.owner.x;
			dy = owner.focus.y-owner.owner.y;
			d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
			
		}
		owner.goTo(dx,dy,d);
		return 0;
	}

}
