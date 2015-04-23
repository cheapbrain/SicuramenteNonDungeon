package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.world.World;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public final class Entity {
	public long id;
	public float x,y;
	private String name = null;
	private List<Component> components = new LinkedList<Component>();
	private LinkedBlockingQueue<Component> toAdd = new LinkedBlockingQueue<Component>();
	private LinkedBlockingQueue<Component> toRemove = new LinkedBlockingQueue<Component>();
	
	public <T extends Component> T getComponent(Class<T> c){
		for(Component component:components)
			if (component.getClass()==c)
				return (T) component;
		return null;
	}
	
	public float getDist(Entity entity) {
		float dx = this.x-entity.x;
		float dy = this.y-entity.y;
		return (float) Math.sqrt(dx*dx+dy*dy);
	}
	
	public void addComponent(Component c){
		toAdd.add(c);
	}
	
	public void removeComponent(Component c){
		toRemove.add(c);
	}
	
	public void update(World w, long delta){
		while (!toAdd.isEmpty())
			components.add(toAdd.poll());
		
		while (!toRemove.isEmpty())
			components.remove(toAdd.poll());
		
		for (Component component : components)
			if (component.enabled)
				component.update(w, delta);
	}

	public Entity(long id, String nome){
		this.id = id;
		name = nome;
	}
	
	public String getName() {
		return name;
	}
}
