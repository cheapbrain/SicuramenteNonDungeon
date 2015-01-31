package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;

public class Npc extends WalkEntity{
	protected boolean isEnemy;
	public Npc(boolean doesCollide, boolean isEnemy) {
		super(doesCollide);
		this.isEnemy=isEnemy;
	}
	public void update(GameContainer container, World world, int delta) {
		super.update(container, world, delta);
	}
	public void render() {
		super.render();
	}
	
}
