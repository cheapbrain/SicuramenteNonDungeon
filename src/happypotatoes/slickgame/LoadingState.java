package happypotatoes.slickgame;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.entity.FighterEntity;
import happypotatoes.slickgame.entitysystem.entity.Item;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.entitysystem.entity.Wolf;
import happypotatoes.slickgame.gui.GuiSystem;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Minimap;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends BasicGameState {
	public World world;
	public UI ui;
	private int step = 0;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		step = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.white);
		g.drawString("Loading.. "+step+"%", 100, 100);
	}

	Entity player;
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		switch(step) {
		case 0:
			EntityRenderer.init();
			EntitySystem.getInstance().clear();
			break;
		case 1:
			ItemSystem.load();
			break;
		case 2:
			Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
			break;
		case 3:
			world = new World(container);
			break;
		case 4:
			player = Player.create();
			player.x = 2.5f;
			player.y = 2.5f;
			world.add(player);
			break;
		case 5:
			Entity item = Item.create(".\\res\\MyMod\\Items\\Spada.item");
			item.x=3.5f;
			item.y=2.5f;
			world.add(item);
			break;
		case 6:
			for(int i=0; i<1; i++) world.add(Wolf.create(3,3));
			break;
		case 7: Minimap minimap = new Minimap(world, player);
				break;
		case 8:
			LightingBello.lighting.add(new Light(player, 0, 0, 6, 1f));
			Camera.camera.setTarget(player);
			EntitySystem.getInstance().update(world, 0);
			break;
		case 9:
			ui = new UI(container, game);
			GuiSystem.init(ui, player);		
			container.getGraphics().setBackground(new Color(0,0,0,255));
			break;
		case 100:
			game.enterState(1);
			break;
		default:
			
		}
		if (step<100)
		step++;
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		GameState state = (GameState) game.getState(1);
		state.world = world;
		state.ui = ui;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
