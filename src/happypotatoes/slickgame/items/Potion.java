package happypotatoes.slickgame.items;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;


public class Potion extends Junk{
	public final static int health=0, energy=1;
	private int potionType;
	
	public Potion(int id, String name, int type) {
		super(id, name);
		setPotionType(type);
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
				health.heal(200);
			} else 
				return false;
		} else {
			Energy energy = e.getComponent(Energy.class);
			if (energy!=null) {
				energy.charge(200);
			} else 
				return false;
		}
		return true;
	}

}
