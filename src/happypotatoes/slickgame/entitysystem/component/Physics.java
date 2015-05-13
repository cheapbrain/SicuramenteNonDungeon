package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Physics extends Component{
	public float speedx, speedy, speedz;
	public float oldx, oldy, oldz, z, friction;
	public Physics(Entity owner, float priority, float speedx, float speedy, float speedz, float z,
			float friction) {
		super(owner, priority);
		this.z=z;
		this.speedx = speedx;
		this.speedy = speedy;
		this.speedz = speedz;
		this.friction = friction;
	}
	public void update(World w, long delta) {
		
		oldx = owner.x;
		oldy = owner.y;
		oldz = z;
		owner.x += speedx*delta;
		owner.y += speedy*delta;
		z += speedz*delta;
		speedx *= friction;
		speedy *= friction;
		speedz *= friction;
		speedz-= 0.00098f/4;
		if(z<0) speedz=Math.abs(speedz)*.8f;
	}

}
