package happypotatoes.slickgame.entity;

import java.util.ArrayList;
import java.util.List;

import happypotatoes.slickgame.world.World;
import happypotatoes.slickgame.worldgenerator.Generator;
import happypotatoes.slickgame.worldgenerator.Room;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Plate extends Trap{
	
	private int pressed=0;
	
	public Plate(Room a){
		this(Generator.getR().nextInt(a.width)+a.x,Generator.getR().nextInt(a.height)+a.y,Generator.getR().nextInt(1));
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
	public void update(GameContainer container, World world, int delta){
		List<Entity> over = world.getEntities(this.getSpriteShape());
		pressed=0;
		for(int i=0; i<over.size()-1; i++) 
			if(over.get(i) instanceof Player)
				pressed=1;
		this.setState(pressed);
	}
	
	@Override
	public void collideWith(Entity a){
	}
}
