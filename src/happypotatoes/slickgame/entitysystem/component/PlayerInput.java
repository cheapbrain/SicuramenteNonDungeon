package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.world.World;

public class PlayerInput extends Component {
	float destx, desty;
	
	Walker walker;
	Movement movement;
	float speed = 0.001f;
	
	public PlayerInput(Entity owner, float priority, Walker walker, Movement movement) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;
		
	}
	public void update(World w, long delta) {
		Camera c = Camera.camera;
		Input input = w.container.getInput();
		
		if (Math.abs(movement.speedx)<0.0001&&Math.abs(movement.speedy)<0.0001&&walker.state==1) {
			walker.state = 0;
		}
		
		float selectx = input.getMouseX()/(float)c.getUnit()+c.getRekt().x0;
		float selecty = input.getMouseY()/(float)c.getUnit()+c.getRekt().y0;
		
		Entity selected = null;
		float depth = Float.NEGATIVE_INFINITY;
		for (Entity e:EntitySystem.getInstance().getAll()) {
			RenderComponent rc = e.getComponent(RenderComponent.class);
			if (rc!=null&&rc.getRect().contain(selectx, selecty)&&rc.depth>=depth) {
				depth = rc.depth;
				selected = e;
			}
		}
		EntityRenderer.hover = selected;
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			EntityRenderer.click = selected;
		
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			destx = selectx;
			desty = selecty;
			if (walker.state==0)
				walker.state = 1;
		}
		
		if(walker.state==1) {
			float dx = destx-owner.x;
			float dy = desty-owner.y;
			float d = (float)Math.sqrt(dx*dx+dy*dy);
			float msx = 0;
			float msy = 0;
			if (d<.1) {
				walker.state = 0;
				movement.speedx = 0;
				movement.speedy = 0;
			} else {
				msx = dx/d*speed;
				msy = dy/d*speed;
				movement.speedx += msx;
				movement.speedy += msy;
			}
			if (msx!=0||msy!=0)
				walker.setFacing(msx, msy);
		}			
			
		if (input.isKeyDown(Input.KEY_E)&&walker.state<2) {
			walker.state=2;
			//((Energy) owner.getComponent(Energy.class)).setEnergy(((Energy) owner.getComponent(Energy.class)).getEnergy()-20);
		}
		
		if (input.isKeyDown(Input.KEY_D)&&walker.state<2) {
			walker.state=4;
		}
		else{
			if(walker.state==4) walker.state=0;
		}
		if(input.isKeyPressed(Input.KEY_A)){
			((Inventory) owner.getComponent(Inventory.class)).add(".\\res\\MyMod\\Items\\Spada.item");
		}
		if(input.isKeyPressed(Input.KEY_S)){
			((Inventory) owner.getComponent(Inventory.class)).add(".\\res\\MyMod\\Items\\Spada2.item");
		}
	}
}
