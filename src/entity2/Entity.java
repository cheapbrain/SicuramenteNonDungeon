package entity2;

import happypotatoes.slickgame.world.World;

import java.util.ArrayList;

public final class Entity {
	public long id;
	public float x,y;	
	public boolean enabled;
	ArrayList<Component> components;
	
	public Component getComponent(Class<?> c){
		for(int i=0; i<components.size(); i++) 
			if(components.get(i).getClass()==c) return components.get(i);
		return null;
	}
	
	public void addComponent(Component c){
		components.add(c);
	}
	
	public void removeComponent(Class<?> c){
	for(int i=0; i<components.size(); i++) 
		if(components.get(i).getClass()==c) components.remove(i);
	}
	
	public void update(World w, long delta){
		
	}

	public Entity(long id){
		this.id = id;
	}
}
