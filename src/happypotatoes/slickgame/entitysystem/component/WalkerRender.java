package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class WalkerRender extends RenderComponent{
	private Image foreground, healthBar;
	Walker walker;
	private Animation[][] animations;
	private int state;
	private float ox, oy;
	private Rectangle rect;
	private int frameTime = (int)Math.round(1000f/24);

	public WalkerRender(Entity owner, Walker walker, String spriteFolder, int width, int height, float offsetX, float offsetY) {
		super(owner, false);
		this.walker = walker;
		ox = offsetX;
		oy = offsetY;
		try {
			foreground = new Image("./res/MonsterBarBackGround.png");
			healthBar = new Image("./res/MonsterBarHealthBar.png");
		} catch (SlickException e1) {
			e1.printStackTrace();
		}	
		float unit = Camera.camera.getUnit();
		animations = new Animation[walker.states][walker.directions];
		for (int i=0;i<walker.states;i++)
			try {
				Image texture = new Image(spriteFolder+i+".png");
				int frames = texture.getWidth()/width;
				int w = width;
				int h = height;
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

	static Color color = new Color(0,0,0,1f);
	@Override
	public void render(float i) {
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
		
		SelectComponent select = owner.getComponent(SelectComponent.class);
		
		Health hc = owner.getComponent(Health.class);
		if(hc!=null&&!owner.getName().equals("Player")){
			healthBar.getSubImage(0, 0, (int) (healthBar.getWidth()*hc.getHealth()/hc.getMaxHealth()), healthBar.getHeight()).draw(owner.x+select.ox-.15f, owner.y+select.oy-.3f, (select.rect.w+.3f)*hc.getHealth()/hc.getMaxHealth(), .3f, color);
			foreground.draw(owner.x+select.ox-.15f, owner.y+select.oy-.3f, select.rect.w+.3f, .3f, color);
		}
	}
	@Override
	public Rectangle getRect() {
		return rect;
	}
	
	public int getFrameTime() {
		return frameTime;
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		Camera c = Camera.camera;
		int unit = c.getUnit();
		int x = (int) ((selectx-getRect().x0)*unit);
		int y = (int) ((selecty-getRect().y0)*unit);
		return animations[state][walker.facing].getCurrentFrame().getColor(x, y);
	}

	public int getFrames(int i) {
		return animations[i][walker.facing].getFrameCount();
	}

	@Override
	public void updateRect() {
		rect.setPosition(owner.x+ox, owner.y+oy);
	}
}
