package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.world.World;

public abstract class RenderComponent extends Component{
	
	public boolean isFloor = false;
	public float depth;

	public RenderComponent(Entity owner, boolean isFloor) {
		super(owner, Float.MAX_VALUE);
		this.isFloor = isFloor;
	}

	@Override
	public void update(World w, long delta) {
		if (Camera.camera.getRekt().intersect(getRect())) {
			depth = isFloor?Float.NEGATIVE_INFINITY:owner.y;
			EntityRenderer.addRenderTask(this);
		}
	}
	
	public abstract void render(float i);
		
	public abstract Rectangle getRect();

	public abstract Color getPixel(float selectx, float selecty);
	
}
