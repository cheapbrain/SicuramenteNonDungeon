package happypotatoes.slickgame.entitysystem.entity;


import happypotatoes.slickgame.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemInfo {
	private Map<String, String> info = new HashMap();
	private Image texture;
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
		String name = fileInfo.getPath().split(".item")[0];
		name =name.substring(name.lastIndexOf("\\"));
		try {
			texture = Loader.image((fileInfo.getPath().split("Items")[0])+"Sprites/Items"+name+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public String get(String property) {
		return info.get(property);
	}
	public Image getTexture(){
		return texture;
	}
}
