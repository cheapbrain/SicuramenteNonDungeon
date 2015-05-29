package happypotatoes.slickgame.items;

import java.util.HashMap;

public class ItemType {
	public static HashMap<Integer, String> types = new HashMap<Integer, String>();
	public static int junk=0, weapon=1, armour=2, helm=3, trinket=4, consumable=5, reagent=6, secondhand=7; 

	public ItemType(){
		types.put(junk, "junk");
		types.put(weapon, "weapon");
		types.put(armour, "armour");
		types.put(helm, "helm");
		types.put(trinket, "trinket");
		types.put(consumable, "consumable");
		types.put(reagent, "reagent");
		types.put(secondhand, "secondhand");
	}
	
	public static String getTypeName(int type){
		return types.get(type);
	}
}
