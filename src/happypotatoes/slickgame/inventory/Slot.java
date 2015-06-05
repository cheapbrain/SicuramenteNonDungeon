package happypotatoes.slickgame.inventory;

import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.items.ItemList;

public class Slot {
	public int nItems, nMaxItems;
	public int idItem;
	public Inventory owner;
	public Slot(Inventory owner){
		nItems = 0;
		nMaxItems = 0;
		idItem = 0;
		this.owner = owner;
	}
	public Inventory getOwner(){
		return owner;
	}
	public int HowManyStacked(){
		return nItems;
	}
	public boolean isFree(){
		if(idItem==0){
			return true;
		}
		return false;
	}
	public boolean addItem(int id){	
		if(((idItem==id)&&(nItems<nMaxItems))){
			nItems++;
		} else if (idItem==0) {
			nItems = 1;
			idItem = id;
			nMaxItems = ItemList.getItemForId(id).getMaxStack();
		} else
			return false;
		
		return true;
	}
	
	public int popItemId(){
		int tmp=0;
		if(nItems!=0){
			nItems--;
			tmp=idItem;
			if(nItems==0){
				nMaxItems = 0;
				idItem=0;
			}
		} else {

		}
		return tmp;
	}
	public int getItemId() {
		return idItem;
	}
}
