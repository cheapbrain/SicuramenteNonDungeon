package happypotatoes.slickgame.states;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;

public class Follow extends State {
	public long delay = 100;
	public float dx, dy, d;
	public float speed;
	
	public Follow(AI owner, Integer...states) {
		super(owner, states);
	}
	
	@Override
	public int update(long delta) {
		if (owner.time<=0) {
			if(owner.focus==null)
				return 1;
			Entity attacker;
			if((attacker = owner.isAttacked(owner.owner))!=null){ //if pet is attacked
				if(attacker.getComponent(PlayerInput.class)!=null){ //if pet is attacked and attacker is not player
					owner.focus=attacker;
					return 2;
				}
			}
			if(owner.focus.getComponent(PlayerInput.class)!=null){ //if focus is on player
				if((attacker = owner.isAttacked(owner.focus))!=null){ //if player is attacked
					if(attacker.getComponent(PlayerInput.class)==null){ //if player is not suiciding
						owner.focus=attacker;
						return 3;
					}
				}
			}
			owner.walker.setWalking();
			owner.time = delay;
			dx = owner.focus.x-owner.owner.x;
			dy = owner.focus.y-owner.owner.y;
			d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;	
		}
		owner.goTo(dx,dy,d);
		
		return 0;
	}

}
