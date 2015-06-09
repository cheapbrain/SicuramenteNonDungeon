package happypotatoes.slickgame.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Notification extends Window{
	static String text;
	static long time;
	static int state = 3;
	static int fadein = 500;
	static int fadeout = 1000;
	static int delay;

	static int w, h;
	
	static long oldtime;
	
	static Color bg = new Color(0, 0, 0, 1f);
	static Color fg = new Color(1, 1, 1, 1f);
	
	

	public Notification() {
		super("notif", 0, 0, 100, 100);
		setEnabled(false);
		setHorizontalAlign(Component.CENTER);
		setVerticalAlign(Component.CENTER);
		setPriority(10000);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (state==3)
			return;
		
		float i = 0;
		long newtime = System.currentTimeMillis();
		time += (newtime-oldtime);
		oldtime = newtime;
		switch(state) {
		case 0:
			if (time<fadein) {
				i = 1 - (fadein - time)/(float)fadein;
				break;	
			}
			time = 0;
			state = 1;
		case 1:
			if (time<delay) {
				i = 1;
				break;	
			}
			time = 0;
			state = 2;
		case 2:
			if (time<fadeout) {
				i = (fadeout - time)/(float)fadeout;
				break;	
			}
			time = 0;
			state = 3;
			return;
		}
		
		bg.a = i*.7f;
		g.setColor(bg);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		fg.a = i;
		Fonts.font1.drawString((getWidth()-w)/2, 20, text, fg);
	}
	
	public static void showMessage(String text, int delay) {
		Notification.text = text;
		Notification.delay = delay;
		state = 0;
		time = 0;
		oldtime = System.currentTimeMillis();
		
		w = Fonts.font1.getWidth(text);
		h = Fonts.font1.getLineHeight();
		
	}
	
	public int getWidth() {
		return container.getWidth();
	}
	
	public int getHeight() {
		return h+40;
	}

}
