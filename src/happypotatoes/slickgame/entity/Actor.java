//Interfaccia attore che DEVE essere implementata da tutte le entità che interagiscono con altre entità
package happypotatoes.slickgame.entity;

import happypotatoes.slickgame.world.World;

public interface Actor {
	//ritorna la distanza dall'entità passata come parametro
	public float getDist(Entity entity);
	//ritorna il puntatore all'item corrispondente
	public void use(Entity user, World world);
	//
	public char getIdItem();
}