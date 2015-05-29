package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.entitysystem.entity.ItemInfo;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.Weapon;

import java.util.Properties;

public class WeaponComponent extends ItemComponent{
	private float damage, weight;
	public Weapon weapon;
	public WeaponComponent(Entity owner, float priority, Weapon weapon) {
		super(owner, priority, weapon);
		this.weapon=weapon;
		setDamage(weapon.getDamage());
		setWeight(weapon.getWeight());
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float f) {
		this.weight = f;
	}
	public float getDamage() {
		return damage;
	}
	public void setDamage(float f) {
		this.damage = f;
	}

}
