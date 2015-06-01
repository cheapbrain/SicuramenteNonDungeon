package happypotatoes.slickgame.items;

import java.util.HashMap;

public class ItemList {
	private static int freeid = 100;
	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	public static Weapon sword = new Weapon(getFreeId(), "sword", 10f, 5f);
	public static Weapon sword2 = new Weapon(getFreeId(), "sword2", 10f, 5f);
	
	
	
	public static int getFreeId(){
		int x = freeid;
		freeid++;
		return x;
	}
	
	public ItemList(){
		items.put(sword.getId(), sword);
		items.put(sword2.getId(), sword2);
	}
	public static Item getItemForId(int id){
		return items.get(id);
	}
}
