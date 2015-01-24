package happypotatoes.slickgame.world;

import happypotatoes.slickgame.entity.Entity;

public class EntityCommand {
	public static final int ADD = 0;
	public static final int REMOVE = 1;
	public static final int REFRESH = 2;
	public Entity e;
	public int action;
	public EntityCommand(Entity e, int action) {
		this.e = e;
		this.action = action;
	}
}
