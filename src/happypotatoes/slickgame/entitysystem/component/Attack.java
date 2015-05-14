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
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float damage) {
		super(owner, priority);
		this.walker = walker;
		this.damage = damage;
		animationTotalTime = walkerRender.getFrames(2)* walkerRender.getFrameTime();
	}
	
	public void attack(Entity focus) {
		walker.state = 2;
		this.focus = focus;
	}
	
	public void update(World w, long delta) {
		if(walker.state==2){
			animationTime+=delta;
			if(animationTime>=animationTotalTime){
				if (owner.getComponent(AI.class)!=null)
					focus = owner.getComponent(AI.class).focus;
				Health EnemyHp = ((Health) focus.getComponent(Health.class));
				if(focus.getComponent(Defend.class)!=null)
						EnemyHp.setHealth(EnemyHp.getHealth()-damage*(1f-((Defend)focus.getComponent(Defend.class)).mitigation));
					else EnemyHp.setHealth(EnemyHp.getHealth()-damage);
				walker.state=0;
				animationTime=0;
			} else{
			}
		}
		else animationTime=0;
	}
	
	public boolean isAttacking(){
		if(walker.state==2&&animationTime>0) return true;
		else return false;
	}
}
