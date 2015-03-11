package happypotatoes.slickgame.items;

import happypotatoes.slickgame.entity.Entity;

public class ItemEntity extends Entity{
	private int id;
	private int age;
    private int delayBeforeCanPickup;
    private int health;
    private String thrower;
    private String owner;
    
	public ItemEntity(boolean doesCollide) {
		super(doesCollide);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDelayBeforeCanPickup() {
		return delayBeforeCanPickup;
	}

	public void setDelayBeforeCanPickup(int delayBeforeCanPickup) {
		this.delayBeforeCanPickup = delayBeforeCanPickup;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getThrower() {
		return thrower;
	}

	public void setThrower(String thrower) {
		this.thrower = thrower;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
