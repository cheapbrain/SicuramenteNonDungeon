package inventory;

import happypotatoes.slickgame.entity.EntityKey;
import happypotatoes.slickgame.entity.Entity;

public class BagManager {
	public static char[] toItem(Entity entity){
		char ID[] = new char[Bag.dimID];
		if(entity instanceof EntityKey){
			ID[0]=((EntityKey) entity).getIdItem();
			//ID[1]=((EntityKey)entity).getPorta();
		}
		
		return ID;
	}
}
