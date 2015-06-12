package happypotatoes.slickgame.items;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;


public class Potion extends Junk{
	public final static int health=0, energy=1;
	private int potionType;
	private float charge;
	
	public Potion(int id, String name, int type, float charge) {
		super(id, name);
		setPotionType(type);
		setCharge(charge);
	}

	public int getPotionType() {
		return potionType;
	}

	public void setPotionType(int potionType) {
		this.potionType = potionType;
	}
	
	@Override
	public boolean use(Entity e) {
		if (potionType==health) {
			Health health = e.getComponent(Health.class);
			if (health!=null) {
				health.heal(getCharge());
			} else 
				return false;
		} else {
			Energy energy = e.getComponent(Energy.class);
			if (energy!=null) {
				energy.charge(getCharge());
			} else 
				return false;
		}
		return true;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

}
