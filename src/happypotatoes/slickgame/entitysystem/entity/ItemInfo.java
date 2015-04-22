package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.component.ArmourComponent;
import happypotatoes.slickgame.entitysystem.component.MeleeWeaponComponent;
import happypotatoes.slickgame.entitysystem.component.RangedWeaponComponent;
import happypotatoes.slickgame.entitysystem.component.StaticRender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class ItemInfo {
	private Map<String, String> info = new HashMap();
	public ItemInfo(File fileInfo){
		Properties props = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(fileInfo);
			props.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String key : props.keySet().toArray(new String[0])) {
			String[] tmp = new String[2];
			info.put(key, props.getProperty(key));
		}
	}
	public String get(String property) {
		return info.get(property);
	}
}
