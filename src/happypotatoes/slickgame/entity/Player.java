package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Entity{
	Image texture;
	float speed = 0.004f;
	
	public Player() {
		x = 3;
		y = 3;
		try {
			texture = new Image("./res/player.png");
			texture.setFilter(Image.FILTER_NEAREST);
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
	}
	
	public void render() {
		texture.draw(x-.5f, y-1, 1, 1);
	}
}
