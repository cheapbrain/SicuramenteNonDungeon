package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Input;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.gui.GuiSystem;
import happypotatoes.slickgame.gui.InventoryWindow;
import happypotatoes.slickgame.inventory.EquipSlot;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.ItemType;
import happypotatoes.slickgame.world.World;

public class PlayerInput extends Component {
	float destx, desty;
	Entity interactTarget = null;
	
	Walker walker;
	Movement movement;
	float speed = 0.0015f;
	public Entity focus; //temp
	
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
			focus=selected;
		}
		
		Walk walk = owner.getComponent(Walk.class);
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
			walk.dx = destx-owner.x;
			walk.dy = desty-owner.y;
			walk.d = (float)Math.sqrt(walk.dx*walk.dx+walk.dy*walk.dy);
			if (walk.d<1&&interactTarget!=null) {
				walker.setFacing(walk.dx, walk.dy);
				Interact inter = interactTarget.getComponent(Interact.class);
				Health health = interactTarget.getComponent(Health.class);
				if (inter!=null) {
					inter.interact(owner);
					interactTarget = null;
					walker.setStill();
				} 
				else 
				if (health!=null){
					//create particle spostato in Attack
					//owner.getComponent(Attack.class).attack(interactTarget);
					
					focus=interactTarget;
					walker.setAttacking();
					
				}
				movement.speedx = 0;
				movement.speedy = 0;
			}
		}			
			
		if (input.isKeyPressed(Input.KEY_B)&&walker.getState()!=3) {
			GuiSystem.iw.setOpen(!GuiSystem.iw.isOpen());
			GuiSystem.eq.setOpen(!GuiSystem.eq.isOpen());
		}
		
		if (input.isKeyPressed(Input.KEY_K)&&walker.getState()!=3) {
			if(focus!=null)
				if(focus.getComponent(Health.class)!=null)
					focus.getComponent(Health.class).setHealth(0);
		}
		
		if (input.isKeyDown(Input.KEY_D)&&walker.getState()!=3) {
			if(walker.getState()!=5)
				walker.setDefending();
		}
		else{
			if(walker.getState()==4) walker.setStill();
		}
		if(input.isKeyPressed(Input.KEY_A)){
			Equip e = owner.getComponent(Equip.class);
			EquipSlot slot = e.get(ItemType.weapon,1);
			if(slot!=null) {
				e.takeOut(slot);
				
			}
			else e.add(ItemList.sword.getId());
		}
		if(input.isKeyPressed(Input.KEY_S)){
			owner.getComponent(Inventory.class).add(ItemList.key.getId());
		}
	}
	
	
	public void goTo(float dx, float dy, float d) {
		if (d>.1f&&walker.getState()<2) {
			float nsx = dx/d*speed;
			float nsy = dy/d*speed;
			movement.speedx += nsx;
			movement.speedy += nsy;
			walker.setFacing(nsx, nsy);
			walker.setWalking();
			return;
		} 
		else{ 
			if (walker.getState()==1)
				walker.setStill();
			return;
		}
	}
}
