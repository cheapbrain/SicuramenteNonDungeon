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

		Image texture = new Image("./res/sheet3.png");
		SpriteSheet sheet = new SpriteSheet(texture, 64, 64);
		texture.setFilter(Image.FILTER_NEAREST);
		materials = new ArrayList<Material>();
		

		materials.add(new Material(null, false, 0, 1));
		
		FLOOR = materials.size();
		for (int y=0;y<6;y++)
			for (int x=0;x<3;x++)
				materials.add(new Material(sheet.getSprite(x, y), true, 0, 1));

		WALLS = materials.size();


		for (int x=0;x<3;x++)
			materials.add(new Material(sheet.getSprite(x+3, 0), false, -1, 1));
		

		for (int x=0;x<3;x++)
			materials.add(new Material(sheet.getSprite(x+3, 1), false, -1, 1));
		

		for (int x=0;x<3;x++)
			materials.add(new Material(sheet.getSubImage((x+3)*64, 128, 64, 128), false, -1, 2));
		
		materials.add(new Material(sheet.getSubImage(3*64, 4*64, 64, 128), false, -1, 2));
		materials.add(new Material(sheet.getSubImage(4*64, 4*64, 64, 128), false, -1, 2));

		materials.add(new Material(sheet.getSprite(3, 6), false, -1, 1));
		materials.add(new Material(sheet.getSprite(4, 6), false, -1, 1));
				
	}

	public static Image getTexture(int i) {
		return materials.get(i).getTexture();
	}
	
	public static Material getMaterial(int i) {
		return materials.get(i);
	}
}
