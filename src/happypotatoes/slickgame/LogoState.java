package happypotatoes.slickgame;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LogoState extends BasicGameState{
	Image logo;
	float i;
	long time;
	int state;
	int x, y, w, h;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		System.out.println(new File("res/logo.png").getAbsolutePath());
		System.out.println(new File(new File("res/logo.png").getAbsolutePath()).getAbsolutePath());
		container.setShowFPS(false);
		logo = Loader.image("res/logo.png");
		time = 0;
		state = 0;
		
		w = (container.getWidth()*2)/3;
		h = (logo.getHeight()*w)/logo.getWidth();
		
		x = (container.getWidth()-w)/2;
		y = (container.getHeight()-h)/2;
	}

	Color color = new Color(1,1,1,1f);
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);
		g.clear();
		color.a = i;
		logo.draw(x, y, w, h, color);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		time += delta;

		switch(state) {
		case 0:
			i = time/1000f;
			if (time>=1000) {
				time = 0;
				state = 1;
			}
			break;
		case 1:
			i = 1f;
			if (time>=1000) {
				time = 0;
				state = 2;
			}
			break;
		case 2:
			i = 1-time/1000f;
			if (time>=1000) {
				game.enterState(0);
			}
			break;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 23;
	}

}
