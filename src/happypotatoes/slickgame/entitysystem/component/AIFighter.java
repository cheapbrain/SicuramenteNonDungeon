package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.states.Chase;
import happypotatoes.slickgame.states.Escape;
import happypotatoes.slickgame.states.Fight;
import happypotatoes.slickgame.states.Idle;
import happypotatoes.slickgame.states.State;
import happypotatoes.slickgame.states.Stay;
import happypotatoes.slickgame.world.World;

public class AIFighter extends AI{
	
	List<Entity> inSight;
	State state;
	State states[]={new Idle(this,2), new Chase(this,1,3),
			new Fight(this,1,2,4), new Escape(this,3,5), new Stay(this,0) };
	
	
	public AIFighter(Entity owner, float priority, Walker walker,
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
				//if(r==1) walker.state=3;
				try {
					state=(states[state.pointers[r-1]-1].getClass().getDeclaredConstructor(AI.class,Integer[].class).newInstance(this,states[state.pointers[r-1]-1].pointers));
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			//if(state instanceof Idle) walker.state=3;
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
