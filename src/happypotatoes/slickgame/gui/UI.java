package happypotatoes.slickgame.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.state.StateBasedGame;

public class UI extends Container implements InputListener{
	public GameContainer container;
	public StateBasedGame game;
	public Component activeComponent;
	public Input input;
	
	public static int mx, my;
	public static boolean mb0, mb1, mb2;
	
	public UI(GameContainer container, StateBasedGame game) {
		(input = container.getInput()).addPrimaryListener(this);
		Fonts.init();
		x = 0;
		y = 0;
		width = container.getWidth();
		height = container.getHeight();
		System.out.println(width+" "+height);
	}
		
	public void update(GameContainer container) {
		
	}

	public void render(Graphics g) {
		paintChildren(g);
	}
	
	public int getAbsoluteX() {
		return 0;
	}

	public int getAbsoluteY() {
		return 0;
	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		switch(button) {
			case 0:
				mb0 = true;
				break;
			case 1:
				mb1 = true;
				break;
			case 2:
				mb2 = true;
				break;
		}
		mx = x;
		my = y;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		switch(button) {
			case 0:
				mb0 = false;
				break;
			case 1:
				mb1 = false;
				break;
			case 2:
				mb2 = false;
				break;
		}
		mx = x;
		my = y;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mx = newx;
		my = newy;
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		mx = newx;
		my = newy;
	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub
		
	}
}
