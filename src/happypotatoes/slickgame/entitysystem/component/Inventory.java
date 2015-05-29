package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.inventory.Slot;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.world.World;

public class Inventory extends Component{
	public Slot[][] slots;
	public int width, height;
	
	public Inventory(Entity owner, float priority, int width, int height) {
		super(owner, priority);
		this.width = width;
		this.height = height;
		slots = new Slot[width][height];
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				slots[i][j] = new Slot(this);
	}
	
	public Slot getSlot(int x, int y){
		return slots[x][y];
	}
	public boolean add(int id){
		Slot slot=null;
		for(Slot[] asd:slots){
			for(Slot tmp:asd){
				if(tmp.getItemId()==id){
					if(tmp.addItem(id))	return true;
				}
			}
		}
		for(Slot[] asd:slots){
			for(Slot tmp:asd){
				if(tmp.isFree()){
					slot = tmp;
					break;
				}
			}
		}
		if(slot!=null){
			int maxStack = ItemList.getItemForId(id).getMaxStack();
			slot.setSlotItem(id, maxStack);
			slot.addItem(id);
		} else return false;
		return true;
	}
	public int getContent(int x, int y){
		return slots[x][y].getItemId();
	}
	public int takeOut(int x, int y){
		return slots[x][y].popItemId();
	}
	public int takeOut(Slot slot){
		return slot.popItemId();
	}
	public void update(World w, long delta) {
	}
}
