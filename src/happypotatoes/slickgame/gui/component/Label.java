package happypotatoes.slickgame.gui.component;

import happypotatoes.slickgame.gui.Component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Label extends Component{
	
	protected String text;
	protected Color color = Color.black;

	public Label(String text, int x, int y, int width, int height) {
		this.text = text;
		setPosition(x, y);
		setSize(width, height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, 0, 0);
	}
}
