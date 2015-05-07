package happypotatoes.slickgame.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PopUpWindow extends Window{
	private String folder="./res/popup/";
	protected final static int borderOffset=64;
	private Image back, upDx, upSx, doDx, doSx, sx, dx, up, down;
	public PopUpWindow(String title, int x, int y, int width, int height) {
		super(title, x, y, width+borderOffset, height+borderOffset);
		try {
			back = new Image(folder+"Background.jpg");
			upDx = new Image(folder+"LatoAltoDestra1.png");
			upSx  = new Image(folder+"LatoAltoSinistra1.png");
			doSx = new Image(folder+"LatoBassoSinistra1.png");
			doDx = new Image(folder+"LatoBassoDestra1.png");
			up = new Image(folder+"LatoSopra.png");
			sx = new Image(folder+"LatoSinistra.png");
			dx = new Image(folder+"LatoDestra.png");
			down = new Image(folder+"LatoSotto.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	public void paint(Graphics g) {
		back.draw(32,32,width-64,height-64);
		paintComponent(g);
		paintChildren(g);
		upSx.draw(0,0,64,64);
		up.draw(64,0,width-64,64,0,0,width/up.getTextureWidth(),64);
		upDx.draw(width-64,0,64,64);
		sx.draw(0,64,64,height-64,0,0,64,height/sx.getTextureHeight());
		dx.draw(width-64,64,width,height-64,0,0,64,height/dx.getTextureHeight());
		doSx.draw(0,height-64,64,64);
		down.draw(64,height-64,width-64,height,0,0,width/down.getTextureHeight(),64);
		doDx.draw(width-64,height-64,64,64);
	}
}
