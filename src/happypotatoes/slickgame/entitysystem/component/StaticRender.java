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
	private float ox, oy;
	private Image texture;
	public StaticRender(Entity owner, String spritePath, float offX, float offY) {
		super(owner);
		ox=offX;
		oy=offY;
		float unit = Camera.camera.getUnit();
		try {
			texture = new Image(spritePath);
			int w = texture.getWidth();
			int h = texture.getHeight();
			rect = new Rectangle(owner.x+ox, owner.y+oy, w/unit, h/unit);
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
		rect.setPosition(owner.x+ox, owner.y+oy);
	}
	
	@Override
	public void renderShadow(float i) {
		texture.drawFlash(rect.x0-.2f, rect.y0-.1f, rect.w+.4f, rect.h+.2f, new Color(i,0,0,1));
		
	}
}
