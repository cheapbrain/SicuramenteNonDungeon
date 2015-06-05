package happypotatoes.slickgame;

import java.io.File;
import java.util.Stack;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.EnergyPotion;
import happypotatoes.slickgame.entitysystem.entity.HealthPotion;
import happypotatoes.slickgame.entitysystem.entity.Sword;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.entitysystem.entity.Wolf;
import happypotatoes.slickgame.gui.GuiSystem;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Minimap;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.ItemType;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadingState extends BasicGameState {
	Stack<File> files;
	public World world;
	public UI ui;
	private int step = 0;
	private int max;
	private Image background, foreground, loadingBar;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		step = 0;
		background = Loader.image("res/Loading/LoadingScreenBackground.png");
		loadingBar = Loader.image("./res/Loading/LoadingScreenBar.png");
		foreground = Loader.image("./res/Loading/LoadingScreenForeground.png");
		done = false;
		files = Loader.getFiles();
		max = files.size();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException { 
		background.draw(0,0,container.getWidth(), container.getHeight());
		loadingBar.draw(0, 0, container.getWidth()*((float)step/max), container.getHeight(), 0, 0, loadingBar.getWidth()*((float)step/max), loadingBar.getHeight());
		foreground.draw(0,0,container.getWidth(), container.getHeight());
	}

	int delay = 100;
	boolean done = false;
	Entity player;
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		long start = System.currentTimeMillis();
		while(System.currentTimeMillis()-start<delay) {
			if (!files.isEmpty()) {
				String path = files.pop().getAbsolutePath();
				Loader.preload(path);
				System.out.println(path);
				step++;
			} else {
				done = true;
				break;
			}
		}
		
		if (done) {
			System.out.println("renderer");
			EntityRenderer.init();
			System.out.println("es");
			EntitySystem.getInstance().clear();
			System.out.println("items");
			ItemType itemType = new ItemType();
			ItemList itemList = new ItemList();
			//ItemSprite itemSprite = new ItemSprite();
			System.out.println("camera");
			Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
			System.out.println("world");
			world = new World(container);

			System.out.println("entities");
			player = Player.create();
			player.x = 2.5f;
			player.y = 2.5f;
			world.add(player);
			Entity item = Sword.create();
			item.x=3.5f;
			item.y=2.5f;
			world.add(item);
			
			Entity item1 = HealthPotion.create();
			item1.x=5.5f;
			item1.y=2.5f;
			world.add(item1);
			
			Entity item2 = EnergyPotion.create();
			item2.x=7.5f;
			item2.y=2.5f;
			world.add(item2);
			world.add(Wolf.create(3,3));
			
			System.out.println("fbo");
			new Minimap(world, player);

			System.out.println("light");
			LightingBello.lighting.add(new Light(player, 0, 0, 6, 1f));
			
			System.out.println("stuff");
			Camera.camera.setTarget(player);
			EntitySystem.getInstance().update(world, 0);
			ui = new UI(container, game);
			container.getGraphics().setBackground(new Color(0,0,0,255));

			System.out.println("ui");
			GuiSystem.init(ui, player);		
			game.enterState(1);
		}
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
		return 2;
	}

}
