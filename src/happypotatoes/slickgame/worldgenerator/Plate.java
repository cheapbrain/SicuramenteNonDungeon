package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.entity.WalkEntity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plate extends Trap{
	
	public Plate(Room a){
		this(Generator.r.nextInt(a.width)+a.x,Generator.r.nextInt(a.height)+a.y,Generator.r.nextInt(1));
	}

	public Plate(float x, float y, int type) {
		super(x,y,type,1);
		try {
			texture = new Image("./res/plateSheet.png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.setDirections(1);
		this.setFacing(0);
		this.setSize(64, 64, 2);
		this.setSprites(texture, 0);
		this.setSprites(texture, 1);
		this.setState(0);
	}
	
	@Override
	public void collideWith(Entity a){
		if(a instanceof WalkEntity){
			this.setState(1);
			//System.out.println("A");
		}
		//System.out.println("B");
	}
}
