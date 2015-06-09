package happypotatoes.slickgame;


import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.Container;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Button;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PauseState extends BasicGameState{
	private Image background, bg2, foreground;
	private int width =0;
	private int height =0;
	private UI ui;
	private Button play, exit, menu;
	GameContainer cont;
	StateBasedGame basedGame;
	float scale;
	int dx;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		basedGame = game;
		cont = container;

		background  = Loader.image("res/Pause/PauseMenuBackground.png");
		bg2  = Loader.image("res/Pause/PauseMenuBackFrame.png");
		foreground  = Loader.image("res/Pause/PauseMenuForeGround.png");
		width=container.getWidth();
		height=container.getHeight();
		
		scale = height/768f;
		dx = (int) ((bg2.getWidth()*scale-width)/2);
		
		ui = new UI(container, game);

		play = new Button("",0,(int)(322*scale),(int)(245*scale),(int)(60*scale),"res/Pause/Resume");
		play.setHorizontalAlign(Component.CENTER);
		play.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(1);
			}
		});
		
		menu = new Button("",0,(int)(435*scale),(int)(206*scale),(int)(50*scale),"res/Pause/Menu");
		menu.setHorizontalAlign(Component.CENTER);
		menu.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(0);
			}
		});
		
		exit = new Button("",0,(int)(500*scale),(int)(206*scale),(int)(50*scale),"res/Pause/Exit");
		exit.setHorizontalAlign(Component.CENTER);
		exit.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				cont.exit();
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
		background.draw(-dx,0,bg2.getWidth()*scale,height);
		bg2.draw(-dx-2,0,bg2.getWidth()*scale,height);
		ui.render(g);
		foreground.draw(-dx-2,0,bg2.getWidth()*scale,height);
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
