package happypotatoes.slickgame.entity;

import inventory.Picker;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EntityKey extends Entity implements Actor{
	private Image texture;
	private String porta;
	private char ID_item=0;
	public EntityKey(float x, float y, String port) {
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
		float distX = (float) this.getX()-player.getX();
		float distY = (float) this.getY()-player.getY();
		float ris= (float) Math.sqrt(distX*distX+distY*distY);
		return ris;
	}
	@Override
	public void use(Entity user, World world) {
		if(user instanceof Picker){
			if(((Picker)user).getBag().add(this)==1){}
			//serve un metodo remove che elimini questa entità dalla lista delle entità
				//world.remove(this);
		}
	}
	public String getPorta() {
		return porta;
	}
	@Override
	public char getIdItem() {
		return ID_item;
	}
}
