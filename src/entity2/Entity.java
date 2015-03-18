package entity2;

import happypotatoes.slickgame.world.World;

import java.util.ArrayList;

public final class Entity {
	public long id;
	public float x,y;	
	ArrayList<Component> components;
	
	public Component getComponent(Class<?> c){
		for(Component component:components)
			if (component.getClass()==c)
				return component;
		return null;
	}
	
	public void addComponent(Component c){
		if(!components.isEmpty()) {
			for(int i=0;i<components.size();i++)
				if (c.priority<components.get(i).priority) {
					components.add(i, c);
					return;
				}
		}
		components.add(c);
	}
	
	public void removeComponent(Component c){
		components.remove(c);
	}
	
	public void update(World w, long delta){
		for (Component component : components)
			if (component.enabled)
				component.update(w, delta);
	}

	public Entity(long id){
		this.id = id;
	}
}
