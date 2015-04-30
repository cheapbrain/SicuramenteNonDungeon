package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;
import java.util.Random;





import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public abstract class AI extends Component {
	Walker walker;
	Movement movement;
	int intelligence;
	float speed, time;
	Random r = new Random();
	Entity focus=null;
		
	
	public AI(Entity owner, float priority, Walker walker, Movement movement,  float speed) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;		
		this.speed=speed;	
	}

	@Override
	public void update(World w, long delta) {
	}
	
	public float getDistance(Entity focus){
		float dx = focus.x-owner.x;
		float dy = focus.y-owner.y;	
		return (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
	}
	
	public void goTo(float dx, float dy, float d){
		if (d>1.4&&walker.state<2) {
			float nsx = dx/d*speed;
			float nsy = dy/d*speed;
			movement.speedx += nsx;
			movement.speedy += nsy;
			walker.setFacing(nsx, nsy);
			walker.state = 1;
		} else if (walker.state==1){
			walker.state = 0;
		}
	}
	
	public List<Entity> getEntitiesInSight(){
		List<Entity> tmp = (EntitySystem.getInstance().getEntities(Walker.class));
		Iterator<Entity> iterator = tmp.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			float d = (float) Math.sqrt(((owner.x-t.x)*(owner.x-t.x))+((owner.y-t.y)*(owner.y-t.y)));
			if(d>6){
				iterator.remove();
			}
		}
		return tmp;
	}
	
	public abstract Entity getFocus();
	
}
