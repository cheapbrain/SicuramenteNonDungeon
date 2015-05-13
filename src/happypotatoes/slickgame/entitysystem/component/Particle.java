package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class Particle extends Component {
	public long time, totalTime;
	public Particle(Entity owner, float priority, long ttl) {
		super(owner, priority);
		totalTime = ttl;
	}

	@Override
	public void update(World w, long delta) {
		time+=delta;
		if(time>=totalTime){
			EntitySystem.getInstance().removeEntity(owner);
		}
	}

}
