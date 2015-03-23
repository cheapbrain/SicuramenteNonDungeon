package happypotatoes.slickgame;

import entity2.Entity;


public class Light {
	Entity e = null;
	float x, y;
	float r;
	
	public Light(Entity e, float r) {
		this.e = e;
		this.r = r;
	}
	
	public Light(float x, float y, float r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public float getX() {
		return e!=null?e.x:x;
	}
	
	public float getY() {
		return e!=null?e.y:y;
	}
	
	public float getR() {
		return r;
	}
}
