package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.Actor;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

import java.awt.Transparency;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Trap extends Entity implements Actor{
	public int type; //steam, magic, natural
	Image texture;
	public int visibility; //visible, semi-visible, invisible
	
	public Trap(Room a){
		super(false);
		x=(Generator.r.nextInt(a.width)+a.x);
		y=(Generator.r.nextInt(a.height)+a.y);
		type=Generator.r.nextInt(2);
		try {
			texture = new Image("./res/trap"+type+".png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Trap(int x, int y, int type){
		super(false);
		this.x=x;
		this.y=y;
		this.type=type;
		try {
			texture = new Image("./res/trap"+type+".png");
			texture.setFilter(Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void use(Entity user, World world) {
		
	}
	
	@Override
	public void render(){
		texture.draw(x,y,2,2);
	}
	
	
}
