//Interfaccia attore che DEVE essere implementata da tutte le entit� che interagiscono con altre entit�
package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

public interface Actor {
	//ritorna la distanza dall'entit� passata come parametro
	public float getDist(Entity entity);
	//ritorna il puntatore all'item corrispondente
	public void use(Entity user, World world);
	//
	public char getIdItem();
}