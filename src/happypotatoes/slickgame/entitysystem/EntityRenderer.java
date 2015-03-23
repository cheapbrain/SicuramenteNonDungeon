package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.entitysystem.component.RenderComponent;

import java.util.LinkedList;
import java.util.List;


public class EntityRenderer {
	private static List<RenderComponent> tasks = new LinkedList<RenderComponent>();
	
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

	public static void render() {
		for (RenderComponent task : tasks)
			task.render();
		
		tasks.clear();
	}
	
}
