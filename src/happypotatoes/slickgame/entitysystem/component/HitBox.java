package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.world.World;

public class HitBox extends Component {
	public Rectangle rect;
	public float sizex, sizey;
	
	public HitBox(Entity owner, float sizex, float sizey, float priority) {
		super(owner, priority);
		this.sizex=sizex;
		this.sizey=sizey;
		rect = new Rectangle(owner.x-sizex, owner.y-sizey, sizex*2, sizey*2);
	}

	@Override
	public void update(World w, long delta) {
		rect.setPosition(owner.x-sizex, owner.y-sizey);
	}
	/*
	public float getSizex() {
		
		return sizex[getD()];
	}

	public void setSizex(float sizex[]) {
		this.sizex = sizex;
	}

	public float getSizey() {
		return sizey[getD()];
	}

	public void setSizey(float sizey[]) {
		this.sizey = sizey;
	}
	
	public int getD(){
		int d = owner.getComponent(Walker.class).facing;
		switch (d){
		case 3:d=1; break; 
		case 4:d=0; break;
		case 5:d=1; break; 
		case 6:d=2; break;
		case 7:d=1; break; 
		}
		return d;
	}*/
}
