package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Npc extends WalkEntity{

	public Npc(boolean doesCollide) {
		super(doesCollide);
	}
	public void update(GameContainer container, World world, int delta) {
		super.update(container, world, delta);
	}
	public void render() {
		super.render();
	}
	
}
