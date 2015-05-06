package happypotatoes.slickgame.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.gui.component.Button;
import happypotatoes.slickgame.gui.component.EnergyBar;
import happypotatoes.slickgame.gui.component.HealthBar;
import happypotatoes.slickgame.gui.component.Label;

public class GuiSystem {
	private static InventoryWindow iw = null;
	private static Window hud = null;
	public static void init(UI ui, Entity player){
		iw=inventory(ui, player);
		hud=hud(ui, player);
	}
	public static Window getHud(){
		return hud;
	}
	public static InventoryWindow getInventory(){
		return iw;
	}
	private static Window hud(UI ui, Entity player){
		Image image = null;
		try {
			image = new Image("./res/GUI.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		int xNuova = ui.width*2/3;
		Window hud = new Window("hud", 0, 0, xNuova, xNuova*image.getHeight()/image.getWidth());
		hud.setVerticalAlign(Component.SOUTH);
		hud.setHorizontalAlign(Component.CENTER);
		hud.setBackground(image);
		float k = (ui.width*2f/3)/image.getWidth();
		hud.add(new HealthBar((Health) player.getComponent(Health.class), k));
		hud.add(new EnergyBar((Energy) player.getComponent(Energy.class), k));
		ui.add(hud);	
		return hud;
	}
	private static InventoryWindow inventory(UI ui, Entity player){
		InventoryWindow iw = new InventoryWindow("inventario", 0,0, ((Inventory) player.getComponent(Inventory.class)));
		iw.setVerticalAlign(Component.CENTER);
		iw.setHorizontalAlign(Component.EAST);
		iw.setVisible(false);
		ui.add(iw);
		return iw;
	}
}
