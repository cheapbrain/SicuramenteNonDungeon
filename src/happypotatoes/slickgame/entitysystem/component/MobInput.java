package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class MobInput extends Component{
	Walker walker;
	Movement movement;
	float speed = 0.004f;
	public MobInput(Entity owner, float priority, Walker walker, Movement movement) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;
	}
	public void update(World w, long delta) {
		movement.speedx = speed;
		if (movement.speedx!=0||movement.speedy!=0)
			walker.setFacing(movement.speedx, movement.speedy);
	}
}
