package happypotatoes.slickgame.entitysystem.component;

import java.util.ArrayList;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.inventory.Slot;
import happypotatoes.slickgame.world.World;

public class Inventory extends Component{
	private Slot[] slots;
	public Inventory(Entity owner, float priority, int nSlots) {
		super(owner, priority);
		slots = new Slot[nSlots];
	}
	public boolean add(String id, int maxStack){
		Slot slot=null;
		for(Slot tmp:slots){
			if(tmp.isFree()){
				slot = tmp;
				break;
			}
		}
		if(slot!=null){
			slot.setSlotItem(id, maxStack);
			slot.addItem(id);
		} else return false;
		return true;
	}
	public String get(int pos){
		return slots[pos].getItem();
	}
	public void update(World w, long delta) {
		
	}

}
