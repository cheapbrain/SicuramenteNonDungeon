package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Defend extends Component{
	public float animationTime = 0;
	private Walker walker;
	public float animationTotalTime;
	public float mitigation; //da 0 a 1
	public float consumeSecond=20f;
	
	public Defend(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(4)* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		if(walker.getState()==4){
			Energy thisEnergy = ((Energy) owner.getComponent(Energy.class));
			if(animationTime>animationTotalTime){
				mitigation = 0.99f;
				if(thisEnergy!=null)
					thisEnergy.setEnergy(thisEnergy.getEnergy()-(consumeSecond/1000*delta));
			} else{
				animationTime+=delta;	
			}
		}
		else{
			animationTime=0;
			mitigation = 0f;
		}
		
	}
	
	public boolean canDefend(long delta){
		return false;
	}
	
	public boolean isDefending(){
		if(animationTime>animationTotalTime) return true; 
		else return false;
	}
}
