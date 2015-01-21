package happypotatoes.slickgame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameStateController extends StateBasedGame {

	public GameStateController() {
		super("cacca <3");
	}

	@Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new GameState());
    }
}
