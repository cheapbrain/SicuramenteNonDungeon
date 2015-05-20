package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.entity.Target;

public class Escape extends State {
	public long delay = 100;
	public float dx, dy, d;
	public float speed;

	public Escape(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			if(!(owner.focus.getClass().isInstance(Target.class))){
				owner.focus=null;
			}
				
			if(owner.focus==null){
				Entity attacker=owner.isAttacked(owner.owner);
				if(attacker!=null){
					float x = attacker.x-(owner.owner.x-attacker.x);
					float y = attacker.y-(owner.owner.y-attacker.y);
					Entity target = Target.create(x,y);
					owner.focus=target;
				}
				else{
					return 2;
				}
			}
			if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
				return 0;
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
