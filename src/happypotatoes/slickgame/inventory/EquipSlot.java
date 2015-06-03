package happypotatoes.slickgame.inventory;

import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.items.ItemList;

public class EquipSlot{
	public Equip owner;
	private boolean free;
	private int itemId;
	private int type;
	
	public EquipSlot(Equip owner, int type) {
		this.owner=owner;
		setType(type);
		itemId=0;
		free=true;
	}
	

	public Equip getOwner(){
		return owner;
	}
	

	public boolean isFree(){
		return free;
	}
	
	public boolean canEquip(int id){
		return (ItemList.getItemForId(id).getType()==this.getType());
	}
	public boolean canEquip(Item item){
		return (item.getType()==this.getType());
	}
	
	public boolean addItem(int id){	
		if(isFree()&&canEquip(id)){
			itemId=id;
			setFree(false);
			return true;
		}
		else return false;
	}
	
	public int popItemId(){
		int tmp=itemId;
		itemId=0;
		setFree(true);
		return tmp;
	}
	public int getItemId() {
		return itemId;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
