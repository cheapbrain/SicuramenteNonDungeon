package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.items.Trinket;
import happypotatoes.slickgame.world.World;

public class TrinketComponent extends ItemComponent {

	public TrinketComponent(Entity owner, float priority, Trinket trinket) {
		super(owner, priority, trinket);
	}

	@Override
	public void update(World w, long delta) {
		

	}

}
