package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.geom.Rectangle;
import entity2.Entity;
import entity2.Walker;

public class WalkerRender extends RenderComponent{
	Walker walker;
	private Animation[][] animations;
	private int state;
	private float ox, oy;
	private Rectangle rect;

	public WalkerRender(Entity owner, Walker walker, String spriteFolder, int frames, int frameTime, float offsetX, float offsetY) {
		super(owner);
		this.walker = walker;
		ox = offsetX;
		oy = offsetY;
		float unit = Camera.camera.getUnit();
		animations = new Animation[walker.states][walker.directions];
		for (int i=0;i<walker.states;i++)
			try {
				Image texture = new Image(spriteFolder+i+".png");
				int w = texture.getWidth()/frames;
				int h = texture.getHeight()/walker.directions;
				
				if (i==0)
					rect = new Rectangle(owner.x+ox, owner.y+oy, w/unit, h/unit);
				
				SpriteSheet s = new SpriteSheet(texture, w, h);
				for (int j=0;j<walker.directions;j++) {
					Animation a = new Animation();
					a.setLooping(true);
					for (int k=0;k<frames;k++) {
						a.addFrame(s.getSprite(k, j), frameTime);
					}
					animations[i][j] = a;
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public void render() {
		if(state!=walker.state)
			animations[state][walker.facing].start();
		state = walker.state;
		
		animations[state][walker.facing].draw(rect.x0, rect.y0, rect.w, rect.h);
		
		rect.setPosition(owner.x+ox, owner.y+oy);
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

}