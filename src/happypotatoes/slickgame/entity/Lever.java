package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.worldgenerator.Generator;
import happypotatoes.slickgame.worldgenerator.Room;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Lever extends Trap {

	public Lever(Room a){
		this(Generator.getR().nextInt(a.width)+a.x,Generator.getR().nextInt(a.height)+a.y,Generator.getR().nextInt(1));
	}
	
	public Lever(float x, float y, int type){
		super(x,y,type,1);
		try {
			texture = new Image("./res/leverSheet.png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.setDirections(4);
		this.setFacing(Generator.getR().nextInt(4));
		this.setSize(32, 32, 2);
		this.setSprites(texture, 0);
		this.setSprites(texture, 1);
		this.setState(Generator.getR().nextInt(2));
	}
	
}
