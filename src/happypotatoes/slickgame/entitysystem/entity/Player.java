package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Player {
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getFreeID(), "Player");
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .4f, .4f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 3);
		PlayerInput input = new PlayerInput(e, 0, walker, movement);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/player/", 12, 50, -.5f, -1.6f);	
		Attack attack = new Attack(e, 0, walker, walkerRender, 20);
		Health health = new Health(e, 0, 100, 0);
		Energy energy = new Energy(e, 0, 100, 5);
		Inventory inventory = new Inventory(e,0, 10);
		return e;
	}
}
