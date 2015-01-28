package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.Actor;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

import java.util.Random;

public class Trap extends Entity implements Actor{
	public int x, y;
	public int type; //steam, magic, natural
	
	public Trap(Room a, Random r){
		super(false);
		x=r.nextInt(a.x);
		y=r.nextInt(a.y);
		type=r.nextInt(3);
	}
	
	public Trap(int x, int y, int type){
		super(false);
		this.x=x;
		this.y=y;
		this.type=type;
	}

	@Override
	public void use(Entity user, World world) {
		
	}
	
	
}
