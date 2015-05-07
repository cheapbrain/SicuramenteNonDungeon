package happypotatoes.slickgame.entitysystem.component;

import java.util.List;
import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class Attack extends Component{
	private int timeAttack = 0;
	private Walker walker;
	private int totalTimeAttack = 0;
	private float damage;
	private Entity focus;
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float damage) {
		super(owner, priority);
		this.walker = walker;
		this.damage = damage;
		totalTimeAttack = walkerRender.getFrames(2)* walkerRender.getFrameTime();
	}
	
	public void attack(Entity focus) {
		walker.state = 2;
		this.focus = focus;
	}
	
	public void update(World w, long delta) {
		if(walker.state==2){
			timeAttack+=delta;
			if(timeAttack>=totalTimeAttack){
				if (owner.getComponent(AI.class)!=null)
					focus = owner.getComponent(AI.class).focus;
				Health EnemyHp = ((Health) focus.getComponent(Health.class));
				EnemyHp.setHealth(EnemyHp.getHealth()-damage);
				walker.state=0;
				timeAttack=0;
			} else{
			}
		}
	}
}
