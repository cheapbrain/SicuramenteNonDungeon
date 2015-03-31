package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Input;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class PlayerInput extends Component {
	Walker walker;
	Movement movement;
	float speed = 0.005f;
	public PlayerInput(Entity owner, float priority, Walker walker, Movement movement) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;
	}

	@Override
	public void update(World w, long delta) {
		Input input = w.container.getInput();
		movement.speedy = 0;
		movement.speedx = 0;
		if (input.isKeyDown(Input.KEY_W)) {
			movement.speedy = -speed;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			movement.speedy = speed;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			movement.speedx = -speed;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			movement.speedx = speed;
		}
		if (movement.speedx!=0||movement.speedy!=0)
			walker.setFacing(movement.speedx, movement.speedy);
	}

}
