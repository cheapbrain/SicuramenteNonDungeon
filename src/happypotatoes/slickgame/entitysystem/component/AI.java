package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;
import java.util.Random;






import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public abstract class AI extends Component {
	public Walker walker;
	public Movement movement;
	public float speed;
	public float delay=100;
	public float time=delay;
	public Random r = new Random();
	public Entity focus=null;
	public List<Entity> inSight;
		
	
	public AI(Entity owner, float priority, Walker walker, Movement movement,  float speed) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;		
		this.speed=speed;	
	}

	@Override
	public abstract void update(World w, long delta);
	
	public boolean time(){
		if(time<=0){
			time=delay;
			return true;
		}
		else return false;
	}
	
	public float getDistance(Entity focus){
		float dx = focus.x-owner.x;
		float dy = focus.y-owner.y;	
		return (float)Math.sqrt(dx*dx+dy*dy)+0.000001f;
	}
	
	public void goTo(float dx, float dy, float d){ //0 raggiunto, 1 non raggiunto
		if (d>1.4&&walker.getState()<2) {
			float nsx = dx/d*speed;
			float nsy = dy/d*speed;
			movement.speedx += nsx;
			movement.speedy += nsy;
			walker.setFacing(nsx, nsy);
			walker.setWalking();
			return;
		} 
		else{ 
			if (walker.getState()==1)
				walker.setStill();
			return;
			}
	}
	
	public List<Entity> getEntitiesInSight(){
		List<Entity> tmp = (EntitySystem.getInstance().getEntities(Walker.class));
		Iterator<Entity> iterator = tmp.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			float d = (float) Math.sqrt(((owner.x-t.x)*(owner.x-t.x))+((owner.y-t.y)*(owner.y-t.y)));
			if(t==this.owner){
				iterator.remove();
			}
			if(d>6){
				iterator.remove();
			}
		}
		return tmp;
	}
	
	public abstract Entity getFocus();
	
	public Entity isAttacked(Entity victim){
		Iterator<Entity> iterator = inSight.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			if(t.getComponent(AI.class) != null){
				if(t.getComponent(AI.class).focus == victim){
					return t;
				}
			}
		}
		return null;
	}
}
