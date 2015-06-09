package happypotatoes.slickgame.geom;

import org.newdawn.slick.Graphics;

public class Rectangle {
	public float x0, y0, x1, y1, w, h;
	
	public Rectangle(){
		
	}
	public Rectangle(float x, float y, float width, float height) {
		w = width;
		h = height;
		x0 = x;
		y0 = y;
		x1 = x0+w;
		y1 = y0+h;
	}
	
	public void setPosition(float x, float y) {
		x0 = x;
		y0 = y;
		x1 = x0+w;
		y1 = y0+h;
	}
	
	public void setSize(float width, float height) {
		w = width;
		h = height;
		x1 = x0+w;
		y1 = y0+h;
	}
	
	public boolean intersect(Rectangle rect) {
		return
			x0<=rect.x1&&
			x1>=rect.x0&&
			y0<=rect.y1&&
			y1>=rect.y0;
	}
	
	public boolean contain(Rectangle rect) {
		return
			x0<=rect.x0&&
			x1>=rect.x1&&
			y0<=rect.y0&&
			y1>=rect.y1;
	}
	
	public void render(Graphics g) {
		g.drawRect(x0, y0, w, h);
	}

	public boolean contain(float x, float y) {
		return
				x0<=x&&
				x1>=x&&
				y0<=y&&
				y1>=y;
	}

	public void move(float x, float y) {
		setPosition(x+x0, y+y0);
	}
}
