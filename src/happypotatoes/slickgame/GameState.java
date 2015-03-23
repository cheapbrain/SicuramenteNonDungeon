package happypotatoes.slickgame;


import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.Window;
import happypotatoes.slickgame.gui.component.Button;
import happypotatoes.slickgame.gui.component.Label;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState {
	World world;
	UI ui;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		world = new World(container);
		ui = new UI(container, game);
		Window test = new Window("test", 200, 550, 400, 50);
		test.add(new Label("ebola", 0, 0, 100, 50));
		test.add(new Button("omg", 150, 0, 50, 30));
		ui.add(test);
		container.getGraphics().setBackground(new Color(0,0,0,255));
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
		
		ui.update(container);
		world.update(container, delta);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
