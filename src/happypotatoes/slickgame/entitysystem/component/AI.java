package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;
import java.util.Random;








import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.states.State;
import happypotatoes.slickgame.world.World;

public abstract class AI extends Component {
	public Walker walker;
	public Movement movement;
	public float speed;
	public float delay=100;
	public float time=delay;
	public World w;
	public Random r = new Random();
	public Entity focus=null;
	public State state;
	public List<Entity> inSight;
		
	
	public AI(Entity owner, float priority, Walker walker, Movement movement,  float speed) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;		
		this.speed=speed;	
	}
	
	@Override
	public abstract void update(World w, long delta);
	public abstract Entity getFocus();
	
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
			if(!castRay(owner,t)||d>6){
				iterator.remove();
			}
		}
		return tmp;
	}
	
	public boolean castRay(Entity a, Entity b){
		return castRay(a.x,a.y,b.x,b.y);
	}
	
	public boolean castRay(float x1, float y1, float x2, float y2) {
		if ((int)x1==(int)x2&&(int)y1==(int)y2)
			return true;
		float dx = x2-x1;
		float dy = y2-y1;
		float d = (float)Math.sqrt(dx*dx+dy*dy)+0.0000001f;
		
		int ix, iy;
		float sdx, sdy;
		if (dx>0) {
			ix = +1;
			sdx = (int)x1+1-x1;
		} else {
			ix = -1;
			sdx = x1-(int)x1;
		}
		if (dy>0) {
			iy = +1;
			sdy = (int)y1+1-y1;
		} else {
			iy = -1;
			sdy = y1-(int)y1;
		}
		
		float stepx = d/Math.abs(dx);
		float stepy = d/Math.abs(dy);
		
		float countx = stepx*sdx;
		float county = stepy*sdy;
		
		int x = (int)x1;
		int y = (int)y1;
				
		while(true) {
			if (w.getTerrainType()[x][y]==1)
				return false;
			if (countx>=d&&county>=d)
				return true;
			
			if (countx<county) {
				x += ix;
				countx += stepx;
			} else {
				y += iy;
				county += stepy;
			}
		}
	}
	
	public Entity seeAttacked(Entity victim){
		Iterator<Entity> iterator = inSight.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			//per il player
			if(t.getComponent(PlayerInput.class) != null){
				if(t.getComponent(PlayerInput.class).focus == victim){
					if(t.getComponent(Faction.class).enemyOf(owner))
						return t;
				}
			}
			//per i mob
			if(t.getComponent(AI.class) != null){
				if(t.getComponent(AI.class).focus == victim){
					if(t.getComponent(Faction.class).enemyOf(owner))
						return t;
				}
			}
		}
		return null;
	}
}
