package happypotatoes.slickgame.entitysystem.component.equip;

import java.io.File;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.items.ItemSprite;
import happypotatoes.slickgame.world.World;

public class EquipRender extends RenderComponent {
	Walker walker;
	public Animation[][] animations;
	private int state;
	private int width, height;
	private float offsetX, offsetY;
	private float unit = Camera.camera.getUnit();
	private Rectangle rect;
	private int frameTime = (int)Math.round(1000f/24);
	
	public EquipRender(Entity owner, Walker walker, int width, int height, float offsetX, float offsetY) {
		super(owner, false);
		this.walker = walker;
		this.height = height;
		this.width = width;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.rect = new Rectangle(owner.x+offsetX, owner.y+offsetY, width/unit, height/unit);
		
	}

	public void updateAnimation(String spriteFolder) {
		this.animations = new Animation[walker.states][walker.directions];
		for (int i=0;i<walker.states;i++)
			try {
				//System.out.println(spriteFolder+String.valueOf(i)+".png");
				Image texture = ItemSprite.load(spriteFolder+String.valueOf(i)+".png");
				//System.out.println(texture);
				int frames = texture.getWidth()/width;
				int w = width;
				int h = height;
				
				SpriteSheet s = new SpriteSheet(texture, w, h);
				for (int j=0;j<walker.directions;j++) {
					Animation a = new Animation();
					a.setLooping(true);
					for (int k=0;k<frames;k++) {
						a.addFrame(s.getSprite(k, j), frameTime);
					}
					animations[i][j] = a;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
	}

	@Override
	public void render(float i) {
		if(exists()){
			state = walker.getState();
			animations[state][walker.facing].setCurrentFrame(
					owner.getComponent(WalkerRender.class).animations[state][walker.facing].getFrame());
			animations[state][walker.facing].draw(rect.x0, rect.y0, rect.w, rect.h, new Color(i,i,i,1));
		}
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		Camera c = Camera.camera;
		int unit = c.getUnit();
		int x = (int) ((selectx-getRect().x0)*unit);
		int y = (int) ((selecty-getRect().y0)*unit);
		return animations[state][walker.facing].getCurrentFrame().getColor(x, y);
	}

	public int getFrameTime() {
		return frameTime;
	}

	public void setFrameTime(int frameTime) {
		this.frameTime = frameTime;
	}
	
	public int getFrames(int i) {
		return animations[i][walker.facing].getFrameCount();
	}

	@Override
	public void updateRect() {
		rect.setPosition(owner.x+offsetX, owner.y+offsetY);
	}

	public boolean exists() {
		return (animations!=null);
	}

}
