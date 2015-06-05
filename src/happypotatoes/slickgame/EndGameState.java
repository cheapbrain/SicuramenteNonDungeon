package happypotatoes.slickgame;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Button;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGameState extends BasicGameState{
	private float scale;
	private Button menu;
	private StateBasedGame basedGame;
	private UI ui;
	private int width =0;
	private int height =0;
	private Component bg2;
	private int dx;
	private Image background;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException{
		basedGame = game;
		background  = new Image("./res/Pause/PauseMenuBackground.png");
		scale = height/768f;
		dx = (int) ((1366*scale-width)/2);
		ui = new UI(container, game);		
		menu = new Button("",0,(int)(435*scale),(int)(206*scale),(int)(50*scale),"./res/Pause/Menu");
		menu.setHorizontalAlign(Component.CENTER);
		menu.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(0);
			}
		});
		ui.add(menu);
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(-dx,0,bg2.getWidth()*scale,height);
		ui.render(g);
		g.setColor(Color.white);
		g.drawString("You managed to exit the dungeon", 100, 100);
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
}


