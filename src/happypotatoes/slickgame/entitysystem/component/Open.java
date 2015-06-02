package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.interact.OpenChest;
import happypotatoes.slickgame.world.World;

public class Open extends Component {
	public int animationTime = 0;
	private Walker walker;
	public int animationTotalTime = 0;
	public Entity agent;
	public Open(Entity owner, float priority,Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(1)* walkerRender.getFrameTime();
	}

	@Override
	public void update(World w, long delta) {
		//andrà cambiato con lo state 3
		
		if(walker.getState()==1){
			animationTime+=delta;
			
			OpenChest o = owner.getComponent(OpenChest.class);
			//controllo allontanamento
			if(o!=null)
				if(o.agent!=null)
					if(o.agent.getDist(owner)>2f){
						o.closeView();
						return;
					}
			
			//animazione
			if(animationTime>=animationTotalTime){
				if(o!=null){
					//controllo aperture/chiusure
					if(o.opening){
						walker.setState(2);
						o.opening=false;
					}
					if(o.closing){
						walker.setState(0);
						o.closing=false;						
					}
					
				}
				animationTime=0;
			} else{
			}
		}
		else animationTime=0;
	}

}
