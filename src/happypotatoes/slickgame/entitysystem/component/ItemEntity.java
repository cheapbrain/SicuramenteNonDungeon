package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class ItemEntity extends Interact{
	private String id;
	public ItemEntity(Entity owner, float priority, String id) {
		super(owner, priority);
		this.id = id;
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
