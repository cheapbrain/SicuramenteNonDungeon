package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

public class Armour extends Item implements Actor{
	
	private Float mitigation;

	public Armour(){
		super();
		
	}
	
	public Float getmitigation(){
		return mitigation;
	}

	@Override
	public float getDist(Entity entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void use(Entity user, World world) {
		// TODO Auto-generated method stub
		
	}

	public char getIdItem() {
		// TODO Auto-generated method stub
		return 0;
	}
}
