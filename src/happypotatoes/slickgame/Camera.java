package happypotatoes.slickgame;

import happypotatoes.slickgame.entity.Entity;

public class Camera {
	private float x, y;
	private float x1, y1;
	private float x2, y2;
	private float hw, hh;
	private float tx, ty;
	private int unit;
	
	private Entity target;
	private float speed = .2f;
	private float viewAngle = .4f;
	
	public Camera(int width, int height, int unit, Entity target) {
		this.target = target;
		hw = width/2f/unit;
		hh = height/2f/unit;
		this.unit = unit;
	}
	
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public void setTarget(float x, float y) {
		tx = x;
		ty = y;
		target = null;
	}
	
	public void update(int delta) {
		if (target!=null) { 
			tx = target.getX();
			ty = target.getY();
		}
		
		x += (tx-x)*delta/speed/1000;
		y += (ty-y)*delta/speed/1000;
		
		x1 = x-hw;
		x2 = x+hw;
		
		y1 = y-hh;
		y2 = y+hh;
	}
	
	public boolean isInside(float x, float y) {
		return (x>=x1&&x<=x2&&
				y>=y1&&y<=y2);
	}
	
	public float getViewAngle() {
		return viewAngle;
	}
	
	public float getX1() {
		return x1;
	}
	
	public float getY1() {
		return y1;
	}

	public float getX2() {
		return x2;
	}
	
	public float getY2() {
		return y2;
	}
	
	public float getXC() {
		return x;
	}
	
	public float getYC() {
		return y;
	}
	
	public int getUnit() {
		return unit;
	}
}
