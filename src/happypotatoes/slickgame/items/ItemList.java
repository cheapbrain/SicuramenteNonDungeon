package happypotatoes.slickgame.items;

import java.util.HashMap;

public class ItemList {
	private static int freeid = 100;
	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	public static Weapon sword = new Weapon(getFreeId(), "sword", 10f, 5f);
	public static Weapon sword2 = new Weapon(getFreeId(), "sword2", 10f, 5f);
	public static Trinket health_potion = new Trinket(getFreeId(), "health_potion");
	public static Trinket energy_potion = new Trinket(getFreeId(), "energy_potion");
	
	
	public static int getFreeId(){
		int x = freeid;
		freeid++;
		return x;
	}
	
	public ItemList(){
		items.put(sword.getId(), sword);
		items.put(sword2.getId(), sword2);
		items.put(health_potion.getId(), health_potion);
		items.put(energy_potion.getId(), energy_potion);
	}
	
	public static Item getItemForId(int id){
		return items.get(id);
	}
}
