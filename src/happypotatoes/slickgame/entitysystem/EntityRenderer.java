package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.LightingBello;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EntityRenderer {
	private static List<RenderComponent> tasks = new ArrayList<RenderComponent>();
	private static Image select;
	public static Entity click = null, hover = null;
	public static Graphics g;
	
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
	
	public static void renderTask(RenderComponent task) {
		LightingBello light = LightingBello.lighting;
		Entity e = task.owner;
		float i = light.getColorAt(e.x, e.y);
		if (e==click) {
			select.draw(click.x-.5f, click.y-.25f, 1, .5f, new Color(0, 1, 0.7f, i));
		} else if (e==hover) {
			select.draw(hover.x-.5f, hover.y-.25f, 1, .5f, new Color(1, 0, 0.7f, i));
		}
		task.render(i);
	}
	
	public static List<RenderComponent> getTaskes(Graphics g){
		EntityRenderer.g = g;
		return tasks;
	}
	
	public static void clear() {
		tasks.clear();
	}
}
