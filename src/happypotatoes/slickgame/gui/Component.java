package happypotatoes.slickgame.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.TrueTypeFont;

public class Component implements MouseListener{
	protected Image background = null;
	protected TrueTypeFont font = Fonts.font1;
	protected boolean focusable = true;
	protected boolean visible = true;
	protected boolean enabled = true;
	protected int x, y, width, height;
	
	protected int id;
	protected MouseListener mouseListener;
	protected Container container;
	
	public static final int WEST = 0;
	public static final int EAST = 1;
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int CENTER = 2;
	
	private int valign = NORTH;
	private int halign = WEST;
	
	public void paint(Graphics g) {
		paintComponent(g);
	}
	
	protected void paintComponent(Graphics g) {
		if(background!=null){
			background.draw(0,0,width, height);
		}
	}
	public void setBackground(Image img){
		background=img;
	}
	
	public boolean sendMouseEvent(MouseEvent e) {
		if (enabled&&contain(e.x, e.y)) {
			if (e.action == MouseEvent.PRESSED) {
				this.mousePressed(e.button, e.x, e.y);
			} else {
				this.mouseReleased(e.button, e.x, e.y);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean sendMouseMovementEvent(MouseMovementEvent e) {
		boolean mousewas = contain(e.oldx, e.oldy);
		boolean mouseis = contain(e.x, e.y);
		if (enabled&&(mousewas||mouseis)) {
			if (mousewas&&mouseis) {
				if (e.type==MouseMovementEvent.MOVE) {
					mouseMoved(e.oldx, e.oldy, e.x, e.y);
				} 
			} else if (mouseis) {
				mouseEntered();
			} else if (mousewas) {
				mouseLeft();
			}
			return true;
		} else {
			return false;
		}
	}
	
	
		
	public void setVerticalAlign(int align) {
		valign = align;
	}
	
	public void setHorizontalAlign(int align) {
		halign = align;
	}
	
	public void setContainer(Container container) {
		this.container = container;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean contain(int x, int y) {
		int x0 = getX();
		int x1 = x0+getWidth();
		int y0 = getY();
		int y1 = y0+getHeight();
		return x>=x0&&x<=x1&&y>=y0&&y<=y1;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;		
	}
	
	public int getHeight() {
		return height;
	}

	public int getX() {
		switch(halign) {
		case CENTER:
			return (container.getWidth()-getWidth())/2;
		case EAST:
			return container.getWidth()-x-getWidth();
		default:
			return x;
		}
	}

	public int getY() {
		switch(valign) {
		case CENTER:
			return (container.getHeight()-getHeight())/2;
		case SOUTH:
			return container.getHeight()-y-getHeight();
		default:
			return y;
		}
	}

	public int getAbsoluteX() {
		return container.getAbsoluteX()+getX();
	}

	public int getAbsoluteY() {
		return container.getAbsoluteY()+getY();
	}


	public final void setInput(Input input) {}
	public final boolean isAcceptingInput() {return true;}
	public final void inputEnded() {}
	public final void inputStarted() {}
	public final void mouseClicked(int button, int x, int y, int clickCount) {}

	
	public void mouseWheelMoved(int change) {}

	public void mousePressed(int button, int x, int y) {}

	public void mouseReleased(int button, int x, int y) {}

	public void mouseMoved(int oldx, int oldy, int newx, int newy) {}
	
	public void mouseEntered() {}

	public void mouseLeft() {}

	public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
}
