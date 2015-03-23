package entity2;

import happypotatoes.slickgame.world.World;

public class Walker extends Component{
	public int directions;
	public int states;
	
	public int facing = 0;
	public int state = 0;
	
	public Walker(Entity owner, float priority, int directions, int states) {
		super(owner, priority);
		this.directions = directions;
		this.states = states;
	}

	@Override
	public void update(World w, long delta) {
		
	}
	
	public void setFacing(float dx, float dy) {
		float angle = (float)(Math.atan2(dx, dy)/Math.PI/2+1);
		facing = (int)(angle*directions+4)%directions;
	}

}
