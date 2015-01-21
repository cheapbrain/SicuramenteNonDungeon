package happypotatoes.slickgame.material;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MaterialManager {
	private static Material[] materials;
		
	public static void init() throws SlickException {

		Image texture = new Image("./res/sheet.png");
		texture.setFilter(Image.FILTER_NEAREST);
		materials = new Material[2];
		
		materials[0] = new Material(texture.getSubImage(0, 64, 64, 64), true, 0, 1);
	
		materials[1] = new Material(texture.getSubImage(64, 64, 64, 64), false, 0, 1);
	}

	public static Image getTexture(int i) {
		return materials[i].getTexture();
	}
	
	public static Material getMaterial(int i) {
		return materials[i];
	}
}
