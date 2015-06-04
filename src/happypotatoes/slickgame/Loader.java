package happypotatoes.slickgame;

import java.io.File;
import java.util.Stack;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Loader {
	
	public static Stack<File> getFiles() {
		Stack<File> stack = new Stack<File>();
		Stack<File> folders = new Stack<File>();
		folders.add(new File("res/"));
		while(!folders.isEmpty()) {
			File[] files = folders.pop().listFiles();
			for (File file:files)
				if (file.isDirectory()) {
					folders.push(file);
				} else {
					stack.push(file);
				}
		}
		
		return stack;
	}
	
	public static void preload(String path) throws SlickException {
		if (path.endsWith(".png"))
			image(path);
		else if (path.endsWith(".ogg"))
			sound(path);
	}
	

	public static Image image(String path) throws SlickException {
		return new Image(new File(path).getAbsolutePath());
	}

	public static Sound sound(String path) throws SlickException {
		return new Sound(new File(path).getAbsolutePath());
	}
}
