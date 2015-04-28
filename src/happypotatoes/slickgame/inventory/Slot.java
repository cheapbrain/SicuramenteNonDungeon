package happypotatoes.slickgame.inventory;

import happypotatoes.slickgame.entitysystem.component.Inventory;

public class Slot {
	private int nItems, nMaxItems;
	private String idItem;
	private Inventory owner;
	public Slot(Inventory owner){
		nItems = 0;
		nMaxItems = 0;
		idItem = "";
		this.owner = owner;
	}
	public Inventory getOwner(){
		return owner;
	}
	public boolean isFree(){
		if(idItem.equals("")){
			return true;
		}
		return false;
	}
	public boolean addItem(String id){	
		if((idItem.equals(id))&&(nItems<nMaxItems)){
			nItems++;
		} else return false;
		return true;
	}
	public boolean setSlotItem(String id, int max){
		if(nItems==0){
			idItem=id;
			nMaxItems = max;
		} else return false;
		return true;
	}
	public String getItem(){
		String tmp="";
		if(nItems!=0){
			nItems--;
			tmp=idItem;
			if(nItems==0){
				nMaxItems = 0;
				idItem="";
			}
		}
		return tmp;
	}
	public String getItemId() {
		return idItem;
	}
}
