package happypotatoes.slickgame;

import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.Door;
import happypotatoes.slickgame.entitysystem.entity.Robot;
import happypotatoes.slickgame.gui.Notification;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState {
	public World world;
	public UI ui;
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	public void enter(GameContainer container, StateBasedGame game)	throws SlickException {
		ui.enter();
		Notification.showMessage("Level 1", 3000);
		EntitySystem.getInstance().addEntity(Door.create(5.5f, 2.01f));
		
	}
	
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		ui.dispose();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.clear();
		world.render(g);
		ui.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
			game.enterState(3);
		ui.update(container);
		world.update(container, delta);

	}

	@Override
	public int getID() {
		return 1;
	}

}
