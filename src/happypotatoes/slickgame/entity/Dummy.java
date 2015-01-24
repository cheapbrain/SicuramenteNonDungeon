package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Dummy extends Player{

	public Dummy() {
		super();
		facing = 0;
	}
	
	@Override
	public void update(GameContainer container, World world, int delta) {
		

	}

	public void render() {
		super.render();
	}
}
