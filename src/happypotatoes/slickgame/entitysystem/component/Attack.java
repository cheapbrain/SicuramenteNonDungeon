package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
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
				Health EnemyHp = ((Health) w.getNearest(owner).getComponent(Health.class));
				EnemyHp.setHealth(EnemyHp.getHealth()-damage);
				walker.state=0;
				timeAttack=0;
			} else{
				timeAttack+=delta;
			}
		}
	}
}
