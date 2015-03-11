package entity2;

import happypotatoes.slickgame.world.World;

import java.util.ArrayList;

public class Entity {
	public int id;
	public float x,y;	
	public boolean enabled;
	ArrayList<Component> components;
	
	public Component getComponent(Class<Component> c){
		for(int i=0; i<components.size(); i++) 
			if(components.get(i).getClass()==c) return components.get(i);
		return null;
	}
	
	public void addComponent(Component c){
		components.add(c);
	}
	
	public void removeComponent(Class<Component> c){
	for(int i=0; i<components.size(); i++) 
		if(components.get(i).getClass()==c) components.remove(i);
	}
	
	public void update(World w, long delta){
		//todo
	}

	public Entity(long id){
		//todo
	}
}
