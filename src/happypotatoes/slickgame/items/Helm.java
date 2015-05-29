package happypotatoes.slickgame.items;


public class Helm extends Item{
	private float mitigation;
	private float weight;
	
	public Helm(int id, String name, float weight, float mitigation) {
		super(id, name);
		setType(ItemType.helm);
		setMitigation(mitigation);
		setWeight(weight);
	}

	public float getMitigation() {
		return mitigation;
	}

	public void setMitigation(float mitigation) {
		this.mitigation = mitigation;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
