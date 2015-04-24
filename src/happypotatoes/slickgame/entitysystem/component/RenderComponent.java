package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.world.World;

public abstract class RenderComponent extends Component{
	
	public float depth;

	public RenderComponent(Entity owner) {
		super(owner, Float.MAX_VALUE);
		
	}

	@Override
	public void update(World w, long delta) {
		if (Camera.camera.getRekt().intersect(getRect())) {
			depth = owner.y;
			EntityRenderer.addRenderTask(this);
		}
	}
	
	public abstract void render(float i);
	
	public abstract Rectangle getRect();
	
}
