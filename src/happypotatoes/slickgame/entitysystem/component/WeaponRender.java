package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class WeaponRender extends RenderComponent{

	private Rectangle rect;
	private float ox, oy;
	private Image texture;
	public WeaponRender(Entity owner, String spriteFolder, int width, int height, float offsetX, float offsetY) {
		super(owner, false);

		ox=offsetX;
		oy=offsetY;
		float unit = Camera.camera.getUnit();
		try {
			texture = Loader.image(spriteFolder);
			int w = width;
			int h = height;
			rect = new Rectangle(owner.x+ox, owner.y+oy, w/unit, h/unit);
		} catch (SlickException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void render(float i) {
		texture.draw(rect.x0, rect.y0, rect.w, rect.h, new Color(i,i,i,1));
		
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		return null;
	}

	@Override
	public void updateRect() {
		rect.setPosition(owner.x+ox, owner.y+oy);
	}

}
