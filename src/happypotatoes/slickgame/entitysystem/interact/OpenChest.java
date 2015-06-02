package happypotatoes.slickgame.entitysystem.interact;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Interact;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.entity.Chest;
import happypotatoes.slickgame.gui.Component;
import happypotatoes.slickgame.gui.GuiSystem;
import happypotatoes.slickgame.gui.InventoryWindow;
import happypotatoes.slickgame.gui.UI;

public class OpenChest extends Interact {
	Walker walker;
	InventoryWindow iv;
	public Entity agent;
	public boolean opening=false, closing=false;
	public boolean used=false;
	public OpenChest(Entity owner, Walker walker, float priority) {
		super(owner, priority);
		this.walker = walker;
	}

	@Override
	public void interact(Entity e) {
		
		if(!used){
			used=true;
			iv=content(GuiSystem.u, owner);
		}
		agent=null;
		if(walker.getState()==0){		
			openView();
			agent = e;u
		}
		if(walker.getState()==2){
			closeView();
		}
	}
	
	private InventoryWindow content(UI ui, Entity chest){
		InventoryWindow iw = new InventoryWindow("chest", 0,0, ((Inventory) chest.getComponent(Inventory.class)));
		iw.setVerticalAlign(Component.NORTH);
		iw.setHorizontalAlign(Component.EAST);
		iw.setVisible(false);
		ui.add(iw);
		return iw;
	}
	
	public void openView(){
		iv.setOpen(true);
		walker.setState(1);
		opening=true;
	}
	
	public void closeView(){
		iv.setOpen(false);
		walker.setState(1);
		closing=true;
		
	}
}
