package happypotatoes.slickgame.items;

public class Equippable extends Item {
	public int height, width;
	public float offsetX, offsetY;

	public Equippable(int id, String name, int width, int height, float offsetX, float offsetY) {
		super(id, name);
		this.width=width;
		this.height=height;
		this.offsetX=offsetX;
		this.offsetY=offsetY;	
	}

}
