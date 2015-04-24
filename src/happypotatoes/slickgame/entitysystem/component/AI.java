package happypotatoes.slickgame.entitysystem.component;

import java.util.Random;

import org.newdawn.slick.Input;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.PlayerAction.State;
import happypotatoes.slickgame.world.World;

public class AI extends Component {
	Walker walker;
	Movement movement;
	int intelligence;
	float speed, time;
	Random r = new Random();
	
	
	
	public AI(Entity owner, float priority, Walker walker, Movement movement,  float speed) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;		
		this.speed=speed;
	}

	@Override
	public void update(World w, long delta) {
		
	}
	
	public void turn(String s){
		float angle = (float) Math.atan2(movement.speedx,movement.speedy);

		switch(s){
		case "right": case "r":
			angle-=Math.PI/4;
			break;
		case "left": case "l":
			angle+=Math.PI/4;
			break;	
		}
		movement.speedx= (float) (Math.sin(angle)*speed);
		movement.speedy= (float) (Math.cos(angle)*speed);
		walker.setFacing(movement.speedx,movement.speedy);
		
		
	}
	
}
