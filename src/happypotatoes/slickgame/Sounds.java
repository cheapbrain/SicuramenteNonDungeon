package happypotatoes.slickgame;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {
	private static final String FOLDER = "res/sounds/";
	private static Sounds instance;
	
	private Sounds() {
		
	}
	
	public static Sounds getInstance() {
		if (instance==null) instance = new Sounds();
		return instance;
	}
	
	private Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	public void playSound(String name, float x, float y) {
		Sound sound = sounds.get(name);
		if (sound==null) {
			try {
				sound = Loader.sound(FOLDER+name);
				sounds.put(name, sound);
			} catch (SlickException e) {
				e.printStackTrace();
				return;
			}
		}
		sound.playAt(x, 0, y);
	}
	
	public void stopSound(String name) {
		Sound sound = sounds.get(name);
		if (sound!=null&&sound.playing()) {
			sound.stop();
		}
	}

}
