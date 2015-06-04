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
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{
	private Image background, bg2, foreground;
	private ParticleSystem ps;
	private Button play, exit, opt, about;
	private int width =0;
	private int height =0;
	private UI ui;
	private float i=1; 
	private int dx;
	private float scale;
	private int  time=0, max=1500;
	private StateBasedGame basedGame;
	public void enter(GameContainer container, StateBasedGame game)	throws SlickException {
		ui.enter();
	}
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		try {
			ps = new ParticleSystem(Loader.image("res/light.png"), 1000);
			basedGame = game;
			ParticleEmitter pe = new FogEmitter(container.getWidth(), container.getHeight());
			ps.addEmitter(pe);
			background  = Loader.image("res/menu/BackGround.png");
			bg2  = Loader.image("res/menu/BackForeGround.png");
			foreground  = Loader.image("res/menu/ForeGround.png");
			width=container.getWidth();
			height=container.getHeight();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

		scale = height/768f;
		dx = (int) ((1366*scale-width)/2);
		
		ui = new UI(container, game);
		play = new Button("",0,(int)(295*scale),(int)(245*scale),(int)(67*scale),"res/menu/Play");
		play.setHorizontalAlign(Component.CENTER);
		play.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(2);
			}
		});
		
		opt = new Button("",0,(int)(380*scale),(int)(245*scale),(int)(67*scale),"res/menu/Options");
		opt.setHorizontalAlign(Component.CENTER);
		opt.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(2);
			}
		});
		
		about = new Button("",0,(int)(465*scale),(int)(245*scale),(int)(67*scale),"res/menu/About");
		about.setHorizontalAlign(Component.CENTER);
		about.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				basedGame.enterState(2);
			}
		});
		
		exit = new Button("",0,(int)(625*scale),(int)(180*scale),(int)(50*scale),"res/menu/Exit");
		exit.setHorizontalAlign(Component.CENTER);
		exit.setActionListener(new ActionListener() {
			@Override
			public void actionPerformed(Component source) {
				System.exit(0);
			}
		});
		ui.add(exit);
		ui.add(play);
		ui.add(opt);
		ui.add(about);
	}
	
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		ui.dispose();
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0,0,width,height);
		ps.render();
		bg2.draw(-dx,0,1366*scale,height);
		ui.render(g);
		foreground.draw(-dx,0,1366*scale,height);
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
