package happypotatoes.slickgame.inventory;

import happypotatoes.slickgame.entitysystem.component.Inventory;

public class Slot {
	public int nItems, nMaxItems;
	public int idItem;
	private boolean free;
	public Inventory owner;
	public Slot(Inventory owner){
		nItems = 0;
		nMaxItems = 0;
		idItem = 0;
		this.owner = owner;
		free=true;
	}
	public Inventory getOwner(){
		return owner;
	}
	public int HowManyStacked(){
		return nItems;
	}
	public boolean isFree(){
		return free;
	}
	public boolean addItem(int id){	
		if((idItem==0)&&(nItems<nMaxItems)){
			nItems++;
			setFree(false);
			return true;
		} 
		else return false;
		
	}
	public boolean setSlotItem(int id, int max){
		if(nItems==0){
			idItem=id;
			nMaxItems = max;
		} else return false;
		return true;
	}
	public int popItemId(){
		int tmp=idItem;
		if(nItems>0){
			nItems--;
			if(nItems==0){
				nMaxItems = 0;
				idItem=0;
			}
			setFree(true);
		}
		return tmp;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public int getItemId() {
		return idItem;
	}
}
