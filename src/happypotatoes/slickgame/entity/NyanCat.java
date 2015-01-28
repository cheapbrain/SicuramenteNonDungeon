package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.particles.Particle;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class NyanCat extends Entity{
	float speed = 0.006f;
	float ospeedx, ospeedy;
	int facing = 1;
	Animation[] walk;
	Image tail;
	
	public NyanCat() {
		super(true);
		speedx = 0.006f;
		speedy = 0.003f;
		ospeedx = speedx;
		ospeedy = speedy;
		
		Image texture;
		try {
			tail = new Image("./res/nyanparticle.png");
			tail.setFilter(Image.FILTER_NEAREST);
			texture = new Image("./res/nyanwalk.png");
			texture.setFilter(Image.FILTER_NEAREST);
			SpriteSheet s = new SpriteSheet(texture, 36, 24);
			width = 1.5f;
			height = width*(float)24/36;
			
			walk = new Animation[2];
			
			walk[0] = new Animation();
			walk[1] = new Animation();
			for (int j=1;j<7;j++) {
				walk[0].addFrame(s.getSprite(j, 0), 100);
				walk[1].addFrame(s.getSprite(j, 1), 100);
			}
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	int taildelay = 0;
	
	@Override
	public void update(GameContainer container, World world, int delta) {
		float dx = x-oldx;
		if (dx>0) facing = 0;
		else facing = 1;
		
		super.update(container, world, delta);

		if (speedx==0) ospeedx = speedx = -ospeedx;
		if (speedy==0) ospeedy = speedy = -ospeedy;
		
		taildelay-= delta;
		if (taildelay<=0) {
			world.add(new Particle(tail, 2000, x+1f, y-.3f));
			taildelay = 5;
		}
	}
	
	public void render() {
		walk[facing].draw(x-width/2, y-height, width, height);
	}
}
