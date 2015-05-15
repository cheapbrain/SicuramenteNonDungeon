package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class StupidInput extends Component{
	Walker walker;
	int attTimer = 0;
	public StupidInput(Entity owner, float priority, Walker walker) {
		super(owner, priority);
		this.walker = walker;
	}
	public void update(World w, long delta) {
		attTimer +=delta;
		if(attTimer>5000){
			attTimer=0;
			walker.setFacing(1,0);
			walker.setWalking();
		}
	}

}
