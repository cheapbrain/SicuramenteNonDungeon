package happypotatoes.slickgame.gui;

public class MouseMovementEvent {
	public static final int MOVE = 0;
	public static final int DRAG = 1;
	
	public int oldx, oldy;
	public int x, y;
	public int type;
	
	public MouseMovementEvent(int oldx, int oldy, int x, int y, int type) {
		this.oldx = oldx;
		this.oldy = oldy;
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
