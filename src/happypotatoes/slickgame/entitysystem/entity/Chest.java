package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.entitysystem.component.AIManager;
import happypotatoes.slickgame.entitysystem.component.AIPet;
import happypotatoes.slickgame.entitysystem.component.AIType;
import happypotatoes.slickgame.entitysystem.component.Attack;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.EntityCollision;
import happypotatoes.slickgame.entitysystem.component.Faction;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Interact;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.LoopSound;
import happypotatoes.slickgame.entitysystem.component.Open;
import happypotatoes.slickgame.entitysystem.component.SelectComponent;
import happypotatoes.slickgame.entitysystem.component.StateSound;
import happypotatoes.slickgame.entitysystem.component.StateSoundManager;
import happypotatoes.slickgame.entitysystem.component.Walk;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.component.HitBox;
import happypotatoes.slickgame.entitysystem.component.Movement;
import happypotatoes.slickgame.entitysystem.component.TerrainCollision;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.entitysystem.interact.OpenChest;

public class Chest {
	static float speed = 0.001f;
	int facing;
	
	public static Entity create(int facing, Integer[] integers) {
		Entity e = new Entity(EntitySystem.getInstance().getFreeID(),"Chest");
		HitBox hitBox;
		switch(facing){
		case 2: case 6: hitBox = new HitBox(e, .4f, .7f, 0); break;
		case 0: case 4: hitBox = new HitBox(e, .7f, .3f, 0); break;
		default: hitBox = new HitBox(e, .65f, .65f, 0); break;
		}
		Faction f = new Faction(e, 0, Faction.neutral);
		Walker walker = new Walker(e, 0, 8, 4, facing);
		WalkerRender walkerRender = new WalkerRender(e, walker, "res/Sprites/Statics/chest/", 222, 144, -1.73f, -1.73f);	
		SelectComponent selectComponent = new SelectComponent(e, 0, -.7f,-1.2f, 1.4f, 1.4f);
		Inventory i = new Inventory(e, 0, 2, 2, integers, true);
		Open o = new Open(e, 0,walker, walkerRender);
		OpenChest open = new OpenChest(e, walker, 0);
		
		StateSoundManager soundManager = new StateSoundManager(e, 0, walker,
				new StateSound(e,1,StateSound.ENTER, "Chest.ogg"),
				new StateSound(e,3,StateSound.ENTER, "Chest.ogg")
				);
		
		return e;
	}
	
	public static Entity create(float x, float y, int facing, Integer[] integers){
		Entity e = create(facing, integers);
		e.x=x; e.y=y;
		return e;
	}
}
