package happypotatoes.slickgame.entitysystem.entity;

import java.io.File;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.MobInput;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Mob {
	public static Entity create(String mod, String name){
		File modFile = new File("./res/"+mod);
		if(!modFile.exists()) return null;
		File mobFile = new File("./res/"+mod+"/Mobs/"+name+".mob");
		if(!mobFile.exists()) return null;
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "mob");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 1);
		MobInput input = new MobInput(e, 0, walker, movement);
		WalkerRender walkerRender = new WalkerRender(e, walker, "./res/"+mod+"/Sprites/Mobs/"+name+"/", 10, 50, -.75f, -.78f);
		return e;
	}
}
