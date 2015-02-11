package happypotatoes.slickgame.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Window extends Container{
	private boolean decorated = false;
	private String title = "";
	
	public Window(String title, int x, int y, int width, int height) {
		this.title = title;
		setPosition(x, y);
		setSize(width, height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, width, height);
	}
}
