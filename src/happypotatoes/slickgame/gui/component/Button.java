package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.Container;
import happypotatoes.slickgame.gui.UI;

public class Button extends Component{
	protected ActionListener actionListener;
	protected Image imgIdle=null, imgLightened=null, imgClicked=null;
	protected String text;
	protected Color color = Color.black;
	
	public Button(String text, int x, int y, int width, int height, String imgPath) {
		this.text = text;
		setPosition(x, y);
		setSize(width, height);
		try {
			imgIdle = new Image(imgPath+"/1.png");
			imgLightened = new Image(imgPath+"/2.png");
			imgClicked = new Image(imgPath+"/3.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
			if (UI.mb0){
				bg = Color.darkGray;
				if(imgClicked!=null) imgClicked.draw(0,0,width,height);
			}else{
				bg = Color.white;
				if(imgLightened!=null) imgLightened.draw(0,0,width,height);
			}
		else{
			bg = Color.lightGray;
			if(imgIdle!=null) imgIdle.draw(0,0,width,height);
		}
				
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, 0, 0);
	}
}
