package happypotatoes.slickgame.entitysystem.component.equip;

import java.io.File;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.items.Equippable;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.items.ItemList;
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
	
	public EquipRender(Entity owner, Walker walker) {
		super(owner, false);
		this.walker = walker;
		this.rect = new Rectangle();
	}

	public void updateAnimation(Item item, String spriteFolder) {
		Equippable e = (Equippable) item;
		this.height = e.height;
		this.width = e.width;
		this.offsetX = e.offsetX;
		this.offsetY = e.offsetY;
		this.rect = new Rectangle(owner.x+offsetX, owner.y+offsetY, width/unit, height/unit);
		this.animations = new Animation[walker.states][walker.directions];
		for (int i=0;i<walker.states;i++)
			try {
				//System.out.println(spriteFolder+String.valueOf(i)+".png");
				Image texture = Loader.image("res/Sprites/"+spriteFolder+String.valueOf(i)+".png");
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		
	}

	static Color color = new Color(0,0,0,1f);
	@Override
	public void render(float i) {
		
		if(exists()){
			color.r = i;
			color.g = i;
			color.b = i;
			
			if(state!=walker.getState()) {
				animations[state][walker.facing].restart();
			}
			state = walker.getState();
			
			if(animations[state][walker.facing].getFrame()==animations[state][walker.facing].getFrameCount()-1&&state==3){	
				animations[state][walker.facing].draw(rect.x0, rect.y0, rect.w, rect.h, color);
				animations[state][walker.facing].stop();
			}else{
				animations[state][walker.facing].draw(rect.x0, rect.y0, rect.w, rect.h, color);
			}
			
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
