package happypotatoes.slickgame.states;

import java.util.Random;

import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.entity.Target;

public class Idle extends State {
	
	public Idle(AI owner, Integer...state){
		super(owner, state);
	}

	@Override
	public int update(long delta) {
		if(owner.time(1000)){
			owner.inSight = owner.getEntitiesInSight();
			owner.focus = owner.getFocus();

			if((owner.focus!=null)&&(owner.focus.getComponent(AI.class)!=null))
				return 1;
			else {
				Random r = new Random();
				float dx = (r.nextFloat()*2f-1f);
				float dy = (float) Math.sqrt(1f-dx*dx)*(r.nextInt(2)*2f-1f);;
				float d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
				float x = owner.owner.x-dx/d*2f;
				float y = owner.owner.y-dy/d*2f;
				owner.focus = Target.create(x,y);
				EntitySystem.getInstance().addEntity(owner.focus);
				return 1;
			}			
		}
		return 0;
	}

}
