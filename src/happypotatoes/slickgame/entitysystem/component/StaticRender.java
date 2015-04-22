package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class StaticRender extends RenderComponent{
	public StaticRender(Entity owner) {
		super(owner);
		
	}
	public void render() {
		
	}
	public Rectangle getRect() {
		return null;
	}

}
