package happypotatoes.slickgame.entity;

public class Trap extends StaticEntity{
	public int type; //steam, magic, natural

	public Trap(float x, float y, int type, int directions) {
		super(x, y, directions);
		this.type=type;
	}

}
