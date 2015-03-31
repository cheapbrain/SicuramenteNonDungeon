package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class HitBox extends Component {
	public float sizex, sizey;
	
	public HitBox(Entity owner, float sizex, float sizey, float priority) {
		super(owner, priority);
		this.sizex = sizex;
		this.sizey = sizey;
	}

	@Override
	public void update(World w, long delta) {
		
	}
}