package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.world.World;

public abstract class Component {
	public Entity owner;
	public boolean enabled;

	public abstract void update(World w, long delta);
	
	public Component(Entity owner, float priority){
		owner.addComponent(this);
		this.owner = owner;
		enabled = true;
	}
	
}
