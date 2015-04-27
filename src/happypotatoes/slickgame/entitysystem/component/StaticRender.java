package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class StaticRender extends RenderComponent{
	private Rectangle rect;
	private Image texture;
	public StaticRender(Entity owner, String spritePath) {
		super(owner);
		float unit = Camera.camera.getUnit();
			try {
				texture = new Image(spritePath);
				float w = texture.getWidth()/unit;
				float h = texture.getHeight()/unit;
				rect = new Rectangle(owner.x, owner.y, w, h);
			} catch (SlickException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}
	
	@Override
	public void render(float i) {
		texture.draw(rect.x0, rect.y0, rect.w, rect.h);
		
	}
	
	@Override
	public void renderShadow(float i) {
		// TODO Auto-generated method stub
		
	}
}
