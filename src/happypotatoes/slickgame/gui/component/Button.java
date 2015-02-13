package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.Container;
import happypotatoes.slickgame.gui.UI;

public class Button extends Component{
	protected ActionListener actionListener;

	protected String text;
	protected Color color = Color.black;
	
	public Button(String text, int x, int y, int width, int height) {
		this.text = text;
		setPosition(x, y);
		setSize(width, height);
	}
	
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Color bg = null;

		int x = getAbsoluteX();
		int y = getAbsoluteY();
		
		int mx = UI.mx;
		int my = UI.my;
		if (mx>x&&mx<x+width&&my>y&&my<y+height)
			if (UI.mb0)
				bg = Color.darkGray;
			else
				bg = Color.white;
		else
			bg = Color.lightGray;
				
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, 0, 0);
	}
}
