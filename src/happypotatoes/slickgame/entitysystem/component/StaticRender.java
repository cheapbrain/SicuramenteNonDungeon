package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Loader;
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
			texture = Loader.image(spritePath);
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

	static Color color = new Color(0,0,0,1f);
	@Override
	public void render(float i) {
		color.r = i;
		color.g = i;
		color.b = i;
		texture.draw(rect.x0, rect.y0, rect.w, rect.h, color);
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		Camera c = Camera.camera;
		int unit = c.getUnit();
		int x = (int) ((selectx-getRect().x0)*unit);
		int y = (int) ((selecty-getRect().y0)*unit);
		return texture.getColor(x, y);
	}

	@Override
	public void updateRect() {
		rect.setPosition(owner.x+ox, owner.y+oy);
	}
}
