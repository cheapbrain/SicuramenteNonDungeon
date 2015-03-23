package happypotatoes.slickgame.entitysystem;

public class EntitySystem {
	private static long nextid = 1;
	
	public static long getFreeID() {
		return nextid++;
	}
}
