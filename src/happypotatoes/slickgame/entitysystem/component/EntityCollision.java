package happypotatoes.slickgame.entitysystem.component;

import java.util.List;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class EntityCollision extends Component {
	HitBox hitBox;
	Movement movement;

	public EntityCollision(Entity owner,  float priority, Movement movement, HitBox hitBox) {
		super(owner, priority);
		this.hitBox = hitBox;
		this.movement = movement;
	}
	
	private int sign(float x) {
		if (x<0)
			return -1;
		else 
			return 1;
	}

	long delay = 30;
	long time = delay;
	@Override
	public void update(World w, long delta) {
		float speed = 0.01f;
		time -= delta;
		if (time<=0) {
			time += delay;
			List<Entity> entities = EntitySystem.getInstance().getAll();
			for (Entity e:entities) {
				if (e==owner)
					continue;
				HitBox hb = e.getComponent(HitBox.class);
				if (hb!=null&&hb.rect.intersect(hitBox.rect)) {
					float dx = owner.x-e.x;
					float dy = owner.y-e.y;
					int sx = sign(dx);
					int sy = sign(dy);
					dx *= dx;
					dy *= dy;
					float d = dx + dy+0.000001f;
					float r = 1.2f*1.2f;
					if (d<r) {
						float ss = r-d;
						ss = ss*ss;
						movement.speedx += sx*dx*ss/d*speed;
						movement.speedy += sy*dy*ss/d*speed;
					}
				}
			}
				
		}
	}

}
