package happypotatoes.slickgame.entitysystem.component;

import java.util.ArrayList;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Inventory extends Component{
	private ArrayList<Object> items = new ArrayList<Object>();
	public Inventory(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {
		
	}

}
