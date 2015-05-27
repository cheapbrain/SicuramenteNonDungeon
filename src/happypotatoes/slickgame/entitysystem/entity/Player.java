package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.WeaponRender;

public class Player {
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Player");
		Faction f = new Faction(e, 0, Faction.good);
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .5f, .3f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 5);
		PlayerInput input = new PlayerInput(e, 0, walker, movement);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/player/", 204, 222, -1.6f, -2.4f);	
		WalkerRender weaponRender = new WalkerRender(e, walker, "res/Sprites/weapons/", 231, 251, -1.8f, -2.7f);	
		Walk walk = new Walk(e, 0, walker, walkerRender);
		Attack attack = new Attack(e, 0, walker, walkerRender, 3);
		Health health = new Health(e, 0, 100, 1);
		Energy energy = new Energy(e, 0, 100, 8);
		Inventory inventory = new Inventory(e, 0, 16);
		Defend defend = new Defend(e, 0, walker, walkerRender);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.5f,-1.9f, 1f, 2);
		return e;
	}
}
