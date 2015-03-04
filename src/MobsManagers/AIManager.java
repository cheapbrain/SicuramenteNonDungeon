package MobsManagers;

import java.util.List;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Mob;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.world.World;

public class AIManager {
	public static void update(Entity entity, int delta, World world){
		boolean playerFound=false;
		List<Entity> lista = world.getEntities(new Circle(entity.getX(),entity.getY(),4));
		for(Entity e:lista){
			if(e instanceof Player){
				((Mob) entity).follow(e);
				playerFound=true;
			}
			if(!playerFound){
				((Mob) entity).stop();
				((Mob) entity).wander(delta);
			}
		}
	}
}
