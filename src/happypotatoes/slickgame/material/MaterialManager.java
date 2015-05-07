package happypotatoes.slickgame.material;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MaterialManager {
	private static List<Material> materials;
	public static int FLOOR;
	public static int WALLS;
		
	public static void init() throws SlickException {

		Image texture = new Image("./res/sheet2.png");
		SpriteSheet sheet = new SpriteSheet(texture, 64, 64);
		texture.setFilter(Image.FILTER_NEAREST);
		materials = new ArrayList<Material>();
		

		materials.add(new Material(null, false, 0, 1));
		
		FLOOR = materials.size();
		for (int y=0;y<6;y++)
			for (int x=0;x<3;x++)
				materials.add(new Material(sheet.getSprite(x, y), true, 0, 1));

		WALLS = materials.size();
		for (int y=0;y<4;y++)
			for (int x=0;x<3;x++)
				materials.add(new Material(sheet.getSprite(x+3, y), false, 0, 1));

		for (int y=0;y<2;y++)
			for (int x=0;x<2;x++)
				materials.add(new Material(sheet.getSprite(x+3, y+3), false, 0, 1));
				
	}

	public static Image getTexture(int i) {
		return materials.get(i).getTexture();
	}
	
	public static Material getMaterial(int i) {
		return materials.get(i);
	}
}
