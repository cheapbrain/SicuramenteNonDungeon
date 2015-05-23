package happypotatoes.slickgame.states;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.Walk;

public class Follow extends State {
	public float speed;
	
	public Follow(AI owner, Integer...states) {
		super(owner, states);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
			
			if(owner.focus==null)
				return 1;
			
			Entity attacker;
			if((attacker = owner.seeAttacked(owner.owner))!=null){ //if pet is attacked
				if(attacker.getComponent(PlayerInput.class)!=null){ //if pet is attacked and attacker is not player
					owner.focus=attacker;
					return 2;
				}
			}
			if(owner.focus.getComponent(PlayerInput.class)!=null){ //if focus is on player
				if((attacker = owner.seeAttacked(owner.focus))!=null){ //if player is attacked
					if(attacker.getComponent(PlayerInput.class)==null){ //if player is not suiciding
						owner.focus=attacker;
						return 3;
					}
				}
			}
			if(owner.getDistance(owner.focus)<1.5f){ //1.5 andrà sostituito con range
				owner.walker.setStill();
			}
			else{
				owner.walker.setWalking();
				Walk thisWalk = owner.owner.getComponent(Walk.class); 
				thisWalk.dx = owner.focus.x-owner.owner.x;
				thisWalk.dy = owner.focus.y-owner.owner.y;
				thisWalk.d = (float)Math.sqrt(thisWalk.dx*thisWalk.dx+thisWalk.dy*thisWalk.dy)+0.000001f;
			}
		}		
		return 0;
	}
}
