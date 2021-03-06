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
		app.setIcon("./res/icon32.png");
		app.setDisplayMode(width, height, false);
		//app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(false);
        app.start();
	}
}
