package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIFighter;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Defend;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.LoopSound;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StateSound;
import happypotatoes.slickgame.entitysystem.component.StateSoundManager;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.items.ItemList;

public class FighterEntity {
	static float speed = 0.001f;
	
	public static Entity create() {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(), "Fighter");
		Faction f = new Faction(e, 0, Faction.bad);
		Movement movement = new Movement(e, 0);
		HitBox hitBox = new HitBox(e, .5f, .3f, 0);
		TerrainCollision terrainCollision = new TerrainCollision(e, 0, movement, hitBox);
		EntityCollision entityCollision = new EntityCollision(e, 0, movement, hitBox);
		Walker walker = new Walker(e, 0, 8, 5);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Mobs/fighterTest/", 204, 222, -1.6f, -2.4f);
		Equip equip = new Equip(e, walker, 0, 1, 5, new Integer[]{ItemList.sword.getId(), ItemList.sword2.getId()}, true);
		Inventory inventory = new Inventory(e, 0, 3, 3, new Integer[]{ItemList.key.getId()}, true);
		AI intelligence = (AI) (new AIFighter(e, 0f, walker, movement, speed));
		Walk walk = new Walk(e, 0, walker, walkerRender);
		Attack attack = new Attack(e, 0, walker, walkerRender, 1);
		Health health = new Health(e, 0, 100, 0);
		Energy energy = new Energy(e, 0, 100, 2);
		Defend defend = new Defend(e, 0, walker, walkerRender, .2f);
		SelectComponent selectComponent = new SelectComponent(e, 0, -.4f,-1.8f, .8f, 2);
		
		StateSoundManager soundManager = new StateSoundManager(e, 0, walker,
				new LoopSound(e,1,300, "step1.ogg", "step2.ogg", "step3.ogg"),
				new StateSound(e,3,StateSound.ENTER, "Evil_Player_Death.ogg"),
				new LoopSound(e,1,3000, "Evil_Player.ogg")
				);
		
		return e;
	}
	
	public static Entity create(float x, float y ){
		Entity e = create();
		e.x=x; e.y=y;
		return e;
	}
}
