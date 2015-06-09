package happypotatoes.slickgame.states;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.entity.Target;
import happypotatoes.slickgame.entitysystem.entity.Robot;

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
			if(owner.focus!=null)
				if(!(owner.focus.getClass().isInstance(Target.class))){
					owner.focus=null;
				}
				
			if(owner.focus==null){
				Entity attacker=owner.seeAttacked(owner.owner);
				if(attacker!=null){
					float dx = (attacker.x-owner.owner.x);
					float dy = (attacker.y-owner.owner.y);
					float d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
					float x = owner.owner.x-dx/d*5;
					float y = owner.owner.y-dy/d*5;
					owner.focus = Target.create(x,y);
				}
				else{
					return 2;
				}
			}
			if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
				return 0;
			}
			if(owner.focus.getComponent(AI.class)!=null) System.exit(0);
			owner.walker.setWalking();
			Walk thisWalk = owner.owner.getComponent(Walk.class); 
			thisWalk.dx = owner.focus.x-owner.owner.x;
			thisWalk.dy = owner.focus.y-owner.owner.y;
			thisWalk.d = (float)Math.sqrt(thisWalk.dx*thisWalk.dx+thisWalk.dy*thisWalk.dy)+0.000001f;
		}
		return 0;
	}
	

}
