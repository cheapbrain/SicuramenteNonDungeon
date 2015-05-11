package happypotatoes.slickgame.gui;

public class MouseEvent {
	public static final int BUTTON0 = 0;
	public static final int BUTTON1 = 1;
	public static final int BUTTON2 = 2;

	public static final int PRESSED = 0;
	public static final int RELEASED = 1;
	
	public int x, y;
	public int button;
	public int action;
	
	public MouseEvent(int x, int y, int button, int action) {
		this.x = x;
		this.y = y;
		this.button = button;
		this.action = action;
	}
	
}
