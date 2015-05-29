package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.items.Helm;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.world.World;

public class HelmComponent extends ItemComponent {

	public HelmComponent(Entity owner, float priority, Helm helm) {
		super(owner, priority, helm);
	}
	
	public HelmComponent(Entity owner, float priority, int id) {
		super(owner, priority, ItemList.getItemForId(id));
	}

	@Override
	public void update(World w, long delta) {
		
	}

}
