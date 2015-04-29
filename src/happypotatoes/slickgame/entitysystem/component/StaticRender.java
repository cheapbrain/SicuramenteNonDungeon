package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class StaticRender extends RenderComponent{
	private Rectangle rect;
	private float ox, oy;
	private Image texture;
	public StaticRender(Entity owner, String spritePath, float offX, float offY, boolean isFloor) {
		super(owner, isFloor);
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
		rect.setPosition(owner.x+ox, owner.y+oy);
		texture.draw(rect.x0, rect.y0, rect.w, rect.h, new Color(i,i,i,1));
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		Camera c = Camera.camera;
		int unit = c.getUnit();
		int x = (int) ((selectx-getRect().x0)*unit);
		int y = (int) ((selecty-getRect().y0)*unit);
		return texture.getColor(x, y);
	}
}
