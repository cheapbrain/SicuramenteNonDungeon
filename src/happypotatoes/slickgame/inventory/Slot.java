package happypotatoes.slickgame.inventory;

import happypotatoes.slickgame.entitysystem.component.Inventory;

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
		if((idItem==0)&&(nItems<nMaxItems)){
			nItems++;
		} else return false;
		return true;
	}
	public boolean setSlotItem(int id, int max){
		if(nItems==0){
			idItem=id;
			nMaxItems = max;
		} else return false;
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
		}
		return tmp;
	}
	public int getItemId() {
		return idItem;
	}
}
