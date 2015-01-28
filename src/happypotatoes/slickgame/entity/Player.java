package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends WalkEntity{
	float speed = 0.030f;
	
	public Player() {
		super(true);
		try {
			Image texture = new Image("./res/spritea.png");
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
			state = 1;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			speedy = speed;
			state = 1;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			speedx = -speed;
			state = 1;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			speedx = speed;
			state = 1;
		}

		float dx = x-oldx;
		float dy = y-oldy;
		super.update(container, world, delta);
		
		float d = (float)Math.sqrt(dx*dx+dy*dy);
		
		if (state==1&&d>0) {
			speedx = 0;
			speedy = 0;
			
			if (dx>0)
				facing = 3;
			else if (dx<0)
				facing = 1;
			else if(dy>0)
				facing = 0;
			else if (dy<0)
				facing = 2;
		} else {
			state = 0;
		}

	}
	
	public void render() {
		super.render();
	}
}
