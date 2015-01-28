package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Meuwse extends WalkEntity{
	float speed = 0.0015f;
	int timer;
	int direction;
	public Meuwse(){
		super(true);
		x = 3;
		y = 3;
		timer = 0;
		direction=0;
		try {
			Image texture = new Image("./res/mouse.png");
			texture.setFilter(Image.FILTER_NEAREST);
			setSize(48, 48, 2);
			setAnimations(texture, 0, 1, 1);
			setAnimations(texture, 1, 4, 100);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void update(GameContainer container, World world, int delta) {
		super.update(container, world, delta);
		if (timer <= 0){
			Random r = new Random();
			direction = (direction+(1+2*(r.nextInt()%2)))%6;
			timer = (r.nextInt()%1000)+500;
			if (direction<0) direction=-direction;
			System.out.println(direction);
		}
		switch(direction){
		case 0: state = 1; facing=2; speedx = speed; break;//dx
		case 1: state = 1; facing=0; speedy = speed; break;//su
		case 2: state = 1; facing=1; speedx = -speed; break;//sx
		case 3: state = 1; facing=3; speedy = -speed; break;//giu
		case 4: state = 0; facing= 0; break;
		case 5: state = 0; facing= 0; break;
		}
		timer = timer - delta;
		if ((oldx==x)&&(oldy==y)) timer=0;
	}
	public void render() {
		super.render();
	}
}
