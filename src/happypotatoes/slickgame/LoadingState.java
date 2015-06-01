package happypotatoes.slickgame;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.entity.Health_potion;
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
	public World world;
	public UI ui;
	private int step = 0;
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
			gear[i]=new Image("./res/Loading/gear/"+(i+1)+".png");
		}
		background = new Image("./res/Loading/LoadingScreen.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0,0,container.getWidth(), container.getHeight());
		g.setColor(Color.white);
		g.drawString("Loading.. "+step+"%", 100, 100);
		Image thisFrame = gear[counter];
		thisFrame.draw(container.getWidth()-thisFrame.getWidth(), container.getHeight()-thisFrame.getHeight());
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
		case 10:
			ItemType itemType = new ItemType();
			ItemList itemList = new ItemList();
			ItemSprite itemSprite = new ItemSprite();
			break;
		case 20:
			Camera.camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
			break;
		case 30:
			world = new World(container);
			break;
		case 40:
			player = Player.create();
			player.x = 2.5f;
			player.y = 2.5f;
			world.add(player);
			break;
		case 50:
			Entity item = Sword.create();
			Entity item1 = Health_potion.create();
			item.x=3.5f;
			item.y=2.5f;
			item1.x=4.0f;
			item1.y=4.0f;
			world.add(item1);
			break;
		case 60:
			for(int i=0; i<1; i++) world.add(Wolf.create(3,3));
			break;
		case 70: new Minimap(world, player);
				break;
		case 80:
			LightingBello.lighting.add(new Light(player, 0, 0, 6, 1f));
			Camera.camera.setTarget(player);
			EntitySystem.getInstance().update(world, 0);
			break;
		case 90:
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
		// TODO Auto-generated method stub
		return 2;
	}

}
