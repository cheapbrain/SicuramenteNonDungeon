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
	public Defend(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames()* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		if(walker.state==4){
			if(animationTime>animationTotalTime){
				//setta la roba per difendersi
			} else{
				animationTime+=delta;
			}
		}
		else{
			animationTime=0;
		}
	}
	
	public boolean isDefending(){
		if(animationTime>animationTotalTime) return true; 
		else return false;
	}
}
