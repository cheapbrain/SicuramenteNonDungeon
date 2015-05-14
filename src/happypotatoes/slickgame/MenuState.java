package happypotatoes.slickgame;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.UI;
import happypotatoes.slickgame.gui.component.Button;

import java.awt.Toolkit;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{
	private Image background, foreground;
	private ParticleSystem ps;
	private Button play, exit;
	private int width =0;
	private int height =0;
	private UI ui;
	private float i=1; 
	private int  time=0, max=1500;
	private StateBasedGame basedGame;
	public void enter(GameContainer container, StateBasedGame game)	throws SlickException {
		try {
			ps = new ParticleSystem(new Image("./res/light.png"), 1000);
			basedGame = game;
			ParticleEmitter pe = new FogEmitter(container.getWidth(), container.getHeight());
			ps.addEmitter(pe);
			background  = new Image("./res/menu/BackGround.png");
			foreground  = new Image("./res/menu/ForeGround.png");
			width=container.getWidth();
			height=container.getHeight();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		ui = new UI(container, game);
		play = new Button("",0,height*693/1366,180,50,"./res/menu/Play");
		play.setHorizontalAlign(Component.CENTER);
		play.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(2);
			}
		});
		exit = new Button("",0,height*1120/1366,140,40,"./res/menu/Exit");
		exit.setHorizontalAlign(Component.CENTER);
		exit.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				System.exit(0);
			}
		});
		ui.add(exit);
		ui.add(play);
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
	}
	
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		ui.dispose();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0,0,width,height);
		ps.render();
		ui.render(g);
		foreground.draw(0,0,width,height);
		g.setColor(new Color(0,0,0,i));
		g.fillRect(0, 0, width, height);
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		ps.update(delta);
		if((time<max)){
			i = 1-(float)time/max;
			time += delta;
		}
	}

	public int getID() {
		return 0;
	}

}
