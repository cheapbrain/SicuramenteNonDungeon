package happypotatoes.slickgame;

import org.newdawn.slick.Graphics;

import entity2.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class Camera {
	public static Camera camera;
	private Rectangle rect;
	private int unit;
	
	private Entity target;
	private float tx, ty;
	private float cx, cy;
	
	private float speed = .2f;
	
	public Camera(int width, int height, int unit, Entity target) {
		rect = new Rectangle(0, 0, (float)width/unit, (float)height/unit);
		this.target = target;
		this.unit = unit;
		camera = this;
	}
	
	public void applyTrasform(Graphics g) {
		float unit = camera.getUnit();
		float cx = (int)(rect.x0*unit)/unit;
		float cy = (int)(rect.y0*unit)/unit;

		g.scale(unit, unit);
		g.translate(-cx, -cy);
	}
	
	public void setTarget(Entity target) {
		this.target = target;
	}
	
	public void setTarget(float x, float y) {
		tx = x;
		ty = y;
		target = null;
	}
	
	public void goAtTarget() {
		if (target!=null) { 
			tx = target.x;
			ty = target.y;
		}
		cx = tx;
		cy = ty;
	}
	
	public void update(int delta) {
		if (target!=null) { 
			tx = target.x;
			ty = target.y;
		}
		
		cx += (tx-cx)*delta/speed/1000;
		cy += (ty-cy)*delta/speed/1000;
		
		rect.setPosition(cx-rect.w/2, cy-rect.h/2);
	}
	
	public Rectangle getRekt() {
		return rect;
	}
	
	public float getXC() {
		return cx;
	}
	
	public float getYC() {
		return cy;
	}
	
	public int getUnit() {
		return unit;
	}
}
