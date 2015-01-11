package happypotatoes.slickgame.entity;

import inventory.Picker;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Chiave extends Entity implements Actor{
	private Image texture;
	private String porta;
	public Chiave(float x, float y, String port) {
		try {
			texture = new Image("./res/SteamKeyColorata.png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}		
		porta=port;
		this.x=x;
		this.y=y;
		
	}
	public void render() {
		texture.draw(x-.5f, y-1, 0.66f, 0.44f);
	}
	@Override
	public float getDist(Entity player) {
		float distX = (float) Math.pow(this.getX()-player.getX(),2);
		float distY = (float) Math.pow(this.getY()-player.getY(),2);
		float ris= (float) Math.sqrt(distX+distY);
		return ris;
	}
	@Override
	public void use(Entity user, World world) {
		if(user instanceof Picker){
			if(((Picker)user).getBag().add(this)==1)
			//serve un metodo remove che elimini questa entità dalla lista delle entità
				world.remove(this);
		}
	}
	public String getPorta() {
		return porta;
	}
}
