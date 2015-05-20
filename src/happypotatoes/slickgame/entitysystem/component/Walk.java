package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Walk extends Component{
	public float animationTime = 0;
	private Walker walker;
	public float animationTotalTime;
	public float consumeSecond=2f;
	public float dx;
	public float dy;
	public float d;
	
	public Walk(Entity owner, float priority, Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(1)* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		
		if(walker.getState()==1){
			Energy thisEnergy=owner.getComponent(Energy.class);
			if(thisEnergy.getEnergy()<consumeSecond){
				walker.setStill();
				return;
			}
			if(thisEnergy!=null)
				thisEnergy.setEnergy(thisEnergy.getEnergy()-(consumeSecond/1000*delta));
			AI ai = owner.getComponent(AI.class);
			if(ai!=null)
				ai.goTo(dx,dy,d);
			PlayerInput pi = owner.getComponent(PlayerInput.class);
			if(pi!=null)
				pi.goTo(dx,dy,d);
		}
		
	}
	
	
	public boolean isWalking(){
		if(walker.getState()==0) return true; 
		else return false;
	}
}
