package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Entity {
	protected float oldx, oldy, x, y, width, height;
	protected float speedx, speedy;
	protected float size = .4f;
	protected boolean doesCollide = true;
	
	public Entity(boolean doesCollide) {
		this.doesCollide = doesCollide;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public void update(GameContainer container, World world, int delta) {

		oldx = x;
		oldy = y;
		
		x += speedx*delta;
		y += speedy*delta;
		
		if (doesCollide) {
			float r = 1.001f;
			if (x>oldx)  {
				if (!world.isWalkable(x+size, oldy-size)||!world.isWalkable(x+size, oldy+size/2)) {
					x = (float) (Math.floor(x+size)-size*r);
					speedx = 0;
				}
			} else if (x<oldx) {
				if (!world.isWalkable(x-size, oldy-size)||!world.isWalkable(x-size, oldy+size/2)) {
					x = (float) (Math.ceil(x-size)+size*r);
					speedx = 0;
				}
			}
	
			if (y>oldy)  {
				if (!world.isWalkable(oldx+size, y+size/2)||!world.isWalkable(oldx-size, y+size/2)) {
					y = (float) (Math.floor(y+size/2)-size/2*r);
					speedy = 0;
				}
			} else if (y<oldy) {
				if (!world.isWalkable(oldx+size, y-size)||!world.isWalkable(oldx-size, y-size)) {
					y = (float) (Math.ceil(y-size)+size*r);
					speedy = 0;
				}
			}
		}

		float dy = y-oldy;
		
	}

	public void render() {
		
	}
	
	public float getDist(Entity entity) {
		float dx = this.getX()-entity.getX();
		float dy = this.getY()-entity.getY();
		return (float) Math.sqrt(dx*dx+dy*dy);
	}
	
	public boolean doesCollide() {
		return doesCollide;
	}
	
	public void collideWith(Entity entity) {
		
	}

	public Rectangle getSpriteShape() {
		return new Rectangle(x-getWidth()/2, y-getHeight(), getWidth(), getHeight());
	}

	public Shape getShape() {
		return new Rectangle(x-size, y+size, size*2, size*2);
	}
}
