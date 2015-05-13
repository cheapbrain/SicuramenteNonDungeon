package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Defend extends Component{
	public float animationTime = 0;
	private Walker walker;
	public float animationTotalTime;
	public float mitigation; //da 0 a 1
	
	public Defend(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(4)* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		if(walker.state==4){
			if(animationTime>animationTotalTime){
				mitigation = 0.99f;
			} else{
				animationTime+=delta;	
			}
		}
		else{
			animationTime=0;
			mitigation = 0f;
		}
		
	}
	
	public boolean isDefending(){
		if(animationTime>animationTotalTime) return true; 
		else return false;
	}
}
