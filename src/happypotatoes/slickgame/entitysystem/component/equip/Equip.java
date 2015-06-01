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
	public EquipRender[][] render;
	public int width, height;
	
	public Equip(Entity owner, Walker walker, float priority, int width, int height) {
		super(owner, priority);
		this.width = width;
		this.height = height;
		this.slots = new EquipSlot[width][height];
		this.render = new EquipRender[width][height];
				
		slots[0][0] = new EquipSlot(this, ItemType.helm);
		render[0][0] = new EquipRender(owner, walker, 231, 251, -1.8f, -2.7f);
		slots[0][1] = new EquipSlot(this, ItemType.armour);
		render[0][1] = new EquipRender(owner, walker, 231, 251, -1.8f, -2.7f);
		slots[0][2] = new EquipSlot(this, ItemType.weapon);
		render[0][2] = new EquipRender(owner, walker, 231, 251, -1.8f, -2.7f);
		slots[0][3] = new EquipSlot(this, ItemType.secondhand);
		render[0][3] = new EquipRender(owner, walker, 231, 251, -1.8f, -2.7f);
		slots[0][4] = new EquipSlot(this, ItemType.trinket);
		render[0][4] = new EquipRender(owner, walker, 231, 251, -1.8f, -2.7f);
		
	}
	
	public EquipSlot getSlot(int x, int y){
		return slots[x][y];
	}
	
	public boolean add(int id){		
		for(int y=0; y<slots.length; y++)
			for(int x=0; x<slots[y].length; x++){
				Item i = ItemList.getItemForId(id); 
				if(slots[y][x].isFree()&&(slots[y][x].getType()==i.getType())){
					slots[y][x].addItem(id);
					render[y][x].updateAnimation(ItemType.getTypeName(i.getType())+"\\"+i.getName()+"\\");
					//System.out.println("./res/Sprites/"+ItemType.getTypeName(i.getType())+"/"+i.getName()+"/");
					return true;
				}
			}
		return false;
	}
	
	public int getContent(int x, int y){
		return slots[x][y].getItemId();
	}
	public int takeOut(int x, int y){
		render[x][y].animations = null;
		return slots[x][y].popItemId();
	}
	public int takeOut(EquipSlot slot){
		for(int y=0; y<slots.length; y++)
			for(int x=0; x<slots[y].length; x++){
				if(slots[y][x].equals(slot)){
					render[y][x].animations=null;
					return slot.popItemId();
				}
			}
		return 0;
	}
	
	public void update(World w, long delta) {
		for(EquipRender[] asd:render)
			for(EquipRender tmp:asd)
				if(tmp.exists())
					tmp.update(w, 0); //o equivalente
	
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
