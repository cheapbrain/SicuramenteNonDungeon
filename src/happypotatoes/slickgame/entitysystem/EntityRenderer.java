package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.LightingBrutto;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.geom.Rectangle;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;


public class EntityRenderer {
	private static List<RenderComponent> tasks = new LinkedList<RenderComponent>();
	public static float selecty;
	public static float selectx;
	
	public static void addRenderTask(RenderComponent task) {
		if (!tasks.isEmpty()) {
			float y = task.depth;
			for (int i=0;i<tasks.size();i++)
				if (tasks.get(i).depth>y) {
					tasks.add(i, task);
					return;
				}
		}
		tasks.add(task);
	}

	public static void render(Graphics g) {
		
		for (RenderComponent task : tasks) {
			float[][] map = LightingBrutto.lighting.lightMap;
			Rectangle rect = Camera.camera.getRekt();
			int sx = (int)rect.x0;
			int sy = (int)rect.y0;
			if (sx<0) sx = 0;
			if (sy<0) sy = 0;
			
			Entity e = task.owner;
			
			float i = 0;
			if (e.x>=sx&&e.x<sx+map.length&&
				e.y>=sy&&e.y<sy+map[0].length)
				i = map[(int)e.x-sx][(int)e.y-sy];
			

			if (task.getRect().contain(selectx, selecty))
				task.renderShadow(i);
			task.render(i);
		}
	}
	
	public static void clear() {
		tasks.clear();
	}
}
