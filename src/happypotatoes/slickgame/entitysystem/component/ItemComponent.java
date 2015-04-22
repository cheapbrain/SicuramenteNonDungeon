package happypotatoes.slickgame.entitysystem.component;

import java.io.File;
import java.util.Properties;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;
import happypotatoes.slickgame.world.World;

public class ItemComponent extends Component{
	private String name, descBase, descAvanz;
	private int maxStack, value;
	public ItemComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority);
		value = Integer.parseInt(info.get("Value"));
		descBase = info.get("BasicDescription");
		descAvanz = info.get("AdvancedDescription");
		maxStack = Integer.parseInt(info.get("MaxStack"));
		//name = path.split("/")[4].split("\\.")[0];
	}
	public void update(World w, long delta) {
	}
}
