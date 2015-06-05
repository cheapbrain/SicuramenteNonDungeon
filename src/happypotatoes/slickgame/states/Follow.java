package happypotatoes.slickgame.states;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.entity.Target;

public class Follow extends State {
	public float x,y; //last seen x y
	
	public Follow(AI owner, Integer...states) {
		super(owner, states);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time()) {
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();
			
			if(owner.focus==null){
				owner.focus = Target.create(x,y);
			}
			else x=owner.focus.x; y=owner.focus.y;
			
			Entity attacker;
			if((attacker = owner.seeAttacked(owner.owner))!=null){ //if pet is attacked
				if(attacker.getComponent(PlayerInput.class)==null){ //if pet is attacked and attacker is not player
					owner.focus=attacker;
					return 3;
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
				//se ha raggiunto un nemico
				if(owner.focus.getComponent(AI.class)!=null)
					owner.walker.setStill();
				//se ha raggiunto il player
				else if(owner.focus.getComponent(PlayerInput.class)!=null)
					owner.walker.setStill();
				//se ha raggiunto l'ultimo punto dove ha visto il nemico
				else return 1;
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
