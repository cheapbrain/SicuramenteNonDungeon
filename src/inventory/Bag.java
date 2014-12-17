//classe borsa che contiene tutti gli oggetti all'interno dell'inventario di una entità
package inventory;
import java.util.ArrayList;
import java.util.List;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

public class Bag {
	private int dimensione;
	private Entity Owner;
	private int content[];
	public Bag(Entity owner, int dim){
		dimensione=dim;
		content=new int[dimensione];
		Owner=owner;	
	}
	public void add(Entity pickedUpEntity, World world){
		if(pickedUpEntity==null) return;
		contents.add(pickedUpEntity.getItem());
		world.remove(pickedUpEntity);
	}
}
