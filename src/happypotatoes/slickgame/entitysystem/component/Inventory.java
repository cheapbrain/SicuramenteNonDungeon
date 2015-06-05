package happypotatoes.slickgame.entitysystem.component;

import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.interact.OpenChest;
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
	
	//inventario chest/mob
	public Inventory(Entity owner, float priority, int width, int height, Integer[] items, boolean random) {
		this(owner, priority, width, height);
		if(random){
			Random a = new Random();
			int n=a.nextInt(Math.min(width*height+1, items.length+1));
			//se è una chest almeno un item
			if(owner.getComponent(OpenChest.class)!=null) n++;
			for(int i=0; i<n; i++)
				this.add(items[a.nextInt(items.length)]);
		}
		else{
			for(int i=0; i<items.length; i++){
				this.add(items[i]);
			}
		}
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
