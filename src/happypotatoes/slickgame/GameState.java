package happypotatoes.slickgame;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.entitysystem.entity.Wolf;
import happypotatoes.slickgame.gui.Component;
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
import org.newdawn.slick.Input;
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

		Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
		
		world = new World(container);
		Entity player = Player.create();
		player.x = 2.5f;
		player.y = 2.5f;
		world.add(player);

		for(int i=0; i<1; i++) world.add(Wolf.create(3,3));
		
		Camera.camera.setTarget(player);
		
		EntitySystem.getInstance().update(world, 0);
		
		LightingBrutto lighting = new LightingBrutto();
		lighting.add(new Light(player, 0, 0, 6, 1f));
		world.setLighting(lighting);
		ui = new UI(container, game);
		Window test = new Window("test", 0, 0, 400, 50);
		test.setVerticalAlign(Component.SOUTH);
		test.setHorizontalAlign(Component.CENTER);
		test.add(new Label("ebola", 0, 0, 100, 50));
		test.add(new Button("omg", 100, 0, 50, 30));
		test.add(new HealthBar((Health) player.getComponent(Health.class), 200,0,100,20));
		test.add(new EnergyBar((Energy) player.getComponent(Energy.class), 200,25,100,20));
		
		ui.add(test);

		test = new Window("eee", 0, 0, 400, 50);
		test.setVerticalAlign(Component.NORTH);
		test.setHorizontalAlign(Component.WEST);
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
		
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
			System.exit(0);
		
		ui.update(container);
		world.update(container, delta);
	}

	@Override
	public int getID() {
		return 0;
	}

}
