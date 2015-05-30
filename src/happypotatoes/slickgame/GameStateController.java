package happypotatoes.slickgame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {

	public GameStateController() {
		super("Nostos - Alpha");
	}

	@Override
    public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMouseCursor("./res/Cursor.png", 0, 0);
		addState(new MenuState());
		addState(new LoadingState());
        addState(new GameState());
        addState(new PauseState());
    }
}
