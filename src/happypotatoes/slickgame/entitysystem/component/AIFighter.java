package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class AIFighter extends AI{
	
	List<Entity> inSight;
	long delay = 100;
	float dx, dy, d;
	float speed;
	Attack focusAttack;
	Health focusHealth;
	Energy focusEnergy;
	
	
	public AIFighter(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		
	}
	
	@Override
	public void update(World w, long delta) {
		inSight = getEntitiesInSight();
		focus = getFocus();
		Attack atk = null;
		if(focus!=null) atk = focus.getComponent(Attack.class);
		time -= delta;
		if((focus != null)&&(focus.getComponent(Walker.class).state!=3)) {
			if (time<=0) {
				time = delay;
				dx = focus.x-owner.x;
				dy = focus.y-owner.y;
				d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
				walker.state=1;
				if(getDistance(focus)<1.5f&&walker.state!=4){
					walker.state=2;
					if(atk!=null)
						if(atk.animationTime>0
								&&(atk.animationTotalTime-atk.animationTime)>owner.getComponent(Defend.class).animationTotalTime)
							if(walker.state!=5) walker.state=4;						
				}
			}
			if(walker.state==1) goTo(dx,dy,d);	
		}
	}
	

	@Override
	public Entity getFocus() {
		Iterator<Entity> iterator = inSight.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			if(t.getComponent(PlayerInput.class) != null)
				return t;
		}
		return null;
	}
		
}
