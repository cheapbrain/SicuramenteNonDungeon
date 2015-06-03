package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.interact.OpenChest;
import happypotatoes.slickgame.world.World;

public class Open extends Component {
	public int animationTime = 0;
	private Walker walker;
	public int animationTotalTime = 0;
	public Open(Entity owner, float priority,Walker walker, WalkerRender walkerRender) {
		super(owner, priority);
		this.walker = walker;
		animationTotalTime = walkerRender.getFrames(1)* walkerRender.getFrameTime();
	}

	@Override
	public void update(World w, long delta) {
		//andrà cambiato con lo state 3
		
		OpenChest o = owner.getComponent(OpenChest.class);
		//controllo allontanamento
		if(o!=null){
			if(o.agent!=null){
				if(o.agent.getDist(owner)>2f){
					o.closeView();
					return;
				}
			}
		}
		
		if((walker.getState()==1)||(walker.getState()==3)){
			animationTime+=delta;	
				
			//animazione
			if(animationTime>=animationTotalTime){
				//controllo aperture/chiusure
				if(walker.getState()==1){
					walker.setState(2);
				}
				if(walker.getState()==3){
					walker.setState(0);
				}
				animationTime=0;
			} else{
			}
		}
		else animationTime=0;
	}

}
