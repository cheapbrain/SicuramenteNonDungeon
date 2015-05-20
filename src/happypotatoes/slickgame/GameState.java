package happypotatoes.slickgame;

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
		/*
		EntityRenderer.init();
		ItemSystem.load();
		Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
		world = new World(container);
		Entity player = Player.create();
		player.x = 2.5f;
		player.y = 2.5f;
		world.add(player);
		LightingBello.lighting.add(new Light(player, 0, 0, 6, 1f));
		
		Entity item = Item.create(".\\res\\MyMod\\Items\\Spada.item");
		item.x=3.5f;
		item.y=2.5f;
		world.add(item);
		for(int i=0; i<1; i++) world.add(Wolf.create(3,3));
		world.add(FighterEntity.create(3,3));
		
		Camera.camera.setTarget(player);
		
		EntitySystem.getInstance().update(world, 0);
		
		ui = new UI(container, game);
		GuiSystem.init(ui, player);		
		container.getGraphics().setBackground(new Color(0,0,0,255));
		*/
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
