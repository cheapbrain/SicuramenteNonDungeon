package happypotatoes.slickgame.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class WalkEntity extends Entity{
	protected int txwidth, txheight;
	protected int state = 0;
	protected int facing = 0;
	protected Animation[][] animations;
	
	public void render() {
		animations[state][facing].draw(x-width/2, y-height, width, height);
	}
	
	public void setSize(int width, int height, int states) {
		txwidth = width;
		txheight = height;
		this.width = 1;
		this.height = (float)height/width;
		animations = new Animation[states][8];
	}
	
	public void setAnimations(Image texture, int state, int frames, int time) {
		SpriteSheet s = new SpriteSheet(texture, txwidth, txheight);
		for (int i=0;i<8;i++) {
			Animation a = new Animation();
			for (int j=0;j<frames;j++) {
				a.addFrame(s.getSprite(j, i), time);
			}
			animations[state][i] = a;
		}
	}
	
}
