package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends WalkEntity{
	float speed = 0.001f;
	int direction;
	
	public Player() {
		x = 3;
		y = 3;
		try {
			Image texture = new Image("./res/spritetest.png");
			texture.setFilter(Image.FILTER_NEAREST);
			setSize(48, 92, 2);
			setAnimations(texture, 0, 1, 1);
			setAnimations(texture, 1, 9, 100);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(GameContainer container, World world, int delta) {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_W)) {
			speedy = -speed;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			speedy = speed;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			speedx = -speed;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			speedx = speed;
		}

		super.update(container, world, delta);

		float dx = x-oldx;
		float dy = y-oldy;
		float d = (float)Math.sqrt(dx*dx+dy*dy);
		
		if (d>0) {
			int newdir = (int)Math.round(Math.acos(dx/d)/Math.PI*4);
			if (dy<0) newdir = -newdir;
			newdir = ((newdir+5)%8+1)%8;
			
			
			facing = newdir;

			state = 1;
		} else {
			state = 0;
		}

	}
	
	public void render() {
		super.render();
	}
}
