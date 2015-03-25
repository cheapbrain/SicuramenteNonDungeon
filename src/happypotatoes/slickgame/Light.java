package happypotatoes.slickgame;

import entity2.Entity;


public class Light {
	Entity e = null;
	float x, y;
	public float r;
	public float i;
	
	public Light(Entity e,float x, float y, float r, float i) {
		this.e = e;
		this.r = r;
		this.i = i;
	}
	
	public Light(float x, float y, float r, float i) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.i = i;
	}
	
	public float getX() {
		return (e!=null?e.x:0)+x;
	}
	
	public float getY() {
		return (e!=null?e.y:0)+y;
	}
}
