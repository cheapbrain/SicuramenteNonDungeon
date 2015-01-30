package inventory;

import happypotatoes.slickgame.entity.Entity;

public class BagManager {
	public enum ItemsID{
		KeyItemID(1);
		private int val;
		private ItemsID(int val){
			this.val = val;
		}
		public int getVal() {
			return val;
		}
	}
	public static char[] toItem(Entity entity){
		char ID[] = new char[Bag.dimID];
//		if(entity instanceof EntityKey){
//			ID[0]=(char)ItemsID.KeyItemID.val;
//		}	
		return ID;
	}
}
