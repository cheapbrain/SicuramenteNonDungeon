package entity2;

import happypotatoes.slickgame.world.World;

public class Trap extends Component {
	public int type; //steam, magic, natural

	public Trap(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {

	}

}
