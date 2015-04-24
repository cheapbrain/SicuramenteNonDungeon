package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.world.World;

public class AIPet extends AI{
	Entity focus=null;
	
	public AIPet(Entity owner, float priority, Walker walker,
			Movement movement, float speed) {
		super(owner, priority, walker, movement, speed);
		
	}
	
	@Override
	public void update(World w, long delta) {
		if(focus==null) focus = EntitySystem.getInstance().getEntities(PlayerInput.class).get(0);
		time+=delta;
		if(time>=100){
			followPlayer();	
			time=0;			
		}
	}
	
	public void followPlayer(){
		float destx=owner.x, desty=owner.y;
		if(Math.abs(focus.x-owner.x)>2) {
			destx=focus.x;
			if (walker.state==0)
				walker.state = 1;
		}
		if(Math.abs(focus.y-owner.y)>2) {
			desty=focus.y;
			if (walker.state==0)
				walker.state = 1;
		}	
		goTo(destx,desty);
		
	}
	
	public void goTo(float destx, float desty){
		if(walker.state==1) {
			float dx = destx-owner.x;
			float dy = desty-owner.y;
			float d = (float)Math.sqrt(dx*dx+dy*dy);
			float msx = 0;
			float msy = 0;
			if (d<.1) {
				walker.state=0;
				movement.speedx = 0;
				movement.speedy = 0;
			} else {
				msx = dx/d*speed;
				msy = dy/d*speed;
				movement.speedx += msx;
				movement.speedy += msy;
			}
			if (msx!=0||msy!=0)
				walker.setFacing(msx, msy);
		}
	}
	

}
