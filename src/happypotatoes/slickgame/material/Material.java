package happypotatoes.slickgame.material;

import org.newdawn.slick.Image;

public class Material {
	private Image texture;
	private boolean isWalkable;
	
	public Material(Image texture, boolean isWalkable) {
		this.texture = texture;
		this.isWalkable = isWalkable;
	}
	
	public Image getTexture() {
		return texture; 
	}

	public boolean isWalkable() {
		return isWalkable;
	}
}
