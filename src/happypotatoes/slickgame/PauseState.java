package happypotatoes.slickgame;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Button;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState{
	private UI ui;
	private Button play, exit, menu;
	StateBasedGame basedGame;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		basedGame = game;
		int height = container.getHeight();
		ui = new UI(container, game);
		
		play = new Button("",0,height*693/1366,180,50,"./res/menu/Play");
		play.setHorizontalAlign(Component.CENTER);
		play.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(1);
			}
		});
		menu = new Button("",0,height*1120/1366,140,40,"./res/menu/Exit");
		menu.setHorizontalAlign(Component.CENTER);
		menu.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(0);
			}
		});

		exit = new Button("",0,height*1220/1366,140,40,"./res/menu/Exit");
		exit.setHorizontalAlign(Component.CENTER);
		exit.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				System.exit(0);
			}
		});
		ui.add(exit);
		ui.add(menu);
		ui.add(play);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {

		ui.enter();
		
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {

		ui.dispose();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		ui.render(g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
