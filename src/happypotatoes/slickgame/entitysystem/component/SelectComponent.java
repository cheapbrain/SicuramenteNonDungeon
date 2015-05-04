package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.world.World;

public class SelectComponent extends Component{
	Rectangle rect;
	float ox, oy;
	public float depth;

	public SelectComponent(Entity owner, float priority, float ox, float oy, float w, float h) {
		super(owner, priority);
		rect = new Rectangle(owner.x+ox, owner.y+oy,  w, h);
		this.ox = ox;
		this.oy = oy;
		depth = owner.y;
	}

	@Override
	public void update(World w, long delta) {
		rect.setPosition(owner.x+ox, owner.y+oy);
		depth = owner.y;
	}
	
	public Rectangle getRect() {
		return rect;
	}

}
