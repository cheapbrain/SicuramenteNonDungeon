package entity2;

import happypotatoes.slickgame.world.World;

public class Walker extends Component{
	public int directions = 4;
	public int facing = 0;
	
	public Walker(Entity owner, float priority) {
		super(owner, priority);
		
	}

	@Override
	public void update(World w, long delta) {
		
	}

}
