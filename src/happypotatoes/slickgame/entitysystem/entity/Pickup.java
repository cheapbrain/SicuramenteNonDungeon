package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Interact;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.world.World;

public class Pickup extends Interact{
	private int id;
	public Pickup(Entity owner, float priority, int i) {
		super(owner, priority);
		this.id = i;
	}
	@Override
	public void update(World w, long delta) {
	}
	@Override
	public void interact(Entity e) {
		Inventory zaino = (Inventory) e.getComponent(Inventory.class);
		if(zaino!=null){
			if(zaino.add(id))
				EntitySystem.getInstance().removeEntity(owner);
		}	
	}

}
