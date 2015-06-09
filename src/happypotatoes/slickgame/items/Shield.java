package happypotatoes.slickgame.items;


public class Shield extends Equippable{
	private float mitigation;
	private float weight;
	
	public Shield(int id, String name, float weight, float mitigation, int width, int height, float offsetX, float offsetY) {
		super(id, name, height, width, offsetX, offsetY);
		setType(ItemType.secondhand);
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
