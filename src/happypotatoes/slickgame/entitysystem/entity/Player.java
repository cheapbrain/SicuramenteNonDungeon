package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Attack2;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.LoopSound;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StateSound;
import happypotatoes.slickgame.entitysystem.component.StateSoundManager;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.WeaponRender;
import happypotatoes.slickgame.entitysystem.component.equip.ArmourComponent;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.entitysystem.component.equip.HelmComponent;
import happypotatoes.slickgame.entitysystem.component.equip.SecondHandComponent;
import happypotatoes.slickgame.entitysystem.component.equip.TrinketComponent;
import happypotatoes.slickgame.entitysystem.component.equip.WeaponComponent;
import happypotatoes.slickgame.items.ItemList;

public class Player {
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Player");
		Faction f = new Faction(e, 0, Faction.good);
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .5f, .3f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 6);
		PlayerInput input = new PlayerInput(e, 0, walker, movement);	
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/player/", 204, 222, -1.6f, -2.4f);			
		Walk walk = new Walk(e, 0, walker, walkerRender);
		Attack attack = new Attack(e, 0, walker, walkerRender, 3);
		Attack2 attack2 = new Attack2(e, 0, walker, walkerRender, 3);
		Health health = new Health(e, 0, 100, 2);
		Energy energy = new Energy(e, 0, 100, 8);
		Inventory inventory = new Inventory(e, 0, 4, 4);
		Equip equip = new Equip(e, walker, 0, 1, 5, new Integer[]{ItemList.sword2.getId(), ItemList.armour.getId()}, false);
		Defend defend = new Defend(e, 0, walker, walkerRender, .2f);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.5f,-1.9f, 1f, 2);
		
		StateSoundManager soundManager = new StateSoundManager(e, 0, walker,
				new LoopSound(e,1,300, "step1.ogg", "step2.ogg", "step3.ogg")
				);
		
		return e;
	}
}
