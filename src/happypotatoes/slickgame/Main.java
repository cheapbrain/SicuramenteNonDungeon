package happypotatoes.slickgame;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		AppGameContainer app = new AppGameContainer(new GameStateController());
		app.setDisplayMode(800, 600, true);
        app.start();
	}
}
