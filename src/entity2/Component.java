package entity2;

import happypotatoes.slickgame.world.World;

public abstract class Component {
	public float priority;
	
	public abstract void update(World w, long delta);
	
	public Component(Entity owner, float priority){
		this.priority = priority;
	}
	
}
