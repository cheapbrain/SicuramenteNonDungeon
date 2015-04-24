package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.inventory.Slot;
import happypotatoes.slickgame.world.World;

public class Inventory extends Component{
	private Slot[] slots;
	public Inventory(Entity owner, float priority, int nSlots) {
		super(owner, priority);
		slots = new Slot[nSlots];
	}
	public boolean add(String id){
		Slot slot=null;
		for(Slot tmp:slots){
			if(tmp.getItem().equals(id)){
				if(tmp.addItem(id))	return true;
				else return false;
			}
		}
		for(Slot tmp:slots){
			if(tmp.isFree()){
				slot = tmp;
				break;
			}
		}
		if(slot!=null){
			int maxStack = Integer.parseInt(ItemSystem.get(id).get("MaxStack"));
			slot.setSlotItem(id, maxStack);
			slot.addItem(id);
		} else return false;
		return true;
	}
	public String getContent(int pos){
		return slots[pos].getItemId();
	}
	public String takeOut(int pos){
		return slots[pos].getItem();
	}
	public void update(World w, long delta) {
	}
}
