package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.gui.ActionListener;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.Container;
import happypotatoes.slickgame.gui.MouseEvent;
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
	
	
	boolean pressed = false;
	boolean hover = false;
	
	@Override
	public void mousePressed(int button, int x, int y) {
		if (button==MouseEvent.BUTTON0)
			pressed = true;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button==MouseEvent.BUTTON0) {
			pressed = false;
			if (actionListener!=null)
				actionListener.actionPerformed(this);
		}
	}

	@Override
	public void mouseEntered() {
		hover = true;
	}
	
	@Override
	public void mouseLeft() {
		hover = false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Color bg = null;

		int x = getAbsoluteX();
		int y = getAbsoluteY();
		
		int mx = UI.mx;
		int my = UI.my;
		if (hover)
			if (pressed){
				if(imgClicked!=null) imgClicked.draw(0,0,width,height);
			}else{
				if(imgLightened!=null) imgLightened.draw(0,0,width,height);
			}
		else{
			if(imgIdle!=null) imgIdle.draw(0,0,width,height);
		}
				
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, 0, 0);
	}
}
