package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.Actor;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Trap extends Entity implements Actor{
	public int type; //steam, magic, natural
	Image texture;
	private int directions;
	private int state = 0;
	private int txwidth, txheight;
	private int facing;
	private Animation[][] animations;
	private Image[][] sprites;
		
	public Trap(float x, float y, int type, int directions){
	super(true);
	this.x=x;
	this.y=y;
	this.type=type;
	this.directions=directions;
	this.facing=0;
	}
	
	public void setSize(int width, int height, int states) {
		txwidth = width;
		txheight = height;
		this.width = 1;
		this.height = 1;
		sprites = new Image[directions][states];
	}
	

	@Override
	public void use(Entity user, World world) {
		
	}

	public int getDirections() {
		return directions;
	}

	public void setDirections(int directions) {
		this.directions = directions;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public Animation[][] getAnimations() {
		return animations;
	}
	
	public void setSprites(Image texture, int state) {
		SpriteSheet s = new SpriteSheet(texture, txwidth, txheight);
		for (int i=0;i<directions;i++) {
			sprites[i][state]= s.getSprite(i, state);
		}
	}

	public int getTxwidth() {
		return txwidth;
	}

	public void setTxwidth(int txwidth) {
		this.txwidth = txwidth;
	}

	public int getTxheight() {
		return txheight;
	}

	public void setTxheight(int txheight) {
		this.txheight = txheight;
	}
	
	@Override
	public void render(){
		sprites[facing][state].draw(x, y,width,height);
	}
	
}
