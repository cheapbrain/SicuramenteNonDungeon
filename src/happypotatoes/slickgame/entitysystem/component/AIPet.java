package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.states.Chase;
import happypotatoes.slickgame.states.Fight;
import happypotatoes.slickgame.states.Follow;
import happypotatoes.slickgame.states.Idle;
import happypotatoes.slickgame.states.State;
import happypotatoes.slickgame.world.World;

public class AIPet extends AI{
	List<Entity> inSight;
	State state;
	State states[]={new Idle(this,2), new Follow(this,1,3), 
			new Chase(this,1,4,2), new Fight(this,1,3,2,2)};
	
	public AIPet(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		try {
			state=states[0].getClass().getDeclaredConstructor(AI.class,Integer[].class).newInstance(this,states[0].pointers);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(World w, long delta) {
		//update AI
		if(walker.state!=3){
				inSight = getEntitiesInSight();
				focus = getFocus();
				time -= delta;
				//update stati
				int r = state.update(delta);
				
				if(r!=0){
					try {
						state=(states[state.pointers[r-1]-1].getClass().getDeclaredConstructor(AI.class,Integer[].class).newInstance(this,states[state.pointers[r-1]-1].pointers));
						
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
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
