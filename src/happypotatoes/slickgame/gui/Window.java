package happypotatoes.slickgame.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Window extends Container{
	private boolean decorated = false;
	private String title = "";
	private Image background = null;
	public Window(String title, int x, int y, int width, int height) {
		this.title = title;
		setPosition(x, y);
		setSize(width, height);
	}
	public void setBackground(Image background){
		this.background=background;
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0,1));
		g.fillRect(0, 0, width, height);
		if(background!=null){
			background.draw(0, 0, width, height);
		}
	}
}
