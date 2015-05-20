package happypotatoes.slickgame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {

	public GameStateController() {
		super("cacca <3");
	}

	@Override
    public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMouseCursor("./res/Cursor.png", 0, 0);
		//uno dei due deve essere commentato:
		//per andare nel gioco
		//per andare nel menu
		addState(new MenuState());
		addState(new LoadingState());
        addState(new GameState());
        addState(new PauseState());
    }
}
