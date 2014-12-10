package happypotatoes.slickgame.world;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MaterialManager {
	private static Material[] materials;
		
	public static void init() throws SlickException {
		
		Image texture = new Image("./res/dirt.png");
		texture.setFilter(Image.FILTER_NEAREST);
		materials = new Material[2];
		materials[0] = new Material(null);
		materials[1] = new Material(texture);
	}

	public static Image getTexture(int i) {
		return materials[i].getTexture();
	}
}
