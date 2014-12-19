package inventory;

import happypotatoes.slickgame.entity.Chiave;
import happypotatoes.slickgame.entity.Entity;

public class BagManager {
	public static String toItem(Entity entity){
		String ID="";
		if(entity instanceof Chiave){
			String ID_class="01";
			String ID_Porta=((Chiave)entity).getPorta();
			ID=ID_class+ID_Porta;
		}
		return ID;
	}
}
