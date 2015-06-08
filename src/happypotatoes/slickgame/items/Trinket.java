package happypotatoes.slickgame.items;


public class Trinket extends Equippable{
	
	public Trinket(int id, String name,int width, int height, float offsetX, float offsetY) {
		super(id, name, height, width, offsetX, offsetY);
		setType(ItemType.trinket);
	}

}
