package happypotatoes.slickgame.entitysystem.component.equip;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.Walker;
import happypotatoes.slickgame.entitysystem.component.WalkerRender;
import happypotatoes.slickgame.inventory.EquipSlot;
import happypotatoes.slickgame.inventory.Slot;
import happypotatoes.slickgame.items.Item;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.ItemType;
import happypotatoes.slickgame.world.World;

public class Equip extends Component {
	public EquipSlot[][] slots;
	public int width, height;
	
	public Equip(Entity owner, float priority, int width, int height) {
		super(owner, priority);
		this.width = width;
		this.height = height;
		this.slots = new EquipSlot[width][height];
		slots[0][0] = new EquipSlot(this, ItemType.helm);
		slots[0][1] = new EquipSlot(this, ItemType.armour);
		slots[0][2] = new EquipSlot(this, ItemType.weapon);
		slots[0][3] = new EquipSlot(this, ItemType.secondhand);
		slots[0][4] = new EquipSlot(this, ItemType.trinket);
	}
	
	public EquipSlot getSlot(int x, int y){
		return slots[x][y];
	}
	
	public boolean add(int id){		
		for(EquipSlot[] asd:slots){
			for(EquipSlot tmp:asd){
				Item i = ItemList.getItemForId(id);
				if(tmp.isFree()&&(tmp.getType()==i.getType())){
					tmp.addItem(id);
					owner.addComponent(new WalkerRender(owner, owner.getComponent(Walker.class),
							"res/Sprites/"+ItemType.getTypeName(i.getType())+"/"+i.getName()+"/", 231, 251, -1.8f, -2.7f));
					return true;
				}
			}
		}
		return false;
	}
	
	public int getContent(int x, int y){
		return slots[x][y].getItemId();
	}
	public int takeOut(int x, int y){
		return slots[x][y].popItemId();
	}
	public int takeOut(EquipSlot slot){
		owner.removeComponent(owner.getComponent(WalkerRender.class, 2));
		return slot.popItemId();
	}
	public void update(World w, long delta) {
		/*EquipSlot slot;
		
		for(int i=0; i<slots.length; i++){
			for(int j=0; j<slots[i].length; j++){
				slot=slots[i][j];
				WalkerRender c = new WalkerRender(owner, owner.getComponent(Walker.class), "res/Sprites/weapons/", 231, 251, -1.8f, -2.7f);
				owner.addComponent(c);
			}
		}*/
	}
	
	
	public EquipSlot get(int type, int n) {
		int x = 0;
		for(EquipSlot[] asd:slots){
			for(EquipSlot tmp:asd){
				if(tmp.getType()==type){
					x++;
					if(x==n){
						if(tmp.isFree())
							return null;
						else return tmp;
					}
				}
			}
		}
		return null;
	}

}
