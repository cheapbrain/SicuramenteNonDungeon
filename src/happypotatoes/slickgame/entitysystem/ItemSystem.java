package happypotatoes.slickgame.entitysystem;

import happypotatoes.slickgame.entitysystem.entity.ItemInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.Image;

public class ItemSystem {
	private static Map<String, ItemInfo> items = new HashMap();
	public static void load(){
		File res = new File("./res");
		File[] mods = res.listFiles();
		for (int i = 0; i<mods.length; i++){
			if(mods[i].isDirectory()){
				File tmp = new File(mods[i].getPath()+"/Items");
				if (tmp.exists()) loadItems(tmp);
			}
		}
	}
	public static Image getTexture(String id){
		return items.get(id).getTexture();
	}
	private static void loadItems(File file) {
		File[] modItems = file.listFiles();
		for(int i=0; i<modItems.length;i++){
			items.put(modItems[i].getPath(), new ItemInfo(modItems[i]));
		}
	}
	public static ItemInfo get(String id) {
		return items.get(id);
	}
}
