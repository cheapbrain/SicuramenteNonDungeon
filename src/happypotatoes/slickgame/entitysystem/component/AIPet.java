package happypotatoes.slickgame.entitysystem.component;

import java.util.Iterator;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.states.Chase;
import happypotatoes.slickgame.states.DefendPlayer;
import happypotatoes.slickgame.states.Follow;
import happypotatoes.slickgame.states.Idle;
import happypotatoes.slickgame.states.State;
import happypotatoes.slickgame.world.World;

public class AIPet extends AI{
	State state;
	State states[]={new Idle(this,2), new Follow(this,1,3,3), 
			new Chase(this,1,4,2), new DefendPlayer(this,1,3,1)};
	
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
		this.w=w;
		//update AI
		if(walker.getState()!=3){
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
			if(t==focus)
				return t;
		}
		iterator = inSight.iterator();
		while(iterator.hasNext()){
			Entity t = iterator.next();
			if(t.getComponent(PlayerInput.class) != null)
				return t;
		}
		return null;
	}
	
}
