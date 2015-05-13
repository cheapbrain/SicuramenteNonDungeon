package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class AIPet extends AI{
	Entity focus=null;
	float dx, dy, d;
	
	float speed;
	
	long delay = 100;
	long time = 0;
	private List<Entity> inSight;
	
	public AIPet(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		this.speed = speed;
	}
	
	@Override
	public void update(World w, long delta) {
		super.update(w, delta);
		inSight = getEntitiesInSight();
		focus = getFocus();	
		time -= delta;
		if(focus != null) {
			if (time<=0) {
				time = delay;
				dx = focus.x-owner.x;
				dy = focus.y-owner.y;	
				d = (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
			}
			goTo(dx,dy,d);
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
	
	
		
	@Override
	public int goTo(float dx, float dy, float d){
		if (d>1.4&&walker.state<2) {
			float nsx = dx/d*speed*(d-1.2f);
			float nsy = dy/d*speed*(d-1.2f);;
			movement.speedx += nsx;
			movement.speedy += nsy;
			walker.setFacing(nsx, nsy);
			walker.state = 1;
			return 1;
		} else{
			if (walker.state==1)
				walker.state = 0;
			return 0;
		}
	}
}
