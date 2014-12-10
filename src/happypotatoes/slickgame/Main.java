package happypotatoes.slickgame;

import org.newdawn.slick.AppGameContainer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		AppGameContainer app = new AppGameContainer(new GameStateController());
		app.setDisplayMode(800, 600, false);
        app.start();
	}

}
