package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.LightingBello;
import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

import java.util.ArrayList;
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
			select = Loader.image("res/select.png");
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
		
		for (RenderComponent task : tasks) {
			Entity e = task.owner;

			i = light.getColorAt(e.x, e.y);
			task.render(i);
		}
	}
	
	static Color scolor = new Color(0);
	static Color hcolor = new Color(0);
	
	public static void renderTask(RenderComponent task) {
		LightingBello light = LightingBello.lighting;
		Entity e = task.owner;
		float i = light.getColorAt(e.x, e.y);
		if (task instanceof WalkerRender) {
			if (e==click) {
				scolor.r = 0.0f*i;
				scolor.g = 1.0f*i;
				scolor.b = 0.7f*i;
				scolor.a = 0.7f;
				select.draw(click.x-.5f, click.y-.25f, 1, .5f, scolor);
			} else if (e==hover) {
				hcolor.r = 1.0f*i;
				hcolor.g = 0.0f*i;
				hcolor.b = 0.7f*i;
				hcolor.a = 0.7f;
				select.draw(hover.x-.5f, hover.y-.25f, 1, .5f, hcolor);
			}
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
