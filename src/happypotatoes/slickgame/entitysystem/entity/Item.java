package happypotatoes.slickgame.entitysystem.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.ArmourComponent;
import happypotatoes.slickgame.entitysystem.component.ItemComponent;
import happypotatoes.slickgame.entitysystem.component.MeleeWeaponComponent;
import happypotatoes.slickgame.entitysystem.component.RangedWeaponComponent;
import happypotatoes.slickgame.entitysystem.component.StaticRender;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;

public class Item {
	public static Entity create(String id){
		Entity item = new Entity(EntitySystem.getFreeID(), "Item");
		ItemInfo info = ItemSystem.get(id);
		switch(Integer.parseInt(info.get("Type"))){
		//Melee weapon
		case 0: MeleeWeaponComponent mwc = new MeleeWeaponComponent(item, 0, info);
				break;
		//Ranged weapon
		/*case 1: RangedWeaponComponent rwc = new RangedWeaponComponent(item, 0, info);
				break;
		//Armour
		case 2: ArmourComponent ar = new ArmourComponent(item, 0, info);
				break;*/
		}
		Walker walker = new Walker(item, 0, 1, 1);
		WalkerRender walkerRender = new WalkerRender(item, walker, "res/Sprites/Mobs/player/", 12, 50, -.5f, -1.6f);	
		return item;
	}
}
