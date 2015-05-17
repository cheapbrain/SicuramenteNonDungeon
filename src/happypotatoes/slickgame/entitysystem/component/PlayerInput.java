package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Input;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.ParticleBuilder;
import happypotatoes.slickgame.world.World;

public class PlayerInput extends Component {
	float destx, desty;
	Entity interactTarget = null;
	
	Walker walker;
	Movement movement;
	float speed = 0.0015f;
	
	public PlayerInput(Entity owner, float priority, Walker walker, Movement movement) {
		super(owner, priority);
		this.walker = walker;
		this.movement = movement;
		
	}
	public void update(World w, long delta) {
		Camera c = Camera.camera;
		Input input = w.container.getInput();
		
		if (Math.abs(movement.speedx)<0.0001&&Math.abs(movement.speedy)<0.0001&&walker.getState()==1) {
			walker.setStill();
		}
		
		float selectx = input.getMouseX()/(float)c.getUnit()+c.getRekt().x0;
		float selecty = input.getMouseY()/(float)c.getUnit()+c.getRekt().y0;
		
		Entity selected = null;
		float depth = Float.NEGATIVE_INFINITY;
		for (Entity e:EntitySystem.getInstance().getAll()) {
			if (e==owner)
				continue;
			SelectComponent rc = e.getComponent(SelectComponent.class);
			if (rc!=null&&rc.getRect().contain(selectx, selecty)&&rc.depth>=depth) {
				depth = rc.depth;
				selected = e;
			}
		}
		EntityRenderer.hover = selected;
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			EntityRenderer.click = selected;
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			
			if (walker.getState()==0)
				walker.setWalking();
			interactTarget = selected;
			if (selected!=null) {
				destx = selected.x;
				desty = selected.y;
			} else {
				destx = selectx;
				desty = selecty;
			}
		}
		
		if(walker.getState()==1) {
			float dx = destx-owner.x;
			float dy = desty-owner.y;
			float d = (float)Math.sqrt(dx*dx+dy*dy);
			float msx = 0;
			float msy = 0;
			if (d<1&&interactTarget!=null) {
				
				walker.setFacing(dx, dy);
				Interact inter = interactTarget.getComponent(Interact.class);
				Health health = interactTarget.getComponent(Health.class);
				if (inter!=null) {
					inter.interact(owner);
					interactTarget = null;
					walker.setStill();
				} else if (health!=null){
					for(int i=0;i<10;i++){
						float angle = (float) (Math.random()+Math.PI/5*i);
						float speedx = (float) Math.cos(angle)*.004f;
						float speedy = (float) Math.sin(angle)*.004f;
						EntitySystem.getInstance().addEntity(ParticleBuilder.create("./res/blood.png", interactTarget.x, interactTarget.y, 1, 500, speedx, speedy, 0, 0.99999f, .1f));
					}
					
					owner.getComponent(Attack.class).attack(interactTarget);
				}
				movement.speedx = 0;
				movement.speedy = 0;
			}
			else if (d<.1) {
				walker.setStill();
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
			
		if (input.isKeyDown(Input.KEY_E)&&walker.getState()<2) {
			//walker.state=2;
			//((Energy) owner.getComponent(Energy.class)).setEnergy(((Energy) owner.getComponent(Energy.class)).getEnergy()-20);
		}
		
		if (input.isKeyDown(Input.KEY_D)&&walker.getState()!=3) {
			if(walker.getState()!=5)
				walker.setDefending();
		}
		else{
			if(walker.getState()==4||walker.getState()==5) walker.setStill();
		}
		if(input.isKeyPressed(Input.KEY_A)){
			((Inventory) owner.getComponent(Inventory.class)).add(".\\res\\MyMod\\Items\\Spada.item");
		}
		if(input.isKeyPressed(Input.KEY_S)){
			((Inventory) owner.getComponent(Inventory.class)).add(".\\res\\MyMod\\Items\\Spada2.item");
		}
	}
}
