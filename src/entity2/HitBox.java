package entity2;

import happypotatoes.slickgame.world.World;

public class HitBox extends Component {
	public float sizex, sizey;
	
	public HitBox(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void update(World w, long delta) {
		
	}
}
