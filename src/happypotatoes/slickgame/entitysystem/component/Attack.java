package happypotatoes.slickgame.entitysystem.component;

import java.util.List;
import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class Attack extends Component{
	public int animationTime = 0;
	private Walker walker;
	public int animationTotalTime = 0;
	private float damage;
	private Entity focus;
	private float consume = 5f;
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float damage) {
		super(owner, priority);
		this.walker = walker;
		this.damage = damage;
		animationTotalTime = walkerRender.getFrames(2)* walkerRender.getFrameTime();
	}
	
	public void attack(Entity focus) {
		walker.setAttacking();
		this.focus = focus;
	}
	
	public void update(World w, long delta) {
		if(walker.getState()==2){
			animationTime+=delta;
			if(animationTime>=animationTotalTime){
				if (owner.getComponent(AI.class)!=null)
					focus = owner.getComponent(AI.class).focus;
				if(focus!=null)
					owner.getComponent(Walker.class).setFacing(focus.x-owner.x, focus.y-owner.y);
				Energy thisEnergy = ((Energy) owner.getComponent(Energy.class));
				if(thisEnergy!=null)
					thisEnergy.setEnergy(thisEnergy.getEnergy()-consume );
				Health EnemyHp = ((Health) focus.getComponent(Health.class));
				if(focus.getComponent(Defend.class)!=null)
						EnemyHp.setHealth(EnemyHp.getHealth()-damage*(1f-((Defend)focus.getComponent(Defend.class)).mitigation));
					else EnemyHp.setHealth(EnemyHp.getHealth()-damage);
				walker.setStill();
				animationTime=0;
			} else{
			}
		}
		else animationTime=0;
	}
	
	public boolean isAttacking(){
		if(walker.getState()==2&&animationTime>0) return true;
		else return false;
	}
}
