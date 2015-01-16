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
			Image texture = new Image("./res/spriteomg.png");
			texture.setFilter(Image.FILTER_NEAREST);
			setSize(64, 128, 2);
			setAnimations(texture, 0, 1, 1);
			setAnimations(texture, 1, 4, 100);
			
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

		float dx = x-oldx;
		float dy = y-oldy;
		super.update(container, world, delta);

		float d = (float)Math.sqrt(dx*dx+dy*dy);
		
		if (d>0) {
			if(dy>0)
				facing = 0;
			else if (dy<0)
				facing = 2;
			else if (dx>0)
				facing = 3;
			else if (dx<0)
				facing = 1;
			

			state = 1;
		} else {
			state = 0;
		}

	}
	
	public void render() {
		super.render();
	}
}
