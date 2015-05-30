package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.world.World;

public class ItemComponent extends Component{
	private String name, description;
	private int maxStack;
	public ItemComponent(Entity owner, float priority, Item item) {
		super(owner, priority);
		name = item.getName();
		maxStack = item.getMaxStack();
		description = item.getDescription();
	}
	public void update(World w, long delta) {
	}
}
