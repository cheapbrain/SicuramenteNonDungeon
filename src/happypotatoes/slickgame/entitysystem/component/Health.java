package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.StupidEntity;
import happypotatoes.slickgame.gui.component.HealthBar;
import happypotatoes.slickgame.world.World;

public class Health extends Component{
	private float health, healthRegeneration, maxHealth;
	private int timer;
	private boolean death;
	public Health(Entity owner, float priority, float health, float healthRegeneration ) {
		super(owner, priority);
		maxHealth = health;
		this.setHealth(health);
		this.healthRegeneration=healthRegeneration;
	}
	public void update(World w, long delta) {
		if(death) w.remove(owner);
		timer += delta;
		if(timer>1000 && health+healthRegeneration<=maxHealth){
			timer=0;
			setHealth(getHealth() + healthRegeneration);
		}
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		if(health>0) this.health = health;
		else death = true;
		//System.out.println(this.health+"  "+owner.getName());
	}
	public float getHealthRegeneration() {
		return healthRegeneration;
	}
	public void setHealthRegeneration(float healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}
}
