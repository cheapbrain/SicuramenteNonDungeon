package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.world.World;

public class SecondHandComponent extends ItemComponent {

	public SecondHandComponent(Entity owner, float priority, Item item) {
		super(owner, priority, item);
	}

	@Override
	public void update(World w, long delta) {

	}

}
