package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.LightingBello;
import happypotatoes.slickgame.LightingBrutto;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.geom.Rectangle;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EntityRenderer {
	private static List<RenderComponent> tasks = new LinkedList<RenderComponent>();
	private static Image select;
	public static Entity click = null, hover = null;
	
	public static void init() {
		try {
			select = new Image("res/select.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
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
		LightingBello light = LightingBello.lighting;
		Rectangle rect = Camera.camera.getRekt();
		int sx = (int)rect.x0;
		int sy = (int)rect.y0;
		if (sx<0) sx = 0;
		if (sy<0) sy = 0;
		

		float i = 0;
		
		if (hover!=null&&hover!=click) {
			i = light.getColorAt(hover.x, hover.y);
			select.draw(hover.x-.5f, hover.y-.25f, 1, .5f, new Color(1, 0, 0.7f, i));
		}
		
		if (click!=null) {
			i = light.getColorAt(click.x, click.y);
			select.draw(click.x-.5f, click.y-.25f, 1, .5f, new Color(0, 1, 0.7f, i));
		}
		
		for (RenderComponent task : tasks) {
			
			
			Entity e = task.owner;

			i = light.getColorAt(e.x, e.y);
			task.render(i);
		}
	}
	
	public static void clear() {
		tasks.clear();
	}
}
