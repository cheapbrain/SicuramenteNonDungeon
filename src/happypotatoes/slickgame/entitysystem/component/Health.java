package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Health extends Component{
	private float health, healthRegeneration, maxHealth;
	private int timer;
	public Health(Entity owner, float priority, float health, float healthRegeneration ) {
		super(owner, priority);
		maxHealth = health;
		this.setHealth(health);
		this.healthRegeneration=healthRegeneration;
	}
	public void update(World w, long delta) {
		if(owner.getComponent(Walker.class).getState()==0){
			timer += delta;
			if(timer>2000){
				if(getHealth()+getHealthRegeneration()*delta/1000f<=maxHealth){
				setHealth(getHealth()+getHealthRegeneration()*delta/1000f);
				}
				else setHealth(maxHealth);
			}
		}
		else timer=0;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		if(health<this.health) timer=0;
		if(health>0) this.health = health;
		else{
			this.health = 0;
			owner.getComponent(Walker.class).setDead();
		}
	}
	public float getHealthRegeneration() {
		return healthRegeneration;
	}
	public void setHealthRegeneration(float healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}
	public float getMaxHealth() {
		return maxHealth;
	}
}
