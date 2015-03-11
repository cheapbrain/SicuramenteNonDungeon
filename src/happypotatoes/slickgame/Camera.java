package happypotatoes.slickgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import happypotatoes.slickgame.entity.Entity;

public class Camera {
	public static Camera camera;
	private float w, h;
	private float x, y;
	private float x1, y1;
	private float x2, y2;
	private float hw, hh;
	private float tx, ty;
	private float unit;
	
	private Entity target;
	private float speed = .2f;
	private float viewAngle = 1f;
	
	public Camera(int width, int height, int unit, Entity target) {
		camera = this;
		this.target = target;
		w = (float)width/unit;
		h = (float)height/unit;
		hw = w/2;
		hh = h/2;
		this.unit = unit;
	}
	
	public void applyTrasform(Graphics g) {
		float unit = camera.getUnit();
		float cx = (int)(camera.getX1()*unit)/unit;
		float cy = (int)(camera.getY1()*unit)/unit;

		g.scale(unit, unit);
		g.translate(-cx, -cy);
	}
	
	public void scale(float k) {
		unit *= k;
		w /= k;
		h /= k;
		hh = h/2;
		hw = w/2;
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
	
	public Rectangle getRekt() {
		return new Rectangle(x1, y1, w, h);
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
		return Math.round(unit);
	}
}
