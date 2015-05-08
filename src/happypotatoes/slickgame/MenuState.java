package happypotatoes.slickgame;

import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Button;

import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{
	private Image background, foreground;
	private Button play, exit;
	private int width =0;
	private int height =0;
	private UI ui;
	private int i=0, time=0;
	public void enter(GameContainer container, StateBasedGame game)	throws SlickException {
	try {
			background  = new Image("./res/menu/BackGround.png");
			foreground  = new Image("./res/menu/ForeGround.png");
			height=Toolkit.getDefaultToolkit().getScreenSize().height;
			width=Toolkit.getDefaultToolkit().getScreenSize().width;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		ui = new UI(container, game);
		play = new Button("",0,0,180,50,"./res/menu/Play");
		play.setVerticalAlign(Component.CENTER);
		play.setHorizontalAlign(Component.CENTER);
		exit = new Button("",0,height/19*15,140,40,"./res/menu/Exit");
		exit.setHorizontalAlign(Component.CENTER);
		ui.add(exit);
		ui.add(play);
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0,0,width,height);
		ui.render(g);
		foreground.draw(0,0,width,height);
		g.setColor(new Color(0,0,0,255-i));
		g.fillRect(0, 0, width, height);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		time+=delta;
		if((time>=10)&&(i<=255)){
			time=0;
			i++;
		}
	}

	public int getID() {
		return 0;
	}

}
