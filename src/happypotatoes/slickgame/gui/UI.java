package happypotatoes.slickgame.gui;

import happypotatoes.slickgame.gui.component.GameInput;

import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.state.StateBasedGame;

public class UI extends Container implements InputListener{
	public GameContainer container;
	public StateBasedGame game;
	public Component eventOwner;
	public Component activeComponent;
	public Input input;
	
	public static int mx, my;
	public static boolean mb0, mb1, mb2;
	
	public UI(GameContainer container, StateBasedGame game) {
		input = container.getInput();
		Fonts.init();
		x = 0;
		y = 0;
		width = container.getWidth();
		height = container.getHeight();
	}
	
	public void dispose() {
		input.removeListener(this);
	}
	
	public void enter() {
		input.addPrimaryListener(this);
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
	
	public boolean sendMouseEvent(MouseEvent e) {
		if (enabled&&contain(e.x, e.y)) {
			Iterator<Component> it = children.descendingIterator();
			while(it.hasNext())
				if (it.next().sendMouseEvent(e))
					return true;
			GameInput.setButton(e.button, e.action==0);
			return true;
		} else {
			return false;
		}
	}

	public boolean sendMouseMovementEvent(MouseMovementEvent e) {
		boolean mousewas = contain(e.oldx, e.oldy);
		boolean mouseis = contain(e.x, e.y);
		if (enabled&&(mousewas||mouseis)) {
			Iterator<Component> it = children.descendingIterator();
			while(it.hasNext()) {
				if (it.next().sendMouseMovementEvent(e)) {
					GameInput.clear();
					return true;
				}
			}
			return mousewas&&mouseis;
		} else {
			GameInput.clear();
			return false;
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
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
		sendMouseEvent(new MouseEvent(x, y, button, MouseEvent.PRESSED));
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
		sendMouseEvent(new MouseEvent(x, y, button, MouseEvent.RELEASED));
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		mx = newx;
		my = newy;
		sendMouseMovementEvent(new MouseMovementEvent(oldx, oldy, newx, newy, MouseMovementEvent.MOVE));
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		mx = newx;
		my = newy;
		sendMouseMovementEvent(new MouseMovementEvent(oldx, oldy, newx, newy, MouseMovementEvent.DRAG));
	}

	@Override
	public void keyPressed(int key, char c) {
		
	}

	@Override
	public void keyReleased(int key, char c) {
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
