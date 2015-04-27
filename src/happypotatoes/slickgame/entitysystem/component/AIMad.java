package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.world.World;

public class AIMad extends AI{
	Entity focus=null;
	
	public AIMad(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		
	}
	
	@Override
	public void update(World w, long delta) {
		if(focus==null) focus = EntitySystem.getInstance().getEntities(PlayerInput.class).get(0);
		time+=delta;
		if(time>=20){
			
			time=0;			
		}
	}
	
	
	
}
