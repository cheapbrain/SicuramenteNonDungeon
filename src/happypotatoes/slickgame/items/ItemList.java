package happypotatoes.slickgame.items;

import java.util.HashMap;
import java.util.Set;

public class ItemList {
	private static int freeid = 100;
	public static HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	
	public static Weapon sword = new Weapon(getFreeId(), "sword", 3f, 2f, 231, 251, -1.8f, -2.7f);
	public static Weapon sword2 = new Weapon(getFreeId(), "sword2", 1.5f, 3f, 236, 257, -1.8f, -2.7f);
	public static Armour armour = new Armour(getFreeId(), "armour", 2f, 3f, 158, 195, -1.25f, -2.25f);
	public static Potion health_potion = new Potion(getFreeId(), "health_potion", Potion.health, 30f);
	public static Potion energy_potion = new Potion(getFreeId(), "energy_potion", Potion.energy, 30f);
	public static Junk key = new Junk(getFreeId(), "key");
	public static Junk goldIngot = new Junk(getFreeId(), "goldIngot");
	public static Junk greenGem = new Junk(getFreeId(), "greenGem");
	
	public static int getFreeId(){
		int x = freeid;
		freeid++;
		return x;
	}
	
	public ItemList(){
		items.put(sword.getId(), sword);
		items.put(sword2.getId(), sword2);
		items.put(armour.getId(), armour);
		items.put(health_potion.getId(), health_potion);
		items.put(energy_potion.getId(), energy_potion);
		items.put(key.getId(), key);
		items.put(goldIngot.getId(), goldIngot);
		items.put(greenGem.getId(), greenGem);
	}
	
	public static Item getItemForId(int id){
		return items.get(id);
	}
	
	public static Integer[] getAllIds(){
		Integer[] ids= new Integer[items.size()];
		for(int i=0; i<items.size(); i++)
			ids=items.keySet().toArray(ids);
		return ids;
	}
}
