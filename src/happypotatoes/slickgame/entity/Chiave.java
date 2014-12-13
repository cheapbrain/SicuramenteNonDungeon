package happypotatoes.slickgame.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Chiave extends Entity implements Actor{
	Image texture;
	
	public Chiave(float x, float y) {
		try {
			texture = new Image("./res/SteamKeyColorata.png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}		
		this.x=x;
		this.y=y;
		
	}
	public void render() {
		texture.draw(x-.5f, y-1, 0.66f, 0.44f);
	}
	@Override
	public float getDist(Player player) {
		float distX = (float) Math.pow(this.getX()-player.getX(),2);
		float distY = (float) Math.pow(this.getY()-player.getY(),2);
		float ris= (float) Math.sqrt(distX+distY);
		return ris;
	}
}
