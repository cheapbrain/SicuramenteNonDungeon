package happypotatoes.slickgame;

import java.io.File;
import java.util.Stack;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.entity.EnergyPotion;
import happypotatoes.slickgame.entitysystem.entity.HealthPotion;
import happypotatoes.slickgame.entitysystem.entity.Sword;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.entitysystem.entity.Wolf;
import happypotatoes.slickgame.gui.GuiSystem;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Minimap;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.ItemSprite;
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
	private Image background;
	private Image gear[] = new Image[20];
	private int counter =0, frameCount=0;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		step = 0;
		for(int i=0;i<20;i++){
			gear[i]=Loader.image("res/Loading/gear/"+(i+1)+".png");
		}
		background = Loader.image("res/Loading/LoadingScreen.png");
		done = false;
		files = Loader.getFiles();
		max = files.size();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0,0,container.getWidth(), container.getHeight());
		g.setColor(Color.white);
		g.drawString("Loading.. "+(step*100)/max+"%", 100, 100);
		Image thisFrame = gear[counter];
		thisFrame.draw(container.getWidth()-thisFrame.getWidth(), container.getHeight()-thisFrame.getHeight());
	}

	boolean done = false;
	Entity player;
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if (!files.isEmpty()) {
			String path = files.pop().getAbsolutePath();
			Loader.preload(path);
			System.out.println(path);
			step++;
		} else {
			done = true;
		}
		
		if (done) {
			EntityRenderer.init();
			EntitySystem.getInstance().clear();
			ItemType itemType = new ItemType();
			ItemList itemList = new ItemList();
			ItemSprite itemSprite = new ItemSprite();
			Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
			world = new World(container);
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
			new Minimap(world, player);
			LightingBello.lighting.add(new Light(player, 0, 0, 6, 1f));
			Camera.camera.setTarget(player);
			EntitySystem.getInstance().update(world, 0);
			ui = new UI(container, game);
			GuiSystem.init(ui, player);		
			container.getGraphics().setBackground(new Color(0,0,0,255));
			game.enterState(1);
		}
		
		frameCount+=delta;
		if(frameCount>=50){
			counter++;
			frameCount=0;
		}
		if(counter>=20) counter =0;
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
