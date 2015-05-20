package happypotatoes.slickgame.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Window extends Container{
	private boolean decorated = false;
	private String title = "";
	protected Image background = null;
	public Window(String title, int x, int y, int width, int height) {
		this.setTitle(title);
		setPosition(x, y);
		setSize(width, height);
	}
	public void setBackground(Image background){
		this.background=background;
	}
	@Override
	protected void paintComponent(Graphics g) {
		if(background!=null){
			background.draw(0, 0, width, height);
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isDecorated() {
		return decorated;
	}
	public void setDecorated(boolean decorated) {
		this.decorated = decorated;
	}
}
