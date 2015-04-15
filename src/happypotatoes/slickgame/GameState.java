package happypotatoes.slickgame;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.entitysystem.entity.StupidEntity;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.Window;
import happypotatoes.slickgame.gui.component.EnergyBar;
import happypotatoes.slickgame.gui.component.HealthBar;
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
		Entity player = Player.create();
		player.x = 2.5f;
		player.y = 2.5f;
		world.add(player);
		Entity stupid = StupidEntity.create();
		stupid.x= 3.5f;
		stupid.y = 2.5f;
		world.add(stupid);
		world.setCameraTarget(player);
		LightingBrutto lighting = new LightingBrutto();
		lighting.add(new Light(player, 0, 0, 10, 1f));
		lighting.add(new Light(2, 2, 10, 1f));
		lighting.add(new Light(15, 2, 10, 1f));
		world.setLighting(lighting);
		ui = new UI(container, game);
		Window test = new Window("test", 200, 550, 400, 50);
		test.add(new Label("ebola", 0, 0, 100, 50));
		test.add(new Button("omg", 150, 0, 50, 30));
		test.add(new EnergyBar((Energy) player.getComponent(Energy.class), 300,25,100,20));
		test.add(new HealthBar((Health) player.getComponent(Health.class), 150,0,100,20));
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
