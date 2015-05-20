package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;
import java.util.List;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.states.Chase;
import happypotatoes.slickgame.states.Escape;
import happypotatoes.slickgame.states.Fight;
import happypotatoes.slickgame.states.Idle;
import happypotatoes.slickgame.states.State;
import happypotatoes.slickgame.states.Rest;
import happypotatoes.slickgame.world.World;

public class AIFighter extends AI{
	State state;
	State states[]={new Idle(this,2), new Chase(this,1,3),
			new Fight(this,1,2,4), new Escape(this,3,5), new Rest(this,1,4) };
	
	
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
		if(walker.getState()!=3){
			inSight = getEntitiesInSight();
			time -= delta;
			focus = getFocus();
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
			if(t==focus)
				return t;
		}
		iterator = inSight.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			if(t.getComponent(PlayerInput.class) != null)
				if(t.isAlive())
					return t;
		}
		return null;
	}
		
}
