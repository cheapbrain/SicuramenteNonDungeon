package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Entity {
	protected float oldx, oldy, x, y, width, height;
	protected float speedx, speedy;
	protected float size = .4f;
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void update(GameContainer container, World world, int delta) {

		oldx = x;
		oldy = y;
		
		x += speedx*delta;
		y += speedy*delta;
		
		float r = 1.001f;
		
		if (x>oldx)  {
			if (!world.isWalkable(x+size, oldy-size)||!world.isWalkable(x+size, oldy+size/2)) {
				x = (float) (Math.floor(x+size)-size*r);
			}
		} else if (x<oldx) {
			if (!world.isWalkable(x-size, oldy-size)||!world.isWalkable(x-size, oldy+size/2)) {
				x = (float) (Math.ceil(x-size)+size*r);
			}
		}

		if (y>oldy)  {
			if (!world.isWalkable(oldx+size, y+size/2)||!world.isWalkable(oldx-size, y+size/2)) {
				y = (float) (Math.floor(y+size/2)-size/2*r);
			}
		} else if (y<oldy) {
			if (!world.isWalkable(oldx+size, y-size)||!world.isWalkable(oldx-size, y-size)) {
				y = (float) (Math.ceil(y-size)+size*r);
			}
		}
		
		speedx = 0;
		speedy = 0;
	}

	public void render() {
		
	}
	
	public float getDist(Entity entity) {
		float dx = this.getX()-entity.getX();
		float dy = this.getY()-entity.getY();
		float ris = (float) Math.sqrt(dx*dx+dy*dy);
		return ris;
	}
}
