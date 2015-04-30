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
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float damage) {
		super(owner, priority);
		this.walker = walker;
		this.damage = damage;
		totalTimeAttack = walkerRender.getFrames()* walkerRender.getFrameTime();
	}
	public void update(World w, long delta) {
		if(walker.state==2){
			if(timeAttack>totalTimeAttack){
				Entity focus;
				if (owner.getComponent(AI.class)!=null)
					focus = owner.getComponent(AI.class).focus;
				else {
					List<Entity> l = EntitySystem.getInstance().getAll();
					focus = l.get((new Random()).nextInt(l.size()-2)+2);
				}
				Health EnemyHp = ((Health) focus.getComponent(Health.class));
				EnemyHp.setHealth(EnemyHp.getHealth()-damage);
				walker.state=0;
				timeAttack=0;
			} else{
				timeAttack+=delta;
			}
		}
	}
}
