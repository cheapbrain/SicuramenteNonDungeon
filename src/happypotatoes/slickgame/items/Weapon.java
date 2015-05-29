package happypotatoes.slickgame.items;


public class Weapon extends Item{
	private float damage;
	private float weight;
	
	public Weapon(int id, String name, float weight, float damage) {
		super(id, name);
		setType(ItemType.weapon);
		setWeight(weight);
		setDamage(damage);
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
