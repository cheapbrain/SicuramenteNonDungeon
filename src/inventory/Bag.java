//classe borsa che contiene tutti gli oggetti all'interno dell'inventario di una entità
package inventory;

import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.world.World;

public class Bag {
	public final static int dimID=4;
	private int dimensione;
	private Entity Owner;
	public char content[][];
	public int nOggetti;
	public Bag(Entity owner, int dim){
		dimensione=dim;
		content = new char[dimensione][dimID];
		Owner=owner;	
		nOggetti=0;
	}
	public int add(Entity pickedUpEntity){
		if (nOggetti==dimensione) return 0;
		content[nOggetti]=BagManager.toItem(pickedUpEntity);
		nOggetti++;
		return 1;
	}
}