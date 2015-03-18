package entity2;

import happypotatoes.slickgame.world.World;

public class Health extends Component{
	private float health, healthRegeneration;
	public Health(Entity owner, float priority, float health, float healthRegeneration ) {
		super(owner, priority);
		this.setHealth(health);
		this.healthRegeneration=healthRegeneration;
	}
	public void update(World w, long delta) {
		setHealth(getHealth() + healthRegeneration);
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public float getHealthRegeneration() {
		return healthRegeneration;
	}
	public void setHealthRegeneration(float healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}
}
