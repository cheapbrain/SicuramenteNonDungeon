package happypotatoes.slickgame;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import happypotatoes.slickgame.entity.Entity;

public class Light {
	private Entity target = null;
	private float dx, dy;
	private float size;
	
	private List<Vector2f> lines = new ArrayList<Vector2f>();
	
	public Light(Entity entity, float dx, float dy, float size) {
		this.target = entity;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
	}
	
	public void render(Graphics g) {
		if (target!=null) {
			dx = target.getX();
			dy = target.getY();
		}
		
		int[][] terr = Lighting.terrain;
		lines.clear();
		
		int sx = ((int)(dx-size)/2)*2+1;
		if (sx<1) sx = 1;
		
		int ex = ((int)(dx+size)/2)*2+1;
		if (ex>=terr.length) ex = terr.length-2;
		
		int sy = ((int)(dy-size)/2)*2+1;
		if (sy<1) sy = 1;
		
		int ey = ((int)(dy+size)/2)*2+1;
		if (ey>=terr.length) ey = terr.length-2;
		
		for (int y=sy;y<ey;y+=2) {
			for (int x=sx;x<ex;x+=2) {
				int t = 0;
				t += terr[x][y]*3;
				t += terr[x+1][y];
				t += terr[x+1][y+1]*3;
				t += terr[x][y+1];
				if (t!=0&&t!=8&&t!=4)
					lines.add(new Vector2f(x+1, y+1));
			}
		}
		g.setLineWidth(4);
		for (Vector2f v:lines)
			g.drawLine(dx, dy, v.x, v.y);
		
		
	}
	

}
