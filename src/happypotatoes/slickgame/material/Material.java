package happypotatoes.slickgame.material;

import org.newdawn.slick.Image;

public class Material {
	private Image texture;
	private boolean isWalkable;
	private int offset;
	private int height;
	
	public Material(Image texture, boolean isWalkable, int offset, int height) {
		this.texture = texture;
		this.isWalkable = isWalkable;
		this.offset = offset;
		this.height = height;
	}
	
	public Image getTexture() {
		return texture; 
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getHeight() {
		return height;
	}

	public boolean isWalkable() {
		return isWalkable;
	}
}
