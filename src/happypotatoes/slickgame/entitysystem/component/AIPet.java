package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class AIPet extends AI{
	Entity focus;
	
	public AIPet(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
	}
	
	@Override
	public void update(World w, long delta) {
		time+=delta;
		if(time>=100){
			
			
			time=0;			
		}
	}
	

}
