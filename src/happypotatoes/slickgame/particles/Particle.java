package happypotatoes.slickgame.particles;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

public class Particle extends Entity{
	protected Image image;
	protected Color color1, color2, color;
	protected float scale1, scale2, scale;
	protected int duration, time;
	
	public Particle(Image image, int duration, float x, float y) {
		super(false);
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.image = image;
		time = 0;
		
		color1 = Color.white;
		color2 = new Color(255, 255, 255, 0);
		scale1 = 1.5f;
		scale2 = 1.5f;
	}
	
	@Override
	public void update(GameContainer container, World world, int delta) {
		x += speedx;
		y += speedy;
		
		time += delta;
		if (time>duration)
			world.remove(this);
	}
	
	@Override
	public void render() {

		float k = (float)time/duration;
		float p = 1-k;
		
		color = color1.scaleCopy(p).addToCopy(color2.scaleCopy(k));
		scale = scale1*p+scale2*k;
		
		image.draw(x-scale, y-scale/2, scale/image.getWidth(), color);
	}
}
