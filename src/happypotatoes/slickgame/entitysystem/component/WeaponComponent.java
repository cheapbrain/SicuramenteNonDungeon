package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;

import java.util.Properties;

public class WeaponComponent extends ItemComponent{
	private int damage, weight;
	public WeaponComponent(Entity owner, float priority, ItemInfo info) {
		super(owner, priority, info);
		setDamage(Integer.parseInt(info.get("Damage")));
		setWeight(Integer.parseInt(info.get("Weight")));
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

}
