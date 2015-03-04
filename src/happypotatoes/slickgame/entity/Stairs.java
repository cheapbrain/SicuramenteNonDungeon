package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.worldgenerator.Generator;
import happypotatoes.slickgame.worldgenerator.Room;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Stairs extends StaticEntity {

	public Stairs(Room a) {
		this(Generator.r.nextInt(a.width-2)+a.x+2,Generator.r.nextInt(a.height-2)+a.y+2,Generator.r.nextInt(1));
	}
	
	public Stairs(float x, float y, int directions) {
		super(x, y, directions);
		try {
			texture = new Image("./res/stairsSheet.png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.doesCollide=true;
		this.setDirections(1);
		this.setFacing(0);
		this.setSize(64, 64, 1);
		this.width=2;
		this.height=2;
		this.setSprites(texture, 0);
		this.setState(0);
	}

}
