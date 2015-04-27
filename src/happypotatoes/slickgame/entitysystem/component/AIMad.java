package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class AIMad extends AI{
	
	List<Entity> inSight;
	long delay = 100;
	float dx, dy, d;
	
	float speed;
	
	public AIMad(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		
	}
	
	@Override
	public void update(World w, long delta) {
		inSight = getEntitiesInSight();
		focus = getFocus();	
		time -= delta;
		if(focus != null) {
			if (time<=0) {
				time = delay;
				dx = focus.x-owner.x;
				dy = focus.y-owner.y;
				if(getDistance(focus)<1){
					walker.state=2;
				}
			}
			goTo(dx,dy);
			
			
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
