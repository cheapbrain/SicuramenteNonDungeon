package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.gui.Component;

public class GameInput{
	
	static boolean[] pressed = new boolean[]{false, false, false};	
	static boolean[] down = new boolean[]{false, false, false};	
	
	public static boolean isMouseButtonDown(int button) {
		return down[button];
	}

	public static boolean isMousePressed(int button) {
		boolean state = pressed[button];
		pressed[button] = false;
		return state;
	}
	
	public static void setButton(int button, boolean state) {
		pressed[button] = state;
		down[button] = state;
	}
	
	public static void clear() {
		for (int i=0;i<pressed.length;i++) {
			pressed[i] = false;
			down[i] = false;
		}
	}
}
