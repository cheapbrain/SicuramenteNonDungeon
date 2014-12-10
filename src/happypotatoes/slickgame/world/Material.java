package happypotatoes.slickgame.world;

import org.newdawn.slick.Image;

public class Material {
	private Image texture;
	
	public Material(Image texture) {
		this.texture = texture;
	}
	
	public Image getTexture() {
		return texture;
	}
}
