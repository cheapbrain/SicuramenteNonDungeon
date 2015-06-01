package happypotatoes.slickgame.items;

import happypotatoes.slickgame.geom.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ItemSprite {
	static HashMap<String, Image> sprites = new HashMap<String, Image>();
	List<Image> cache = new LinkedList<Image>();
	
	public ItemSprite(){
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		File folder = new File("./res/Sprites/");
		System.out.println(folder.getAbsolutePath());
		Stack<File> folders = new Stack<File>();
		folders.push(folder);
		
		while(!folders.isEmpty()) {
			File path = folders.pop();
			File[] files = path.listFiles();
			for (File file:files)
				if (file.isDirectory()) {
					folders.push(file);
				} else {
					String name = file.getName();
					if (name.endsWith(".png"))
						try {
							images.add(new Image(file.getAbsolutePath()));
							sprites.put(file.getAbsolutePath(), new Image(file.getAbsolutePath()));
							System.out.println(file.getAbsolutePath());
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
		}
	}
	
	public static Image load(String s){
		File f = new File("./res/Sprites/");
		return sprites.get(f.getAbsolutePath()+"\\"+s);
	}
}
