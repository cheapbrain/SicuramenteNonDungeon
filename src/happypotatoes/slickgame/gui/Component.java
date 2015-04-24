package happypotatoes.slickgame.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.TrueTypeFont;

public class Component {
	
	protected TrueTypeFont font = Fonts.font1;
	protected boolean focusable = true;
	protected boolean visible = true;
	protected boolean enabled = true;
	protected int x, y, width, height;
	
	protected int id;
	protected MouseListener mouseListener;
	protected boolean acceptMouseInput = false;
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
	
	public boolean doesAcceptMouseInput() {
		return acceptMouseInput;
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
	

}
