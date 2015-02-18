package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.entity.WalkEntity;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Plate extends Trap{
	
	private int pressed=0;
	
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
	public void update(GameContainer container, World world, int delta){
		this.setState(pressed);
		pressed=0;
	}
	
	@Override
	public void collideWith(Entity a){
		if(a instanceof Player){
			this.pressed=1;
		}
	}
}
