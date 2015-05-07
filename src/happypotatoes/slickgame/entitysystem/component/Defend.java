package happypotatoes.slickgame.entitysystem.component;

import java.util.List;
import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class Defend extends Component{
	private float animationTime = 0;
	private Walker walker;
	private float animationTotalTime;
	public float mitigation; //da 0 a 1
	
	public Defend(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(4)* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		if(walker.state==4||walker.state==5){
			if(animationTime>animationTotalTime){
				mitigation = 0.99f;
				walker.state = 5;
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
