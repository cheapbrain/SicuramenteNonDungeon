package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.GameOver;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.gui.Notification;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.world.World;

public class OpenDoor extends Interact{
	boolean exit = false;

	public OpenDoor(Entity owner, float priority) {
		super(owner, priority);
	}

	@Override
	public void interact(Entity e) {
		Inventory inv = e.getComponent(Inventory.class);
		if (inv!=null) {
			if (inv.has(ItemList.key.getId())) {
				exit = true;
			} else {
				Notification.showMessage("trova la chiave stronzo", 4000);
			}
		}
	}


	@Override
	public void update(World w, long delta) {
		if (exit) {
			GameOver.winner = true;
			w.game.enterState(420);
		}
	}
}
