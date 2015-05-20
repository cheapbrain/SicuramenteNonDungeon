package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.entity.Target;

public class Escape extends State {
	public float speed;

	public Escape(AI owner, Integer...state){
		super(owner, state);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
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
			Walk thisWalk = owner.owner.getComponent(Walk.class); 
			thisWalk.dx = owner.focus.x-owner.owner.x;
			thisWalk.dy = owner.focus.y-owner.owner.y;
			thisWalk.d = (float)Math.sqrt(thisWalk.dx*thisWalk.dx+thisWalk.dy*thisWalk.dy)+0.000001f;
		}
		return 0;
	}
	

}
