package happypotatoes.slickgame.items;


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

}
