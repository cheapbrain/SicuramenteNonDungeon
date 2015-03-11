package entity2;

import happypotatoes.slickgame.world.World;

public class Movement extends Component {
	public float speedx, speedy;
	public float oldx, oldy;
	
	public Movement(Entity owner, float priority) {
		super(owner, priority);

	}


	@Override
	public void update(World w, long delta) {		
		oldx = owner.x;
		oldy = owner.y;
		
		owner.x += speedx*delta;
		owner.y += speedy*delta;
	}
}
