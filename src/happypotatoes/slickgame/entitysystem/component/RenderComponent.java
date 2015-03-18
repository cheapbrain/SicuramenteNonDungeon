package happypotatoes.slickgame.entitysystem.component;

import entity2.Component;
import entity2.Entity;
import happypotatoes.slickgame.world.World;

public abstract class RenderComponent extends Component{
	
	public float x, y, w, h, depth;

	public RenderComponent(Entity owner) {
		super(owner, 0);
		
	}

	@Override
	public void update(World w, long delta) {
		
	}
	
	public abstract void render();

}
