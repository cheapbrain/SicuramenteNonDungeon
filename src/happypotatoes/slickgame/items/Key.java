package happypotatoes.slickgame.items;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.Item;

public class Key{
	public static Entity create(int id){
		Entity key = Item.create(id);
		return key;
		
	}
}
