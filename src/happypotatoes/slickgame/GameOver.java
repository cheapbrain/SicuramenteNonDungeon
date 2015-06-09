package happypotatoes.slickgame;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState{
	Image win, lose;
	
	public static boolean winner;

	float i;
	long time;
	int state;
	int x, y, w, h;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		win = Loader.image("res/win.png");
		lose = Loader.image("res/lose.png");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		time = 0;
		state = 0;
		
		w = (container.getWidth()*2)/3;
		h = (win.getHeight()*w)/win.getWidth();
		
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
		if (winner)
			win.draw(x, y, w, h, color);
		else
			lose.draw(x, y, w, h, color);
			
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
			if (container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				time = 0;
				state = 2;
			}
			break;
		case 2:
			i = 1-time/1000f;
			if (time>=1000) {
				game.enterState(23);
			}
			break;
		}
	}

	@Override
	public int getID() {
		return 420;
	}

}
