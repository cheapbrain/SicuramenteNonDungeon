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
	
	
	public void paint(Graphics g) {
		paintComponent(g);
	}
	
	protected void paintComponent(Graphics g) {
		
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
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAbsoluteX() {
		return container.getAbsoluteX()+x;
	}

	public int getAbsoluteY() {
		return container.getAbsoluteY()+y;
	}
	

}
