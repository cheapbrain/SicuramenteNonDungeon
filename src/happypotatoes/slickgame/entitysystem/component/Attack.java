package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Attack extends Component{
	private int timeAttack = 0;
	private Walker walker;
	private int totalTimeAttack = 0;
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		totalTimeAttack = walkerRender.getFrames()* walkerRender.getFrameTime();
	}
	public void update(World w, long delta) {
		if(walker.state==1){
			if(timeAttack>totalTimeAttack){
				walker.state=0;
				timeAttack=0;
			} else{
				timeAttack+=delta;
			}
		}
	}
}
