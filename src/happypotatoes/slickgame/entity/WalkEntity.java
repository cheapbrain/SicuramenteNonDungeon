package happypotatoes.slickgame.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class WalkEntity extends Entity{

	protected float speed;
	protected int directions = 4;
	protected int txwidth, txheight;
	protected int state = 0;
	protected int facing = 0;
	protected Animation[][] animations;
	
	public void render() {
		animations[state][facing].draw(x-width/2, y-height, width, height);
	}

	public WalkEntity(boolean doesCollide) {
		super(doesCollide);
	}
	
	public void setSize(int width, int height, int states) {
		txwidth = width;
		txheight = height;
		this.width = 1;
		this.height = (float)height/width;
		animations = new Animation[states][directions];
	}
	
	public void setAnimations(Image texture, int state, int frames, int time) {
		SpriteSheet s = new SpriteSheet(texture, txwidth, txheight);
		for (int i=0;i<directions;i++) {
			Animation a = new Animation();
			for (int j=0;j<frames;j++) {
				a.addFrame(s.getSprite(j, i), time);
			}
			animations[state][i] = a;
		}
	}
	
}
